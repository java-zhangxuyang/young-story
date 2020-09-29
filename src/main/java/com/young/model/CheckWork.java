package com.young.model;

import java.util.Date;

public class CheckWork {
    private Integer id;

    private Integer staffId;

    private Date nowDate;

    private Integer status;

    private Date closeTime;

    private String back1;

    private String back2;

    private String back3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Date getNowDate() {
        return nowDate;
    }

    public void setNowDate(Date nowDate) {
        this.nowDate = nowDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
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