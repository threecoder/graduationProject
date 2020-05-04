package com.lutayy.campbackend.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainingExample {
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

    public TrainingExample() {
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

        public Criteria andTrainingNameIsNull() {
            addCriterion("training_name is null");
            return (Criteria) this;
        }

        public Criteria andTrainingNameIsNotNull() {
            addCriterion("training_name is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingNameEqualTo(String value) {
            addCriterion("training_name =", value, "trainingName");
            return (Criteria) this;
        }

        public Criteria andTrainingNameNotEqualTo(String value) {
            addCriterion("training_name <>", value, "trainingName");
            return (Criteria) this;
        }

        public Criteria andTrainingNameGreaterThan(String value) {
            addCriterion("training_name >", value, "trainingName");
            return (Criteria) this;
        }

        public Criteria andTrainingNameGreaterThanOrEqualTo(String value) {
            addCriterion("training_name >=", value, "trainingName");
            return (Criteria) this;
        }

        public Criteria andTrainingNameLessThan(String value) {
            addCriterion("training_name <", value, "trainingName");
            return (Criteria) this;
        }

        public Criteria andTrainingNameLessThanOrEqualTo(String value) {
            addCriterion("training_name <=", value, "trainingName");
            return (Criteria) this;
        }

        public Criteria andTrainingNameLike(String value) {
            addCriterion("training_name like", value, "trainingName");
            return (Criteria) this;
        }

        public Criteria andTrainingNameNotLike(String value) {
            addCriterion("training_name not like", value, "trainingName");
            return (Criteria) this;
        }

        public Criteria andTrainingNameIn(List<String> values) {
            addCriterion("training_name in", values, "trainingName");
            return (Criteria) this;
        }

        public Criteria andTrainingNameNotIn(List<String> values) {
            addCriterion("training_name not in", values, "trainingName");
            return (Criteria) this;
        }

        public Criteria andTrainingNameBetween(String value1, String value2) {
            addCriterion("training_name between", value1, value2, "trainingName");
            return (Criteria) this;
        }

        public Criteria andTrainingNameNotBetween(String value1, String value2) {
            addCriterion("training_name not between", value1, value2, "trainingName");
            return (Criteria) this;
        }

        public Criteria andTrainingIntroduceIsNull() {
            addCriterion("training_introduce is null");
            return (Criteria) this;
        }

        public Criteria andTrainingIntroduceIsNotNull() {
            addCriterion("training_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingIntroduceEqualTo(String value) {
            addCriterion("training_introduce =", value, "trainingIntroduce");
            return (Criteria) this;
        }

        public Criteria andTrainingIntroduceNotEqualTo(String value) {
            addCriterion("training_introduce <>", value, "trainingIntroduce");
            return (Criteria) this;
        }

        public Criteria andTrainingIntroduceGreaterThan(String value) {
            addCriterion("training_introduce >", value, "trainingIntroduce");
            return (Criteria) this;
        }

        public Criteria andTrainingIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("training_introduce >=", value, "trainingIntroduce");
            return (Criteria) this;
        }

        public Criteria andTrainingIntroduceLessThan(String value) {
            addCriterion("training_introduce <", value, "trainingIntroduce");
            return (Criteria) this;
        }

        public Criteria andTrainingIntroduceLessThanOrEqualTo(String value) {
            addCriterion("training_introduce <=", value, "trainingIntroduce");
            return (Criteria) this;
        }

        public Criteria andTrainingIntroduceLike(String value) {
            addCriterion("training_introduce like", value, "trainingIntroduce");
            return (Criteria) this;
        }

        public Criteria andTrainingIntroduceNotLike(String value) {
            addCriterion("training_introduce not like", value, "trainingIntroduce");
            return (Criteria) this;
        }

        public Criteria andTrainingIntroduceIn(List<String> values) {
            addCriterion("training_introduce in", values, "trainingIntroduce");
            return (Criteria) this;
        }

        public Criteria andTrainingIntroduceNotIn(List<String> values) {
            addCriterion("training_introduce not in", values, "trainingIntroduce");
            return (Criteria) this;
        }

        public Criteria andTrainingIntroduceBetween(String value1, String value2) {
            addCriterion("training_introduce between", value1, value2, "trainingIntroduce");
            return (Criteria) this;
        }

        public Criteria andTrainingIntroduceNotBetween(String value1, String value2) {
            addCriterion("training_introduce not between", value1, value2, "trainingIntroduce");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeNormalIsNull() {
            addCriterion("training_fee_normal is null");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeNormalIsNotNull() {
            addCriterion("training_fee_normal is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeNormalEqualTo(BigDecimal value) {
            addCriterion("training_fee_normal =", value, "trainingFeeNormal");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeNormalNotEqualTo(BigDecimal value) {
            addCriterion("training_fee_normal <>", value, "trainingFeeNormal");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeNormalGreaterThan(BigDecimal value) {
            addCriterion("training_fee_normal >", value, "trainingFeeNormal");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeNormalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("training_fee_normal >=", value, "trainingFeeNormal");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeNormalLessThan(BigDecimal value) {
            addCriterion("training_fee_normal <", value, "trainingFeeNormal");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeNormalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("training_fee_normal <=", value, "trainingFeeNormal");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeNormalIn(List<BigDecimal> values) {
            addCriterion("training_fee_normal in", values, "trainingFeeNormal");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeNormalNotIn(List<BigDecimal> values) {
            addCriterion("training_fee_normal not in", values, "trainingFeeNormal");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeNormalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("training_fee_normal between", value1, value2, "trainingFeeNormal");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeNormalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("training_fee_normal not between", value1, value2, "trainingFeeNormal");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeVipIsNull() {
            addCriterion("training_fee_vip is null");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeVipIsNotNull() {
            addCriterion("training_fee_vip is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeVipEqualTo(BigDecimal value) {
            addCriterion("training_fee_vip =", value, "trainingFeeVip");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeVipNotEqualTo(BigDecimal value) {
            addCriterion("training_fee_vip <>", value, "trainingFeeVip");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeVipGreaterThan(BigDecimal value) {
            addCriterion("training_fee_vip >", value, "trainingFeeVip");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeVipGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("training_fee_vip >=", value, "trainingFeeVip");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeVipLessThan(BigDecimal value) {
            addCriterion("training_fee_vip <", value, "trainingFeeVip");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeVipLessThanOrEqualTo(BigDecimal value) {
            addCriterion("training_fee_vip <=", value, "trainingFeeVip");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeVipIn(List<BigDecimal> values) {
            addCriterion("training_fee_vip in", values, "trainingFeeVip");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeVipNotIn(List<BigDecimal> values) {
            addCriterion("training_fee_vip not in", values, "trainingFeeVip");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeVipBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("training_fee_vip between", value1, value2, "trainingFeeVip");
            return (Criteria) this;
        }

        public Criteria andTrainingFeeVipNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("training_fee_vip not between", value1, value2, "trainingFeeVip");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeIsNull() {
            addCriterion("training_end_time is null");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeIsNotNull() {
            addCriterion("training_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeEqualTo(Date value) {
            addCriterion("training_end_time =", value, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeNotEqualTo(Date value) {
            addCriterion("training_end_time <>", value, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeGreaterThan(Date value) {
            addCriterion("training_end_time >", value, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("training_end_time >=", value, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeLessThan(Date value) {
            addCriterion("training_end_time <", value, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("training_end_time <=", value, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeIn(List<Date> values) {
            addCriterion("training_end_time in", values, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeNotIn(List<Date> values) {
            addCriterion("training_end_time not in", values, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeBetween(Date value1, Date value2) {
            addCriterion("training_end_time between", value1, value2, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("training_end_time not between", value1, value2, "trainingEndTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeIsNull() {
            addCriterion("training_start_time is null");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeIsNotNull() {
            addCriterion("training_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeEqualTo(Date value) {
            addCriterion("training_start_time =", value, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeNotEqualTo(Date value) {
            addCriterion("training_start_time <>", value, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeGreaterThan(Date value) {
            addCriterion("training_start_time >", value, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("training_start_time >=", value, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeLessThan(Date value) {
            addCriterion("training_start_time <", value, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("training_start_time <=", value, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeIn(List<Date> values) {
            addCriterion("training_start_time in", values, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeNotIn(List<Date> values) {
            addCriterion("training_start_time not in", values, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeBetween(Date value1, Date value2) {
            addCriterion("training_start_time between", value1, value2, "trainingStartTime");
            return (Criteria) this;
        }

        public Criteria andTrainingStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("training_start_time not between", value1, value2, "trainingStartTime");
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

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Byte value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Byte value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Byte value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Byte value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Byte value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Byte value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Byte> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Byte> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Byte value1, Byte value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Byte value1, Byte value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andTrainingPicIsNull() {
            addCriterion("training_pic is null");
            return (Criteria) this;
        }

        public Criteria andTrainingPicIsNotNull() {
            addCriterion("training_pic is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingPicEqualTo(String value) {
            addCriterion("training_pic =", value, "trainingPic");
            return (Criteria) this;
        }

        public Criteria andTrainingPicNotEqualTo(String value) {
            addCriterion("training_pic <>", value, "trainingPic");
            return (Criteria) this;
        }

        public Criteria andTrainingPicGreaterThan(String value) {
            addCriterion("training_pic >", value, "trainingPic");
            return (Criteria) this;
        }

        public Criteria andTrainingPicGreaterThanOrEqualTo(String value) {
            addCriterion("training_pic >=", value, "trainingPic");
            return (Criteria) this;
        }

        public Criteria andTrainingPicLessThan(String value) {
            addCriterion("training_pic <", value, "trainingPic");
            return (Criteria) this;
        }

        public Criteria andTrainingPicLessThanOrEqualTo(String value) {
            addCriterion("training_pic <=", value, "trainingPic");
            return (Criteria) this;
        }

        public Criteria andTrainingPicLike(String value) {
            addCriterion("training_pic like", value, "trainingPic");
            return (Criteria) this;
        }

        public Criteria andTrainingPicNotLike(String value) {
            addCriterion("training_pic not like", value, "trainingPic");
            return (Criteria) this;
        }

        public Criteria andTrainingPicIn(List<String> values) {
            addCriterion("training_pic in", values, "trainingPic");
            return (Criteria) this;
        }

        public Criteria andTrainingPicNotIn(List<String> values) {
            addCriterion("training_pic not in", values, "trainingPic");
            return (Criteria) this;
        }

        public Criteria andTrainingPicBetween(String value1, String value2) {
            addCriterion("training_pic between", value1, value2, "trainingPic");
            return (Criteria) this;
        }

        public Criteria andTrainingPicNotBetween(String value1, String value2) {
            addCriterion("training_pic not between", value1, value2, "trainingPic");
            return (Criteria) this;
        }

        public Criteria andTrainingAddressIsNull() {
            addCriterion("training_address is null");
            return (Criteria) this;
        }

        public Criteria andTrainingAddressIsNotNull() {
            addCriterion("training_address is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingAddressEqualTo(String value) {
            addCriterion("training_address =", value, "trainingAddress");
            return (Criteria) this;
        }

        public Criteria andTrainingAddressNotEqualTo(String value) {
            addCriterion("training_address <>", value, "trainingAddress");
            return (Criteria) this;
        }

        public Criteria andTrainingAddressGreaterThan(String value) {
            addCriterion("training_address >", value, "trainingAddress");
            return (Criteria) this;
        }

        public Criteria andTrainingAddressGreaterThanOrEqualTo(String value) {
            addCriterion("training_address >=", value, "trainingAddress");
            return (Criteria) this;
        }

        public Criteria andTrainingAddressLessThan(String value) {
            addCriterion("training_address <", value, "trainingAddress");
            return (Criteria) this;
        }

        public Criteria andTrainingAddressLessThanOrEqualTo(String value) {
            addCriterion("training_address <=", value, "trainingAddress");
            return (Criteria) this;
        }

        public Criteria andTrainingAddressLike(String value) {
            addCriterion("training_address like", value, "trainingAddress");
            return (Criteria) this;
        }

        public Criteria andTrainingAddressNotLike(String value) {
            addCriterion("training_address not like", value, "trainingAddress");
            return (Criteria) this;
        }

        public Criteria andTrainingAddressIn(List<String> values) {
            addCriterion("training_address in", values, "trainingAddress");
            return (Criteria) this;
        }

        public Criteria andTrainingAddressNotIn(List<String> values) {
            addCriterion("training_address not in", values, "trainingAddress");
            return (Criteria) this;
        }

        public Criteria andTrainingAddressBetween(String value1, String value2) {
            addCriterion("training_address between", value1, value2, "trainingAddress");
            return (Criteria) this;
        }

        public Criteria andTrainingAddressNotBetween(String value1, String value2) {
            addCriterion("training_address not between", value1, value2, "trainingAddress");
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

        public Criteria andCerTemPathIsNull() {
            addCriterion("cer_tem_path is null");
            return (Criteria) this;
        }

        public Criteria andCerTemPathIsNotNull() {
            addCriterion("cer_tem_path is not null");
            return (Criteria) this;
        }

        public Criteria andCerTemPathEqualTo(String value) {
            addCriterion("cer_tem_path =", value, "cerTemPath");
            return (Criteria) this;
        }

        public Criteria andCerTemPathNotEqualTo(String value) {
            addCriterion("cer_tem_path <>", value, "cerTemPath");
            return (Criteria) this;
        }

        public Criteria andCerTemPathGreaterThan(String value) {
            addCriterion("cer_tem_path >", value, "cerTemPath");
            return (Criteria) this;
        }

        public Criteria andCerTemPathGreaterThanOrEqualTo(String value) {
            addCriterion("cer_tem_path >=", value, "cerTemPath");
            return (Criteria) this;
        }

        public Criteria andCerTemPathLessThan(String value) {
            addCriterion("cer_tem_path <", value, "cerTemPath");
            return (Criteria) this;
        }

        public Criteria andCerTemPathLessThanOrEqualTo(String value) {
            addCriterion("cer_tem_path <=", value, "cerTemPath");
            return (Criteria) this;
        }

        public Criteria andCerTemPathLike(String value) {
            addCriterion("cer_tem_path like", value, "cerTemPath");
            return (Criteria) this;
        }

        public Criteria andCerTemPathNotLike(String value) {
            addCriterion("cer_tem_path not like", value, "cerTemPath");
            return (Criteria) this;
        }

        public Criteria andCerTemPathIn(List<String> values) {
            addCriterion("cer_tem_path in", values, "cerTemPath");
            return (Criteria) this;
        }

        public Criteria andCerTemPathNotIn(List<String> values) {
            addCriterion("cer_tem_path not in", values, "cerTemPath");
            return (Criteria) this;
        }

        public Criteria andCerTemPathBetween(String value1, String value2) {
            addCriterion("cer_tem_path between", value1, value2, "cerTemPath");
            return (Criteria) this;
        }

        public Criteria andCerTemPathNotBetween(String value1, String value2) {
            addCriterion("cer_tem_path not between", value1, value2, "cerTemPath");
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