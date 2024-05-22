package com.java.stockexchange;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StockExchangeRepository extends CrudRepository<StockExchange,Long> {

    Optional<StockExchange> findByName(String name);

}
