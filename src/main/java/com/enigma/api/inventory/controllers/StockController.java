package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.entities.StockSummary;
import com.enigma.api.inventory.exceptions.EntityNotFoundException;
import com.enigma.api.inventory.models.PagedList;
import com.enigma.api.inventory.models.ResponseMessage;
import com.enigma.api.inventory.models.elements.StockElement;
import com.enigma.api.inventory.models.requests.StockPatchRequest;
import com.enigma.api.inventory.models.requests.StockRequest;
import com.enigma.api.inventory.models.searches.StockSearch;
import com.enigma.api.inventory.models.responses.StockResponse;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.impl.StockServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/stocks")
@RestController
public class StockController {

    @Autowired
    private StockServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public ResponseMessage<StockResponse> findById(@PathVariable Integer id) {
        Stock entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        StockResponse data = modelMapper.map(entity, StockResponse.class);
        return ResponseMessage.success(data);
    }

    @PostMapping("/add")
    public ResponseMessage<StockResponse> add(@RequestBody StockRequest request) {
        Stock entity = modelMapper.map(request, Stock.class);

        Item item = itemService.findById(request.getItemId());
        entity.setItem(item);

        entity = service.save(entity);

        StockResponse data = modelMapper.map(entity, StockResponse.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}/form")
    public ResponseMessage<StockResponse> editPartial(@PathVariable Integer id, @RequestBody @Valid StockPatchRequest request) {
        Stock edited = service.findById(id);
        if (edited == null) {
            throw new EntityNotFoundException();
        }
        request = getRequest(request, edited);
        modelMapper.map(request, edited);

        edited = service.save(edited);

        StockResponse data = modelMapper.map(edited, StockResponse.class);
        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<StockResponse> removeById(@PathVariable Integer id) {
        Stock entity = service.removeById(id);
        if (entity == null) {
            throw  new EntityNotFoundException();
        }
        StockResponse data = modelMapper.map(entity, StockResponse.class);
        return ResponseMessage.success(data);
    }

    @GetMapping("/summaries")
    public ResponseMessage<List<StockSummary>> findAllSummaries(
    ) {
        List<StockSummary> summaries = service.findAllSummaries();
        return ResponseMessage.success(summaries);
    }

    @GetMapping
    public ResponseMessage<PagedList<StockElement>> findAll(
            @Valid StockSearch model
            ) {
        Stock search = modelMapper.map(model, Stock.class);
        Page<Stock> entitypage = service.findAll(search, model.getPage(), model.getSize(), model.getSort());
        List<Stock> entities = entitypage.toList();

        List<StockElement> models = entities.stream().map(
                e -> modelMapper.map(e, StockElement.class))
                .collect(Collectors.toList());

        PagedList<StockElement> data = new PagedList<>(
                models,
                entitypage.getNumber(),
                entitypage.getSize(),
                entitypage.getTotalElements());

        return ResponseMessage.success(data);
    }
    public StockPatchRequest getRequest(StockPatchRequest request, Stock entity) {
        if (request.getQuantity() == null) request.setQuantity(entity.getQuantity());
        if (request.getItemId() == null) request.setItemId(entity.getItem().getId());
        entity.setItem(itemService.findById(request.getItemId()));
        return request;
    }

}
