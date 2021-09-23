package com.inditex.ecomerce.domain.port.in;

import com.inditex.ecomerce.domain.Price;

import java.time.LocalDateTime;

public interface ProductRateUseCase {

    Price productRate(Long productId, Long brandId, LocalDateTime applicationDate);

}
