package com.lutayy.campbackend.pojo;

import java.util.ArrayList;
import java.util.List;

public class ExamReStudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamReStudentExample() {
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

        public Criteria andExamIdIsNull() {
            addCriterion("exam_id is null");
            return (Criteria) this;
        }

        public Criteria andExamIdIsNotNull() {
            addCriterion("exam_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamIdEqualTo(Integer value) {
            addCriterion("exam_id =", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotEqualTo(Integer value) {
            addCriterion("exam_id <>", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThan(Integer value) {
            addCriterion("exam_id >", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_id >=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThan(Integer value) {
            addCriterion("exam_id <", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThanOrEqualTo(Integer value) {
            addCriterion("exam_id <=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdIn(List<Integer> values) {
            addCriterion("exam_id in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotIn(List<Integer> values) {
            addCriterion("exam_id not in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdBetween(Integer value1, Integer value2) {
            addCriterion("exam_id between", value1, value2, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_id not between", value1, value2, "examId");
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

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andRemainingTimesIsNull() {
            addCriterion("remaining_times is null");
            return (Criteria) this;
        }

        public Criteria andRemainingTimesIsNotNull() {
            addCriterion("remaining_times is not null");
            return (Criteria) this;
        }

        public Criteria andRemainingTimesEqualTo(Byte value) {
            addCriterion("remaining_times =", value, "remainingTimes");
            return (Criteria) this;
        }

        public Criteria andRemainingTimesNotEqualTo(Byte value) {
            addCriterion("remaining_times <>", value, "remainingTimes");
            return (Criteria) this;
        }

        public Criteria andRemainingTimesGreaterThan(Byte value) {
            addCriterion("remaining_times >", value, "remainingTimes");
            return (Criteria) this;
        }

        public Criteria andRemainingTimesGreaterThanOrEqualTo(Byte value) {
            addCriterion("remaining_times >=", value, "remainingTimes");
            return (Criteria) this;
        }

        public Criteria andRemainingTimesLessThan(Byte value) {
            addCriterion("remaining_times <", value, "remainingTimes");
            return (Criteria) this;
        }

        public Criteria andRemainingTimesLessThanOrEqualTo(Byte value) {
            addCriterion("remaining_times <=", value, "remainingTimes");
            return (Criteria) this;
        }

        public Criteria andRemainingTimesIn(List<Byte> values) {
            addCriterion("remaining_times in", values, "remainingTimes");
            return (Criteria) this;
        }

        public Criteria andRemainingTimesNotIn(List<Byte> values) {
            addCriterion("remaining_times not in", values, "remainingTimes");
            return (Criteria) this;
        }

        public Criteria andRemainingTimesBetween(Byte value1, Byte value2) {
            addCriterion("remaining_times between", value1, value2, "remainingTimes");
            return (Criteria) this;
        }

        public Criteria andRemainingTimesNotBetween(Byte value1, Byte value2) {
            addCriterion("remaining_times not between", value1, value2, "remainingTimes");
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