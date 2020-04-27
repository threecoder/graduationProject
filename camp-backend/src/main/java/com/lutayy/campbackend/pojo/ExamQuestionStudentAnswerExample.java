package com.lutayy.campbackend.pojo;

import java.util.ArrayList;
import java.util.List;

public class ExamQuestionStudentAnswerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamQuestionStudentAnswerExample() {
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

        public Criteria andQuestionIdIsNull() {
            addCriterion("question_id is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNotNull() {
            addCriterion("question_id is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdEqualTo(Integer value) {
            addCriterion("question_id =", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotEqualTo(Integer value) {
            addCriterion("question_id <>", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThan(Integer value) {
            addCriterion("question_id >", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("question_id >=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThan(Integer value) {
            addCriterion("question_id <", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThanOrEqualTo(Integer value) {
            addCriterion("question_id <=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIn(List<Integer> values) {
            addCriterion("question_id in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotIn(List<Integer> values) {
            addCriterion("question_id not in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdBetween(Integer value1, Integer value2) {
            addCriterion("question_id between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("question_id not between", value1, value2, "questionId");
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

        public Criteria andAnswerOneIsNull() {
            addCriterion("answer_one is null");
            return (Criteria) this;
        }

        public Criteria andAnswerOneIsNotNull() {
            addCriterion("answer_one is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerOneEqualTo(Byte value) {
            addCriterion("answer_one =", value, "answerOne");
            return (Criteria) this;
        }

        public Criteria andAnswerOneNotEqualTo(Byte value) {
            addCriterion("answer_one <>", value, "answerOne");
            return (Criteria) this;
        }

        public Criteria andAnswerOneGreaterThan(Byte value) {
            addCriterion("answer_one >", value, "answerOne");
            return (Criteria) this;
        }

        public Criteria andAnswerOneGreaterThanOrEqualTo(Byte value) {
            addCriterion("answer_one >=", value, "answerOne");
            return (Criteria) this;
        }

        public Criteria andAnswerOneLessThan(Byte value) {
            addCriterion("answer_one <", value, "answerOne");
            return (Criteria) this;
        }

        public Criteria andAnswerOneLessThanOrEqualTo(Byte value) {
            addCriterion("answer_one <=", value, "answerOne");
            return (Criteria) this;
        }

        public Criteria andAnswerOneIn(List<Byte> values) {
            addCriterion("answer_one in", values, "answerOne");
            return (Criteria) this;
        }

        public Criteria andAnswerOneNotIn(List<Byte> values) {
            addCriterion("answer_one not in", values, "answerOne");
            return (Criteria) this;
        }

        public Criteria andAnswerOneBetween(Byte value1, Byte value2) {
            addCriterion("answer_one between", value1, value2, "answerOne");
            return (Criteria) this;
        }

        public Criteria andAnswerOneNotBetween(Byte value1, Byte value2) {
            addCriterion("answer_one not between", value1, value2, "answerOne");
            return (Criteria) this;
        }

        public Criteria andAnswerTwoIsNull() {
            addCriterion("answer_two is null");
            return (Criteria) this;
        }

        public Criteria andAnswerTwoIsNotNull() {
            addCriterion("answer_two is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerTwoEqualTo(Byte value) {
            addCriterion("answer_two =", value, "answerTwo");
            return (Criteria) this;
        }

        public Criteria andAnswerTwoNotEqualTo(Byte value) {
            addCriterion("answer_two <>", value, "answerTwo");
            return (Criteria) this;
        }

        public Criteria andAnswerTwoGreaterThan(Byte value) {
            addCriterion("answer_two >", value, "answerTwo");
            return (Criteria) this;
        }

        public Criteria andAnswerTwoGreaterThanOrEqualTo(Byte value) {
            addCriterion("answer_two >=", value, "answerTwo");
            return (Criteria) this;
        }

        public Criteria andAnswerTwoLessThan(Byte value) {
            addCriterion("answer_two <", value, "answerTwo");
            return (Criteria) this;
        }

        public Criteria andAnswerTwoLessThanOrEqualTo(Byte value) {
            addCriterion("answer_two <=", value, "answerTwo");
            return (Criteria) this;
        }

        public Criteria andAnswerTwoIn(List<Byte> values) {
            addCriterion("answer_two in", values, "answerTwo");
            return (Criteria) this;
        }

        public Criteria andAnswerTwoNotIn(List<Byte> values) {
            addCriterion("answer_two not in", values, "answerTwo");
            return (Criteria) this;
        }

        public Criteria andAnswerTwoBetween(Byte value1, Byte value2) {
            addCriterion("answer_two between", value1, value2, "answerTwo");
            return (Criteria) this;
        }

        public Criteria andAnswerTwoNotBetween(Byte value1, Byte value2) {
            addCriterion("answer_two not between", value1, value2, "answerTwo");
            return (Criteria) this;
        }

        public Criteria andAnswerThreeIsNull() {
            addCriterion("answer_three is null");
            return (Criteria) this;
        }

        public Criteria andAnswerThreeIsNotNull() {
            addCriterion("answer_three is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerThreeEqualTo(Byte value) {
            addCriterion("answer_three =", value, "answerThree");
            return (Criteria) this;
        }

        public Criteria andAnswerThreeNotEqualTo(Byte value) {
            addCriterion("answer_three <>", value, "answerThree");
            return (Criteria) this;
        }

        public Criteria andAnswerThreeGreaterThan(Byte value) {
            addCriterion("answer_three >", value, "answerThree");
            return (Criteria) this;
        }

        public Criteria andAnswerThreeGreaterThanOrEqualTo(Byte value) {
            addCriterion("answer_three >=", value, "answerThree");
            return (Criteria) this;
        }

        public Criteria andAnswerThreeLessThan(Byte value) {
            addCriterion("answer_three <", value, "answerThree");
            return (Criteria) this;
        }

        public Criteria andAnswerThreeLessThanOrEqualTo(Byte value) {
            addCriterion("answer_three <=", value, "answerThree");
            return (Criteria) this;
        }

        public Criteria andAnswerThreeIn(List<Byte> values) {
            addCriterion("answer_three in", values, "answerThree");
            return (Criteria) this;
        }

        public Criteria andAnswerThreeNotIn(List<Byte> values) {
            addCriterion("answer_three not in", values, "answerThree");
            return (Criteria) this;
        }

        public Criteria andAnswerThreeBetween(Byte value1, Byte value2) {
            addCriterion("answer_three between", value1, value2, "answerThree");
            return (Criteria) this;
        }

        public Criteria andAnswerThreeNotBetween(Byte value1, Byte value2) {
            addCriterion("answer_three not between", value1, value2, "answerThree");
            return (Criteria) this;
        }

        public Criteria andAnswerFourIsNull() {
            addCriterion("answer_four is null");
            return (Criteria) this;
        }

        public Criteria andAnswerFourIsNotNull() {
            addCriterion("answer_four is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerFourEqualTo(Byte value) {
            addCriterion("answer_four =", value, "answerFour");
            return (Criteria) this;
        }

        public Criteria andAnswerFourNotEqualTo(Byte value) {
            addCriterion("answer_four <>", value, "answerFour");
            return (Criteria) this;
        }

        public Criteria andAnswerFourGreaterThan(Byte value) {
            addCriterion("answer_four >", value, "answerFour");
            return (Criteria) this;
        }

        public Criteria andAnswerFourGreaterThanOrEqualTo(Byte value) {
            addCriterion("answer_four >=", value, "answerFour");
            return (Criteria) this;
        }

        public Criteria andAnswerFourLessThan(Byte value) {
            addCriterion("answer_four <", value, "answerFour");
            return (Criteria) this;
        }

        public Criteria andAnswerFourLessThanOrEqualTo(Byte value) {
            addCriterion("answer_four <=", value, "answerFour");
            return (Criteria) this;
        }

        public Criteria andAnswerFourIn(List<Byte> values) {
            addCriterion("answer_four in", values, "answerFour");
            return (Criteria) this;
        }

        public Criteria andAnswerFourNotIn(List<Byte> values) {
            addCriterion("answer_four not in", values, "answerFour");
            return (Criteria) this;
        }

        public Criteria andAnswerFourBetween(Byte value1, Byte value2) {
            addCriterion("answer_four between", value1, value2, "answerFour");
            return (Criteria) this;
        }

        public Criteria andAnswerFourNotBetween(Byte value1, Byte value2) {
            addCriterion("answer_four not between", value1, value2, "answerFour");
            return (Criteria) this;
        }

        public Criteria andCorrectOrNotIsNull() {
            addCriterion("correct_or_not is null");
            return (Criteria) this;
        }

        public Criteria andCorrectOrNotIsNotNull() {
            addCriterion("correct_or_not is not null");
            return (Criteria) this;
        }

        public Criteria andCorrectOrNotEqualTo(Boolean value) {
            addCriterion("correct_or_not =", value, "correctOrNot");
            return (Criteria) this;
        }

        public Criteria andCorrectOrNotNotEqualTo(Boolean value) {
            addCriterion("correct_or_not <>", value, "correctOrNot");
            return (Criteria) this;
        }

        public Criteria andCorrectOrNotGreaterThan(Boolean value) {
            addCriterion("correct_or_not >", value, "correctOrNot");
            return (Criteria) this;
        }

        public Criteria andCorrectOrNotGreaterThanOrEqualTo(Boolean value) {
            addCriterion("correct_or_not >=", value, "correctOrNot");
            return (Criteria) this;
        }

        public Criteria andCorrectOrNotLessThan(Boolean value) {
            addCriterion("correct_or_not <", value, "correctOrNot");
            return (Criteria) this;
        }

        public Criteria andCorrectOrNotLessThanOrEqualTo(Boolean value) {
            addCriterion("correct_or_not <=", value, "correctOrNot");
            return (Criteria) this;
        }

        public Criteria andCorrectOrNotIn(List<Boolean> values) {
            addCriterion("correct_or_not in", values, "correctOrNot");
            return (Criteria) this;
        }

        public Criteria andCorrectOrNotNotIn(List<Boolean> values) {
            addCriterion("correct_or_not not in", values, "correctOrNot");
            return (Criteria) this;
        }

        public Criteria andCorrectOrNotBetween(Boolean value1, Boolean value2) {
            addCriterion("correct_or_not between", value1, value2, "correctOrNot");
            return (Criteria) this;
        }

        public Criteria andCorrectOrNotNotBetween(Boolean value1, Boolean value2) {
            addCriterion("correct_or_not not between", value1, value2, "correctOrNot");
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