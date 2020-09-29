package com.young.model;

import java.math.BigDecimal;
import java.util.Date;

public class ConsumptionNote {
    private Integer id;

    private Integer passId;

    private Integer type;

    private Integer freeCharge;

    private BigDecimal money;

    private Date time;

    private String remark;

    private String back1;

    private String back2;

    private String back3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPassId() {
        return passId;
    }

    public void setPassId(Integer passId) {
        this.passId = passId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFreeCharge() {
        return freeCharge;
    }

    public void setFreeCharge(Integer freeCharge) {
        this.freeCharge = freeCharge;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getBack1() {
        return back1;
    }

    public void setBack1(String back1) {
        this.back1 = back1 == null ? null : back1.trim();
    }

    public String getBack2() {
        return back2;
    }

    public void setBack2(String back2) {
        this.back2 = back2 == null ? null : back2.trim();
    }

    public String getBack3() {
        return back3;
    }

    public void setBack3(String back3) {
        this.back3 = back3 == null ? null : back3.trim();
    }
}