package com.young.model;

import java.util.Date;

public class BoxNote {
    private Integer id;

    private Integer boxId;

    private Integer number;

    private Date startTime;

    private Date endTime;

    private Integer useDate;

    private Integer maidNum;

    private Integer people;

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

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getUseDate() {
        return useDate;
    }

    public void setUseDate(Integer useDate) {
        this.useDate = useDate;
    }

    public Integer getMaidNum() {
        return maidNum;
    }

    public void setMaidNum(Integer maidNum) {
        this.maidNum = maidNum;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
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