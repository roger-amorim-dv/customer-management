package com.customer.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    @NotBlank(message = "Field not be blank!")
    private String name;

    @Column
    @NotBlank(message = "Field not be blank!")
    private String identifier;

    @Column
    @NotBlank(message = "Field not be blank!")
    private String identifierType;
}
