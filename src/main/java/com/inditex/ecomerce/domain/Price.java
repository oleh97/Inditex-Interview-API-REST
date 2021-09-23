package com.inditex.ecomerce.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Price {

    private Brand brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long priceRateId;

    private Long productId;

    private Integer priority;

    private Double price;

    private Currency currency;

}
