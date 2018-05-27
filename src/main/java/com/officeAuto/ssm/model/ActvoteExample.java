package com.officeAuto.ssm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ActvoteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActvoteExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(Integer value) {
            addCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(Integer value) {
            addCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(Integer value) {
            addCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(Integer value) {
            addCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(Integer value) {
            addCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<Integer> values) {
            addCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<Integer> values) {
            addCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(Integer value1, Integer value2) {
            addCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(Integer value1, Integer value2) {
            addCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andVotetimeIsNull() {
            addCriterion("votetime is null");
            return (Criteria) this;
        }

        public Criteria andVotetimeIsNotNull() {
            addCriterion("votetime is not null");
            return (Criteria) this;
        }

        public Criteria andVotetimeEqualTo(Date value) {
            addCriterionForJDBCDate("votetime =", value, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("votetime <>", value, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("votetime >", value, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("votetime >=", value, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeLessThan(Date value) {
            addCriterionForJDBCDate("votetime <", value, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("votetime <=", value, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeIn(List<Date> values) {
            addCriterionForJDBCDate("votetime in", values, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("votetime not in", values, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("votetime between", value1, value2, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("votetime not between", value1, value2, "votetime");
            return (Criteria) this;
        }

        public Criteria andActoptionIsNull() {
            addCriterion("actoption is null");
            return (Criteria) this;
        }

        public Criteria andActoptionIsNotNull() {
            addCriterion("actoption is not null");
            return (Criteria) this;
        }

        public Criteria andActoptionEqualTo(Integer value) {
            addCriterion("actoption =", value, "actoption");
            return (Criteria) this;
        }

        public Criteria andActoptionNotEqualTo(Integer value) {
            addCriterion("actoption <>", value, "actoption");
            return (Criteria) this;
        }

        public Criteria andActoptionGreaterThan(Integer value) {
            addCriterion("actoption >", value, "actoption");
            return (Criteria) this;
        }

        public Criteria andActoptionGreaterThanOrEqualTo(Integer value) {
            addCriterion("actoption >=", value, "actoption");
            return (Criteria) this;
        }

        public Criteria andActoptionLessThan(Integer value) {
            addCriterion("actoption <", value, "actoption");
            return (Criteria) this;
        }

        public Criteria andActoptionLessThanOrEqualTo(Integer value) {
            addCriterion("actoption <=", value, "actoption");
            return (Criteria) this;
        }

        public Criteria andActoptionIn(List<Integer> values) {
            addCriterion("actoption in", values, "actoption");
            return (Criteria) this;
        }

        public Criteria andActoptionNotIn(List<Integer> values) {
            addCriterion("actoption not in", values, "actoption");
            return (Criteria) this;
        }

        public Criteria andActoptionBetween(Integer value1, Integer value2) {
            addCriterion("actoption between", value1, value2, "actoption");
            return (Criteria) this;
        }

        public Criteria andActoptionNotBetween(Integer value1, Integer value2) {
            addCriterion("actoption not between", value1, value2, "actoption");
            return (Criteria) this;
        }

        public Criteria andEmployeeIsNull() {
            addCriterion("employee is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIsNotNull() {
            addCriterion("employee is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeEqualTo(Integer value) {
            addCriterion("employee =", value, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeNotEqualTo(Integer value) {
            addCriterion("employee <>", value, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeGreaterThan(Integer value) {
            addCriterion("employee >", value, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("employee >=", value, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeLessThan(Integer value) {
            addCriterion("employee <", value, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeLessThanOrEqualTo(Integer value) {
            addCriterion("employee <=", value, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeIn(List<Integer> values) {
            addCriterion("employee in", values, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeNotIn(List<Integer> values) {
            addCriterion("employee not in", values, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeBetween(Integer value1, Integer value2) {
            addCriterion("employee between", value1, value2, "employee");
            return (Criteria) this;
        }

        public Criteria andEmployeeNotBetween(Integer value1, Integer value2) {
            addCriterion("employee not between", value1, value2, "employee");
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