package com.inditex.ecomerce.domain.service;

import com.inditex.ecomerce.application.PriceController;
import com.inditex.ecomerce.domain.Price;
import com.inditex.ecomerce.domain.port.out.LoadPriceWithinDatePort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;
import utils.ObjectTestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class PriceServiceTest {

    @Mock
    LoadPriceWithinDatePort loadPriceWithinDatePort;

    AutoCloseable closeable;

    PriceService priceService;

    @BeforeEach
    void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
        priceService = new PriceService(loadPriceWithinDatePort);
    }

    @AfterEach
    void closeMocks() throws Exception {
        closeable.close();
    }

    @Test
    void productRateWithPriority() {
        List<Price> prices = ObjectTestUtils.validPricesBetweenSameTime();
        Price expected = prices.get(0);
        when(loadPriceWithinDatePort.loadPriceWithinDate(any(), any(), any())).thenReturn(Optional.of(prices));
        assertEquals(expected, priceService.productRate(1L, 1L, LocalDateTime.MAX));
    }

    @Test
    void productRateReturnsEmpty() {
        when(loadPriceWithinDatePort.loadPriceWithinDate(any(), any(), any())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class, () -> priceService.productRate(1L, 1L, LocalDateTime.MAX));
    }

}