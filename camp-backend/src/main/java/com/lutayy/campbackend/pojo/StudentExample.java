package com.lutayy.campbackend.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentExample() {
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

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(Integer value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(Integer value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(Integer value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(Integer value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(Integer value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<Integer> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<Integer> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(Integer value1, Integer value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentPasswordIsNull() {
            addCriterion("student_password is null");
            return (Criteria) this;
        }

        public Criteria andStudentPasswordIsNotNull() {
            addCriterion("student_password is not null");
            return (Criteria) this;
        }

        public Criteria andStudentPasswordEqualTo(String value) {
            addCriterion("student_password =", value, "studentPassword");
            return (Criteria) this;
        }

        public Criteria andStudentPasswordNotEqualTo(String value) {
            addCriterion("student_password <>", value, "studentPassword");
            return (Criteria) this;
        }

        public Criteria andStudentPasswordGreaterThan(String value) {
            addCriterion("student_password >", value, "studentPassword");
            return (Criteria) this;
        }

        public Criteria andStudentPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("student_password >=", value, "studentPassword");
            return (Criteria) this;
        }

        public Criteria andStudentPasswordLessThan(String value) {
            addCriterion("student_password <", value, "studentPassword");
            return (Criteria) this;
        }

        public Criteria andStudentPasswordLessThanOrEqualTo(String value) {
            addCriterion("student_password <=", value, "studentPassword");
            return (Criteria) this;
        }

        public Criteria andStudentPasswordLike(String value) {
            addCriterion("student_password like", value, "studentPassword");
            return (Criteria) this;
        }

        public Criteria andStudentPasswordNotLike(String value) {
            addCriterion("student_password not like", value, "studentPassword");
            return (Criteria) this;
        }

        public Criteria andStudentPasswordIn(List<String> values) {
            addCriterion("student_password in", values, "studentPassword");
            return (Criteria) this;
        }

        public Criteria andStudentPasswordNotIn(List<String> values) {
            addCriterion("student_password not in", values, "studentPassword");
            return (Criteria) this;
        }

        public Criteria andStudentPasswordBetween(String value1, String value2) {
            addCriterion("student_password between", value1, value2, "studentPassword");
            return (Criteria) this;
        }

        public Criteria andStudentPasswordNotBetween(String value1, String value2) {
            addCriterion("student_password not between", value1, value2, "studentPassword");
            return (Criteria) this;
        }

        public Criteria andStudentIdcardIsNull() {
            addCriterion("student_idcard is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdcardIsNotNull() {
            addCriterion("student_idcard is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdcardEqualTo(String value) {
            addCriterion("student_idcard =", value, "studentIdcard");
            return (Criteria) this;
        }

        public Criteria andStudentIdcardNotEqualTo(String value) {
            addCriterion("student_idcard <>", value, "studentIdcard");
            return (Criteria) this;
        }

        public Criteria andStudentIdcardGreaterThan(String value) {
            addCriterion("student_idcard >", value, "studentIdcard");
            return (Criteria) this;
        }

        public Criteria andStudentIdcardGreaterThanOrEqualTo(String value) {
            addCriterion("student_idcard >=", value, "studentIdcard");
            return (Criteria) this;
        }

        public Criteria andStudentIdcardLessThan(String value) {
            addCriterion("student_idcard <", value, "studentIdcard");
            return (Criteria) this;
        }

        public Criteria andStudentIdcardLessThanOrEqualTo(String value) {
            addCriterion("student_idcard <=", value, "studentIdcard");
            return (Criteria) this;
        }

        public Criteria andStudentIdcardLike(String value) {
            addCriterion("student_idcard like", value, "studentIdcard");
            return (Criteria) this;
        }

        public Criteria andStudentIdcardNotLike(String value) {
            addCriterion("student_idcard not like", value, "studentIdcard");
            return (Criteria) this;
        }

        public Criteria andStudentIdcardIn(List<String> values) {
            addCriterion("student_idcard in", values, "studentIdcard");
            return (Criteria) this;
        }

        public Criteria andStudentIdcardNotIn(List<String> values) {
            addCriterion("student_idcard not in", values, "studentIdcard");
            return (Criteria) this;
        }

        public Criteria andStudentIdcardBetween(String value1, String value2) {
            addCriterion("student_idcard between", value1, value2, "studentIdcard");
            return (Criteria) this;
        }

        public Criteria andStudentIdcardNotBetween(String value1, String value2) {
            addCriterion("student_idcard not between", value1, value2, "studentIdcard");
            return (Criteria) this;
        }

        public Criteria andStudentPhoneIsNull() {
            addCriterion("student_phone is null");
            return (Criteria) this;
        }

        public Criteria andStudentPhoneIsNotNull() {
            addCriterion("student_phone is not null");
            return (Criteria) this;
        }

        public Criteria andStudentPhoneEqualTo(String value) {
            addCriterion("student_phone =", value, "studentPhone");
            return (Criteria) this;
        }

        public Criteria andStudentPhoneNotEqualTo(String value) {
            addCriterion("student_phone <>", value, "studentPhone");
            return (Criteria) this;
        }

        public Criteria andStudentPhoneGreaterThan(String value) {
            addCriterion("student_phone >", value, "studentPhone");
            return (Criteria) this;
        }

        public Criteria andStudentPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("student_phone >=", value, "studentPhone");
            return (Criteria) this;
        }

        public Criteria andStudentPhoneLessThan(String value) {
            addCriterion("student_phone <", value, "studentPhone");
            return (Criteria) this;
        }

        public Criteria andStudentPhoneLessThanOrEqualTo(String value) {
            addCriterion("student_phone <=", value, "studentPhone");
            return (Criteria) this;
        }

        public Criteria andStudentPhoneLike(String value) {
            addCriterion("student_phone like", value, "studentPhone");
            return (Criteria) this;
        }

        public Criteria andStudentPhoneNotLike(String value) {
            addCriterion("student_phone not like", value, "studentPhone");
            return (Criteria) this;
        }

        public Criteria andStudentPhoneIn(List<String> values) {
            addCriterion("student_phone in", values, "studentPhone");
            return (Criteria) this;
        }

        public Criteria andStudentPhoneNotIn(List<String> values) {
            addCriterion("student_phone not in", values, "studentPhone");
            return (Criteria) this;
        }

        public Criteria andStudentPhoneBetween(String value1, String value2) {
            addCriterion("student_phone between", value1, value2, "studentPhone");
            return (Criteria) this;
        }

        public Criteria andStudentPhoneNotBetween(String value1, String value2) {
            addCriterion("student_phone not between", value1, value2, "studentPhone");
            return (Criteria) this;
        }

        public Criteria andStudentNameIsNull() {
            addCriterion("student_name is null");
            return (Criteria) this;
        }

        public Criteria andStudentNameIsNotNull() {
            addCriterion("student_name is not null");
            return (Criteria) this;
        }

        public Criteria andStudentNameEqualTo(String value) {
            addCriterion("student_name =", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotEqualTo(String value) {
            addCriterion("student_name <>", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameGreaterThan(String value) {
            addCriterion("student_name >", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameGreaterThanOrEqualTo(String value) {
            addCriterion("student_name >=", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLessThan(String value) {
            addCriterion("student_name <", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLessThanOrEqualTo(String value) {
            addCriterion("student_name <=", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameLike(String value) {
            addCriterion("student_name like", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotLike(String value) {
            addCriterion("student_name not like", value, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameIn(List<String> values) {
            addCriterion("student_name in", values, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotIn(List<String> values) {
            addCriterion("student_name not in", values, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameBetween(String value1, String value2) {
            addCriterion("student_name between", value1, value2, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentNameNotBetween(String value1, String value2) {
            addCriterion("student_name not between", value1, value2, "studentName");
            return (Criteria) this;
        }

        public Criteria andStudentEmailIsNull() {
            addCriterion("student_email is null");
            return (Criteria) this;
        }

        public Criteria andStudentEmailIsNotNull() {
            addCriterion("student_email is not null");
            return (Criteria) this;
        }

        public Criteria andStudentEmailEqualTo(String value) {
            addCriterion("student_email =", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailNotEqualTo(String value) {
            addCriterion("student_email <>", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailGreaterThan(String value) {
            addCriterion("student_email >", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailGreaterThanOrEqualTo(String value) {
            addCriterion("student_email >=", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailLessThan(String value) {
            addCriterion("student_email <", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailLessThanOrEqualTo(String value) {
            addCriterion("student_email <=", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailLike(String value) {
            addCriterion("student_email like", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailNotLike(String value) {
            addCriterion("student_email not like", value, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailIn(List<String> values) {
            addCriterion("student_email in", values, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailNotIn(List<String> values) {
            addCriterion("student_email not in", values, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailBetween(String value1, String value2) {
            addCriterion("student_email between", value1, value2, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentEmailNotBetween(String value1, String value2) {
            addCriterion("student_email not between", value1, value2, "studentEmail");
            return (Criteria) this;
        }

        public Criteria andStudentPositonIsNull() {
            addCriterion("student_positon is null");
            return (Criteria) this;
        }

        public Criteria andStudentPositonIsNotNull() {
            addCriterion("student_positon is not null");
            return (Criteria) this;
        }

        public Criteria andStudentPositonEqualTo(String value) {
            addCriterion("student_positon =", value, "studentPositon");
            return (Criteria) this;
        }

        public Criteria andStudentPositonNotEqualTo(String value) {
            addCriterion("student_positon <>", value, "studentPositon");
            return (Criteria) this;
        }

        public Criteria andStudentPositonGreaterThan(String value) {
            addCriterion("student_positon >", value, "studentPositon");
            return (Criteria) this;
        }

        public Criteria andStudentPositonGreaterThanOrEqualTo(String value) {
            addCriterion("student_positon >=", value, "studentPositon");
            return (Criteria) this;
        }

        public Criteria andStudentPositonLessThan(String value) {
            addCriterion("student_positon <", value, "studentPositon");
            return (Criteria) this;
        }

        public Criteria andStudentPositonLessThanOrEqualTo(String value) {
            addCriterion("student_positon <=", value, "studentPositon");
            return (Criteria) this;
        }

        public Criteria andStudentPositonLike(String value) {
            addCriterion("student_positon like", value, "studentPositon");
            return (Criteria) this;
        }

        public Criteria andStudentPositonNotLike(String value) {
            addCriterion("student_positon not like", value, "studentPositon");
            return (Criteria) this;
        }

        public Criteria andStudentPositonIn(List<String> values) {
            addCriterion("student_positon in", values, "studentPositon");
            return (Criteria) this;
        }

        public Criteria andStudentPositonNotIn(List<String> values) {
            addCriterion("student_positon not in", values, "studentPositon");
            return (Criteria) this;
        }

        public Criteria andStudentPositonBetween(String value1, String value2) {
            addCriterion("student_positon between", value1, value2, "studentPositon");
            return (Criteria) this;
        }

        public Criteria andStudentPositonNotBetween(String value1, String value2) {
            addCriterion("student_positon not between", value1, value2, "studentPositon");
            return (Criteria) this;
        }

        public Criteria andStudentCountryIsNull() {
            addCriterion("student_country is null");
            return (Criteria) this;
        }

        public Criteria andStudentCountryIsNotNull() {
            addCriterion("student_country is not null");
            return (Criteria) this;
        }

        public Criteria andStudentCountryEqualTo(String value) {
            addCriterion("student_country =", value, "studentCountry");
            return (Criteria) this;
        }

        public Criteria andStudentCountryNotEqualTo(String value) {
            addCriterion("student_country <>", value, "studentCountry");
            return (Criteria) this;
        }

        public Criteria andStudentCountryGreaterThan(String value) {
            addCriterion("student_country >", value, "studentCountry");
            return (Criteria) this;
        }

        public Criteria andStudentCountryGreaterThanOrEqualTo(String value) {
            addCriterion("student_country >=", value, "studentCountry");
            return (Criteria) this;
        }

        public Criteria andStudentCountryLessThan(String value) {
            addCriterion("student_country <", value, "studentCountry");
            return (Criteria) this;
        }

        public Criteria andStudentCountryLessThanOrEqualTo(String value) {
            addCriterion("student_country <=", value, "studentCountry");
            return (Criteria) this;
        }

        public Criteria andStudentCountryLike(String value) {
            addCriterion("student_country like", value, "studentCountry");
            return (Criteria) this;
        }

        public Criteria andStudentCountryNotLike(String value) {
            addCriterion("student_country not like", value, "studentCountry");
            return (Criteria) this;
        }

        public Criteria andStudentCountryIn(List<String> values) {
            addCriterion("student_country in", values, "studentCountry");
            return (Criteria) this;
        }

        public Criteria andStudentCountryNotIn(List<String> values) {
            addCriterion("student_country not in", values, "studentCountry");
            return (Criteria) this;
        }

        public Criteria andStudentCountryBetween(String value1, String value2) {
            addCriterion("student_country between", value1, value2, "studentCountry");
            return (Criteria) this;
        }

        public Criteria andStudentCountryNotBetween(String value1, String value2) {
            addCriterion("student_country not between", value1, value2, "studentCountry");
            return (Criteria) this;
        }

        public Criteria andStudentProvinceIsNull() {
            addCriterion("student_province is null");
            return (Criteria) this;
        }

        public Criteria andStudentProvinceIsNotNull() {
            addCriterion("student_province is not null");
            return (Criteria) this;
        }

        public Criteria andStudentProvinceEqualTo(String value) {
            addCriterion("student_province =", value, "studentProvince");
            return (Criteria) this;
        }

        public Criteria andStudentProvinceNotEqualTo(String value) {
            addCriterion("student_province <>", value, "studentProvince");
            return (Criteria) this;
        }

        public Criteria andStudentProvinceGreaterThan(String value) {
            addCriterion("student_province >", value, "studentProvince");
            return (Criteria) this;
        }

        public Criteria andStudentProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("student_province >=", value, "studentProvince");
            return (Criteria) this;
        }

        public Criteria andStudentProvinceLessThan(String value) {
            addCriterion("student_province <", value, "studentProvince");
            return (Criteria) this;
        }

        public Criteria andStudentProvinceLessThanOrEqualTo(String value) {
            addCriterion("student_province <=", value, "studentProvince");
            return (Criteria) this;
        }

        public Criteria andStudentProvinceLike(String value) {
            addCriterion("student_province like", value, "studentProvince");
            return (Criteria) this;
        }

        public Criteria andStudentProvinceNotLike(String value) {
            addCriterion("student_province not like", value, "studentProvince");
            return (Criteria) this;
        }

        public Criteria andStudentProvinceIn(List<String> values) {
            addCriterion("student_province in", values, "studentProvince");
            return (Criteria) this;
        }

        public Criteria andStudentProvinceNotIn(List<String> values) {
            addCriterion("student_province not in", values, "studentProvince");
            return (Criteria) this;
        }

        public Criteria andStudentProvinceBetween(String value1, String value2) {
            addCriterion("student_province between", value1, value2, "studentProvince");
            return (Criteria) this;
        }

        public Criteria andStudentProvinceNotBetween(String value1, String value2) {
            addCriterion("student_province not between", value1, value2, "studentProvince");
            return (Criteria) this;
        }

        public Criteria andStudentCityIsNull() {
            addCriterion("student_city is null");
            return (Criteria) this;
        }

        public Criteria andStudentCityIsNotNull() {
            addCriterion("student_city is not null");
            return (Criteria) this;
        }

        public Criteria andStudentCityEqualTo(String value) {
            addCriterion("student_city =", value, "studentCity");
            return (Criteria) this;
        }

        public Criteria andStudentCityNotEqualTo(String value) {
            addCriterion("student_city <>", value, "studentCity");
            return (Criteria) this;
        }

        public Criteria andStudentCityGreaterThan(String value) {
            addCriterion("student_city >", value, "studentCity");
            return (Criteria) this;
        }

        public Criteria andStudentCityGreaterThanOrEqualTo(String value) {
            addCriterion("student_city >=", value, "studentCity");
            return (Criteria) this;
        }

        public Criteria andStudentCityLessThan(String value) {
            addCriterion("student_city <", value, "studentCity");
            return (Criteria) this;
        }

        public Criteria andStudentCityLessThanOrEqualTo(String value) {
            addCriterion("student_city <=", value, "studentCity");
            return (Criteria) this;
        }

        public Criteria andStudentCityLike(String value) {
            addCriterion("student_city like", value, "studentCity");
            return (Criteria) this;
        }

        public Criteria andStudentCityNotLike(String value) {
            addCriterion("student_city not like", value, "studentCity");
            return (Criteria) this;
        }

        public Criteria andStudentCityIn(List<String> values) {
            addCriterion("student_city in", values, "studentCity");
            return (Criteria) this;
        }

        public Criteria andStudentCityNotIn(List<String> values) {
            addCriterion("student_city not in", values, "studentCity");
            return (Criteria) this;
        }

        public Criteria andStudentCityBetween(String value1, String value2) {
            addCriterion("student_city between", value1, value2, "studentCity");
            return (Criteria) this;
        }

        public Criteria andStudentCityNotBetween(String value1, String value2) {
            addCriterion("student_city not between", value1, value2, "studentCity");
            return (Criteria) this;
        }

        public Criteria andStudentAreaIsNull() {
            addCriterion("student_area is null");
            return (Criteria) this;
        }

        public Criteria andStudentAreaIsNotNull() {
            addCriterion("student_area is not null");
            return (Criteria) this;
        }

        public Criteria andStudentAreaEqualTo(String value) {
            addCriterion("student_area =", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaNotEqualTo(String value) {
            addCriterion("student_area <>", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaGreaterThan(String value) {
            addCriterion("student_area >", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaGreaterThanOrEqualTo(String value) {
            addCriterion("student_area >=", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaLessThan(String value) {
            addCriterion("student_area <", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaLessThanOrEqualTo(String value) {
            addCriterion("student_area <=", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaLike(String value) {
            addCriterion("student_area like", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaNotLike(String value) {
            addCriterion("student_area not like", value, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaIn(List<String> values) {
            addCriterion("student_area in", values, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaNotIn(List<String> values) {
            addCriterion("student_area not in", values, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaBetween(String value1, String value2) {
            addCriterion("student_area between", value1, value2, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAreaNotBetween(String value1, String value2) {
            addCriterion("student_area not between", value1, value2, "studentArea");
            return (Criteria) this;
        }

        public Criteria andStudentAddressIsNull() {
            addCriterion("student_address is null");
            return (Criteria) this;
        }

        public Criteria andStudentAddressIsNotNull() {
            addCriterion("student_address is not null");
            return (Criteria) this;
        }

        public Criteria andStudentAddressEqualTo(String value) {
            addCriterion("student_address =", value, "studentAddress");
            return (Criteria) this;
        }

        public Criteria andStudentAddressNotEqualTo(String value) {
            addCriterion("student_address <>", value, "studentAddress");
            return (Criteria) this;
        }

        public Criteria andStudentAddressGreaterThan(String value) {
            addCriterion("student_address >", value, "studentAddress");
            return (Criteria) this;
        }

        public Criteria andStudentAddressGreaterThanOrEqualTo(String value) {
            addCriterion("student_address >=", value, "studentAddress");
            return (Criteria) this;
        }

        public Criteria andStudentAddressLessThan(String value) {
            addCriterion("student_address <", value, "studentAddress");
            return (Criteria) this;
        }

        public Criteria andStudentAddressLessThanOrEqualTo(String value) {
            addCriterion("student_address <=", value, "studentAddress");
            return (Criteria) this;
        }

        public Criteria andStudentAddressLike(String value) {
            addCriterion("student_address like", value, "studentAddress");
            return (Criteria) this;
        }

        public Criteria andStudentAddressNotLike(String value) {
            addCriterion("student_address not like", value, "studentAddress");
            return (Criteria) this;
        }

        public Criteria andStudentAddressIn(List<String> values) {
            addCriterion("student_address in", values, "studentAddress");
            return (Criteria) this;
        }

        public Criteria andStudentAddressNotIn(List<String> values) {
            addCriterion("student_address not in", values, "studentAddress");
            return (Criteria) this;
        }

        public Criteria andStudentAddressBetween(String value1, String value2) {
            addCriterion("student_address between", value1, value2, "studentAddress");
            return (Criteria) this;
        }

        public Criteria andStudentAddressNotBetween(String value1, String value2) {
            addCriterion("student_address not between", value1, value2, "studentAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andEnterTimeIsNull() {
            addCriterion("enter_time is null");
            return (Criteria) this;
        }

        public Criteria andEnterTimeIsNotNull() {
            addCriterion("enter_time is not null");
            return (Criteria) this;
        }

        public Criteria andEnterTimeEqualTo(Date value) {
            addCriterion("enter_time =", value, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeNotEqualTo(Date value) {
            addCriterion("enter_time <>", value, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeGreaterThan(Date value) {
            addCriterion("enter_time >", value, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("enter_time >=", value, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeLessThan(Date value) {
            addCriterion("enter_time <", value, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeLessThanOrEqualTo(Date value) {
            addCriterion("enter_time <=", value, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeIn(List<Date> values) {
            addCriterion("enter_time in", values, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeNotIn(List<Date> values) {
            addCriterion("enter_time not in", values, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeBetween(Date value1, Date value2) {
            addCriterion("enter_time between", value1, value2, "enterTime");
            return (Criteria) this;
        }

        public Criteria andEnterTimeNotBetween(Date value1, Date value2) {
            addCriterion("enter_time not between", value1, value2, "enterTime");
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