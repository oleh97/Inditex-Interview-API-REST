package com.inditex.ecomerce.application.entity;

import com.inditex.ecomerce.domain.Price;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public final class Product {

    private final Long productId;

    private final Long brandId;

    private final Rate rate;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    public Product(Price price) {
        this.productId = price.getProductId();
        this.brandId = price.getBrandId().getBrandId();
        this.rate = new Rate(price.getPrice(), price.getCurrency().name());
        this.startDate = price.getStartDate();
        this.endDate = price.getEndDate();
    }

}
