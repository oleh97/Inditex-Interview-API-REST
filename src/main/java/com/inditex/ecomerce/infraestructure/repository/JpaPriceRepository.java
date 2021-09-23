package com.inditex.ecomerce.infraestructure.repository;

import com.inditex.ecomerce.infraestructure.repository.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query(value =
            "from PriceEntity p " +
                    "where p.productId = :productId AND p.brand.brand = :brandId " +
                    "AND :applicationDate " +
                    "BETWEEN p.startDate AND p.endDate")
    Optional<List<PriceEntity>> getByIdAndBrandBetweenDate(
            @Param("productId")Long productId,
            @Param("brandId") Long brandId,
            @Param("applicationDate") LocalDateTime applicationDate);

}
