package com.lutayy.campbackend.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamExample() {
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

        public Criteria andExamNameIsNull() {
            addCriterion("exam_name is null");
            return (Criteria) this;
        }

        public Criteria andExamNameIsNotNull() {
            addCriterion("exam_name is not null");
            return (Criteria) this;
        }

        public Criteria andExamNameEqualTo(String value) {
            addCriterion("exam_name =", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotEqualTo(String value) {
            addCriterion("exam_name <>", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameGreaterThan(String value) {
            addCriterion("exam_name >", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameGreaterThanOrEqualTo(String value) {
            addCriterion("exam_name >=", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLessThan(String value) {
            addCriterion("exam_name <", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLessThanOrEqualTo(String value) {
            addCriterion("exam_name <=", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLike(String value) {
            addCriterion("exam_name like", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotLike(String value) {
            addCriterion("exam_name not like", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameIn(List<String> values) {
            addCriterion("exam_name in", values, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotIn(List<String> values) {
            addCriterion("exam_name not in", values, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameBetween(String value1, String value2) {
            addCriterion("exam_name between", value1, value2, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotBetween(String value1, String value2) {
            addCriterion("exam_name not between", value1, value2, "examName");
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

        public Criteria andExamNumIsNull() {
            addCriterion("exam_num is null");
            return (Criteria) this;
        }

        public Criteria andExamNumIsNotNull() {
            addCriterion("exam_num is not null");
            return (Criteria) this;
        }

        public Criteria andExamNumEqualTo(Short value) {
            addCriterion("exam_num =", value, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumNotEqualTo(Short value) {
            addCriterion("exam_num <>", value, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumGreaterThan(Short value) {
            addCriterion("exam_num >", value, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumGreaterThanOrEqualTo(Short value) {
            addCriterion("exam_num >=", value, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumLessThan(Short value) {
            addCriterion("exam_num <", value, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumLessThanOrEqualTo(Short value) {
            addCriterion("exam_num <=", value, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumIn(List<Short> values) {
            addCriterion("exam_num in", values, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumNotIn(List<Short> values) {
            addCriterion("exam_num not in", values, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumBetween(Short value1, Short value2) {
            addCriterion("exam_num between", value1, value2, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumNotBetween(Short value1, Short value2) {
            addCriterion("exam_num not between", value1, value2, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamPassIsNull() {
            addCriterion("exam_pass is null");
            return (Criteria) this;
        }

        public Criteria andExamPassIsNotNull() {
            addCriterion("exam_pass is not null");
            return (Criteria) this;
        }

        public Criteria andExamPassEqualTo(Short value) {
            addCriterion("exam_pass =", value, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassNotEqualTo(Short value) {
            addCriterion("exam_pass <>", value, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassGreaterThan(Short value) {
            addCriterion("exam_pass >", value, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassGreaterThanOrEqualTo(Short value) {
            addCriterion("exam_pass >=", value, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassLessThan(Short value) {
            addCriterion("exam_pass <", value, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassLessThanOrEqualTo(Short value) {
            addCriterion("exam_pass <=", value, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassIn(List<Short> values) {
            addCriterion("exam_pass in", values, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassNotIn(List<Short> values) {
            addCriterion("exam_pass not in", values, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassBetween(Short value1, Short value2) {
            addCriterion("exam_pass between", value1, value2, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassNotBetween(Short value1, Short value2) {
            addCriterion("exam_pass not between", value1, value2, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamLengthMinIsNull() {
            addCriterion("exam_length_min is null");
            return (Criteria) this;
        }

        public Criteria andExamLengthMinIsNotNull() {
            addCriterion("exam_length_min is not null");
            return (Criteria) this;
        }

        public Criteria andExamLengthMinEqualTo(Byte value) {
            addCriterion("exam_length_min =", value, "examLengthMin");
            return (Criteria) this;
        }

        public Criteria andExamLengthMinNotEqualTo(Byte value) {
            addCriterion("exam_length_min <>", value, "examLengthMin");
            return (Criteria) this;
        }

        public Criteria andExamLengthMinGreaterThan(Byte value) {
            addCriterion("exam_length_min >", value, "examLengthMin");
            return (Criteria) this;
        }

        public Criteria andExamLengthMinGreaterThanOrEqualTo(Byte value) {
            addCriterion("exam_length_min >=", value, "examLengthMin");
            return (Criteria) this;
        }

        public Criteria andExamLengthMinLessThan(Byte value) {
            addCriterion("exam_length_min <", value, "examLengthMin");
            return (Criteria) this;
        }

        public Criteria andExamLengthMinLessThanOrEqualTo(Byte value) {
            addCriterion("exam_length_min <=", value, "examLengthMin");
            return (Criteria) this;
        }

        public Criteria andExamLengthMinIn(List<Byte> values) {
            addCriterion("exam_length_min in", values, "examLengthMin");
            return (Criteria) this;
        }

        public Criteria andExamLengthMinNotIn(List<Byte> values) {
            addCriterion("exam_length_min not in", values, "examLengthMin");
            return (Criteria) this;
        }

        public Criteria andExamLengthMinBetween(Byte value1, Byte value2) {
            addCriterion("exam_length_min between", value1, value2, "examLengthMin");
            return (Criteria) this;
        }

        public Criteria andExamLengthMinNotBetween(Byte value1, Byte value2) {
            addCriterion("exam_length_min not between", value1, value2, "examLengthMin");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeIsNull() {
            addCriterion("exam_start_time is null");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeIsNotNull() {
            addCriterion("exam_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeEqualTo(Date value) {
            addCriterion("exam_start_time =", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotEqualTo(Date value) {
            addCriterion("exam_start_time <>", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeGreaterThan(Date value) {
            addCriterion("exam_start_time >", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("exam_start_time >=", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeLessThan(Date value) {
            addCriterion("exam_start_time <", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("exam_start_time <=", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeIn(List<Date> values) {
            addCriterion("exam_start_time in", values, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotIn(List<Date> values) {
            addCriterion("exam_start_time not in", values, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeBetween(Date value1, Date value2) {
            addCriterion("exam_start_time between", value1, value2, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("exam_start_time not between", value1, value2, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIsNull() {
            addCriterion("exam_end_time is null");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIsNotNull() {
            addCriterion("exam_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeEqualTo(Date value) {
            addCriterion("exam_end_time =", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotEqualTo(Date value) {
            addCriterion("exam_end_time <>", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeGreaterThan(Date value) {
            addCriterion("exam_end_time >", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("exam_end_time >=", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeLessThan(Date value) {
            addCriterion("exam_end_time <", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("exam_end_time <=", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIn(List<Date> values) {
            addCriterion("exam_end_time in", values, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotIn(List<Date> values) {
            addCriterion("exam_end_time not in", values, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeBetween(Date value1, Date value2) {
            addCriterion("exam_end_time between", value1, value2, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("exam_end_time not between", value1, value2, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andIsPostedIsNull() {
            addCriterion("is_posted is null");
            return (Criteria) this;
        }

        public Criteria andIsPostedIsNotNull() {
            addCriterion("is_posted is not null");
            return (Criteria) this;
        }

        public Criteria andIsPostedEqualTo(Boolean value) {
            addCriterion("is_posted =", value, "isPosted");
            return (Criteria) this;
        }

        public Criteria andIsPostedNotEqualTo(Boolean value) {
            addCriterion("is_posted <>", value, "isPosted");
            return (Criteria) this;
        }

        public Criteria andIsPostedGreaterThan(Boolean value) {
            addCriterion("is_posted >", value, "isPosted");
            return (Criteria) this;
        }

        public Criteria andIsPostedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_posted >=", value, "isPosted");
            return (Criteria) this;
        }

        public Criteria andIsPostedLessThan(Boolean value) {
            addCriterion("is_posted <", value, "isPosted");
            return (Criteria) this;
        }

        public Criteria andIsPostedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_posted <=", value, "isPosted");
            return (Criteria) this;
        }

        public Criteria andIsPostedIn(List<Boolean> values) {
            addCriterion("is_posted in", values, "isPosted");
            return (Criteria) this;
        }

        public Criteria andIsPostedNotIn(List<Boolean> values) {
            addCriterion("is_posted not in", values, "isPosted");
            return (Criteria) this;
        }

        public Criteria andIsPostedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_posted between", value1, value2, "isPosted");
            return (Criteria) this;
        }

        public Criteria andIsPostedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_posted not between", value1, value2, "isPosted");
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