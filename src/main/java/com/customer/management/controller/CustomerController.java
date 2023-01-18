package com.customer.management.controller;

import com.customer.management.dto.CustomerDto;
import com.customer.management.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerDto> createCustomer(final @RequestBody @Valid CustomerDto customerDto) {
        try {
            return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
        } catch (final Throwable ex) {
            return new ResponseEntity<>(CustomerDto.builder().build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
