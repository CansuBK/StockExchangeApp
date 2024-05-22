package com.java.stock;

import com.java.exception.BadRequestException;
import com.java.exception.StockNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

     public void updatePrice(Long id, BigDecimal price) {

        final Boolean isStockExist = isStockExist(id);

        if(!isStockExist) {
            throw new StockNotFoundException(
                    "Stock with id " + id + " does not found");
        }

        stockRepository.updatePrice(id, price);

    }

    public void delete(Long id) {

        final Boolean isStockExist = isStockExist(id);

        if(!isStockExist) {
            throw new StockNotFoundException(
                    "Stock with id " + id + " does not found");
        }

        stockRepository.deleteById(id);

    }

    public void save(Stock stock) {

       final Optional<Stock> stockOptional = stockRepository.findByName(stock.getName());

        if(stockOptional.isPresent()) {
            throw new BadRequestException(
                    "Name " + stock.getName() + " already exists");
        }

        stockRepository.save(stock);

    }

    private Boolean isStockExist(Long id) {

        final Optional<Stock> stock = stockRepository.findById(id);

        return stock.isPresent() ? true : false;

    }

}
