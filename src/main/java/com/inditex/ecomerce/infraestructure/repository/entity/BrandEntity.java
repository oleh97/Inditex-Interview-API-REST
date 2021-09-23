package com.inditex.ecomerce.infraestructure.repository.entity;

import com.inditex.ecomerce.domain.Brand;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BRANDS")
public class BrandEntity {

    @Id
    @Column(name = "ID")
    private Long brand;

    public Brand mapToBrand() {
        Brand brand = new Brand();
        brand.setBrandId(this.brand);
        return brand;
    }

}
