package com.lutayy.campbackend.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberExample() {
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

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(String value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(String value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(String value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(String value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(String value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(String value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLike(String value) {
            addCriterion("member_id like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotLike(String value) {
            addCriterion("member_id not like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<String> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<String> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(String value1, String value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(String value1, String value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneIsNull() {
            addCriterion("member_phone is null");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneIsNotNull() {
            addCriterion("member_phone is not null");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneEqualTo(String value) {
            addCriterion("member_phone =", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotEqualTo(String value) {
            addCriterion("member_phone <>", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneGreaterThan(String value) {
            addCriterion("member_phone >", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("member_phone >=", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneLessThan(String value) {
            addCriterion("member_phone <", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneLessThanOrEqualTo(String value) {
            addCriterion("member_phone <=", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneLike(String value) {
            addCriterion("member_phone like", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotLike(String value) {
            addCriterion("member_phone not like", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneIn(List<String> values) {
            addCriterion("member_phone in", values, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotIn(List<String> values) {
            addCriterion("member_phone not in", values, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneBetween(String value1, String value2) {
            addCriterion("member_phone between", value1, value2, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotBetween(String value1, String value2) {
            addCriterion("member_phone not between", value1, value2, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberEmailIsNull() {
            addCriterion("member_email is null");
            return (Criteria) this;
        }

        public Criteria andMemberEmailIsNotNull() {
            addCriterion("member_email is not null");
            return (Criteria) this;
        }

        public Criteria andMemberEmailEqualTo(String value) {
            addCriterion("member_email =", value, "memberEmail");
            return (Criteria) this;
        }

        public Criteria andMemberEmailNotEqualTo(String value) {
            addCriterion("member_email <>", value, "memberEmail");
            return (Criteria) this;
        }

        public Criteria andMemberEmailGreaterThan(String value) {
            addCriterion("member_email >", value, "memberEmail");
            return (Criteria) this;
        }

        public Criteria andMemberEmailGreaterThanOrEqualTo(String value) {
            addCriterion("member_email >=", value, "memberEmail");
            return (Criteria) this;
        }

        public Criteria andMemberEmailLessThan(String value) {
            addCriterion("member_email <", value, "memberEmail");
            return (Criteria) this;
        }

        public Criteria andMemberEmailLessThanOrEqualTo(String value) {
            addCriterion("member_email <=", value, "memberEmail");
            return (Criteria) this;
        }

        public Criteria andMemberEmailLike(String value) {
            addCriterion("member_email like", value, "memberEmail");
            return (Criteria) this;
        }

        public Criteria andMemberEmailNotLike(String value) {
            addCriterion("member_email not like", value, "memberEmail");
            return (Criteria) this;
        }

        public Criteria andMemberEmailIn(List<String> values) {
            addCriterion("member_email in", values, "memberEmail");
            return (Criteria) this;
        }

        public Criteria andMemberEmailNotIn(List<String> values) {
            addCriterion("member_email not in", values, "memberEmail");
            return (Criteria) this;
        }

        public Criteria andMemberEmailBetween(String value1, String value2) {
            addCriterion("member_email between", value1, value2, "memberEmail");
            return (Criteria) this;
        }

        public Criteria andMemberEmailNotBetween(String value1, String value2) {
            addCriterion("member_email not between", value1, value2, "memberEmail");
            return (Criteria) this;
        }

        public Criteria andMemberPasswordIsNull() {
            addCriterion("member_password is null");
            return (Criteria) this;
        }

        public Criteria andMemberPasswordIsNotNull() {
            addCriterion("member_password is not null");
            return (Criteria) this;
        }

        public Criteria andMemberPasswordEqualTo(String value) {
            addCriterion("member_password =", value, "memberPassword");
            return (Criteria) this;
        }

        public Criteria andMemberPasswordNotEqualTo(String value) {
            addCriterion("member_password <>", value, "memberPassword");
            return (Criteria) this;
        }

        public Criteria andMemberPasswordGreaterThan(String value) {
            addCriterion("member_password >", value, "memberPassword");
            return (Criteria) this;
        }

        public Criteria andMemberPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("member_password >=", value, "memberPassword");
            return (Criteria) this;
        }

        public Criteria andMemberPasswordLessThan(String value) {
            addCriterion("member_password <", value, "memberPassword");
            return (Criteria) this;
        }

        public Criteria andMemberPasswordLessThanOrEqualTo(String value) {
            addCriterion("member_password <=", value, "memberPassword");
            return (Criteria) this;
        }

        public Criteria andMemberPasswordLike(String value) {
            addCriterion("member_password like", value, "memberPassword");
            return (Criteria) this;
        }

        public Criteria andMemberPasswordNotLike(String value) {
            addCriterion("member_password not like", value, "memberPassword");
            return (Criteria) this;
        }

        public Criteria andMemberPasswordIn(List<String> values) {
            addCriterion("member_password in", values, "memberPassword");
            return (Criteria) this;
        }

        public Criteria andMemberPasswordNotIn(List<String> values) {
            addCriterion("member_password not in", values, "memberPassword");
            return (Criteria) this;
        }

        public Criteria andMemberPasswordBetween(String value1, String value2) {
            addCriterion("member_password between", value1, value2, "memberPassword");
            return (Criteria) this;
        }

        public Criteria andMemberPasswordNotBetween(String value1, String value2) {
            addCriterion("member_password not between", value1, value2, "memberPassword");
            return (Criteria) this;
        }

        public Criteria andMemberTelIsNull() {
            addCriterion("member_tel is null");
            return (Criteria) this;
        }

        public Criteria andMemberTelIsNotNull() {
            addCriterion("member_tel is not null");
            return (Criteria) this;
        }

        public Criteria andMemberTelEqualTo(String value) {
            addCriterion("member_tel =", value, "memberTel");
            return (Criteria) this;
        }

        public Criteria andMemberTelNotEqualTo(String value) {
            addCriterion("member_tel <>", value, "memberTel");
            return (Criteria) this;
        }

        public Criteria andMemberTelGreaterThan(String value) {
            addCriterion("member_tel >", value, "memberTel");
            return (Criteria) this;
        }

        public Criteria andMemberTelGreaterThanOrEqualTo(String value) {
            addCriterion("member_tel >=", value, "memberTel");
            return (Criteria) this;
        }

        public Criteria andMemberTelLessThan(String value) {
            addCriterion("member_tel <", value, "memberTel");
            return (Criteria) this;
        }

        public Criteria andMemberTelLessThanOrEqualTo(String value) {
            addCriterion("member_tel <=", value, "memberTel");
            return (Criteria) this;
        }

        public Criteria andMemberTelLike(String value) {
            addCriterion("member_tel like", value, "memberTel");
            return (Criteria) this;
        }

        public Criteria andMemberTelNotLike(String value) {
            addCriterion("member_tel not like", value, "memberTel");
            return (Criteria) this;
        }

        public Criteria andMemberTelIn(List<String> values) {
            addCriterion("member_tel in", values, "memberTel");
            return (Criteria) this;
        }

        public Criteria andMemberTelNotIn(List<String> values) {
            addCriterion("member_tel not in", values, "memberTel");
            return (Criteria) this;
        }

        public Criteria andMemberTelBetween(String value1, String value2) {
            addCriterion("member_tel between", value1, value2, "memberTel");
            return (Criteria) this;
        }

        public Criteria andMemberTelNotBetween(String value1, String value2) {
            addCriterion("member_tel not between", value1, value2, "memberTel");
            return (Criteria) this;
        }

        public Criteria andMemberNameIsNull() {
            addCriterion("member_name is null");
            return (Criteria) this;
        }

        public Criteria andMemberNameIsNotNull() {
            addCriterion("member_name is not null");
            return (Criteria) this;
        }

        public Criteria andMemberNameEqualTo(String value) {
            addCriterion("member_name =", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotEqualTo(String value) {
            addCriterion("member_name <>", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameGreaterThan(String value) {
            addCriterion("member_name >", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameGreaterThanOrEqualTo(String value) {
            addCriterion("member_name >=", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLessThan(String value) {
            addCriterion("member_name <", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLessThanOrEqualTo(String value) {
            addCriterion("member_name <=", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLike(String value) {
            addCriterion("member_name like", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotLike(String value) {
            addCriterion("member_name not like", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameIn(List<String> values) {
            addCriterion("member_name in", values, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotIn(List<String> values) {
            addCriterion("member_name not in", values, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameBetween(String value1, String value2) {
            addCriterion("member_name between", value1, value2, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotBetween(String value1, String value2) {
            addCriterion("member_name not between", value1, value2, "memberName");
            return (Criteria) this;
        }

        public Criteria andIsVipIsNull() {
            addCriterion("is_vip is null");
            return (Criteria) this;
        }

        public Criteria andIsVipIsNotNull() {
            addCriterion("is_vip is not null");
            return (Criteria) this;
        }

        public Criteria andIsVipEqualTo(Boolean value) {
            addCriterion("is_vip =", value, "isVip");
            return (Criteria) this;
        }

        public Criteria andIsVipNotEqualTo(Boolean value) {
            addCriterion("is_vip <>", value, "isVip");
            return (Criteria) this;
        }

        public Criteria andIsVipGreaterThan(Boolean value) {
            addCriterion("is_vip >", value, "isVip");
            return (Criteria) this;
        }

        public Criteria andIsVipGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_vip >=", value, "isVip");
            return (Criteria) this;
        }

        public Criteria andIsVipLessThan(Boolean value) {
            addCriterion("is_vip <", value, "isVip");
            return (Criteria) this;
        }

        public Criteria andIsVipLessThanOrEqualTo(Boolean value) {
            addCriterion("is_vip <=", value, "isVip");
            return (Criteria) this;
        }

        public Criteria andIsVipIn(List<Boolean> values) {
            addCriterion("is_vip in", values, "isVip");
            return (Criteria) this;
        }

        public Criteria andIsVipNotIn(List<Boolean> values) {
            addCriterion("is_vip not in", values, "isVip");
            return (Criteria) this;
        }

        public Criteria andIsVipBetween(Boolean value1, Boolean value2) {
            addCriterion("is_vip between", value1, value2, "isVip");
            return (Criteria) this;
        }

        public Criteria andIsVipNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_vip not between", value1, value2, "isVip");
            return (Criteria) this;
        }

        public Criteria andEnterDateIsNull() {
            addCriterion("enter_date is null");
            return (Criteria) this;
        }

        public Criteria andEnterDateIsNotNull() {
            addCriterion("enter_date is not null");
            return (Criteria) this;
        }

        public Criteria andEnterDateEqualTo(Date value) {
            addCriterionForJDBCDate("enter_date =", value, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("enter_date <>", value, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateGreaterThan(Date value) {
            addCriterionForJDBCDate("enter_date >", value, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("enter_date >=", value, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateLessThan(Date value) {
            addCriterionForJDBCDate("enter_date <", value, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("enter_date <=", value, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateIn(List<Date> values) {
            addCriterionForJDBCDate("enter_date in", values, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("enter_date not in", values, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("enter_date between", value1, value2, "enterDate");
            return (Criteria) this;
        }

        public Criteria andEnterDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("enter_date not between", value1, value2, "enterDate");
            return (Criteria) this;
        }

        public Criteria andVipEndDateIsNull() {
            addCriterion("vip_end_date is null");
            return (Criteria) this;
        }

        public Criteria andVipEndDateIsNotNull() {
            addCriterion("vip_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andVipEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("vip_end_date =", value, "vipEndDate");
            return (Criteria) this;
        }

        public Criteria andVipEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("vip_end_date <>", value, "vipEndDate");
            return (Criteria) this;
        }

        public Criteria andVipEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("vip_end_date >", value, "vipEndDate");
            return (Criteria) this;
        }

        public Criteria andVipEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("vip_end_date >=", value, "vipEndDate");
            return (Criteria) this;
        }

        public Criteria andVipEndDateLessThan(Date value) {
            addCriterionForJDBCDate("vip_end_date <", value, "vipEndDate");
            return (Criteria) this;
        }

        public Criteria andVipEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("vip_end_date <=", value, "vipEndDate");
            return (Criteria) this;
        }

        public Criteria andVipEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("vip_end_date in", values, "vipEndDate");
            return (Criteria) this;
        }

        public Criteria andVipEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("vip_end_date not in", values, "vipEndDate");
            return (Criteria) this;
        }

        public Criteria andVipEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("vip_end_date between", value1, value2, "vipEndDate");
            return (Criteria) this;
        }

        public Criteria andVipEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("vip_end_date not between", value1, value2, "vipEndDate");
            return (Criteria) this;
        }

        public Criteria andVipBeginDateIsNull() {
            addCriterion("vip_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andVipBeginDateIsNotNull() {
            addCriterion("vip_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andVipBeginDateEqualTo(Date value) {
            addCriterionForJDBCDate("vip_begin_date =", value, "vipBeginDate");
            return (Criteria) this;
        }

        public Criteria andVipBeginDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("vip_begin_date <>", value, "vipBeginDate");
            return (Criteria) this;
        }

        public Criteria andVipBeginDateGreaterThan(Date value) {
            addCriterionForJDBCDate("vip_begin_date >", value, "vipBeginDate");
            return (Criteria) this;
        }

        public Criteria andVipBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("vip_begin_date >=", value, "vipBeginDate");
            return (Criteria) this;
        }

        public Criteria andVipBeginDateLessThan(Date value) {
            addCriterionForJDBCDate("vip_begin_date <", value, "vipBeginDate");
            return (Criteria) this;
        }

        public Criteria andVipBeginDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("vip_begin_date <=", value, "vipBeginDate");
            return (Criteria) this;
        }

        public Criteria andVipBeginDateIn(List<Date> values) {
            addCriterionForJDBCDate("vip_begin_date in", values, "vipBeginDate");
            return (Criteria) this;
        }

        public Criteria andVipBeginDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("vip_begin_date not in", values, "vipBeginDate");
            return (Criteria) this;
        }

        public Criteria andVipBeginDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("vip_begin_date between", value1, value2, "vipBeginDate");
            return (Criteria) this;
        }

        public Criteria andVipBeginDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("vip_begin_date not between", value1, value2, "vipBeginDate");
            return (Criteria) this;
        }

        public Criteria andMemberCountryIsNull() {
            addCriterion("member_country is null");
            return (Criteria) this;
        }

        public Criteria andMemberCountryIsNotNull() {
            addCriterion("member_country is not null");
            return (Criteria) this;
        }

        public Criteria andMemberCountryEqualTo(String value) {
            addCriterion("member_country =", value, "memberCountry");
            return (Criteria) this;
        }

        public Criteria andMemberCountryNotEqualTo(String value) {
            addCriterion("member_country <>", value, "memberCountry");
            return (Criteria) this;
        }

        public Criteria andMemberCountryGreaterThan(String value) {
            addCriterion("member_country >", value, "memberCountry");
            return (Criteria) this;
        }

        public Criteria andMemberCountryGreaterThanOrEqualTo(String value) {
            addCriterion("member_country >=", value, "memberCountry");
            return (Criteria) this;
        }

        public Criteria andMemberCountryLessThan(String value) {
            addCriterion("member_country <", value, "memberCountry");
            return (Criteria) this;
        }

        public Criteria andMemberCountryLessThanOrEqualTo(String value) {
            addCriterion("member_country <=", value, "memberCountry");
            return (Criteria) this;
        }

        public Criteria andMemberCountryLike(String value) {
            addCriterion("member_country like", value, "memberCountry");
            return (Criteria) this;
        }

        public Criteria andMemberCountryNotLike(String value) {
            addCriterion("member_country not like", value, "memberCountry");
            return (Criteria) this;
        }

        public Criteria andMemberCountryIn(List<String> values) {
            addCriterion("member_country in", values, "memberCountry");
            return (Criteria) this;
        }

        public Criteria andMemberCountryNotIn(List<String> values) {
            addCriterion("member_country not in", values, "memberCountry");
            return (Criteria) this;
        }

        public Criteria andMemberCountryBetween(String value1, String value2) {
            addCriterion("member_country between", value1, value2, "memberCountry");
            return (Criteria) this;
        }

        public Criteria andMemberCountryNotBetween(String value1, String value2) {
            addCriterion("member_country not between", value1, value2, "memberCountry");
            return (Criteria) this;
        }

        public Criteria andMemberProvinceIsNull() {
            addCriterion("member_province is null");
            return (Criteria) this;
        }

        public Criteria andMemberProvinceIsNotNull() {
            addCriterion("member_province is not null");
            return (Criteria) this;
        }

        public Criteria andMemberProvinceEqualTo(String value) {
            addCriterion("member_province =", value, "memberProvince");
            return (Criteria) this;
        }

        public Criteria andMemberProvinceNotEqualTo(String value) {
            addCriterion("member_province <>", value, "memberProvince");
            return (Criteria) this;
        }

        public Criteria andMemberProvinceGreaterThan(String value) {
            addCriterion("member_province >", value, "memberProvince");
            return (Criteria) this;
        }

        public Criteria andMemberProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("member_province >=", value, "memberProvince");
            return (Criteria) this;
        }

        public Criteria andMemberProvinceLessThan(String value) {
            addCriterion("member_province <", value, "memberProvince");
            return (Criteria) this;
        }

        public Criteria andMemberProvinceLessThanOrEqualTo(String value) {
            addCriterion("member_province <=", value, "memberProvince");
            return (Criteria) this;
        }

        public Criteria andMemberProvinceLike(String value) {
            addCriterion("member_province like", value, "memberProvince");
            return (Criteria) this;
        }

        public Criteria andMemberProvinceNotLike(String value) {
            addCriterion("member_province not like", value, "memberProvince");
            return (Criteria) this;
        }

        public Criteria andMemberProvinceIn(List<String> values) {
            addCriterion("member_province in", values, "memberProvince");
            return (Criteria) this;
        }

        public Criteria andMemberProvinceNotIn(List<String> values) {
            addCriterion("member_province not in", values, "memberProvince");
            return (Criteria) this;
        }

        public Criteria andMemberProvinceBetween(String value1, String value2) {
            addCriterion("member_province between", value1, value2, "memberProvince");
            return (Criteria) this;
        }

        public Criteria andMemberProvinceNotBetween(String value1, String value2) {
            addCriterion("member_province not between", value1, value2, "memberProvince");
            return (Criteria) this;
        }

        public Criteria andMemberCityIsNull() {
            addCriterion("member_city is null");
            return (Criteria) this;
        }

        public Criteria andMemberCityIsNotNull() {
            addCriterion("member_city is not null");
            return (Criteria) this;
        }

        public Criteria andMemberCityEqualTo(String value) {
            addCriterion("member_city =", value, "memberCity");
            return (Criteria) this;
        }

        public Criteria andMemberCityNotEqualTo(String value) {
            addCriterion("member_city <>", value, "memberCity");
            return (Criteria) this;
        }

        public Criteria andMemberCityGreaterThan(String value) {
            addCriterion("member_city >", value, "memberCity");
            return (Criteria) this;
        }

        public Criteria andMemberCityGreaterThanOrEqualTo(String value) {
            addCriterion("member_city >=", value, "memberCity");
            return (Criteria) this;
        }

        public Criteria andMemberCityLessThan(String value) {
            addCriterion("member_city <", value, "memberCity");
            return (Criteria) this;
        }

        public Criteria andMemberCityLessThanOrEqualTo(String value) {
            addCriterion("member_city <=", value, "memberCity");
            return (Criteria) this;
        }

        public Criteria andMemberCityLike(String value) {
            addCriterion("member_city like", value, "memberCity");
            return (Criteria) this;
        }

        public Criteria andMemberCityNotLike(String value) {
            addCriterion("member_city not like", value, "memberCity");
            return (Criteria) this;
        }

        public Criteria andMemberCityIn(List<String> values) {
            addCriterion("member_city in", values, "memberCity");
            return (Criteria) this;
        }

        public Criteria andMemberCityNotIn(List<String> values) {
            addCriterion("member_city not in", values, "memberCity");
            return (Criteria) this;
        }

        public Criteria andMemberCityBetween(String value1, String value2) {
            addCriterion("member_city between", value1, value2, "memberCity");
            return (Criteria) this;
        }

        public Criteria andMemberCityNotBetween(String value1, String value2) {
            addCriterion("member_city not between", value1, value2, "memberCity");
            return (Criteria) this;
        }

        public Criteria andMemberAreaIsNull() {
            addCriterion("member_area is null");
            return (Criteria) this;
        }

        public Criteria andMemberAreaIsNotNull() {
            addCriterion("member_area is not null");
            return (Criteria) this;
        }

        public Criteria andMemberAreaEqualTo(String value) {
            addCriterion("member_area =", value, "memberArea");
            return (Criteria) this;
        }

        public Criteria andMemberAreaNotEqualTo(String value) {
            addCriterion("member_area <>", value, "memberArea");
            return (Criteria) this;
        }

        public Criteria andMemberAreaGreaterThan(String value) {
            addCriterion("member_area >", value, "memberArea");
            return (Criteria) this;
        }

        public Criteria andMemberAreaGreaterThanOrEqualTo(String value) {
            addCriterion("member_area >=", value, "memberArea");
            return (Criteria) this;
        }

        public Criteria andMemberAreaLessThan(String value) {
            addCriterion("member_area <", value, "memberArea");
            return (Criteria) this;
        }

        public Criteria andMemberAreaLessThanOrEqualTo(String value) {
            addCriterion("member_area <=", value, "memberArea");
            return (Criteria) this;
        }

        public Criteria andMemberAreaLike(String value) {
            addCriterion("member_area like", value, "memberArea");
            return (Criteria) this;
        }

        public Criteria andMemberAreaNotLike(String value) {
            addCriterion("member_area not like", value, "memberArea");
            return (Criteria) this;
        }

        public Criteria andMemberAreaIn(List<String> values) {
            addCriterion("member_area in", values, "memberArea");
            return (Criteria) this;
        }

        public Criteria andMemberAreaNotIn(List<String> values) {
            addCriterion("member_area not in", values, "memberArea");
            return (Criteria) this;
        }

        public Criteria andMemberAreaBetween(String value1, String value2) {
            addCriterion("member_area between", value1, value2, "memberArea");
            return (Criteria) this;
        }

        public Criteria andMemberAreaNotBetween(String value1, String value2) {
            addCriterion("member_area not between", value1, value2, "memberArea");
            return (Criteria) this;
        }

        public Criteria andMemberAddressIsNull() {
            addCriterion("member_address is null");
            return (Criteria) this;
        }

        public Criteria andMemberAddressIsNotNull() {
            addCriterion("member_address is not null");
            return (Criteria) this;
        }

        public Criteria andMemberAddressEqualTo(String value) {
            addCriterion("member_address =", value, "memberAddress");
            return (Criteria) this;
        }

        public Criteria andMemberAddressNotEqualTo(String value) {
            addCriterion("member_address <>", value, "memberAddress");
            return (Criteria) this;
        }

        public Criteria andMemberAddressGreaterThan(String value) {
            addCriterion("member_address >", value, "memberAddress");
            return (Criteria) this;
        }

        public Criteria andMemberAddressGreaterThanOrEqualTo(String value) {
            addCriterion("member_address >=", value, "memberAddress");
            return (Criteria) this;
        }

        public Criteria andMemberAddressLessThan(String value) {
            addCriterion("member_address <", value, "memberAddress");
            return (Criteria) this;
        }

        public Criteria andMemberAddressLessThanOrEqualTo(String value) {
            addCriterion("member_address <=", value, "memberAddress");
            return (Criteria) this;
        }

        public Criteria andMemberAddressLike(String value) {
            addCriterion("member_address like", value, "memberAddress");
            return (Criteria) this;
        }

        public Criteria andMemberAddressNotLike(String value) {
            addCriterion("member_address not like", value, "memberAddress");
            return (Criteria) this;
        }

        public Criteria andMemberAddressIn(List<String> values) {
            addCriterion("member_address in", values, "memberAddress");
            return (Criteria) this;
        }

        public Criteria andMemberAddressNotIn(List<String> values) {
            addCriterion("member_address not in", values, "memberAddress");
            return (Criteria) this;
        }

        public Criteria andMemberAddressBetween(String value1, String value2) {
            addCriterion("member_address between", value1, value2, "memberAddress");
            return (Criteria) this;
        }

        public Criteria andMemberAddressNotBetween(String value1, String value2) {
            addCriterion("member_address not between", value1, value2, "memberAddress");
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