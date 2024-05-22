package com.java.stockexchange;

import com.java.stock.Stock;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="stock-exchange")
@Getter
@Setter
@EqualsAndHashCode
public class StockExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "liveInMarket")
    private Boolean liveInMarket;

    @Column(name = "updateTime")
    private LocalDate updateTime;

    @Column(name = "updateUser")
    private String updateUser;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "stock_exchange_stock",
            joinColumns = @JoinColumn(name = "stock_exchange_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id")
    )
    private List<Stock> stocks;

}
