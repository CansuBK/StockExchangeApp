package com.java.stockexchange;

import com.java.stock.Stock;
import com.java.stock.StockOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockExchangeControllerTest {

    @Mock
    private StockExchangeService stockExchangeService;

    @InjectMocks
    private StockExchangeController stockExchangeController;

    private Stock stock;
    private List<StockOutput> stockOutputs;

    @BeforeEach
    void setUp() {
        stock = new Stock();
        stock.setId(1L);
        stock.setName("demo");
        stock.setDescription("demo");
        stock.setPrice(new BigDecimal("150.00"));

        StockOutput stockOutput = new StockOutput();
        stockOutput.setName("demo");
        stockOutput.setDescription("demo");
        stockOutput.setPrice(new BigDecimal("150.00"));

        stockOutputs = new ArrayList<>();
        stockOutputs.add(stockOutput);
    }

    @Test
    void testGetStocksByStockExchangeName() {
        String stockExchangeName = "demo";
        when(stockExchangeService.getStocksByStockExchangeName(anyString())).thenReturn(stockOutputs);
        ResponseEntity<List<StockOutput>> response = stockExchangeController.getStocksByStockExchangeName(stockExchangeName);
        verify(stockExchangeService).getStocksByStockExchangeName(stockExchangeName);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(stockOutputs, response.getBody());
    }

    @Test
    void testAddStockToStockExchange() {
        String stockExchangeName = "demo";
        doNothing().when(stockExchangeService).addStockToStockExchange(anyString(), any(Stock.class));
        ResponseEntity<HttpStatus> response = stockExchangeController.addStockToStockExchange(stockExchangeName, stock);
        verify(stockExchangeService).addStockToStockExchange(stockExchangeName, stock);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testDeleteStockFromStockExchange() {
        String stockExchangeName = "demo";
        Long stockId = 1L;
        doNothing().when(stockExchangeService).deleteStockFromStockExchange(anyString(), any(Long.class));
        ResponseEntity<HttpStatus> response = stockExchangeController.deleteStockFromStockExchange(stockExchangeName, stockId);
        verify(stockExchangeService).deleteStockFromStockExchange(stockExchangeName, stockId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
