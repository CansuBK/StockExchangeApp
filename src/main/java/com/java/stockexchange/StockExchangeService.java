package com.java.stockexchange;

import com.java.exception.StockExchangeNotFoundException;
import com.java.stock.Stock;
import com.java.stock.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StockExchangeService {

    private final StockExchangeRepository stockExchangeRepository;

    private final StockRepository stockRepository;

    private final static Integer MIN_STOCK_SIZE = 5;

    public StockExchange getStockExchangeByName(String name) {

       final Optional<StockExchange> stockExchange = stockExchangeRepository.findByName(name);

       if(!stockExchange.isPresent()) {
           throw new StockExchangeNotFoundException(
                   "Stock Exchange with name " + name + " does not found");
       }

        return stockExchange.get();
    }

        public void addStockToStockExchange(String stockExchangeName, Stock stock) {
            final Optional<StockExchange> stockExchangeOptional = stockExchangeRepository.findByName(stockExchangeName);
            if (!stockExchangeOptional.isPresent()) {
                throw new StockExchangeNotFoundException(
                        "Stock Exchange with name " + stockExchangeName + " does not found");
            }

            final Optional<Stock> stockOptional = stockRepository.findById(stock.getId());
            if (stockOptional.isPresent()) {
                stock = stockOptional.get();
            }

            stockExchangeOptional.get().getStocks().add(stock);
            final Boolean stockStatus = checkStockSizeInExchange(stockExchangeOptional.get());
            stockExchangeOptional.get().setLiveInMarket(stockStatus);
            stockExchangeRepository.save(stockExchangeOptional.get());
        }

    public void deleteStockFromStockExchange(String stockExchangeName, Long stockId) {
        final Optional<StockExchange> stockExchangeOptional = stockExchangeRepository.findByName(stockExchangeName);

        if(!stockExchangeOptional.isPresent()) {
            throw new StockExchangeNotFoundException(
                    "Stock Exchange with name " + stockExchangeName + " does not found");
        }

        stockExchangeOptional.get().getStocks().removeIf(stock -> stock.getId().equals(stockId));
        final Boolean stockStatus = checkStockSizeInExchange(stockExchangeOptional.get());
        stockExchangeOptional.get().setLiveInMarket(stockStatus);
        stockExchangeRepository.save(stockExchangeOptional.get());
    }

    private Boolean checkStockSizeInExchange(StockExchange stockExchange) {
        return stockExchange.getStocks().size() >= MIN_STOCK_SIZE;
    }

}
