package com.inditex.ecomerce.infraestructure.repository;

import com.inditex.ecomerce.domain.Price;
import com.inditex.ecomerce.domain.service.PriceService;
import com.inditex.ecomerce.infraestructure.repository.entity.PriceEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PriceRepositoryTest {

    @Mock
    JpaPriceRepository jpaPriceRepository;

    @Mock
    PriceEntity priceEntity;

    @Mock
    Price price;

    AutoCloseable closeable;

    PriceRepository priceRepository;

    @BeforeEach
    void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
        priceRepository = new PriceRepository(jpaPriceRepository);
    }

    @AfterEach
    void closeMocks() throws Exception {
        closeable.close();
    }

    @Test
    void loadPricesWithinDate() {
        List<PriceEntity> priceEntities = new ArrayList<>();
        priceEntities.add(priceEntity);
        List<Price> priceList = new ArrayList<>();
        priceList.add(price);
        when(jpaPriceRepository.getByIdAndBrandBetweenDate(any(), any(), any())).thenReturn(Optional.of(priceEntities));
        when(priceEntity.mapToPrice()).thenReturn(price);
        assertEquals(Optional.of(priceList), priceRepository.loadPriceWithinDate(any(), any(), any()));
    }

}