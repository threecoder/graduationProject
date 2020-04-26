package com.lutayy.campbackend.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    //加入 offset 起始位置 和 limit 查询页数 用来分页
    protected Integer offset;
    protected Integer limit;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public ActivityExample() {
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

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(Integer value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(Integer value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(Integer value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(Integer value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(Integer value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<Integer> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<Integer> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(Integer value1, Integer value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityFeeIsNull() {
            addCriterion("activity_fee is null");
            return (Criteria) this;
        }

        public Criteria andActivityFeeIsNotNull() {
            addCriterion("activity_fee is not null");
            return (Criteria) this;
        }

        public Criteria andActivityFeeEqualTo(BigDecimal value) {
            addCriterion("activity_fee =", value, "activityFee");
            return (Criteria) this;
        }

        public Criteria andActivityFeeNotEqualTo(BigDecimal value) {
            addCriterion("activity_fee <>", value, "activityFee");
            return (Criteria) this;
        }

        public Criteria andActivityFeeGreaterThan(BigDecimal value) {
            addCriterion("activity_fee >", value, "activityFee");
            return (Criteria) this;
        }

        public Criteria andActivityFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("activity_fee >=", value, "activityFee");
            return (Criteria) this;
        }

        public Criteria andActivityFeeLessThan(BigDecimal value) {
            addCriterion("activity_fee <", value, "activityFee");
            return (Criteria) this;
        }

        public Criteria andActivityFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("activity_fee <=", value, "activityFee");
            return (Criteria) this;
        }

        public Criteria andActivityFeeIn(List<BigDecimal> values) {
            addCriterion("activity_fee in", values, "activityFee");
            return (Criteria) this;
        }

        public Criteria andActivityFeeNotIn(List<BigDecimal> values) {
            addCriterion("activity_fee not in", values, "activityFee");
            return (Criteria) this;
        }

        public Criteria andActivityFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("activity_fee between", value1, value2, "activityFee");
            return (Criteria) this;
        }

        public Criteria andActivityFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("activity_fee not between", value1, value2, "activityFee");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNull() {
            addCriterion("activity_name is null");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNotNull() {
            addCriterion("activity_name is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNameEqualTo(String value) {
            addCriterion("activity_name =", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotEqualTo(String value) {
            addCriterion("activity_name <>", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThan(String value) {
            addCriterion("activity_name >", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("activity_name >=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThan(String value) {
            addCriterion("activity_name <", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThanOrEqualTo(String value) {
            addCriterion("activity_name <=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLike(String value) {
            addCriterion("activity_name like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotLike(String value) {
            addCriterion("activity_name not like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameIn(List<String> values) {
            addCriterion("activity_name in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotIn(List<String> values) {
            addCriterion("activity_name not in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameBetween(String value1, String value2) {
            addCriterion("activity_name between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotBetween(String value1, String value2) {
            addCriterion("activity_name not between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityIntroductionIsNull() {
            addCriterion("activity_introduction is null");
            return (Criteria) this;
        }

        public Criteria andActivityIntroductionIsNotNull() {
            addCriterion("activity_introduction is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIntroductionEqualTo(String value) {
            addCriterion("activity_introduction =", value, "activityIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivityIntroductionNotEqualTo(String value) {
            addCriterion("activity_introduction <>", value, "activityIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivityIntroductionGreaterThan(String value) {
            addCriterion("activity_introduction >", value, "activityIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivityIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("activity_introduction >=", value, "activityIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivityIntroductionLessThan(String value) {
            addCriterion("activity_introduction <", value, "activityIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivityIntroductionLessThanOrEqualTo(String value) {
            addCriterion("activity_introduction <=", value, "activityIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivityIntroductionLike(String value) {
            addCriterion("activity_introduction like", value, "activityIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivityIntroductionNotLike(String value) {
            addCriterion("activity_introduction not like", value, "activityIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivityIntroductionIn(List<String> values) {
            addCriterion("activity_introduction in", values, "activityIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivityIntroductionNotIn(List<String> values) {
            addCriterion("activity_introduction not in", values, "activityIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivityIntroductionBetween(String value1, String value2) {
            addCriterion("activity_introduction between", value1, value2, "activityIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivityIntroductionNotBetween(String value1, String value2) {
            addCriterion("activity_introduction not between", value1, value2, "activityIntroduction");
            return (Criteria) this;
        }

        public Criteria andActivityDateIsNull() {
            addCriterion("activity_date is null");
            return (Criteria) this;
        }

        public Criteria andActivityDateIsNotNull() {
            addCriterion("activity_date is not null");
            return (Criteria) this;
        }

        public Criteria andActivityDateEqualTo(Date value) {
            addCriterion("activity_date =", value, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateNotEqualTo(Date value) {
            addCriterion("activity_date <>", value, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateGreaterThan(Date value) {
            addCriterion("activity_date >", value, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateGreaterThanOrEqualTo(Date value) {
            addCriterion("activity_date >=", value, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateLessThan(Date value) {
            addCriterion("activity_date <", value, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateLessThanOrEqualTo(Date value) {
            addCriterion("activity_date <=", value, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateIn(List<Date> values) {
            addCriterion("activity_date in", values, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateNotIn(List<Date> values) {
            addCriterion("activity_date not in", values, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateBetween(Date value1, Date value2) {
            addCriterion("activity_date between", value1, value2, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityDateNotBetween(Date value1, Date value2) {
            addCriterion("activity_date not between", value1, value2, "activityDate");
            return (Criteria) this;
        }

        public Criteria andActivityLengthMinIsNull() {
            addCriterion("activity_length_min is null");
            return (Criteria) this;
        }

        public Criteria andActivityLengthMinIsNotNull() {
            addCriterion("activity_length_min is not null");
            return (Criteria) this;
        }

        public Criteria andActivityLengthMinEqualTo(Integer value) {
            addCriterion("activity_length_min =", value, "activityLengthMin");
            return (Criteria) this;
        }

        public Criteria andActivityLengthMinNotEqualTo(Integer value) {
            addCriterion("activity_length_min <>", value, "activityLengthMin");
            return (Criteria) this;
        }

        public Criteria andActivityLengthMinGreaterThan(Integer value) {
            addCriterion("activity_length_min >", value, "activityLengthMin");
            return (Criteria) this;
        }

        public Criteria andActivityLengthMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_length_min >=", value, "activityLengthMin");
            return (Criteria) this;
        }

        public Criteria andActivityLengthMinLessThan(Integer value) {
            addCriterion("activity_length_min <", value, "activityLengthMin");
            return (Criteria) this;
        }

        public Criteria andActivityLengthMinLessThanOrEqualTo(Integer value) {
            addCriterion("activity_length_min <=", value, "activityLengthMin");
            return (Criteria) this;
        }

        public Criteria andActivityLengthMinIn(List<Integer> values) {
            addCriterion("activity_length_min in", values, "activityLengthMin");
            return (Criteria) this;
        }

        public Criteria andActivityLengthMinNotIn(List<Integer> values) {
            addCriterion("activity_length_min not in", values, "activityLengthMin");
            return (Criteria) this;
        }

        public Criteria andActivityLengthMinBetween(Integer value1, Integer value2) {
            addCriterion("activity_length_min between", value1, value2, "activityLengthMin");
            return (Criteria) this;
        }

        public Criteria andActivityLengthMinNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_length_min not between", value1, value2, "activityLengthMin");
            return (Criteria) this;
        }

        public Criteria andActivityAddressIsNull() {
            addCriterion("activity_address is null");
            return (Criteria) this;
        }

        public Criteria andActivityAddressIsNotNull() {
            addCriterion("activity_address is not null");
            return (Criteria) this;
        }

        public Criteria andActivityAddressEqualTo(String value) {
            addCriterion("activity_address =", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressNotEqualTo(String value) {
            addCriterion("activity_address <>", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressGreaterThan(String value) {
            addCriterion("activity_address >", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressGreaterThanOrEqualTo(String value) {
            addCriterion("activity_address >=", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressLessThan(String value) {
            addCriterion("activity_address <", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressLessThanOrEqualTo(String value) {
            addCriterion("activity_address <=", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressLike(String value) {
            addCriterion("activity_address like", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressNotLike(String value) {
            addCriterion("activity_address not like", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressIn(List<String> values) {
            addCriterion("activity_address in", values, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressNotIn(List<String> values) {
            addCriterion("activity_address not in", values, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressBetween(String value1, String value2) {
            addCriterion("activity_address between", value1, value2, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressNotBetween(String value1, String value2) {
            addCriterion("activity_address not between", value1, value2, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNull() {
            addCriterion("open_time is null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNotNull() {
            addCriterion("open_time is not null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeEqualTo(Date value) {
            addCriterion("open_time =", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotEqualTo(Date value) {
            addCriterion("open_time <>", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThan(Date value) {
            addCriterion("open_time >", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("open_time >=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThan(Date value) {
            addCriterion("open_time <", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThanOrEqualTo(Date value) {
            addCriterion("open_time <=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIn(List<Date> values) {
            addCriterion("open_time in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotIn(List<Date> values) {
            addCriterion("open_time not in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeBetween(Date value1, Date value2) {
            addCriterion("open_time between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotBetween(Date value1, Date value2) {
            addCriterion("open_time not between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIsNull() {
            addCriterion("close_time is null");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIsNotNull() {
            addCriterion("close_time is not null");
            return (Criteria) this;
        }

        public Criteria andCloseTimeEqualTo(Date value) {
            addCriterion("close_time =", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotEqualTo(Date value) {
            addCriterion("close_time <>", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeGreaterThan(Date value) {
            addCriterion("close_time >", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("close_time >=", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLessThan(Date value) {
            addCriterion("close_time <", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLessThanOrEqualTo(Date value) {
            addCriterion("close_time <=", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIn(List<Date> values) {
            addCriterion("close_time in", values, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotIn(List<Date> values) {
            addCriterion("close_time not in", values, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeBetween(Date value1, Date value2) {
            addCriterion("close_time between", value1, value2, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotBetween(Date value1, Date value2) {
            addCriterion("close_time not between", value1, value2, "closeTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeIsNull() {
            addCriterion("post_time is null");
            return (Criteria) this;
        }

        public Criteria andPostTimeIsNotNull() {
            addCriterion("post_time is not null");
            return (Criteria) this;
        }

        public Criteria andPostTimeEqualTo(Date value) {
            addCriterion("post_time =", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotEqualTo(Date value) {
            addCriterion("post_time <>", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeGreaterThan(Date value) {
            addCriterion("post_time >", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("post_time >=", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeLessThan(Date value) {
            addCriterion("post_time <", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeLessThanOrEqualTo(Date value) {
            addCriterion("post_time <=", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeIn(List<Date> values) {
            addCriterion("post_time in", values, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotIn(List<Date> values) {
            addCriterion("post_time not in", values, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeBetween(Date value1, Date value2) {
            addCriterion("post_time between", value1, value2, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotBetween(Date value1, Date value2) {
            addCriterion("post_time not between", value1, value2, "postTime");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNull() {
            addCriterion("contact_name is null");
            return (Criteria) this;
        }

        public Criteria andContactNameIsNotNull() {
            addCriterion("contact_name is not null");
            return (Criteria) this;
        }

        public Criteria andContactNameEqualTo(String value) {
            addCriterion("contact_name =", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotEqualTo(String value) {
            addCriterion("contact_name <>", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThan(String value) {
            addCriterion("contact_name >", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameGreaterThanOrEqualTo(String value) {
            addCriterion("contact_name >=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThan(String value) {
            addCriterion("contact_name <", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLessThanOrEqualTo(String value) {
            addCriterion("contact_name <=", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameLike(String value) {
            addCriterion("contact_name like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotLike(String value) {
            addCriterion("contact_name not like", value, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameIn(List<String> values) {
            addCriterion("contact_name in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotIn(List<String> values) {
            addCriterion("contact_name not in", values, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameBetween(String value1, String value2) {
            addCriterion("contact_name between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactNameNotBetween(String value1, String value2) {
            addCriterion("contact_name not between", value1, value2, "contactName");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNull() {
            addCriterion("contact_phone is null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNotNull() {
            addCriterion("contact_phone is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneEqualTo(String value) {
            addCriterion("contact_phone =", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotEqualTo(String value) {
            addCriterion("contact_phone <>", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThan(String value) {
            addCriterion("contact_phone >", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("contact_phone >=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThan(String value) {
            addCriterion("contact_phone <", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("contact_phone <=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLike(String value) {
            addCriterion("contact_phone like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotLike(String value) {
            addCriterion("contact_phone not like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIn(List<String> values) {
            addCriterion("contact_phone in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotIn(List<String> values) {
            addCriterion("contact_phone not in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneBetween(String value1, String value2) {
            addCriterion("contact_phone between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotBetween(String value1, String value2) {
            addCriterion("contact_phone not between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andMaxNumIsNull() {
            addCriterion("max_num is null");
            return (Criteria) this;
        }

        public Criteria andMaxNumIsNotNull() {
            addCriterion("max_num is not null");
            return (Criteria) this;
        }

        public Criteria andMaxNumEqualTo(Integer value) {
            addCriterion("max_num =", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumNotEqualTo(Integer value) {
            addCriterion("max_num <>", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumGreaterThan(Integer value) {
            addCriterion("max_num >", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_num >=", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumLessThan(Integer value) {
            addCriterion("max_num <", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumLessThanOrEqualTo(Integer value) {
            addCriterion("max_num <=", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumIn(List<Integer> values) {
            addCriterion("max_num in", values, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumNotIn(List<Integer> values) {
            addCriterion("max_num not in", values, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumBetween(Integer value1, Integer value2) {
            addCriterion("max_num between", value1, value2, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumNotBetween(Integer value1, Integer value2) {
            addCriterion("max_num not between", value1, value2, "maxNum");
            return (Criteria) this;
        }

        public Criteria andAreaWidthIsNull() {
            addCriterion("area_width is null");
            return (Criteria) this;
        }

        public Criteria andAreaWidthIsNotNull() {
            addCriterion("area_width is not null");
            return (Criteria) this;
        }

        public Criteria andAreaWidthEqualTo(Integer value) {
            addCriterion("area_width =", value, "areaWidth");
            return (Criteria) this;
        }

        public Criteria andAreaWidthNotEqualTo(Integer value) {
            addCriterion("area_width <>", value, "areaWidth");
            return (Criteria) this;
        }

        public Criteria andAreaWidthGreaterThan(Integer value) {
            addCriterion("area_width >", value, "areaWidth");
            return (Criteria) this;
        }

        public Criteria andAreaWidthGreaterThanOrEqualTo(Integer value) {
            addCriterion("area_width >=", value, "areaWidth");
            return (Criteria) this;
        }

        public Criteria andAreaWidthLessThan(Integer value) {
            addCriterion("area_width <", value, "areaWidth");
            return (Criteria) this;
        }

        public Criteria andAreaWidthLessThanOrEqualTo(Integer value) {
            addCriterion("area_width <=", value, "areaWidth");
            return (Criteria) this;
        }

        public Criteria andAreaWidthIn(List<Integer> values) {
            addCriterion("area_width in", values, "areaWidth");
            return (Criteria) this;
        }

        public Criteria andAreaWidthNotIn(List<Integer> values) {
            addCriterion("area_width not in", values, "areaWidth");
            return (Criteria) this;
        }

        public Criteria andAreaWidthBetween(Integer value1, Integer value2) {
            addCriterion("area_width between", value1, value2, "areaWidth");
            return (Criteria) this;
        }

        public Criteria andAreaWidthNotBetween(Integer value1, Integer value2) {
            addCriterion("area_width not between", value1, value2, "areaWidth");
            return (Criteria) this;
        }

        public Criteria andAreaLengthIsNull() {
            addCriterion("area_length is null");
            return (Criteria) this;
        }

        public Criteria andAreaLengthIsNotNull() {
            addCriterion("area_length is not null");
            return (Criteria) this;
        }

        public Criteria andAreaLengthEqualTo(Integer value) {
            addCriterion("area_length =", value, "areaLength");
            return (Criteria) this;
        }

        public Criteria andAreaLengthNotEqualTo(Integer value) {
            addCriterion("area_length <>", value, "areaLength");
            return (Criteria) this;
        }

        public Criteria andAreaLengthGreaterThan(Integer value) {
            addCriterion("area_length >", value, "areaLength");
            return (Criteria) this;
        }

        public Criteria andAreaLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("area_length >=", value, "areaLength");
            return (Criteria) this;
        }

        public Criteria andAreaLengthLessThan(Integer value) {
            addCriterion("area_length <", value, "areaLength");
            return (Criteria) this;
        }

        public Criteria andAreaLengthLessThanOrEqualTo(Integer value) {
            addCriterion("area_length <=", value, "areaLength");
            return (Criteria) this;
        }

        public Criteria andAreaLengthIn(List<Integer> values) {
            addCriterion("area_length in", values, "areaLength");
            return (Criteria) this;
        }

        public Criteria andAreaLengthNotIn(List<Integer> values) {
            addCriterion("area_length not in", values, "areaLength");
            return (Criteria) this;
        }

        public Criteria andAreaLengthBetween(Integer value1, Integer value2) {
            addCriterion("area_length between", value1, value2, "areaLength");
            return (Criteria) this;
        }

        public Criteria andAreaLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("area_length not between", value1, value2, "areaLength");
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