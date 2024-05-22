package com.java.stockexchange;

import com.java.stock.StockController;
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
public class StockExchangeControllerTest {

    @Mock
    private StockExchangeService stockExchangeService;

    @InjectMocks
    private StockController stockController;

   /* @Test
    public void test_get_by_name()  {
        String name = "demo";

        ResponseEntity<HttpStatus> responseEntity = stockExchangeService.getStockExchangeByName(name);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(stockService).updatePrice(id, price);
    } */
}
