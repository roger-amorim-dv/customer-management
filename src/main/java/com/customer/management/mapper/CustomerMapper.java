package com.customer.management.mapper;

import com.customer.management.domain.Customer;
import com.customer.management.dto.CustomerDto;

public class CustomerMapper {

    public Customer adapt(final CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setIdentifier(customerDto.getIdentifier());
        customer.setIdentifierType(customerDto.getIdentifierType());
        return customer;
    }
}
