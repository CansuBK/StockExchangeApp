package com.java.stockexchange;

import com.java.stock.Stock;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock-exchanges")
@AllArgsConstructor
public class StockExchangeController {

    private final StockExchangeService stockExchangeService;

    @GetMapping("/{name}")
    public ResponseEntity<StockExchange> getByName(@PathVariable String name) {
        final StockExchange stockExchange = stockExchangeService.getStockExchangeByName(name);
        return new ResponseEntity<>(stockExchange, HttpStatus.OK);
    }

    @PostMapping("/{stockExchangeName}")
    public ResponseEntity<HttpStatus> addStockToStockExchange(@PathVariable String stockExchangeName, @RequestBody Stock stock) {
        stockExchangeService.addStockToStockExchange(stockExchangeName, stock);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{stockExchangeName}")
    public ResponseEntity<HttpStatus> deleteStockFromStockExchange(@PathVariable String stockExchangeName, @RequestBody Long stockId) {
        stockExchangeService.deleteStockFromStockExchange(stockExchangeName, stockId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
