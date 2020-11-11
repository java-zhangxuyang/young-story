package com.young.model;

import java.math.BigDecimal;

public class Flushing {
    private Integer id;

    private BigDecimal recharge;

    private BigDecimal send;

    private BigDecimal total;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getRecharge() {
        return recharge;
    }

    public void setRecharge(BigDecimal recharge) {
        this.recharge = recharge;
    }

    public BigDecimal getSend() {
        return send;
    }

    public void setSend(BigDecimal send) {
        this.send = send;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}