package com.java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockExchangeNotFoundException extends RuntimeException {

    public StockExchangeNotFoundException(String msg) {
        super(msg);
    }

}