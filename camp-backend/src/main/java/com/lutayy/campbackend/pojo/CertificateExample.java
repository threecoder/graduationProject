package com.lutayy.campbackend.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CertificateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CertificateExample() {
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

        public Criteria andTrainingIdIsNull() {
            addCriterion("training_id is null");
            return (Criteria) this;
        }

        public Criteria andTrainingIdIsNotNull() {
            addCriterion("training_id is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingIdEqualTo(Integer value) {
            addCriterion("training_id =", value, "trainingId");
            return (Criteria) this;
        }

        public Criteria andTrainingIdNotEqualTo(Integer value) {
            addCriterion("training_id <>", value, "trainingId");
            return (Criteria) this;
        }

        public Criteria andTrainingIdGreaterThan(Integer value) {
            addCriterion("training_id >", value, "trainingId");
            return (Criteria) this;
        }

        public Criteria andTrainingIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("training_id >=", value, "trainingId");
            return (Criteria) this;
        }

        public Criteria andTrainingIdLessThan(Integer value) {
            addCriterion("training_id <", value, "trainingId");
            return (Criteria) this;
        }

        public Criteria andTrainingIdLessThanOrEqualTo(Integer value) {
            addCriterion("training_id <=", value, "trainingId");
            return (Criteria) this;
        }

        public Criteria andTrainingIdIn(List<Integer> values) {
            addCriterion("training_id in", values, "trainingId");
            return (Criteria) this;
        }

        public Criteria andTrainingIdNotIn(List<Integer> values) {
            addCriterion("training_id not in", values, "trainingId");
            return (Criteria) this;
        }

        public Criteria andTrainingIdBetween(Integer value1, Integer value2) {
            addCriterion("training_id between", value1, value2, "trainingId");
            return (Criteria) this;
        }

        public Criteria andTrainingIdNotBetween(Integer value1, Integer value2) {
            addCriterion("training_id not between", value1, value2, "trainingId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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

        public Criteria andCertificatePathIsNull() {
            addCriterion("certificate_path is null");
            return (Criteria) this;
        }

        public Criteria andCertificatePathIsNotNull() {
            addCriterion("certificate_path is not null");
            return (Criteria) this;
        }

        public Criteria andCertificatePathEqualTo(String value) {
            addCriterion("certificate_path =", value, "certificatePath");
            return (Criteria) this;
        }

        public Criteria andCertificatePathNotEqualTo(String value) {
            addCriterion("certificate_path <>", value, "certificatePath");
            return (Criteria) this;
        }

        public Criteria andCertificatePathGreaterThan(String value) {
            addCriterion("certificate_path >", value, "certificatePath");
            return (Criteria) this;
        }

        public Criteria andCertificatePathGreaterThanOrEqualTo(String value) {
            addCriterion("certificate_path >=", value, "certificatePath");
            return (Criteria) this;
        }

        public Criteria andCertificatePathLessThan(String value) {
            addCriterion("certificate_path <", value, "certificatePath");
            return (Criteria) this;
        }

        public Criteria andCertificatePathLessThanOrEqualTo(String value) {
            addCriterion("certificate_path <=", value, "certificatePath");
            return (Criteria) this;
        }

        public Criteria andCertificatePathLike(String value) {
            addCriterion("certificate_path like", value, "certificatePath");
            return (Criteria) this;
        }

        public Criteria andCertificatePathNotLike(String value) {
            addCriterion("certificate_path not like", value, "certificatePath");
            return (Criteria) this;
        }

        public Criteria andCertificatePathIn(List<String> values) {
            addCriterion("certificate_path in", values, "certificatePath");
            return (Criteria) this;
        }

        public Criteria andCertificatePathNotIn(List<String> values) {
            addCriterion("certificate_path not in", values, "certificatePath");
            return (Criteria) this;
        }

        public Criteria andCertificatePathBetween(String value1, String value2) {
            addCriterion("certificate_path between", value1, value2, "certificatePath");
            return (Criteria) this;
        }

        public Criteria andCertificatePathNotBetween(String value1, String value2) {
            addCriterion("certificate_path not between", value1, value2, "certificatePath");
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