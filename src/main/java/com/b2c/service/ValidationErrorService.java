package com.b2c.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;

@Service
public class ValidationErrorService {

    public ResponseEntity<?> validate(BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            HashMap<String, String> errorsMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorsMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<HashMap<String, String>>(errorsMap, HttpStatus.BAD_REQUEST);
        }
    // If error doesn't happen then return null:
        return null;
    }

}
