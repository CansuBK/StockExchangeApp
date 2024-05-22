package com.java.stockexchange;

import com.java.stock.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StockExchangeRepository extends CrudRepository<StockExchange,Long> {

    Optional<StockExchange> findByName(String name);

}
