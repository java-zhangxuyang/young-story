package com.young.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Box {
    private Integer id;

    private String name;

    private Integer type;

    private BigDecimal price;

    private Integer status;

    private Integer useDuration;

    private Integer maidNum;

    private Date admissionTime;

    private Date toTime;

    private Integer remind;

    private String back1;

    private String back2;

    private String back3;
    
    private Long supTime;
    
    private List<BoxSubscribeNote> noteList;
    

    public List<BoxSubscribeNote> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<BoxSubscribeNote> noteList) {
		this.noteList = noteList;
	}

	public Long getSupTime() {
		return supTime;
	}

	public void setSupTime(Long supTime) {
		this.supTime = supTime;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUseDuration() {
        return useDuration;
    }

    public void setUseDuration(Integer useDuration) {
        this.useDuration = useDuration;
    }

    public Integer getMaidNum() {
        return maidNum;
    }

    public void setMaidNum(Integer maidNum) {
        this.maidNum = maidNum;
    }

    public Date getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(Date admissionTime) {
        this.admissionTime = admissionTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public Integer getRemind() {
        return remind;
    }

    public void setRemind(Integer remind) {
        this.remind = remind;
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