package com.java.stockexchange;

import com.java.exception.StockExchangeNotFoundException;
import com.java.exception.StockNotFoundException;
import com.java.stock.Stock;
import com.java.stock.StockMapper;
import com.java.stock.StockOutput;
import com.java.stock.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockExchangeServiceTest {

    @Mock
    private StockExchangeRepository stockExchangeRepository;

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockExchangeService stockExchangeService;

    private StockExchange stockExchange;
    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock();
        stock.setId(1L);
        stock.setName("demo");

        stockExchange = new StockExchange();
        stockExchange.setId(1L);
        stockExchange.setName("demo");
        stockExchange.setStocks(new ArrayList<>());
    }

    @Test
    void testGetStocksByStockExchangeName_whenStockExchangeDoesNotExist() {
        when(stockExchangeRepository.findByName("demo")).thenReturn(Optional.empty());
        assertThrows(StockExchangeNotFoundException.class, () -> stockExchangeService.getStocksByStockExchangeName("demo"));
    }

    @Test
    void testAddStockToStockExchange_whenStockExchangeExistsAndStockExists() {
        when(stockExchangeRepository.findByName("demo")).thenReturn(Optional.of(stockExchange));
        when(stockService.getById(1L)).thenReturn(stock);
        stockExchangeService.addStockToStockExchange("demo", stock);
        verify(stockExchangeRepository).findByName("demo");
        verify(stockService).getById(1L);
        verify(stockExchangeRepository).save(stockExchange);
    }

    @Test
    void testAddStockToStockExchange_whenStockExchangeDoesNotExist() {
        when(stockExchangeRepository.findByName("demo")).thenReturn(Optional.empty());
        assertThrows(StockExchangeNotFoundException.class, () -> stockExchangeService.addStockToStockExchange("demo", stock));
    }

    @Test
    void testDeleteStockFromStockExchange_whenStockExchangeExistsAndStockExists() {
        stockExchange.getStocks().add(stock);
        when(stockExchangeRepository.findByName("demo")).thenReturn(Optional.of(stockExchange));
        stockExchangeService.deleteStockFromStockExchange("demo", 1L);
        verify(stockExchangeRepository).findByName("demo");
        verify(stockExchangeRepository).save(stockExchange);
    }

    @Test
    void testDeleteStockFromStockExchange_whenStockExchangeDoesNotExist() {
        when(stockExchangeRepository.findByName("demo")).thenReturn(Optional.empty());
        assertThrows(StockExchangeNotFoundException.class, () -> stockExchangeService.deleteStockFromStockExchange("demo", 1L));
    }

    @Test
    void testDeleteStockFromStockExchange_whenStockDoesNotExist() {
        when(stockExchangeRepository.findByName("demo")).thenReturn(Optional.of(stockExchange));
        assertThrows(StockNotFoundException.class, () -> stockExchangeService.deleteStockFromStockExchange("demo", 1L));
    }
}
