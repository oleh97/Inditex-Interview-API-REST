package utils;

import com.inditex.ecomerce.domain.Brand;
import com.inditex.ecomerce.domain.Currency;
import com.inditex.ecomerce.domain.Price;
import com.inditex.ecomerce.infraestructure.repository.entity.PriceEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ObjectTestUtils {

    public static Price createPrice() {
        Price p = new Price();
        p.setCurrency(Currency.EUR);
        p.setPrice(1.0);
        p.setPriority(1);
        p.setPriceRateId(1L);
        p.setEndDate(LocalDateTime.MAX);
        p.setStartDate(LocalDateTime.MIN);
        p.setBrandId(new Brand());
        return p;
    }

    public static List<Price> validPricesBetweenSameTime() {
        List<Price> prices = new ArrayList<>();
        Price p1 = createPrice();
        Price p2 = createPrice();
        p2.setPriority(0);
        prices.add(p1);
        prices.add(p2);
        return prices;
    }

}
