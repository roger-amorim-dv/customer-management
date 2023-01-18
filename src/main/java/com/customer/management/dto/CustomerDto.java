package com.customer.management.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerDto {

    @NotNull(message = "Field name not be null!")
    private String name;

    @NotNull(message = "Field identifier not be null!")
    private String identifier;

    @NotNull(message = "Field identifierType not be null!")
    private String identifierType;

    private String message;

}
