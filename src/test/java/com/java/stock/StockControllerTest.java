package com.java.stock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StockControllerTest {

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController stockController;

    @Test
    public void test_update_stock()  {
        Long id = 2L;
        BigDecimal price = new BigDecimal(200);

        ResponseEntity<HttpStatus> responseEntity = stockController.updatePrice(id, price);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(stockService).updatePrice(id, price);
    }

    @Test
    void test_delete() {

        Long id = 1L;
        ResponseEntity<HttpStatus> responseEntity = stockController.delete(id);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(stockService).delete(id);
    }

    @Test
    void test_save() {
        Stock stock = new Stock();
        ResponseEntity<HttpStatus> responseEntity = stockController.save(stock);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(stockService).save(stock);

    }
}
