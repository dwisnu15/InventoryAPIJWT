package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Transaction;
import com.enigma.api.inventory.exceptions.EntityNotFoundException;
import com.enigma.api.inventory.models.PagedList;
import com.enigma.api.inventory.models.ResponseMessage;
import com.enigma.api.inventory.models.elements.TransactionElement;
import com.enigma.api.inventory.models.responses.TransactionResponse;
import com.enigma.api.inventory.models.requests.TransactionRequest;
import com.enigma.api.inventory.models.searches.TransactionSearch;
import com.enigma.api.inventory.services.impl.CustomerServiceImpl;
import com.enigma.api.inventory.services.impl.ItemServiceImpl;
import com.enigma.api.inventory.services.impl.StockServiceImpl;
import com.enigma.api.inventory.services.impl.TransactionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/transactions")
@RestController
public class TransactionController {

   @Autowired
   private TransactionServiceImpl service;

   @Autowired
   private ItemServiceImpl itemService;

   @Autowired
   private StockServiceImpl stockService;

   @Autowired
   private CustomerServiceImpl customerService;


   @Autowired
   private ModelMapper modelMapper;

   @Operation(summary = "Add Item", description = "adding new Transaction to dbase")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Success"),
           @ApiResponse(responseCode = "500", description = "Internal server error",
                   content = @Content(array = @ArraySchema
                           (schema = @Schema(implementation = ResponseMessage.class))))
   })
   @PostMapping
   public ResponseMessage<TransactionResponse> add(@RequestBody TransactionRequest model) {
       //should add checker whether the requested item's quantities can be satisfied by the stock
       Transaction newTransaction = modelMapper.map(model, Transaction.class);
       newTransaction = service.save(newTransaction);

       TransactionResponse data = modelMapper.map(newTransaction, TransactionResponse.class);
       return ResponseMessage.success(data);
   }

   @PutMapping("/{id}")
   public ResponseMessage<TransactionResponse> edit(@PathVariable Integer id,
                                          @RequestBody @Valid TransactionRequest model) {
       Transaction edited = service.findById(id);
       if (edited == null) {
           throw new EntityNotFoundException();
       }
       modelMapper.map(model, edited);
       edited = service.save(edited);
       TransactionResponse data = modelMapper.map(edited, TransactionResponse.class);
       return ResponseMessage.success(data);
   }

   @DeleteMapping("/{id}")
   public ResponseMessage<TransactionResponse> removeById(@PathVariable Integer id) {
       Transaction entity = service.removeById(id);
       if (entity == null) {
           throw  new EntityNotFoundException();
       }
       TransactionResponse data = modelMapper.map(entity, TransactionResponse.class);
       return ResponseMessage.success(data);
   }


   @GetMapping("/{id}")
   public ResponseMessage<TransactionResponse> findById(@PathVariable Integer id) {
       Transaction entity = service.findById(id);
       if (entity == null) {
           throw new EntityNotFoundException();
       }
       TransactionResponse data = modelMapper.map(entity, TransactionResponse.class);
       return ResponseMessage.success(data);
   }

//    @GetMapping
//    public ResponseMessage<List<Transaction>> findAll() {
//        List<Transaction> entities = service.findAll();
//        return ResponseMessage.success(entities);
//    }

   @GetMapping
   public ResponseMessage<PagedList<TransactionElement>> findAll(
           @Valid TransactionSearch model
   ) {
       Transaction search = modelMapper.map(model, Transaction.class);
       Page<Transaction> entitypage = service.findAll(search, model.getPage(), model.getSize(), model.getSort());

       List<TransactionRequest> models = entitypage.stream().map(
               e -> modelMapper.map(e, TransactionRequest.class))
               .collect(Collectors.toList());

       PagedList<TransactionElement> data = new PagedList(
               models,
               entitypage.getNumber(),
               entitypage.getSize(),
               entitypage.getTotalElements());

       return ResponseMessage.success(data);
   }
}
