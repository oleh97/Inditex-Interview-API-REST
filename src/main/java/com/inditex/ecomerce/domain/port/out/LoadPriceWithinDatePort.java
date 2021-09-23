package com.inditex.ecomerce.domain.port.out;

import com.inditex.ecomerce.domain.Price;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LoadPriceWithinDatePort {

    Optional<List<Price>> loadPriceWithinDate(Long productId, Long brandId, LocalDateTime applicationDate);

}
