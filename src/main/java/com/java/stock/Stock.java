package com.java.stock;

import com.java.stockexchange.StockExchange;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="stock")
@Getter
@Setter
@EqualsAndHashCode
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "lastUpdate")
    private LocalDate lastUpdate;

    @Column(name = "updateTime")
    private LocalDate updateTime;

    @Column(name = "updateUser")
    private String updateUser;

    @ManyToMany(mappedBy = "stocks")
    private List<StockExchange> stockExchanges;

}
