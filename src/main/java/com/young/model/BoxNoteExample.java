package com.young.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoxNoteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BoxNoteExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBoxIdIsNull() {
            addCriterion("box_id is null");
            return (Criteria) this;
        }

        public Criteria andBoxIdIsNotNull() {
            addCriterion("box_id is not null");
            return (Criteria) this;
        }

        public Criteria andBoxIdEqualTo(Integer value) {
            addCriterion("box_id =", value, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdNotEqualTo(Integer value) {
            addCriterion("box_id <>", value, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdGreaterThan(Integer value) {
            addCriterion("box_id >", value, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("box_id >=", value, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdLessThan(Integer value) {
            addCriterion("box_id <", value, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdLessThanOrEqualTo(Integer value) {
            addCriterion("box_id <=", value, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdIn(List<Integer> values) {
            addCriterion("box_id in", values, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdNotIn(List<Integer> values) {
            addCriterion("box_id not in", values, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdBetween(Integer value1, Integer value2) {
            addCriterion("box_id between", value1, value2, "boxId");
            return (Criteria) this;
        }

        public Criteria andBoxIdNotBetween(Integer value1, Integer value2) {
            addCriterion("box_id not between", value1, value2, "boxId");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andUseDateIsNull() {
            addCriterion("use_date is null");
            return (Criteria) this;
        }

        public Criteria andUseDateIsNotNull() {
            addCriterion("use_date is not null");
            return (Criteria) this;
        }

        public Criteria andUseDateEqualTo(Integer value) {
            addCriterion("use_date =", value, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateNotEqualTo(Integer value) {
            addCriterion("use_date <>", value, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateGreaterThan(Integer value) {
            addCriterion("use_date >", value, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_date >=", value, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateLessThan(Integer value) {
            addCriterion("use_date <", value, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateLessThanOrEqualTo(Integer value) {
            addCriterion("use_date <=", value, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateIn(List<Integer> values) {
            addCriterion("use_date in", values, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateNotIn(List<Integer> values) {
            addCriterion("use_date not in", values, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateBetween(Integer value1, Integer value2) {
            addCriterion("use_date between", value1, value2, "useDate");
            return (Criteria) this;
        }

        public Criteria andUseDateNotBetween(Integer value1, Integer value2) {
            addCriterion("use_date not between", value1, value2, "useDate");
            return (Criteria) this;
        }

        public Criteria andMaidNumIsNull() {
            addCriterion("maid_num is null");
            return (Criteria) this;
        }

        public Criteria andMaidNumIsNotNull() {
            addCriterion("maid_num is not null");
            return (Criteria) this;
        }

        public Criteria andMaidNumEqualTo(Integer value) {
            addCriterion("maid_num =", value, "maidNum");
            return (Criteria) this;
        }

        public Criteria andMaidNumNotEqualTo(Integer value) {
            addCriterion("maid_num <>", value, "maidNum");
            return (Criteria) this;
        }

        public Criteria andMaidNumGreaterThan(Integer value) {
            addCriterion("maid_num >", value, "maidNum");
            return (Criteria) this;
        }

        public Criteria andMaidNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("maid_num >=", value, "maidNum");
            return (Criteria) this;
        }

        public Criteria andMaidNumLessThan(Integer value) {
            addCriterion("maid_num <", value, "maidNum");
            return (Criteria) this;
        }

        public Criteria andMaidNumLessThanOrEqualTo(Integer value) {
            addCriterion("maid_num <=", value, "maidNum");
            return (Criteria) this;
        }

        public Criteria andMaidNumIn(List<Integer> values) {
            addCriterion("maid_num in", values, "maidNum");
            return (Criteria) this;
        }

        public Criteria andMaidNumNotIn(List<Integer> values) {
            addCriterion("maid_num not in", values, "maidNum");
            return (Criteria) this;
        }

        public Criteria andMaidNumBetween(Integer value1, Integer value2) {
            addCriterion("maid_num between", value1, value2, "maidNum");
            return (Criteria) this;
        }

        public Criteria andMaidNumNotBetween(Integer value1, Integer value2) {
            addCriterion("maid_num not between", value1, value2, "maidNum");
            return (Criteria) this;
        }

        public Criteria andPeopleIsNull() {
            addCriterion("people is null");
            return (Criteria) this;
        }

        public Criteria andPeopleIsNotNull() {
            addCriterion("people is not null");
            return (Criteria) this;
        }

        public Criteria andPeopleEqualTo(Integer value) {
            addCriterion("people =", value, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleNotEqualTo(Integer value) {
            addCriterion("people <>", value, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleGreaterThan(Integer value) {
            addCriterion("people >", value, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleGreaterThanOrEqualTo(Integer value) {
            addCriterion("people >=", value, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleLessThan(Integer value) {
            addCriterion("people <", value, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleLessThanOrEqualTo(Integer value) {
            addCriterion("people <=", value, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleIn(List<Integer> values) {
            addCriterion("people in", values, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleNotIn(List<Integer> values) {
            addCriterion("people not in", values, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleBetween(Integer value1, Integer value2) {
            addCriterion("people between", value1, value2, "people");
            return (Criteria) this;
        }

        public Criteria andPeopleNotBetween(Integer value1, Integer value2) {
            addCriterion("people not between", value1, value2, "people");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andBack1IsNull() {
            addCriterion("back1 is null");
            return (Criteria) this;
        }

        public Criteria andBack1IsNotNull() {
            addCriterion("back1 is not null");
            return (Criteria) this;
        }

        public Criteria andBack1EqualTo(String value) {
            addCriterion("back1 =", value, "back1");
            return (Criteria) this;
        }

        public Criteria andBack1NotEqualTo(String value) {
            addCriterion("back1 <>", value, "back1");
            return (Criteria) this;
        }

        public Criteria andBack1GreaterThan(String value) {
            addCriterion("back1 >", value, "back1");
            return (Criteria) this;
        }

        public Criteria andBack1GreaterThanOrEqualTo(String value) {
            addCriterion("back1 >=", value, "back1");
            return (Criteria) this;
        }

        public Criteria andBack1LessThan(String value) {
            addCriterion("back1 <", value, "back1");
            return (Criteria) this;
        }

        public Criteria andBack1LessThanOrEqualTo(String value) {
            addCriterion("back1 <=", value, "back1");
            return (Criteria) this;
        }

        public Criteria andBack1Like(String value) {
            addCriterion("back1 like", value, "back1");
            return (Criteria) this;
        }

        public Criteria andBack1NotLike(String value) {
            addCriterion("back1 not like", value, "back1");
            return (Criteria) this;
        }

        public Criteria andBack1In(List<String> values) {
            addCriterion("back1 in", values, "back1");
            return (Criteria) this;
        }

        public Criteria andBack1NotIn(List<String> values) {
            addCriterion("back1 not in", values, "back1");
            return (Criteria) this;
        }

        public Criteria andBack1Between(String value1, String value2) {
            addCriterion("back1 between", value1, value2, "back1");
            return (Criteria) this;
        }

        public Criteria andBack1NotBetween(String value1, String value2) {
            addCriterion("back1 not between", value1, value2, "back1");
            return (Criteria) this;
        }

        public Criteria andBack2IsNull() {
            addCriterion("back2 is null");
            return (Criteria) this;
        }

        public Criteria andBack2IsNotNull() {
            addCriterion("back2 is not null");
            return (Criteria) this;
        }

        public Criteria andBack2EqualTo(String value) {
            addCriterion("back2 =", value, "back2");
            return (Criteria) this;
        }

        public Criteria andBack2NotEqualTo(String value) {
            addCriterion("back2 <>", value, "back2");
            return (Criteria) this;
        }

        public Criteria andBack2GreaterThan(String value) {
            addCriterion("back2 >", value, "back2");
            return (Criteria) this;
        }

        public Criteria andBack2GreaterThanOrEqualTo(String value) {
            addCriterion("back2 >=", value, "back2");
            return (Criteria) this;
        }

        public Criteria andBack2LessThan(String value) {
            addCriterion("back2 <", value, "back2");
            return (Criteria) this;
        }

        public Criteria andBack2LessThanOrEqualTo(String value) {
            addCriterion("back2 <=", value, "back2");
            return (Criteria) this;
        }

        public Criteria andBack2Like(String value) {
            addCriterion("back2 like", value, "back2");
            return (Criteria) this;
        }

        public Criteria andBack2NotLike(String value) {
            addCriterion("back2 not like", value, "back2");
            return (Criteria) this;
        }

        public Criteria andBack2In(List<String> values) {
            addCriterion("back2 in", values, "back2");
            return (Criteria) this;
        }

        public Criteria andBack2NotIn(List<String> values) {
            addCriterion("back2 not in", values, "back2");
            return (Criteria) this;
        }

        public Criteria andBack2Between(String value1, String value2) {
            addCriterion("back2 between", value1, value2, "back2");
            return (Criteria) this;
        }

        public Criteria andBack2NotBetween(String value1, String value2) {
            addCriterion("back2 not between", value1, value2, "back2");
            return (Criteria) this;
        }

        public Criteria andBack3IsNull() {
            addCriterion("back3 is null");
            return (Criteria) this;
        }

        public Criteria andBack3IsNotNull() {
            addCriterion("back3 is not null");
            return (Criteria) this;
        }

        public Criteria andBack3EqualTo(String value) {
            addCriterion("back3 =", value, "back3");
            return (Criteria) this;
        }

        public Criteria andBack3NotEqualTo(String value) {
            addCriterion("back3 <>", value, "back3");
            return (Criteria) this;
        }

        public Criteria andBack3GreaterThan(String value) {
            addCriterion("back3 >", value, "back3");
            return (Criteria) this;
        }

        public Criteria andBack3GreaterThanOrEqualTo(String value) {
            addCriterion("back3 >=", value, "back3");
            return (Criteria) this;
        }

        public Criteria andBack3LessThan(String value) {
            addCriterion("back3 <", value, "back3");
            return (Criteria) this;
        }

        public Criteria andBack3LessThanOrEqualTo(String value) {
            addCriterion("back3 <=", value, "back3");
            return (Criteria) this;
        }

        public Criteria andBack3Like(String value) {
            addCriterion("back3 like", value, "back3");
            return (Criteria) this;
        }

        public Criteria andBack3NotLike(String value) {
            addCriterion("back3 not like", value, "back3");
            return (Criteria) this;
        }

        public Criteria andBack3In(List<String> values) {
            addCriterion("back3 in", values, "back3");
            return (Criteria) this;
        }

        public Criteria andBack3NotIn(List<String> values) {
            addCriterion("back3 not in", values, "back3");
            return (Criteria) this;
        }

        public Criteria andBack3Between(String value1, String value2) {
            addCriterion("back3 between", value1, value2, "back3");
            return (Criteria) this;
        }

        public Criteria andBack3NotBetween(String value1, String value2) {
            addCriterion("back3 not between", value1, value2, "back3");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}