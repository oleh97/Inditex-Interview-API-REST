package com.inditex.ecomerce.application;

import com.inditex.ecomerce.application.entity.Product;
import com.inditex.ecomerce.domain.Brand;
import com.inditex.ecomerce.domain.Currency;
import com.inditex.ecomerce.domain.Price;
import com.inditex.ecomerce.domain.port.in.ProductRateUseCase;
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
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class PriceControllerTest {

    @Mock
    ProductRateUseCase productRateUseCase;

    PriceController priceController;

    AutoCloseable closeable;


    @BeforeEach
    void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
        priceController = new PriceController(productRateUseCase);
    }

    @AfterEach
    void closeMocks() throws Exception {
        closeable.close();
    }

    @Test
    void controllerReturnsOK() {
        when(productRateUseCase.productRate(any(), any(),any())).thenReturn(ObjectTestUtils.createPrice());
        priceController.getPrice(1L, LocalDateTime.MAX, 1L);
    }

    @Test
    void controllerReturnsNotFound() {
        when(productRateUseCase.productRate(any(), any(),any())).thenThrow(NoSuchElementException.class);
        Assertions.assertThrows(ResponseStatusException.class, () -> priceController.getPrice(1L, LocalDateTime.MAX, 1L));
    }

    @Test
    void controllerReturnsServerError() {
        when(productRateUseCase.productRate(any(), any(),any())).thenThrow(NullPointerException.class);
        Assertions.assertThrows(ResponseStatusException.class, () -> priceController.getPrice(1L, LocalDateTime.MAX, 1L));
    }

}