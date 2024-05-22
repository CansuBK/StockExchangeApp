package com.java.stock;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StockOutput {

    private String name;

    private String description;

    private BigDecimal price;

    private LocalDate lastUpdate;

    private LocalDate updateTime;

    private String updateUser;

}
