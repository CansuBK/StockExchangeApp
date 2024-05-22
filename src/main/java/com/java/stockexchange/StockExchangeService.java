package com.java.stockexchange;

import com.java.exception.StockExchangeNotFoundException;
import com.java.exception.StockNotFoundException;
import com.java.stock.Stock;
import com.java.stock.StockMapper;
import com.java.stock.StockOutput;
import com.java.stock.StockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StockExchangeService {

    private final StockExchangeRepository stockExchangeRepository;

    private final StockService stockService;

    private final static Integer MIN_STOCK_SIZE = 5;

    public List<StockOutput> getStocksByStockExchangeName(String stockExchangeName) {

        final Optional<StockExchange> stockExchangeOptional = stockExchangeRepository.findByName(stockExchangeName);
        if (!stockExchangeOptional.isPresent()) {
            throw new StockExchangeNotFoundException(
                    "Stock Exchange with name " + stockExchangeName + " does not found");
        }

        final StockExchange stockExchange = stockExchangeOptional.get();
        final List<StockOutput> stockOutput = StockMapper.INSTANCE.convertToStockExchangeOutput(stockExchange.getStocks());

        return stockOutput;
    }

    public void addStockToStockExchange(String stockExchangeName, Stock stock) {

        final Optional<StockExchange> stockExchangeOptional = stockExchangeRepository.findByName(stockExchangeName);
        if (!stockExchangeOptional.isPresent()) {
            throw new StockExchangeNotFoundException(
                    "Stock Exchange with name " + stockExchangeName + " does not found");
        }

        final StockExchange stockExchange = stockExchangeOptional.get();

        final Stock existingStock = stockService.getById(stock.getId());
        stock = existingStock;

        stockExchange.getStocks().add(stock);
        final Boolean stockStatus = checkStockSizeInExchange(stockExchange);
        stockExchange.setLiveInMarket(stockStatus);
        stockExchangeRepository.save(stockExchange);
    }

    public void deleteStockFromStockExchange(String stockExchangeName, Long stockId) {

        final Optional<StockExchange> stockExchangeOptional = stockExchangeRepository.findByName(stockExchangeName);
        if (!stockExchangeOptional.isPresent()) {
            throw new StockExchangeNotFoundException(
                    "Stock Exchange with name " + stockExchangeName + " does not found");
        }

        final StockExchange stockExchange = stockExchangeOptional.get();

        Optional<Stock> stockOptional = stockExchange.getStocks().stream().filter(stock -> stock.getId().equals(stockId)).findAny();

        if (!stockOptional.isPresent()) {
            throw new StockNotFoundException(
                    "Stock with id " + stockId + " does not found");
        }

        stockExchange.getStocks().removeIf(stock -> stock.getId().equals(stockId));
        final Boolean stockStatus = checkStockSizeInExchange(stockExchange);
        stockExchange.setLiveInMarket(stockStatus);
        stockExchangeRepository.save(stockExchange);
    }

    private Boolean checkStockSizeInExchange(StockExchange stockExchange) {
        return stockExchange.getStocks().size() >= MIN_STOCK_SIZE;
    }

}
