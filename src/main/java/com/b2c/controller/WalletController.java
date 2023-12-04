package com.b2c.controller;

import com.b2c.entity.Wallet;
import com.b2c.service.ValidationErrorService;
import com.b2c.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private ValidationErrorService validationErrorService;
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Wallet wallet, BindingResult result){
     // If an error comes then we call custom Class- ValidateErrorService:
        ResponseEntity errors = validationErrorService.validate(result);
        if(errors != null)  return  errors;
    // If there's not any erros: Then store in WalletService-Method:
        Wallet walletSaved  = walletService.createOrUpdate(wallet);
        return  new ResponseEntity<Wallet>(walletSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Wallet wallet, BindingResult result){
        // If an error comes then we call custom Class- ValidateErrorService:
        ResponseEntity errors = validationErrorService.validate(result);
        if(errors != null)  return  errors;
        // If there's not any erros: Then store in WalletService-Method:
        Wallet walletSaved  = walletService.createOrUpdate(wallet);
        return  new ResponseEntity<Wallet>(walletSaved, HttpStatus.CREATED);
    }

// Method for Delete Req:
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteUser(@PathVariable Long id){
        walletService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
