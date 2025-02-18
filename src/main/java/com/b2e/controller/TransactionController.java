package com.b2e.controller;

import com.b2e.entity.Transaction;
import com.b2e.service.TransactionService;
import com.b2e.service.ValidationErrorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {
    private final TransactionService transactionService;
    private final ValidationErrorService validationErrorService;

    public TransactionController(TransactionService transactionService, ValidationErrorService validationErrorService) {
        this.transactionService = transactionService;
        this.validationErrorService = validationErrorService;
    }

    @GetMapping("/{wallet_id}")
    public ResponseEntity<?> getAll(@PathVariable Long wallet_id ){
        return new ResponseEntity<>(transactionService.getAll(wallet_id), HttpStatus.OK);
    }

    @GetMapping("/{wallet_id}/{transaction_id}")
    public ResponseEntity<?> getById(@PathVariable Long wallet_id, @PathVariable Long transaction_id ){
        return new ResponseEntity<>(transactionService.getById(wallet_id, transaction_id), HttpStatus.OK);
    }

    @PostMapping("/{wallet_id}")
    public ResponseEntity<?> create(@PathVariable Long wallet_id, @Valid @RequestBody Transaction transaction, BindingResult result){
        // If an error comes then we call custom Class- ValidateErrorService:
        ResponseEntity<?> errors = validationErrorService.validate(result);
        if(errors != null)  return  errors;
        // If there's not any errors: Then store in WalletService-Method:
        Transaction  transactionSaved  = transactionService.createOrUpdate(wallet_id,transaction);
        return new ResponseEntity<>(transactionSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{wallet_id}/{transaction_id}")
    public ResponseEntity<?> update(@PathVariable Long wallet_id, @PathVariable Long transaction_id, @Valid @RequestBody Transaction transaction, BindingResult result){
        // If an error comes then we call custom Class- ValidateErrorService:
        ResponseEntity<?> errors = validationErrorService.validate(result);
        if(errors != null)  return  errors;
        transaction.setId(transaction_id);
        // If there's not any errors: Then store in WalletService-Method:
        Transaction transactionSaved  = transactionService.createOrUpdate(wallet_id,transaction);
        return new ResponseEntity<>(transactionSaved, HttpStatus.ACCEPTED);
    }

    // Method for Delete Req from client:
    // @DeleteMapping("/{wallet_id}/{transaction_id}")
    public  ResponseEntity<?> deleteUser(@PathVariable Long wallet_id,@PathVariable Long transaction_id){
        transactionService.delete(wallet_id, transaction_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
