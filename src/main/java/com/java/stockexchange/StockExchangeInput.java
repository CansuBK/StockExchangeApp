package com.java.stockexchange;

public record StockExchangeInput(
        Long id,
        String name,
        String description,
        Boolean liveInMarket) {
}
