package com.java.stock;

import com.java.exception.BadRequestException;
import com.java.exception.StockNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock();
        stock.setId(1L);
        stock.setName("demo");
        stock.setDescription("demo");
        stock.setPrice(new BigDecimal("100.00"));
    }

    @Test
    void testGetById_whenStockExists() {
        when(stockRepository.findById(anyLong())).thenReturn(Optional.of(stock));
        Stock foundStock = stockService.getById(1L);
        assertEquals(stock, foundStock);
    }

    @Test
    void testGetById_whenStockDoesNotExist() {
        when(stockRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(StockNotFoundException.class, () -> stockService.getById(1L));
    }

    @Test
    void testUpdatePrice_whenStockExists() {
        when(stockRepository.findById(anyLong())).thenReturn(Optional.of(stock));
        stockService.updatePrice(1L, new BigDecimal("100.00"));
        verify(stockRepository).updatePrice(1L, new BigDecimal("100.00"));
    }

    @Test
    void testUpdatePrice_whenStockDoesNotExist() {
        when(stockRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(StockNotFoundException.class, () -> stockService.updatePrice(1L, new BigDecimal("100.00")));
    }

    @Test
    void testDelete_whenStockExists() {
        when(stockRepository.findById(anyLong())).thenReturn(Optional.of(stock));
        stockService.delete(1L);
        verify(stockRepository).deleteById(1L);
    }

    @Test
    void testDelete_whenStockDoesNotExist() {
        when(stockRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(StockNotFoundException.class, () -> stockService.delete(1L));
    }

    @Test
    void testSave_whenStockDoesNotExist() {
        when(stockRepository.findByName(anyString())).thenReturn(Optional.empty());
        stockService.save(stock);
        verify(stockRepository).save(stock);
    }

    @Test
    void testSave_whenStockExists() {
        when(stockRepository.findByName(anyString())).thenReturn(Optional.of(stock));
        assertThrows(BadRequestException.class, () -> stockService.save(stock));
    }

}