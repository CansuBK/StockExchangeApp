package com.java.stock;

import com.java.exception.BadRequestException;
import com.java.exception.StockNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public Stock getById(Long id) {

        final Optional<Stock> stockOptional = stockRepository.findById(id);

        if (!stockOptional.isPresent()) {
            throw new StockNotFoundException(
                    "Stock with id " + id + " does not found");
        }

        return stockOptional.get();

    }

    public void updatePrice(Long id, BigDecimal price) {

        final Boolean isStockExist = checkStockExist(id);

        if (!isStockExist) {
            throw new StockNotFoundException(
                    "Stock with id " + id + " does not found");
        }

        stockRepository.updatePrice(id, price);

    }

    public void delete(Long id) {

        final Boolean isStockExist = checkStockExist(id);

        if (!isStockExist) {
            throw new StockNotFoundException(
                    "Stock with id " + id + " does not found");
        }

        stockRepository.deleteById(id);

    }

    public void save(Stock stock) {

        final Optional<Stock> stockOptional = stockRepository.findByName(stock.getName());

        if (stockOptional.isPresent()) {
            throw new BadRequestException(
                    "Name " + stock.getName() + " already exists");
        }

        stockRepository.save(stock);

    }

    private Boolean checkStockExist(Long id) {

        final Optional<Stock> stock = stockRepository.findById(id);

        return stock.isPresent() ? true : false;

    }

}
