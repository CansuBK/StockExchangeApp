package com.java.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock,Long> {

    Optional<Stock> findByName(String name);

    Optional<Stock> findById(Long id);

    @Query("update Stock s set s.price = :price where s.id = :id")
    void updatePrice(@Param(value = "id") Long id, @Param(value = "price") BigDecimal price);

    void deleteById(Long id);

}
