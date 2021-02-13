package com.enigma.api.inventory.controllers;


import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.exceptions.EntityNotFoundException;
import com.enigma.api.inventory.models.PagedList;
import com.enigma.api.inventory.models.ResponseMessage;
import com.enigma.api.inventory.models.elements.ItemElement;
import com.enigma.api.inventory.models.requests.ImageUploadRequest;
import com.enigma.api.inventory.models.requests.ItemPatchRequest;
import com.enigma.api.inventory.models.requests.ItemRequest;
import com.enigma.api.inventory.models.searches.ItemSearch;
import com.enigma.api.inventory.models.responses.ItemResponse;
import com.enigma.api.inventory.services.UnitService;
import com.enigma.api.inventory.services.impl.FileServiceImpl;
import com.enigma.api.inventory.services.impl.ItemServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.tika.mime.MimeTypeException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/items")
@RestController
public class ItemController {

    @Autowired
    private ItemServiceImpl service;

    @Autowired
    private UnitService unitService;

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Add Item", description = "adding new Item entity to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                content = @Content(array = @ArraySchema
                        (schema = @Schema(implementation = ResponseMessage.class))))
    })
    @PostMapping("/add")
    public ResponseMessage<ItemResponse> add(@RequestBody ItemRequest request) {
        Item newItem = modelMapper.map(request, Item.class);

        Unit unit = unitService.findById(request.getUnitId());
        newItem.setUnit(unit);

        newItem = service.save(newItem);

        ItemResponse data = modelMapper.map(newItem, ItemResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<ItemResponse> findById(@PathVariable Integer id) {
        Item entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        ItemResponse data = modelMapper.map(entity, ItemResponse.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}/form")
    public ResponseMessage<ItemResponse> editPartial(@PathVariable Integer id, @RequestBody @Valid ItemPatchRequest request) {
        Item edited = service.findById(id);
        if (edited == null) {
            throw new EntityNotFoundException();
        }
        request = getRequest(request, edited);
        modelMapper.map(request, edited);

        edited = service.save(edited);
        ItemResponse data = modelMapper.map(edited, ItemResponse.class);
        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<ItemResponse> removeById(@PathVariable Integer id) {
        Item entity = service.removeById(id);
        if (entity == null) {
            throw  new EntityNotFoundException();
        }
        ItemResponse data = modelMapper.map(entity, ItemResponse.class);
        return ResponseMessage.success(data);
    }


//    @GetMapping
//    public ResponseMessage<List<Item>> findAll() {
//        List<Item> entities = service.findAll();
//        return ResponseMessage.success(entities);
//    }

    @GetMapping
    public ResponseMessage<PagedList<ItemElement>> findAll(
            @Valid ItemSearch model
    ) {
        Item search = modelMapper.map(model, Item.class);
        Page<Item> entitypage = service.findAll(search, model.getPage(), model.getSize(), model.getSort());

        List<ItemElement> models = entitypage.stream().map(
                e -> modelMapper.map(e, ItemElement.class))
                .collect(Collectors.toList());

        PagedList<ItemElement> data = new PagedList<>(
                models,
                entitypage.getNumber(),
                entitypage.getSize(),
                entitypage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @PostMapping(value = "/{id}/images", consumes = "multipart/form-data")
    public ResponseMessage<ItemResponse> upload(@PathVariable Integer id,
                                  @Valid ImageUploadRequest file) throws IOException, MimeTypeException {
        Item entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        BufferedInputStream inputStream = new BufferedInputStream(file.getFile().getInputStream());
        String filename = fileService.upload("item", inputStream);
        entity.setImageUrl(filename);
        entity.setOriginalFileName(file.getFile().getOriginalFilename());
        service.save(entity);
        ItemResponse response = modelMapper.map(entity, ItemResponse.class);
        return ResponseMessage.success(response);
    }

    @GetMapping("/images/{filename}")
    public void download(@PathVariable String filename,
                         HttpServletResponse response) throws IOException {
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" +filename+"\"");
        fileService.download("item", filename, response.getOutputStream());
    }

    public ItemPatchRequest getRequest(ItemPatchRequest request, Item item) {
        if (request.getName() == null) request.setName(item.getName());
        if (request.getPrice() == null) request.setPrice(item.getPrice());
        if (request.getUnitId() == null) request.setUnitId(item.getUnit().getId());
        item.setUnit(unitService.findById(request.getUnitId()));
        return request;
    }

}
