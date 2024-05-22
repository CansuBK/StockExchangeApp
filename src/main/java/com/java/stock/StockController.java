package com.java.stock;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stock-exchanges")
@AllArgsConstructor
public class StockController {

    private final StockService stockService;

    @PutMapping
    public ResponseEntity<HttpStatus> updatePrice(@RequestParam Long id, @RequestParam BigDecimal price) {
        stockService.updatePrice(id, price);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable ("id") Long id) {
        stockService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody Stock stock) {
        stockService.save(stock);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
