package com.customer.management.service;

import com.customer.management.dto.CustomerDto;
import com.customer.management.mapper.CustomerMapper;
import com.customer.management.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDto createCustomer(final CustomerDto customerDto) {
        log.info("[CREATE-CUSTOMER] Starting create customer [{}]", customerDto.getName());
        final var customerResponse = validateCustomerDocument(customerDto);

        customerRepository.saveAndFlush(new CustomerMapper().adapt(customerDto));
        log.info("[CREATE-CUSTOMER] New customer [{}] created successfully!", customerDto.getName());
        return customerResponse;
    }


    private CustomerDto validateCustomerDocument(final CustomerDto customerDto) {
        log.info("[VALIDATE-CUSTOMER-DOCUMENT] Starting validation customer [{}] document", customerDto.getName());

        if(Objects.isNull(customerDto.getIdentifierType()) || customerDto.getIdentifierType().isBlank()) {
            log.info("[VALIDATE-CUSTOMER-DOCUMENT] Identifier is null/blank for customer [{}] aborting process.", customerDto.getName());
            return CustomerDto.builder().build();
        }

        if (isCnpj(customerDto.getIdentifierType(), customerDto.getIdentifier())) {
            log.info("[VALIDATE-CUSTOMER-DOCUMENT] New customer [{}] has a CNPJ document", customerDto.getName());
            return CustomerDto.builder()
                    .name(customerDto.getName())
                    .identifierType("CNPJ")
                    .message("Customer created with a CNPJ document")
                    .build();

        } else if (isCpf(customerDto.getIdentifierType(), customerDto.getIdentifier())) {
            log.info("[VALIDATE-CUSTOMER-DOCUMENT] New customer [{}] has a CPF document", customerDto.getName());
            return CustomerDto.builder()
                    .name(customerDto.getName())
                    .identifierType("CPF")
                    .message("Customer created with a CPF document")
                    .build();

        } else if (isAnotherTypeOfDocument(customerDto.getIdentifierType(), customerDto.getIdentifier())) {
            log.info("[VALIDATE-CUSTOMER-DOCUMENT] New customer [{}] has different document", customerDto.getName());
            return CustomerDto.builder()
                    .name(customerDto.getName())
                    .identifierType(customerDto.getIdentifierType())
                    .message("Customer created with a another document type")
                    .build();
        }

        return CustomerDto.builder()
                .name(customerDto.getName())
                .identifierType(customerDto.getIdentifierType())
                .message("Error to create customer")
                .build();
    }

    private boolean isCnpj(final String identifierType, final String identifier) {
        return identifierType.equalsIgnoreCase("CNPJ") && String.valueOf(identifier).length() == 14;
    }

    private boolean isCpf(final String identifierType, final String identifier) {
        return identifierType.equalsIgnoreCase("CPF") && String.valueOf(identifier).length() == 11;
    }

    private boolean isAnotherTypeOfDocument(final String identifierType, final String identifier) {
        return !isCnpj(identifierType, identifier) && !isCnpj(identifierType, identifier);
    }
}
