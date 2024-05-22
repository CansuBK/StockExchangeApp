package com.java.stock;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class StockOutput {

    private String name;

    private String description;

    private BigDecimal price;

    private LocalDate lastUpdate;

    private LocalDate updateTime;

    private String updateUser;

}
