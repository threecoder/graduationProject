package com.lutayy.campbackend.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponMemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CouponMemberExample() {
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

        public Criteria andCouponIdIsNull() {
            addCriterion("coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNotNull() {
            addCriterion("coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponIdEqualTo(Integer value) {
            addCriterion("coupon_id =", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotEqualTo(Integer value) {
            addCriterion("coupon_id <>", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThan(Integer value) {
            addCriterion("coupon_id >", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_id >=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThan(Integer value) {
            addCriterion("coupon_id <", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_id <=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdIn(List<Integer> values) {
            addCriterion("coupon_id in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotIn(List<Integer> values) {
            addCriterion("coupon_id not in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdBetween(Integer value1, Integer value2) {
            addCriterion("coupon_id between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_id not between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponCodeIsNull() {
            addCriterion("coupon_code is null");
            return (Criteria) this;
        }

        public Criteria andCouponCodeIsNotNull() {
            addCriterion("coupon_code is not null");
            return (Criteria) this;
        }

        public Criteria andCouponCodeEqualTo(String value) {
            addCriterion("coupon_code =", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeNotEqualTo(String value) {
            addCriterion("coupon_code <>", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeGreaterThan(String value) {
            addCriterion("coupon_code >", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_code >=", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeLessThan(String value) {
            addCriterion("coupon_code <", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeLessThanOrEqualTo(String value) {
            addCriterion("coupon_code <=", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeLike(String value) {
            addCriterion("coupon_code like", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeNotLike(String value) {
            addCriterion("coupon_code not like", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeIn(List<String> values) {
            addCriterion("coupon_code in", values, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeNotIn(List<String> values) {
            addCriterion("coupon_code not in", values, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeBetween(String value1, String value2) {
            addCriterion("coupon_code between", value1, value2, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeNotBetween(String value1, String value2) {
            addCriterion("coupon_code not between", value1, value2, "couponCode");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdIsNull() {
            addCriterion("member_key_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdIsNotNull() {
            addCriterion("member_key_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdEqualTo(Integer value) {
            addCriterion("member_key_id =", value, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdNotEqualTo(Integer value) {
            addCriterion("member_key_id <>", value, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdGreaterThan(Integer value) {
            addCriterion("member_key_id >", value, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_key_id >=", value, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdLessThan(Integer value) {
            addCriterion("member_key_id <", value, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_key_id <=", value, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdIn(List<Integer> values) {
            addCriterion("member_key_id in", values, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdNotIn(List<Integer> values) {
            addCriterion("member_key_id not in", values, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdBetween(Integer value1, Integer value2) {
            addCriterion("member_key_id between", value1, value2, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_key_id not between", value1, value2, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Boolean value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Boolean value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Boolean value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Boolean value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Boolean value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Boolean> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Boolean> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Boolean value1, Boolean value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andIsInvalidIsNull() {
            addCriterion("is_invalid is null");
            return (Criteria) this;
        }

        public Criteria andIsInvalidIsNotNull() {
            addCriterion("is_invalid is not null");
            return (Criteria) this;
        }

        public Criteria andIsInvalidEqualTo(Boolean value) {
            addCriterion("is_invalid =", value, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidNotEqualTo(Boolean value) {
            addCriterion("is_invalid <>", value, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidGreaterThan(Boolean value) {
            addCriterion("is_invalid >", value, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_invalid >=", value, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidLessThan(Boolean value) {
            addCriterion("is_invalid <", value, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidLessThanOrEqualTo(Boolean value) {
            addCriterion("is_invalid <=", value, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidIn(List<Boolean> values) {
            addCriterion("is_invalid in", values, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidNotIn(List<Boolean> values) {
            addCriterion("is_invalid not in", values, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidBetween(Boolean value1, Boolean value2) {
            addCriterion("is_invalid between", value1, value2, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andIsInvalidNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_invalid not between", value1, value2, "isInvalid");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
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