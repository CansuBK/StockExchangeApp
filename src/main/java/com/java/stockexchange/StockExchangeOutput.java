package com.java.stockexchange;

import lombok.Data;

@Data
public class StockExchangeOutput {

    private String name;

    private String description;

    private Boolean liveInMarket;
}
