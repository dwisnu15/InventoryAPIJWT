package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Customer;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.exceptions.EntityNotFoundException;
import com.enigma.api.inventory.models.ResponseMessage;
import com.enigma.api.inventory.models.PagedList;
import com.enigma.api.inventory.models.CustomerModel;
import com.enigma.api.inventory.models.UnitModel;
import com.enigma.api.inventory.models.requests.CustomerRequest;
import com.enigma.api.inventory.models.searches.CustomerSearch;
import com.enigma.api.inventory.models.searches.UnitSearch;
import com.enigma.api.inventory.services.CustomerService;
import com.enigma.api.inventory.services.impl.CustomerServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/customers")
@RestController
public class CustomerController {
    
    @Autowired
    private CustomerServiceImpl service;

    @Autowired
    private ModelMapper mapper;


    @PostMapping("/add")
    public ResponseMessage<CustomerModel> add(@Valid @RequestBody CustomerRequest model) {

        Customer entity = mapper.map(model, Customer.class);
        entity = service.save(entity);

        CustomerModel data = mapper.map(entity, CustomerModel.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}/form")
    public ResponseMessage<CustomerModel> edit(@PathVariable Integer id, @RequestBody CustomerRequest model) {

        Customer entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        mapper.map(model, entity);
        entity = service.save(entity);

        CustomerModel data = mapper.map(entity, CustomerModel.class);
        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<CustomerModel> removeById(@PathVariable Integer id) {
        Customer entity = service.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        CustomerModel data = mapper.map(entity, CustomerModel.class);
        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<CustomerModel> findById(@PathVariable Integer id) {
        Customer entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }

        CustomerModel data = mapper.map(entity, CustomerModel.class);
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<CustomerModel>> findAll(
            @Valid CustomerSearch model
    ) {
        Customer search = mapper.map(model, Customer.class);

        Page<Customer> entityPage = service.findAll(search,
                model.getPage(), model.getSize(), model.getSort());

        List<Customer> entities = entityPage.toList();

        List<CustomerModel> models = entities.stream()
                .map(e -> mapper.map(e, CustomerModel.class))
                .collect(Collectors.toList());

        PagedList<CustomerModel> data = new PagedList(models,
                entityPage.getNumber(), entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

}
