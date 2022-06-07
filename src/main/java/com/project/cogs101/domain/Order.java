package com.project.cogs101.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "productname")
    @NotEmpty
    @NotNull
    private String productName;

    @Column(name = "amount")
    @NotEmpty
    @NotNull
    private Integer amount;

    @Column(name = "date")
    @NotEmpty
    @NotNull
    private String date;

    @Column(name = "username")
    @NotEmpty
    @NotNull
    private String username;

    public Order() {
    }

    public Order(String productName, Integer amount, String date, String username) {
        this.productName = productName;
        this.amount = amount;
        this.date = date;
        this.username = username;
    }
}
