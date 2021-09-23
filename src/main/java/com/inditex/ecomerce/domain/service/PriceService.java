package com.inditex.ecomerce.domain.service;

import com.inditex.ecomerce.application.PriceController;
import com.inditex.ecomerce.domain.Price;
import com.inditex.ecomerce.domain.port.in.ProductRateUseCase;
import com.inditex.ecomerce.domain.port.out.LoadPriceWithinDatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PriceService implements ProductRateUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ProductRateUseCase.class);

    private final LoadPriceWithinDatePort loadPriceWithinDatePort;

    public PriceService(LoadPriceWithinDatePort loadPriceWithinDatePort) {
        this.loadPriceWithinDatePort = loadPriceWithinDatePort;
    }

    @Override
    public Price productRate(Long productId, Long brandId, LocalDateTime applicationDate) {
        logger.info("Process product {} rate", productId);

        List<Price> validPrices = loadPriceWithinDatePort.loadPriceWithinDate(productId, brandId, applicationDate)
                .orElseThrow(NoSuchElementException::new);
        Price priorityPrice = validPrices
                .stream()
                .max(Comparator.comparing(Price::getPriority))
                .orElseThrow(NoSuchElementException::new);
        return priorityPrice;
    }


}
