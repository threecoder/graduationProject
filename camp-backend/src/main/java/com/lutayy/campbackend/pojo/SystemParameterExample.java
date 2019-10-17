package com.lutayy.campbackend.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SystemParameterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SystemParameterExample() {
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

        public Criteria andParaIdIsNull() {
            addCriterion("para_id is null");
            return (Criteria) this;
        }

        public Criteria andParaIdIsNotNull() {
            addCriterion("para_id is not null");
            return (Criteria) this;
        }

        public Criteria andParaIdEqualTo(Integer value) {
            addCriterion("para_id =", value, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdNotEqualTo(Integer value) {
            addCriterion("para_id <>", value, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdGreaterThan(Integer value) {
            addCriterion("para_id >", value, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("para_id >=", value, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdLessThan(Integer value) {
            addCriterion("para_id <", value, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdLessThanOrEqualTo(Integer value) {
            addCriterion("para_id <=", value, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdIn(List<Integer> values) {
            addCriterion("para_id in", values, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdNotIn(List<Integer> values) {
            addCriterion("para_id not in", values, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdBetween(Integer value1, Integer value2) {
            addCriterion("para_id between", value1, value2, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("para_id not between", value1, value2, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaKeyIsNull() {
            addCriterion("para_key is null");
            return (Criteria) this;
        }

        public Criteria andParaKeyIsNotNull() {
            addCriterion("para_key is not null");
            return (Criteria) this;
        }

        public Criteria andParaKeyEqualTo(String value) {
            addCriterion("para_key =", value, "paraKey");
            return (Criteria) this;
        }

        public Criteria andParaKeyNotEqualTo(String value) {
            addCriterion("para_key <>", value, "paraKey");
            return (Criteria) this;
        }

        public Criteria andParaKeyGreaterThan(String value) {
            addCriterion("para_key >", value, "paraKey");
            return (Criteria) this;
        }

        public Criteria andParaKeyGreaterThanOrEqualTo(String value) {
            addCriterion("para_key >=", value, "paraKey");
            return (Criteria) this;
        }

        public Criteria andParaKeyLessThan(String value) {
            addCriterion("para_key <", value, "paraKey");
            return (Criteria) this;
        }

        public Criteria andParaKeyLessThanOrEqualTo(String value) {
            addCriterion("para_key <=", value, "paraKey");
            return (Criteria) this;
        }

        public Criteria andParaKeyLike(String value) {
            addCriterion("para_key like", value, "paraKey");
            return (Criteria) this;
        }

        public Criteria andParaKeyNotLike(String value) {
            addCriterion("para_key not like", value, "paraKey");
            return (Criteria) this;
        }

        public Criteria andParaKeyIn(List<String> values) {
            addCriterion("para_key in", values, "paraKey");
            return (Criteria) this;
        }

        public Criteria andParaKeyNotIn(List<String> values) {
            addCriterion("para_key not in", values, "paraKey");
            return (Criteria) this;
        }

        public Criteria andParaKeyBetween(String value1, String value2) {
            addCriterion("para_key between", value1, value2, "paraKey");
            return (Criteria) this;
        }

        public Criteria andParaKeyNotBetween(String value1, String value2) {
            addCriterion("para_key not between", value1, value2, "paraKey");
            return (Criteria) this;
        }

        public Criteria andParaValueIsNull() {
            addCriterion("para_value is null");
            return (Criteria) this;
        }

        public Criteria andParaValueIsNotNull() {
            addCriterion("para_value is not null");
            return (Criteria) this;
        }

        public Criteria andParaValueEqualTo(String value) {
            addCriterion("para_value =", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueNotEqualTo(String value) {
            addCriterion("para_value <>", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueGreaterThan(String value) {
            addCriterion("para_value >", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueGreaterThanOrEqualTo(String value) {
            addCriterion("para_value >=", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueLessThan(String value) {
            addCriterion("para_value <", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueLessThanOrEqualTo(String value) {
            addCriterion("para_value <=", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueLike(String value) {
            addCriterion("para_value like", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueNotLike(String value) {
            addCriterion("para_value not like", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueIn(List<String> values) {
            addCriterion("para_value in", values, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueNotIn(List<String> values) {
            addCriterion("para_value not in", values, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueBetween(String value1, String value2) {
            addCriterion("para_value between", value1, value2, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueNotBetween(String value1, String value2) {
            addCriterion("para_value not between", value1, value2, "paraValue");
            return (Criteria) this;
        }

        public Criteria andValueTypeIsNull() {
            addCriterion("value_type is null");
            return (Criteria) this;
        }

        public Criteria andValueTypeIsNotNull() {
            addCriterion("value_type is not null");
            return (Criteria) this;
        }

        public Criteria andValueTypeEqualTo(String value) {
            addCriterion("value_type =", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotEqualTo(String value) {
            addCriterion("value_type <>", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThan(String value) {
            addCriterion("value_type >", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("value_type >=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThan(String value) {
            addCriterion("value_type <", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLessThanOrEqualTo(String value) {
            addCriterion("value_type <=", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeLike(String value) {
            addCriterion("value_type like", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotLike(String value) {
            addCriterion("value_type not like", value, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeIn(List<String> values) {
            addCriterion("value_type in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotIn(List<String> values) {
            addCriterion("value_type not in", values, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeBetween(String value1, String value2) {
            addCriterion("value_type between", value1, value2, "valueType");
            return (Criteria) this;
        }

        public Criteria andValueTypeNotBetween(String value1, String value2) {
            addCriterion("value_type not between", value1, value2, "valueType");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Boolean value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Boolean value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Boolean value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Boolean value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Boolean> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Boolean> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
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