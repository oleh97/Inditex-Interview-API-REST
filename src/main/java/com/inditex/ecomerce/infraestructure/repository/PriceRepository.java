package com.inditex.ecomerce.infraestructure.repository;

import com.inditex.ecomerce.domain.Price;
import com.inditex.ecomerce.domain.port.in.ProductRateUseCase;
import com.inditex.ecomerce.domain.port.out.LoadPriceWithinDatePort;
import com.inditex.ecomerce.infraestructure.repository.entity.PriceEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PriceRepository implements LoadPriceWithinDatePort {

    private static final Logger logger = LoggerFactory.getLogger(PriceRepository.class);

    private final JpaPriceRepository jpaPriceRepository;

    public PriceRepository(JpaPriceRepository jpaPriceRepository) {
        this.jpaPriceRepository = jpaPriceRepository;
    }

    @Override
    public Optional<List<Price>> loadPriceWithinDate(Long productId, Long brandId, LocalDateTime applicationDate) {
        logger.info("Getting all Prices for product {} within application date {}", productId, applicationDate);
        Optional<List<PriceEntity>> priceEntities = jpaPriceRepository
                .getByIdAndBrandBetweenDate(productId, brandId, applicationDate);
        return priceEntities.map(entities -> entities.stream()
                .map(PriceEntity::mapToPrice)
                .collect(Collectors.toList()));
    }
}
