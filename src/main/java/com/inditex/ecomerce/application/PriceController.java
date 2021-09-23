package com.inditex.ecomerce.application;

import com.inditex.ecomerce.application.entity.Product;
import com.inditex.ecomerce.domain.Price;
import com.inditex.ecomerce.domain.port.in.ProductRateUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private static final Logger logger = LoggerFactory.getLogger(PriceController.class);

    private final ProductRateUseCase productRateUseCase;

    public PriceController(ProductRateUseCase productRateUseCase) {
        this.productRateUseCase = productRateUseCase;
    }

    @GetMapping("/{id}")
    Product getPrice(@PathVariable Long id,
                   @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
                  @RequestParam LocalDateTime date,
                   @RequestParam Long brandId) {
        try {
            logger.info("/prices/{} for brand {} within date {}", id, brandId, date);
            Price price = this.productRateUseCase.productRate(id, brandId, date);
            return new Product(price);
        } catch (NoSuchElementException e) {
            logger.error("No Prices found matching given product id {}, brand id {} and date {}", id, brandId, date);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No prices found for given parameters");
        } catch (Exception e) {
            logger.error("Unexpected error happened!");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
