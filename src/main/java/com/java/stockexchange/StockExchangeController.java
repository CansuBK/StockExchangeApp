package com.java.stockexchange;

import com.java.stock.Stock;
import com.java.stock.StockOutput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock-exchanges")
@AllArgsConstructor
public class StockExchangeController {

    private final StockExchangeService stockExchangeService;

    @GetMapping("/{stockExchangeName}")
    public ResponseEntity<List<StockOutput>> getStocksByStockExchangeName(@PathVariable String stockExchangeName) {
        final List<StockOutput> stockOutputs = stockExchangeService.getStocksByStockExchangeName(stockExchangeName);
        return new ResponseEntity<>(stockOutputs, HttpStatus.OK);
    }

    @PostMapping("/{stockExchangeName}")
    public ResponseEntity<HttpStatus> addStockToStockExchange(@PathVariable String stockExchangeName, @RequestBody Stock stock) {
        stockExchangeService.addStockToStockExchange(stockExchangeName, stock);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{stockExchangeName}")
    public ResponseEntity<HttpStatus> deleteStockFromStockExchange(@PathVariable String stockExchangeName, @RequestBody Long stockId) {
        stockExchangeService.deleteStockFromStockExchange(stockExchangeName, stockId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
