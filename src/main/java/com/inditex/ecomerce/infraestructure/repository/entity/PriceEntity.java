package com.inditex.ecomerce.infraestructure.repository.entity;

import com.inditex.ecomerce.domain.Currency;
import com.inditex.ecomerce.domain.Price;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    private BrandEntity brand;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "PRICE_LIST")
    private Long priceRateId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "CURR")
    private Currency currency;

    public Price mapToPrice() {
        Price price = new Price();
        price.setBrandId(this.brand.mapToBrand());
        price.setStartDate(this.startDate);
        price.setEndDate(this.endDate);
        price.setPriceRateId(this.priceRateId);
        price.setProductId(this.productId);
        price.setPriority(this.priority);
        price.setPrice(this.price);
        price.setCurrency(this.currency);
        return price;
    }

}
