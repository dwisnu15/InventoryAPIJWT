package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.exceptions.EntityNotFoundException;
import com.enigma.api.inventory.models.PagedList;
import com.enigma.api.inventory.models.ResponseMessage;
import com.enigma.api.inventory.models.requests.UnitRequest;
import com.enigma.api.inventory.models.UnitModel;
import com.enigma.api.inventory.models.searches.UnitSearch;
import com.enigma.api.inventory.services.UnitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/units")
@RestController
public class UnitController {
    
    @Autowired
    private UnitService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/add")
    public ResponseMessage<UnitModel> add(@RequestBody UnitRequest model) {
        Unit newUnit = modelMapper.map(model, Unit.class);
        newUnit = service.save(newUnit);

        UnitModel data = modelMapper.map(newUnit, UnitModel.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}/form")
    public ResponseMessage<UnitModel> edit(@PathVariable Integer id,
                                @RequestBody @Valid UnitModel model) {
        Unit edited = service.findById(id);
        if (edited == null) {
            throw new EntityNotFoundException();
        }
        modelMapper.map(model, edited);
        edited = service.save(edited);
        UnitModel data = modelMapper.map(edited, UnitModel.class);
        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<UnitModel> removeById(@PathVariable Integer id) {
        Unit entity = service.removeById(id);
        if (entity == null) {
            throw  new EntityNotFoundException();
        }
        UnitModel data = modelMapper.map(entity, UnitModel.class);
        return ResponseMessage.success(data);
    }


    @GetMapping("/{id}")
    public ResponseMessage<UnitModel> findById(@PathVariable Integer id) {
        Unit entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        UnitModel data = modelMapper.map(entity, UnitModel.class);
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<UnitModel>> findAll(
            @Valid UnitSearch model
            ) {
        Unit search = modelMapper.map(model, Unit.class);
        Page<Unit> entitypage = service.findAll(search, model.getPage(), model.getSize(), model.getSort());

        List<UnitModel> models = entitypage.stream().map(
                e -> modelMapper.map(e, UnitModel.class))
                .collect(Collectors.toList());

        PagedList<UnitModel> data = new PagedList<>(
                models,
                entitypage.getNumber(),
                entitypage.getSize(),
                entitypage.getTotalElements());

        return ResponseMessage.success(data);
    }
}
