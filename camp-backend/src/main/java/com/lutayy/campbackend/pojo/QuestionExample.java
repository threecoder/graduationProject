package com.lutayy.campbackend.pojo;

import java.util.ArrayList;
import java.util.List;

public class QuestionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QuestionExample() {
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

        public Criteria andQuestionStateIsNull() {
            addCriterion("question_state is null");
            return (Criteria) this;
        }

        public Criteria andQuestionStateIsNotNull() {
            addCriterion("question_state is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionStateEqualTo(String value) {
            addCriterion("question_state =", value, "questionState");
            return (Criteria) this;
        }

        public Criteria andQuestionStateNotEqualTo(String value) {
            addCriterion("question_state <>", value, "questionState");
            return (Criteria) this;
        }

        public Criteria andQuestionStateGreaterThan(String value) {
            addCriterion("question_state >", value, "questionState");
            return (Criteria) this;
        }

        public Criteria andQuestionStateGreaterThanOrEqualTo(String value) {
            addCriterion("question_state >=", value, "questionState");
            return (Criteria) this;
        }

        public Criteria andQuestionStateLessThan(String value) {
            addCriterion("question_state <", value, "questionState");
            return (Criteria) this;
        }

        public Criteria andQuestionStateLessThanOrEqualTo(String value) {
            addCriterion("question_state <=", value, "questionState");
            return (Criteria) this;
        }

        public Criteria andQuestionStateLike(String value) {
            addCriterion("question_state like", value, "questionState");
            return (Criteria) this;
        }

        public Criteria andQuestionStateNotLike(String value) {
            addCriterion("question_state not like", value, "questionState");
            return (Criteria) this;
        }

        public Criteria andQuestionStateIn(List<String> values) {
            addCriterion("question_state in", values, "questionState");
            return (Criteria) this;
        }

        public Criteria andQuestionStateNotIn(List<String> values) {
            addCriterion("question_state not in", values, "questionState");
            return (Criteria) this;
        }

        public Criteria andQuestionStateBetween(String value1, String value2) {
            addCriterion("question_state between", value1, value2, "questionState");
            return (Criteria) this;
        }

        public Criteria andQuestionStateNotBetween(String value1, String value2) {
            addCriterion("question_state not between", value1, value2, "questionState");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andChoiceAIsNull() {
            addCriterion("choice_a is null");
            return (Criteria) this;
        }

        public Criteria andChoiceAIsNotNull() {
            addCriterion("choice_a is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceAEqualTo(String value) {
            addCriterion("choice_a =", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceANotEqualTo(String value) {
            addCriterion("choice_a <>", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceAGreaterThan(String value) {
            addCriterion("choice_a >", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceAGreaterThanOrEqualTo(String value) {
            addCriterion("choice_a >=", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceALessThan(String value) {
            addCriterion("choice_a <", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceALessThanOrEqualTo(String value) {
            addCriterion("choice_a <=", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceALike(String value) {
            addCriterion("choice_a like", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceANotLike(String value) {
            addCriterion("choice_a not like", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceAIn(List<String> values) {
            addCriterion("choice_a in", values, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceANotIn(List<String> values) {
            addCriterion("choice_a not in", values, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceABetween(String value1, String value2) {
            addCriterion("choice_a between", value1, value2, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceANotBetween(String value1, String value2) {
            addCriterion("choice_a not between", value1, value2, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceBIsNull() {
            addCriterion("choice_b is null");
            return (Criteria) this;
        }

        public Criteria andChoiceBIsNotNull() {
            addCriterion("choice_b is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceBEqualTo(String value) {
            addCriterion("choice_b =", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBNotEqualTo(String value) {
            addCriterion("choice_b <>", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBGreaterThan(String value) {
            addCriterion("choice_b >", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBGreaterThanOrEqualTo(String value) {
            addCriterion("choice_b >=", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBLessThan(String value) {
            addCriterion("choice_b <", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBLessThanOrEqualTo(String value) {
            addCriterion("choice_b <=", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBLike(String value) {
            addCriterion("choice_b like", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBNotLike(String value) {
            addCriterion("choice_b not like", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBIn(List<String> values) {
            addCriterion("choice_b in", values, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBNotIn(List<String> values) {
            addCriterion("choice_b not in", values, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBBetween(String value1, String value2) {
            addCriterion("choice_b between", value1, value2, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBNotBetween(String value1, String value2) {
            addCriterion("choice_b not between", value1, value2, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceCIsNull() {
            addCriterion("choice_c is null");
            return (Criteria) this;
        }

        public Criteria andChoiceCIsNotNull() {
            addCriterion("choice_c is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceCEqualTo(String value) {
            addCriterion("choice_c =", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCNotEqualTo(String value) {
            addCriterion("choice_c <>", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCGreaterThan(String value) {
            addCriterion("choice_c >", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCGreaterThanOrEqualTo(String value) {
            addCriterion("choice_c >=", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCLessThan(String value) {
            addCriterion("choice_c <", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCLessThanOrEqualTo(String value) {
            addCriterion("choice_c <=", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCLike(String value) {
            addCriterion("choice_c like", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCNotLike(String value) {
            addCriterion("choice_c not like", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCIn(List<String> values) {
            addCriterion("choice_c in", values, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCNotIn(List<String> values) {
            addCriterion("choice_c not in", values, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCBetween(String value1, String value2) {
            addCriterion("choice_c between", value1, value2, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCNotBetween(String value1, String value2) {
            addCriterion("choice_c not between", value1, value2, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceDIsNull() {
            addCriterion("choice_d is null");
            return (Criteria) this;
        }

        public Criteria andChoiceDIsNotNull() {
            addCriterion("choice_d is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceDEqualTo(String value) {
            addCriterion("choice_d =", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDNotEqualTo(String value) {
            addCriterion("choice_d <>", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDGreaterThan(String value) {
            addCriterion("choice_d >", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDGreaterThanOrEqualTo(String value) {
            addCriterion("choice_d >=", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDLessThan(String value) {
            addCriterion("choice_d <", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDLessThanOrEqualTo(String value) {
            addCriterion("choice_d <=", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDLike(String value) {
            addCriterion("choice_d like", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDNotLike(String value) {
            addCriterion("choice_d not like", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDIn(List<String> values) {
            addCriterion("choice_d in", values, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDNotIn(List<String> values) {
            addCriterion("choice_d not in", values, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDBetween(String value1, String value2) {
            addCriterion("choice_d between", value1, value2, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDNotBetween(String value1, String value2) {
            addCriterion("choice_d not between", value1, value2, "choiceD");
            return (Criteria) this;
        }

        public Criteria andRightChoiceOneIsNull() {
            addCriterion("right_choice_one is null");
            return (Criteria) this;
        }

        public Criteria andRightChoiceOneIsNotNull() {
            addCriterion("right_choice_one is not null");
            return (Criteria) this;
        }

        public Criteria andRightChoiceOneEqualTo(Byte value) {
            addCriterion("right_choice_one =", value, "rightChoiceOne");
            return (Criteria) this;
        }

        public Criteria andRightChoiceOneNotEqualTo(Byte value) {
            addCriterion("right_choice_one <>", value, "rightChoiceOne");
            return (Criteria) this;
        }

        public Criteria andRightChoiceOneGreaterThan(Byte value) {
            addCriterion("right_choice_one >", value, "rightChoiceOne");
            return (Criteria) this;
        }

        public Criteria andRightChoiceOneGreaterThanOrEqualTo(Byte value) {
            addCriterion("right_choice_one >=", value, "rightChoiceOne");
            return (Criteria) this;
        }

        public Criteria andRightChoiceOneLessThan(Byte value) {
            addCriterion("right_choice_one <", value, "rightChoiceOne");
            return (Criteria) this;
        }

        public Criteria andRightChoiceOneLessThanOrEqualTo(Byte value) {
            addCriterion("right_choice_one <=", value, "rightChoiceOne");
            return (Criteria) this;
        }

        public Criteria andRightChoiceOneIn(List<Byte> values) {
            addCriterion("right_choice_one in", values, "rightChoiceOne");
            return (Criteria) this;
        }

        public Criteria andRightChoiceOneNotIn(List<Byte> values) {
            addCriterion("right_choice_one not in", values, "rightChoiceOne");
            return (Criteria) this;
        }

        public Criteria andRightChoiceOneBetween(Byte value1, Byte value2) {
            addCriterion("right_choice_one between", value1, value2, "rightChoiceOne");
            return (Criteria) this;
        }

        public Criteria andRightChoiceOneNotBetween(Byte value1, Byte value2) {
            addCriterion("right_choice_one not between", value1, value2, "rightChoiceOne");
            return (Criteria) this;
        }

        public Criteria andRightChoiceTwoIsNull() {
            addCriterion("right_choice_two is null");
            return (Criteria) this;
        }

        public Criteria andRightChoiceTwoIsNotNull() {
            addCriterion("right_choice_two is not null");
            return (Criteria) this;
        }

        public Criteria andRightChoiceTwoEqualTo(Byte value) {
            addCriterion("right_choice_two =", value, "rightChoiceTwo");
            return (Criteria) this;
        }

        public Criteria andRightChoiceTwoNotEqualTo(Byte value) {
            addCriterion("right_choice_two <>", value, "rightChoiceTwo");
            return (Criteria) this;
        }

        public Criteria andRightChoiceTwoGreaterThan(Byte value) {
            addCriterion("right_choice_two >", value, "rightChoiceTwo");
            return (Criteria) this;
        }

        public Criteria andRightChoiceTwoGreaterThanOrEqualTo(Byte value) {
            addCriterion("right_choice_two >=", value, "rightChoiceTwo");
            return (Criteria) this;
        }

        public Criteria andRightChoiceTwoLessThan(Byte value) {
            addCriterion("right_choice_two <", value, "rightChoiceTwo");
            return (Criteria) this;
        }

        public Criteria andRightChoiceTwoLessThanOrEqualTo(Byte value) {
            addCriterion("right_choice_two <=", value, "rightChoiceTwo");
            return (Criteria) this;
        }

        public Criteria andRightChoiceTwoIn(List<Byte> values) {
            addCriterion("right_choice_two in", values, "rightChoiceTwo");
            return (Criteria) this;
        }

        public Criteria andRightChoiceTwoNotIn(List<Byte> values) {
            addCriterion("right_choice_two not in", values, "rightChoiceTwo");
            return (Criteria) this;
        }

        public Criteria andRightChoiceTwoBetween(Byte value1, Byte value2) {
            addCriterion("right_choice_two between", value1, value2, "rightChoiceTwo");
            return (Criteria) this;
        }

        public Criteria andRightChoiceTwoNotBetween(Byte value1, Byte value2) {
            addCriterion("right_choice_two not between", value1, value2, "rightChoiceTwo");
            return (Criteria) this;
        }

        public Criteria andRightChoiceThreeIsNull() {
            addCriterion("right_choice_three is null");
            return (Criteria) this;
        }

        public Criteria andRightChoiceThreeIsNotNull() {
            addCriterion("right_choice_three is not null");
            return (Criteria) this;
        }

        public Criteria andRightChoiceThreeEqualTo(Byte value) {
            addCriterion("right_choice_three =", value, "rightChoiceThree");
            return (Criteria) this;
        }

        public Criteria andRightChoiceThreeNotEqualTo(Byte value) {
            addCriterion("right_choice_three <>", value, "rightChoiceThree");
            return (Criteria) this;
        }

        public Criteria andRightChoiceThreeGreaterThan(Byte value) {
            addCriterion("right_choice_three >", value, "rightChoiceThree");
            return (Criteria) this;
        }

        public Criteria andRightChoiceThreeGreaterThanOrEqualTo(Byte value) {
            addCriterion("right_choice_three >=", value, "rightChoiceThree");
            return (Criteria) this;
        }

        public Criteria andRightChoiceThreeLessThan(Byte value) {
            addCriterion("right_choice_three <", value, "rightChoiceThree");
            return (Criteria) this;
        }

        public Criteria andRightChoiceThreeLessThanOrEqualTo(Byte value) {
            addCriterion("right_choice_three <=", value, "rightChoiceThree");
            return (Criteria) this;
        }

        public Criteria andRightChoiceThreeIn(List<Byte> values) {
            addCriterion("right_choice_three in", values, "rightChoiceThree");
            return (Criteria) this;
        }

        public Criteria andRightChoiceThreeNotIn(List<Byte> values) {
            addCriterion("right_choice_three not in", values, "rightChoiceThree");
            return (Criteria) this;
        }

        public Criteria andRightChoiceThreeBetween(Byte value1, Byte value2) {
            addCriterion("right_choice_three between", value1, value2, "rightChoiceThree");
            return (Criteria) this;
        }

        public Criteria andRightChoiceThreeNotBetween(Byte value1, Byte value2) {
            addCriterion("right_choice_three not between", value1, value2, "rightChoiceThree");
            return (Criteria) this;
        }

        public Criteria andRightChoiceFourIsNull() {
            addCriterion("right_choice_four is null");
            return (Criteria) this;
        }

        public Criteria andRightChoiceFourIsNotNull() {
            addCriterion("right_choice_four is not null");
            return (Criteria) this;
        }

        public Criteria andRightChoiceFourEqualTo(Byte value) {
            addCriterion("right_choice_four =", value, "rightChoiceFour");
            return (Criteria) this;
        }

        public Criteria andRightChoiceFourNotEqualTo(Byte value) {
            addCriterion("right_choice_four <>", value, "rightChoiceFour");
            return (Criteria) this;
        }

        public Criteria andRightChoiceFourGreaterThan(Byte value) {
            addCriterion("right_choice_four >", value, "rightChoiceFour");
            return (Criteria) this;
        }

        public Criteria andRightChoiceFourGreaterThanOrEqualTo(Byte value) {
            addCriterion("right_choice_four >=", value, "rightChoiceFour");
            return (Criteria) this;
        }

        public Criteria andRightChoiceFourLessThan(Byte value) {
            addCriterion("right_choice_four <", value, "rightChoiceFour");
            return (Criteria) this;
        }

        public Criteria andRightChoiceFourLessThanOrEqualTo(Byte value) {
            addCriterion("right_choice_four <=", value, "rightChoiceFour");
            return (Criteria) this;
        }

        public Criteria andRightChoiceFourIn(List<Byte> values) {
            addCriterion("right_choice_four in", values, "rightChoiceFour");
            return (Criteria) this;
        }

        public Criteria andRightChoiceFourNotIn(List<Byte> values) {
            addCriterion("right_choice_four not in", values, "rightChoiceFour");
            return (Criteria) this;
        }

        public Criteria andRightChoiceFourBetween(Byte value1, Byte value2) {
            addCriterion("right_choice_four between", value1, value2, "rightChoiceFour");
            return (Criteria) this;
        }

        public Criteria andRightChoiceFourNotBetween(Byte value1, Byte value2) {
            addCriterion("right_choice_four not between", value1, value2, "rightChoiceFour");
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