package com.lutayy.campbackend.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoteExample {
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

    public VoteExample() {
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

        public Criteria andVoteIdIsNull() {
            addCriterion("vote_id is null");
            return (Criteria) this;
        }

        public Criteria andVoteIdIsNotNull() {
            addCriterion("vote_id is not null");
            return (Criteria) this;
        }

        public Criteria andVoteIdEqualTo(Integer value) {
            addCriterion("vote_id =", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotEqualTo(Integer value) {
            addCriterion("vote_id <>", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdGreaterThan(Integer value) {
            addCriterion("vote_id >", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("vote_id >=", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdLessThan(Integer value) {
            addCriterion("vote_id <", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdLessThanOrEqualTo(Integer value) {
            addCriterion("vote_id <=", value, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdIn(List<Integer> values) {
            addCriterion("vote_id in", values, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotIn(List<Integer> values) {
            addCriterion("vote_id not in", values, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdBetween(Integer value1, Integer value2) {
            addCriterion("vote_id between", value1, value2, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("vote_id not between", value1, value2, "voteId");
            return (Criteria) this;
        }

        public Criteria andVoteContentIsNull() {
            addCriterion("vote_content is null");
            return (Criteria) this;
        }

        public Criteria andVoteContentIsNotNull() {
            addCriterion("vote_content is not null");
            return (Criteria) this;
        }

        public Criteria andVoteContentEqualTo(String value) {
            addCriterion("vote_content =", value, "voteContent");
            return (Criteria) this;
        }

        public Criteria andVoteContentNotEqualTo(String value) {
            addCriterion("vote_content <>", value, "voteContent");
            return (Criteria) this;
        }

        public Criteria andVoteContentGreaterThan(String value) {
            addCriterion("vote_content >", value, "voteContent");
            return (Criteria) this;
        }

        public Criteria andVoteContentGreaterThanOrEqualTo(String value) {
            addCriterion("vote_content >=", value, "voteContent");
            return (Criteria) this;
        }

        public Criteria andVoteContentLessThan(String value) {
            addCriterion("vote_content <", value, "voteContent");
            return (Criteria) this;
        }

        public Criteria andVoteContentLessThanOrEqualTo(String value) {
            addCriterion("vote_content <=", value, "voteContent");
            return (Criteria) this;
        }

        public Criteria andVoteContentLike(String value) {
            addCriterion("vote_content like", value, "voteContent");
            return (Criteria) this;
        }

        public Criteria andVoteContentNotLike(String value) {
            addCriterion("vote_content not like", value, "voteContent");
            return (Criteria) this;
        }

        public Criteria andVoteContentIn(List<String> values) {
            addCriterion("vote_content in", values, "voteContent");
            return (Criteria) this;
        }

        public Criteria andVoteContentNotIn(List<String> values) {
            addCriterion("vote_content not in", values, "voteContent");
            return (Criteria) this;
        }

        public Criteria andVoteContentBetween(String value1, String value2) {
            addCriterion("vote_content between", value1, value2, "voteContent");
            return (Criteria) this;
        }

        public Criteria andVoteContentNotBetween(String value1, String value2) {
            addCriterion("vote_content not between", value1, value2, "voteContent");
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

        public Criteria andVoteTypeIsNull() {
            addCriterion("vote_type is null");
            return (Criteria) this;
        }

        public Criteria andVoteTypeIsNotNull() {
            addCriterion("vote_type is not null");
            return (Criteria) this;
        }

        public Criteria andVoteTypeEqualTo(Byte value) {
            addCriterion("vote_type =", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeNotEqualTo(Byte value) {
            addCriterion("vote_type <>", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeGreaterThan(Byte value) {
            addCriterion("vote_type >", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("vote_type >=", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeLessThan(Byte value) {
            addCriterion("vote_type <", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeLessThanOrEqualTo(Byte value) {
            addCriterion("vote_type <=", value, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeIn(List<Byte> values) {
            addCriterion("vote_type in", values, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeNotIn(List<Byte> values) {
            addCriterion("vote_type not in", values, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeBetween(Byte value1, Byte value2) {
            addCriterion("vote_type between", value1, value2, "voteType");
            return (Criteria) this;
        }

        public Criteria andVoteTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("vote_type not between", value1, value2, "voteType");
            return (Criteria) this;
        }

        public Criteria andOptionalSumIsNull() {
            addCriterion("optional_sum is null");
            return (Criteria) this;
        }

        public Criteria andOptionalSumIsNotNull() {
            addCriterion("optional_sum is not null");
            return (Criteria) this;
        }

        public Criteria andOptionalSumEqualTo(Byte value) {
            addCriterion("optional_sum =", value, "optionalSum");
            return (Criteria) this;
        }

        public Criteria andOptionalSumNotEqualTo(Byte value) {
            addCriterion("optional_sum <>", value, "optionalSum");
            return (Criteria) this;
        }

        public Criteria andOptionalSumGreaterThan(Byte value) {
            addCriterion("optional_sum >", value, "optionalSum");
            return (Criteria) this;
        }

        public Criteria andOptionalSumGreaterThanOrEqualTo(Byte value) {
            addCriterion("optional_sum >=", value, "optionalSum");
            return (Criteria) this;
        }

        public Criteria andOptionalSumLessThan(Byte value) {
            addCriterion("optional_sum <", value, "optionalSum");
            return (Criteria) this;
        }

        public Criteria andOptionalSumLessThanOrEqualTo(Byte value) {
            addCriterion("optional_sum <=", value, "optionalSum");
            return (Criteria) this;
        }

        public Criteria andOptionalSumIn(List<Byte> values) {
            addCriterion("optional_sum in", values, "optionalSum");
            return (Criteria) this;
        }

        public Criteria andOptionalSumNotIn(List<Byte> values) {
            addCriterion("optional_sum not in", values, "optionalSum");
            return (Criteria) this;
        }

        public Criteria andOptionalSumBetween(Byte value1, Byte value2) {
            addCriterion("optional_sum between", value1, value2, "optionalSum");
            return (Criteria) this;
        }

        public Criteria andOptionalSumNotBetween(Byte value1, Byte value2) {
            addCriterion("optional_sum not between", value1, value2, "optionalSum");
            return (Criteria) this;
        }

        public Criteria andOptionalNumIsNull() {
            addCriterion("optional_num is null");
            return (Criteria) this;
        }

        public Criteria andOptionalNumIsNotNull() {
            addCriterion("optional_num is not null");
            return (Criteria) this;
        }

        public Criteria andOptionalNumEqualTo(Byte value) {
            addCriterion("optional_num =", value, "optionalNum");
            return (Criteria) this;
        }

        public Criteria andOptionalNumNotEqualTo(Byte value) {
            addCriterion("optional_num <>", value, "optionalNum");
            return (Criteria) this;
        }

        public Criteria andOptionalNumGreaterThan(Byte value) {
            addCriterion("optional_num >", value, "optionalNum");
            return (Criteria) this;
        }

        public Criteria andOptionalNumGreaterThanOrEqualTo(Byte value) {
            addCriterion("optional_num >=", value, "optionalNum");
            return (Criteria) this;
        }

        public Criteria andOptionalNumLessThan(Byte value) {
            addCriterion("optional_num <", value, "optionalNum");
            return (Criteria) this;
        }

        public Criteria andOptionalNumLessThanOrEqualTo(Byte value) {
            addCriterion("optional_num <=", value, "optionalNum");
            return (Criteria) this;
        }

        public Criteria andOptionalNumIn(List<Byte> values) {
            addCriterion("optional_num in", values, "optionalNum");
            return (Criteria) this;
        }

        public Criteria andOptionalNumNotIn(List<Byte> values) {
            addCriterion("optional_num not in", values, "optionalNum");
            return (Criteria) this;
        }

        public Criteria andOptionalNumBetween(Byte value1, Byte value2) {
            addCriterion("optional_num between", value1, value2, "optionalNum");
            return (Criteria) this;
        }

        public Criteria andOptionalNumNotBetween(Byte value1, Byte value2) {
            addCriterion("optional_num not between", value1, value2, "optionalNum");
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