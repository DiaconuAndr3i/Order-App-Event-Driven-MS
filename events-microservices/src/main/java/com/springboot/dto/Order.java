package com.springboot.dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String name;

    private int quantity;

    private double price;

    private String nameBuyer;

    private String phoneNumber;

    private String email;

    private String idBuyer;

    private Address address;
}
