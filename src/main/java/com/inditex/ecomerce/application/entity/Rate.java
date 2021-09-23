package com.inditex.ecomerce.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

    private Double amount;

    private String currency;

    @Override
    public String toString() {
        return  amount + currency;
    }
}
