package com.java.stock;

import org.mapstruct.factory.Mappers;

import java.util.List;

public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    List<StockOutput> convertToStockExchangeOutput(List<Stock> stock);
}
