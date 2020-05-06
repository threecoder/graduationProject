package com.lutayy.campbackend.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CertificateChangeLogExample {
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

    public CertificateChangeLogExample() {
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

        public Criteria andLogIdIsNull() {
            addCriterion("log_id is null");
            return (Criteria) this;
        }

        public Criteria andLogIdIsNotNull() {
            addCriterion("log_id is not null");
            return (Criteria) this;
        }

        public Criteria andLogIdEqualTo(Integer value) {
            addCriterion("log_id =", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotEqualTo(Integer value) {
            addCriterion("log_id <>", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThan(Integer value) {
            addCriterion("log_id >", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("log_id >=", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThan(Integer value) {
            addCriterion("log_id <", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThanOrEqualTo(Integer value) {
            addCriterion("log_id <=", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdIn(List<Integer> values) {
            addCriterion("log_id in", values, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotIn(List<Integer> values) {
            addCriterion("log_id not in", values, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdBetween(Integer value1, Integer value2) {
            addCriterion("log_id between", value1, value2, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotBetween(Integer value1, Integer value2) {
            addCriterion("log_id not between", value1, value2, "logId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdIsNull() {
            addCriterion("certificate_id is null");
            return (Criteria) this;
        }

        public Criteria andCertificateIdIsNotNull() {
            addCriterion("certificate_id is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateIdEqualTo(Integer value) {
            addCriterion("certificate_id =", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdNotEqualTo(Integer value) {
            addCriterion("certificate_id <>", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdGreaterThan(Integer value) {
            addCriterion("certificate_id >", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("certificate_id >=", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdLessThan(Integer value) {
            addCriterion("certificate_id <", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdLessThanOrEqualTo(Integer value) {
            addCriterion("certificate_id <=", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdIn(List<Integer> values) {
            addCriterion("certificate_id in", values, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdNotIn(List<Integer> values) {
            addCriterion("certificate_id not in", values, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdBetween(Integer value1, Integer value2) {
            addCriterion("certificate_id between", value1, value2, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("certificate_id not between", value1, value2, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateNameIsNull() {
            addCriterion("certificate_name is null");
            return (Criteria) this;
        }

        public Criteria andCertificateNameIsNotNull() {
            addCriterion("certificate_name is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateNameEqualTo(String value) {
            addCriterion("certificate_name =", value, "certificateName");
            return (Criteria) this;
        }

        public Criteria andCertificateNameNotEqualTo(String value) {
            addCriterion("certificate_name <>", value, "certificateName");
            return (Criteria) this;
        }

        public Criteria andCertificateNameGreaterThan(String value) {
            addCriterion("certificate_name >", value, "certificateName");
            return (Criteria) this;
        }

        public Criteria andCertificateNameGreaterThanOrEqualTo(String value) {
            addCriterion("certificate_name >=", value, "certificateName");
            return (Criteria) this;
        }

        public Criteria andCertificateNameLessThan(String value) {
            addCriterion("certificate_name <", value, "certificateName");
            return (Criteria) this;
        }

        public Criteria andCertificateNameLessThanOrEqualTo(String value) {
            addCriterion("certificate_name <=", value, "certificateName");
            return (Criteria) this;
        }

        public Criteria andCertificateNameLike(String value) {
            addCriterion("certificate_name like", value, "certificateName");
            return (Criteria) this;
        }

        public Criteria andCertificateNameNotLike(String value) {
            addCriterion("certificate_name not like", value, "certificateName");
            return (Criteria) this;
        }

        public Criteria andCertificateNameIn(List<String> values) {
            addCriterion("certificate_name in", values, "certificateName");
            return (Criteria) this;
        }

        public Criteria andCertificateNameNotIn(List<String> values) {
            addCriterion("certificate_name not in", values, "certificateName");
            return (Criteria) this;
        }

        public Criteria andCertificateNameBetween(String value1, String value2) {
            addCriterion("certificate_name between", value1, value2, "certificateName");
            return (Criteria) this;
        }

        public Criteria andCertificateNameNotBetween(String value1, String value2) {
            addCriterion("certificate_name not between", value1, value2, "certificateName");
            return (Criteria) this;
        }

        public Criteria andCerStudentIdIsNull() {
            addCriterion("cer_student_id is null");
            return (Criteria) this;
        }

        public Criteria andCerStudentIdIsNotNull() {
            addCriterion("cer_student_id is not null");
            return (Criteria) this;
        }

        public Criteria andCerStudentIdEqualTo(Integer value) {
            addCriterion("cer_student_id =", value, "cerStudentId");
            return (Criteria) this;
        }

        public Criteria andCerStudentIdNotEqualTo(Integer value) {
            addCriterion("cer_student_id <>", value, "cerStudentId");
            return (Criteria) this;
        }

        public Criteria andCerStudentIdGreaterThan(Integer value) {
            addCriterion("cer_student_id >", value, "cerStudentId");
            return (Criteria) this;
        }

        public Criteria andCerStudentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cer_student_id >=", value, "cerStudentId");
            return (Criteria) this;
        }

        public Criteria andCerStudentIdLessThan(Integer value) {
            addCriterion("cer_student_id <", value, "cerStudentId");
            return (Criteria) this;
        }

        public Criteria andCerStudentIdLessThanOrEqualTo(Integer value) {
            addCriterion("cer_student_id <=", value, "cerStudentId");
            return (Criteria) this;
        }

        public Criteria andCerStudentIdIn(List<Integer> values) {
            addCriterion("cer_student_id in", values, "cerStudentId");
            return (Criteria) this;
        }

        public Criteria andCerStudentIdNotIn(List<Integer> values) {
            addCriterion("cer_student_id not in", values, "cerStudentId");
            return (Criteria) this;
        }

        public Criteria andCerStudentIdBetween(Integer value1, Integer value2) {
            addCriterion("cer_student_id between", value1, value2, "cerStudentId");
            return (Criteria) this;
        }

        public Criteria andCerStudentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cer_student_id not between", value1, value2, "cerStudentId");
            return (Criteria) this;
        }

        public Criteria andOpDescriptionIsNull() {
            addCriterion("op_description is null");
            return (Criteria) this;
        }

        public Criteria andOpDescriptionIsNotNull() {
            addCriterion("op_description is not null");
            return (Criteria) this;
        }

        public Criteria andOpDescriptionEqualTo(String value) {
            addCriterion("op_description =", value, "opDescription");
            return (Criteria) this;
        }

        public Criteria andOpDescriptionNotEqualTo(String value) {
            addCriterion("op_description <>", value, "opDescription");
            return (Criteria) this;
        }

        public Criteria andOpDescriptionGreaterThan(String value) {
            addCriterion("op_description >", value, "opDescription");
            return (Criteria) this;
        }

        public Criteria andOpDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("op_description >=", value, "opDescription");
            return (Criteria) this;
        }

        public Criteria andOpDescriptionLessThan(String value) {
            addCriterion("op_description <", value, "opDescription");
            return (Criteria) this;
        }

        public Criteria andOpDescriptionLessThanOrEqualTo(String value) {
            addCriterion("op_description <=", value, "opDescription");
            return (Criteria) this;
        }

        public Criteria andOpDescriptionLike(String value) {
            addCriterion("op_description like", value, "opDescription");
            return (Criteria) this;
        }

        public Criteria andOpDescriptionNotLike(String value) {
            addCriterion("op_description not like", value, "opDescription");
            return (Criteria) this;
        }

        public Criteria andOpDescriptionIn(List<String> values) {
            addCriterion("op_description in", values, "opDescription");
            return (Criteria) this;
        }

        public Criteria andOpDescriptionNotIn(List<String> values) {
            addCriterion("op_description not in", values, "opDescription");
            return (Criteria) this;
        }

        public Criteria andOpDescriptionBetween(String value1, String value2) {
            addCriterion("op_description between", value1, value2, "opDescription");
            return (Criteria) this;
        }

        public Criteria andOpDescriptionNotBetween(String value1, String value2) {
            addCriterion("op_description not between", value1, value2, "opDescription");
            return (Criteria) this;
        }

        public Criteria andOpUserTypeIsNull() {
            addCriterion("op_user_type is null");
            return (Criteria) this;
        }

        public Criteria andOpUserTypeIsNotNull() {
            addCriterion("op_user_type is not null");
            return (Criteria) this;
        }

        public Criteria andOpUserTypeEqualTo(String value) {
            addCriterion("op_user_type =", value, "opUserType");
            return (Criteria) this;
        }

        public Criteria andOpUserTypeNotEqualTo(String value) {
            addCriterion("op_user_type <>", value, "opUserType");
            return (Criteria) this;
        }

        public Criteria andOpUserTypeGreaterThan(String value) {
            addCriterion("op_user_type >", value, "opUserType");
            return (Criteria) this;
        }

        public Criteria andOpUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("op_user_type >=", value, "opUserType");
            return (Criteria) this;
        }

        public Criteria andOpUserTypeLessThan(String value) {
            addCriterion("op_user_type <", value, "opUserType");
            return (Criteria) this;
        }

        public Criteria andOpUserTypeLessThanOrEqualTo(String value) {
            addCriterion("op_user_type <=", value, "opUserType");
            return (Criteria) this;
        }

        public Criteria andOpUserTypeLike(String value) {
            addCriterion("op_user_type like", value, "opUserType");
            return (Criteria) this;
        }

        public Criteria andOpUserTypeNotLike(String value) {
            addCriterion("op_user_type not like", value, "opUserType");
            return (Criteria) this;
        }

        public Criteria andOpUserTypeIn(List<String> values) {
            addCriterion("op_user_type in", values, "opUserType");
            return (Criteria) this;
        }

        public Criteria andOpUserTypeNotIn(List<String> values) {
            addCriterion("op_user_type not in", values, "opUserType");
            return (Criteria) this;
        }

        public Criteria andOpUserTypeBetween(String value1, String value2) {
            addCriterion("op_user_type between", value1, value2, "opUserType");
            return (Criteria) this;
        }

        public Criteria andOpUserTypeNotBetween(String value1, String value2) {
            addCriterion("op_user_type not between", value1, value2, "opUserType");
            return (Criteria) this;
        }

        public Criteria andOpUserIdIsNull() {
            addCriterion("op_user_id is null");
            return (Criteria) this;
        }

        public Criteria andOpUserIdIsNotNull() {
            addCriterion("op_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpUserIdEqualTo(Integer value) {
            addCriterion("op_user_id =", value, "opUserId");
            return (Criteria) this;
        }

        public Criteria andOpUserIdNotEqualTo(Integer value) {
            addCriterion("op_user_id <>", value, "opUserId");
            return (Criteria) this;
        }

        public Criteria andOpUserIdGreaterThan(Integer value) {
            addCriterion("op_user_id >", value, "opUserId");
            return (Criteria) this;
        }

        public Criteria andOpUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("op_user_id >=", value, "opUserId");
            return (Criteria) this;
        }

        public Criteria andOpUserIdLessThan(Integer value) {
            addCriterion("op_user_id <", value, "opUserId");
            return (Criteria) this;
        }

        public Criteria andOpUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("op_user_id <=", value, "opUserId");
            return (Criteria) this;
        }

        public Criteria andOpUserIdIn(List<Integer> values) {
            addCriterion("op_user_id in", values, "opUserId");
            return (Criteria) this;
        }

        public Criteria andOpUserIdNotIn(List<Integer> values) {
            addCriterion("op_user_id not in", values, "opUserId");
            return (Criteria) this;
        }

        public Criteria andOpUserIdBetween(Integer value1, Integer value2) {
            addCriterion("op_user_id between", value1, value2, "opUserId");
            return (Criteria) this;
        }

        public Criteria andOpUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("op_user_id not between", value1, value2, "opUserId");
            return (Criteria) this;
        }

        public Criteria andOpUserNameIsNull() {
            addCriterion("op_user_name is null");
            return (Criteria) this;
        }

        public Criteria andOpUserNameIsNotNull() {
            addCriterion("op_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andOpUserNameEqualTo(String value) {
            addCriterion("op_user_name =", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameNotEqualTo(String value) {
            addCriterion("op_user_name <>", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameGreaterThan(String value) {
            addCriterion("op_user_name >", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("op_user_name >=", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameLessThan(String value) {
            addCriterion("op_user_name <", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameLessThanOrEqualTo(String value) {
            addCriterion("op_user_name <=", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameLike(String value) {
            addCriterion("op_user_name like", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameNotLike(String value) {
            addCriterion("op_user_name not like", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameIn(List<String> values) {
            addCriterion("op_user_name in", values, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameNotIn(List<String> values) {
            addCriterion("op_user_name not in", values, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameBetween(String value1, String value2) {
            addCriterion("op_user_name between", value1, value2, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameNotBetween(String value1, String value2) {
            addCriterion("op_user_name not between", value1, value2, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpTimeIsNull() {
            addCriterion("op_time is null");
            return (Criteria) this;
        }

        public Criteria andOpTimeIsNotNull() {
            addCriterion("op_time is not null");
            return (Criteria) this;
        }

        public Criteria andOpTimeEqualTo(Date value) {
            addCriterion("op_time =", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeNotEqualTo(Date value) {
            addCriterion("op_time <>", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeGreaterThan(Date value) {
            addCriterion("op_time >", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("op_time >=", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeLessThan(Date value) {
            addCriterion("op_time <", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeLessThanOrEqualTo(Date value) {
            addCriterion("op_time <=", value, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeIn(List<Date> values) {
            addCriterion("op_time in", values, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeNotIn(List<Date> values) {
            addCriterion("op_time not in", values, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeBetween(Date value1, Date value2) {
            addCriterion("op_time between", value1, value2, "opTime");
            return (Criteria) this;
        }

        public Criteria andOpTimeNotBetween(Date value1, Date value2) {
            addCriterion("op_time not between", value1, value2, "opTime");
            return (Criteria) this;
        }

        public Criteria andIsSuccessIsNull() {
            addCriterion("is_success is null");
            return (Criteria) this;
        }

        public Criteria andIsSuccessIsNotNull() {
            addCriterion("is_success is not null");
            return (Criteria) this;
        }

        public Criteria andIsSuccessEqualTo(String value) {
            addCriterion("is_success =", value, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessNotEqualTo(String value) {
            addCriterion("is_success <>", value, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessGreaterThan(String value) {
            addCriterion("is_success >", value, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessGreaterThanOrEqualTo(String value) {
            addCriterion("is_success >=", value, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessLessThan(String value) {
            addCriterion("is_success <", value, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessLessThanOrEqualTo(String value) {
            addCriterion("is_success <=", value, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessLike(String value) {
            addCriterion("is_success like", value, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessNotLike(String value) {
            addCriterion("is_success not like", value, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessIn(List<String> values) {
            addCriterion("is_success in", values, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessNotIn(List<String> values) {
            addCriterion("is_success not in", values, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessBetween(String value1, String value2) {
            addCriterion("is_success between", value1, value2, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andIsSuccessNotBetween(String value1, String value2) {
            addCriterion("is_success not between", value1, value2, "isSuccess");
            return (Criteria) this;
        }

        public Criteria andNewMemberNameIsNull() {
            addCriterion("new_member_name is null");
            return (Criteria) this;
        }

        public Criteria andNewMemberNameIsNotNull() {
            addCriterion("new_member_name is not null");
            return (Criteria) this;
        }

        public Criteria andNewMemberNameEqualTo(String value) {
            addCriterion("new_member_name =", value, "newMemberName");
            return (Criteria) this;
        }

        public Criteria andNewMemberNameNotEqualTo(String value) {
            addCriterion("new_member_name <>", value, "newMemberName");
            return (Criteria) this;
        }

        public Criteria andNewMemberNameGreaterThan(String value) {
            addCriterion("new_member_name >", value, "newMemberName");
            return (Criteria) this;
        }

        public Criteria andNewMemberNameGreaterThanOrEqualTo(String value) {
            addCriterion("new_member_name >=", value, "newMemberName");
            return (Criteria) this;
        }

        public Criteria andNewMemberNameLessThan(String value) {
            addCriterion("new_member_name <", value, "newMemberName");
            return (Criteria) this;
        }

        public Criteria andNewMemberNameLessThanOrEqualTo(String value) {
            addCriterion("new_member_name <=", value, "newMemberName");
            return (Criteria) this;
        }

        public Criteria andNewMemberNameLike(String value) {
            addCriterion("new_member_name like", value, "newMemberName");
            return (Criteria) this;
        }

        public Criteria andNewMemberNameNotLike(String value) {
            addCriterion("new_member_name not like", value, "newMemberName");
            return (Criteria) this;
        }

        public Criteria andNewMemberNameIn(List<String> values) {
            addCriterion("new_member_name in", values, "newMemberName");
            return (Criteria) this;
        }

        public Criteria andNewMemberNameNotIn(List<String> values) {
            addCriterion("new_member_name not in", values, "newMemberName");
            return (Criteria) this;
        }

        public Criteria andNewMemberNameBetween(String value1, String value2) {
            addCriterion("new_member_name between", value1, value2, "newMemberName");
            return (Criteria) this;
        }

        public Criteria andNewMemberNameNotBetween(String value1, String value2) {
            addCriterion("new_member_name not between", value1, value2, "newMemberName");
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