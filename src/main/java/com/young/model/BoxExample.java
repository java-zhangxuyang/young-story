package com.young.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BoxExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andUseDurationIsNull() {
            addCriterion("use_duration is null");
            return (Criteria) this;
        }

        public Criteria andUseDurationIsNotNull() {
            addCriterion("use_duration is not null");
            return (Criteria) this;
        }

        public Criteria andUseDurationEqualTo(Integer value) {
            addCriterion("use_duration =", value, "useDuration");
            return (Criteria) this;
        }

        public Criteria andUseDurationNotEqualTo(Integer value) {
            addCriterion("use_duration <>", value, "useDuration");
            return (Criteria) this;
        }

        public Criteria andUseDurationGreaterThan(Integer value) {
            addCriterion("use_duration >", value, "useDuration");
            return (Criteria) this;
        }

        public Criteria andUseDurationGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_duration >=", value, "useDuration");
            return (Criteria) this;
        }

        public Criteria andUseDurationLessThan(Integer value) {
            addCriterion("use_duration <", value, "useDuration");
            return (Criteria) this;
        }

        public Criteria andUseDurationLessThanOrEqualTo(Integer value) {
            addCriterion("use_duration <=", value, "useDuration");
            return (Criteria) this;
        }

        public Criteria andUseDurationIn(List<Integer> values) {
            addCriterion("use_duration in", values, "useDuration");
            return (Criteria) this;
        }

        public Criteria andUseDurationNotIn(List<Integer> values) {
            addCriterion("use_duration not in", values, "useDuration");
            return (Criteria) this;
        }

        public Criteria andUseDurationBetween(Integer value1, Integer value2) {
            addCriterion("use_duration between", value1, value2, "useDuration");
            return (Criteria) this;
        }

        public Criteria andUseDurationNotBetween(Integer value1, Integer value2) {
            addCriterion("use_duration not between", value1, value2, "useDuration");
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

        public Criteria andAdmissionTimeIsNull() {
            addCriterion("admission_time is null");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeIsNotNull() {
            addCriterion("admission_time is not null");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeEqualTo(Date value) {
            addCriterion("admission_time =", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotEqualTo(Date value) {
            addCriterion("admission_time <>", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeGreaterThan(Date value) {
            addCriterion("admission_time >", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("admission_time >=", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeLessThan(Date value) {
            addCriterion("admission_time <", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeLessThanOrEqualTo(Date value) {
            addCriterion("admission_time <=", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeIn(List<Date> values) {
            addCriterion("admission_time in", values, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotIn(List<Date> values) {
            addCriterion("admission_time not in", values, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeBetween(Date value1, Date value2) {
            addCriterion("admission_time between", value1, value2, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotBetween(Date value1, Date value2) {
            addCriterion("admission_time not between", value1, value2, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andToTimeIsNull() {
            addCriterion("to_time is null");
            return (Criteria) this;
        }

        public Criteria andToTimeIsNotNull() {
            addCriterion("to_time is not null");
            return (Criteria) this;
        }

        public Criteria andToTimeEqualTo(Date value) {
            addCriterion("to_time =", value, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeNotEqualTo(Date value) {
            addCriterion("to_time <>", value, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeGreaterThan(Date value) {
            addCriterion("to_time >", value, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("to_time >=", value, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeLessThan(Date value) {
            addCriterion("to_time <", value, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeLessThanOrEqualTo(Date value) {
            addCriterion("to_time <=", value, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeIn(List<Date> values) {
            addCriterion("to_time in", values, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeNotIn(List<Date> values) {
            addCriterion("to_time not in", values, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeBetween(Date value1, Date value2) {
            addCriterion("to_time between", value1, value2, "toTime");
            return (Criteria) this;
        }

        public Criteria andToTimeNotBetween(Date value1, Date value2) {
            addCriterion("to_time not between", value1, value2, "toTime");
            return (Criteria) this;
        }

        public Criteria andRemindIsNull() {
            addCriterion("remind is null");
            return (Criteria) this;
        }

        public Criteria andRemindIsNotNull() {
            addCriterion("remind is not null");
            return (Criteria) this;
        }

        public Criteria andRemindEqualTo(Integer value) {
            addCriterion("remind =", value, "remind");
            return (Criteria) this;
        }

        public Criteria andRemindNotEqualTo(Integer value) {
            addCriterion("remind <>", value, "remind");
            return (Criteria) this;
        }

        public Criteria andRemindGreaterThan(Integer value) {
            addCriterion("remind >", value, "remind");
            return (Criteria) this;
        }

        public Criteria andRemindGreaterThanOrEqualTo(Integer value) {
            addCriterion("remind >=", value, "remind");
            return (Criteria) this;
        }

        public Criteria andRemindLessThan(Integer value) {
            addCriterion("remind <", value, "remind");
            return (Criteria) this;
        }

        public Criteria andRemindLessThanOrEqualTo(Integer value) {
            addCriterion("remind <=", value, "remind");
            return (Criteria) this;
        }

        public Criteria andRemindIn(List<Integer> values) {
            addCriterion("remind in", values, "remind");
            return (Criteria) this;
        }

        public Criteria andRemindNotIn(List<Integer> values) {
            addCriterion("remind not in", values, "remind");
            return (Criteria) this;
        }

        public Criteria andRemindBetween(Integer value1, Integer value2) {
            addCriterion("remind between", value1, value2, "remind");
            return (Criteria) this;
        }

        public Criteria andRemindNotBetween(Integer value1, Integer value2) {
            addCriterion("remind not between", value1, value2, "remind");
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