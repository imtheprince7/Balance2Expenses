package com.b2e.controller;

import com.b2e.entity.Wallet;
import com.b2e.service.ValidationErrorService;
import com.b2e.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@CrossOrigin
public class WalletController {

    private final WalletService walletService;
    private final ValidationErrorService validationErrorService;
    public WalletController(WalletService walletService, ValidationErrorService validationErrorService){
        this.walletService = walletService;
        this.validationErrorService = validationErrorService;
    }

    @GetMapping
    public  ResponseEntity<?> getAll(){
        return new ResponseEntity<>(walletService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Valid @PathVariable Long id){
        return  new ResponseEntity<>(walletService.getById(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Wallet wallet, BindingResult result){
     // If an error comes then we call custom Class- ValidateErrorService:
        ResponseEntity<?> errors = validationErrorService.validate(result);
        if(errors != null)  return  errors;
    // If there's not any errors: Then store in WalletService-Method:
        Wallet walletSaved  = walletService.createOrUpdate(wallet);
        return new ResponseEntity<>(walletSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Wallet wallet, BindingResult result){
        // If an error comes then we call custom Class- ValidateErrorService:
        ResponseEntity<?>errors = validationErrorService.validate(result);
        if(errors != null)  return  errors;
        wallet.setId(id);
        // If there's not any errors: Then store in WalletService-Method:
        Wallet walletSaved  = walletService.createOrUpdate(wallet);
        return  new ResponseEntity<>(walletSaved, HttpStatus.ACCEPTED);
    }

// Method for Delete Req:
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteUser(@PathVariable Long id){
        walletService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
