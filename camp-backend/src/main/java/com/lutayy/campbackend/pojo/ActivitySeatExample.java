package com.lutayy.campbackend.pojo;

import java.util.ArrayList;
import java.util.List;

public class ActivitySeatExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActivitySeatExample() {
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

        public Criteria andSeatIdIsNull() {
            addCriterion("seat_id is null");
            return (Criteria) this;
        }

        public Criteria andSeatIdIsNotNull() {
            addCriterion("seat_id is not null");
            return (Criteria) this;
        }

        public Criteria andSeatIdEqualTo(Integer value) {
            addCriterion("seat_id =", value, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdNotEqualTo(Integer value) {
            addCriterion("seat_id <>", value, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdGreaterThan(Integer value) {
            addCriterion("seat_id >", value, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("seat_id >=", value, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdLessThan(Integer value) {
            addCriterion("seat_id <", value, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdLessThanOrEqualTo(Integer value) {
            addCriterion("seat_id <=", value, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdIn(List<Integer> values) {
            addCriterion("seat_id in", values, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdNotIn(List<Integer> values) {
            addCriterion("seat_id not in", values, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdBetween(Integer value1, Integer value2) {
            addCriterion("seat_id between", value1, value2, "seatId");
            return (Criteria) this;
        }

        public Criteria andSeatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("seat_id not between", value1, value2, "seatId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(Integer value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(Integer value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(Integer value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(Integer value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(Integer value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<Integer> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<Integer> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(Integer value1, Integer value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andRealXIsNull() {
            addCriterion("real_x is null");
            return (Criteria) this;
        }

        public Criteria andRealXIsNotNull() {
            addCriterion("real_x is not null");
            return (Criteria) this;
        }

        public Criteria andRealXEqualTo(Integer value) {
            addCriterion("real_x =", value, "realX");
            return (Criteria) this;
        }

        public Criteria andRealXNotEqualTo(Integer value) {
            addCriterion("real_x <>", value, "realX");
            return (Criteria) this;
        }

        public Criteria andRealXGreaterThan(Integer value) {
            addCriterion("real_x >", value, "realX");
            return (Criteria) this;
        }

        public Criteria andRealXGreaterThanOrEqualTo(Integer value) {
            addCriterion("real_x >=", value, "realX");
            return (Criteria) this;
        }

        public Criteria andRealXLessThan(Integer value) {
            addCriterion("real_x <", value, "realX");
            return (Criteria) this;
        }

        public Criteria andRealXLessThanOrEqualTo(Integer value) {
            addCriterion("real_x <=", value, "realX");
            return (Criteria) this;
        }

        public Criteria andRealXIn(List<Integer> values) {
            addCriterion("real_x in", values, "realX");
            return (Criteria) this;
        }

        public Criteria andRealXNotIn(List<Integer> values) {
            addCriterion("real_x not in", values, "realX");
            return (Criteria) this;
        }

        public Criteria andRealXBetween(Integer value1, Integer value2) {
            addCriterion("real_x between", value1, value2, "realX");
            return (Criteria) this;
        }

        public Criteria andRealXNotBetween(Integer value1, Integer value2) {
            addCriterion("real_x not between", value1, value2, "realX");
            return (Criteria) this;
        }

        public Criteria andRealYIsNull() {
            addCriterion("real_y is null");
            return (Criteria) this;
        }

        public Criteria andRealYIsNotNull() {
            addCriterion("real_y is not null");
            return (Criteria) this;
        }

        public Criteria andRealYEqualTo(Integer value) {
            addCriterion("real_y =", value, "realY");
            return (Criteria) this;
        }

        public Criteria andRealYNotEqualTo(Integer value) {
            addCriterion("real_y <>", value, "realY");
            return (Criteria) this;
        }

        public Criteria andRealYGreaterThan(Integer value) {
            addCriterion("real_y >", value, "realY");
            return (Criteria) this;
        }

        public Criteria andRealYGreaterThanOrEqualTo(Integer value) {
            addCriterion("real_y >=", value, "realY");
            return (Criteria) this;
        }

        public Criteria andRealYLessThan(Integer value) {
            addCriterion("real_y <", value, "realY");
            return (Criteria) this;
        }

        public Criteria andRealYLessThanOrEqualTo(Integer value) {
            addCriterion("real_y <=", value, "realY");
            return (Criteria) this;
        }

        public Criteria andRealYIn(List<Integer> values) {
            addCriterion("real_y in", values, "realY");
            return (Criteria) this;
        }

        public Criteria andRealYNotIn(List<Integer> values) {
            addCriterion("real_y not in", values, "realY");
            return (Criteria) this;
        }

        public Criteria andRealYBetween(Integer value1, Integer value2) {
            addCriterion("real_y between", value1, value2, "realY");
            return (Criteria) this;
        }

        public Criteria andRealYNotBetween(Integer value1, Integer value2) {
            addCriterion("real_y not between", value1, value2, "realY");
            return (Criteria) this;
        }

        public Criteria andIsLockIsNull() {
            addCriterion("is_lock is null");
            return (Criteria) this;
        }

        public Criteria andIsLockIsNotNull() {
            addCriterion("is_lock is not null");
            return (Criteria) this;
        }

        public Criteria andIsLockEqualTo(Boolean value) {
            addCriterion("is_lock =", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockNotEqualTo(Boolean value) {
            addCriterion("is_lock <>", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockGreaterThan(Boolean value) {
            addCriterion("is_lock >", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_lock >=", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockLessThan(Boolean value) {
            addCriterion("is_lock <", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockLessThanOrEqualTo(Boolean value) {
            addCriterion("is_lock <=", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockIn(List<Boolean> values) {
            addCriterion("is_lock in", values, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockNotIn(List<Boolean> values) {
            addCriterion("is_lock not in", values, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockBetween(Boolean value1, Boolean value2) {
            addCriterion("is_lock between", value1, value2, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_lock not between", value1, value2, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsOccupiedIsNull() {
            addCriterion("is_occupied is null");
            return (Criteria) this;
        }

        public Criteria andIsOccupiedIsNotNull() {
            addCriterion("is_occupied is not null");
            return (Criteria) this;
        }

        public Criteria andIsOccupiedEqualTo(Boolean value) {
            addCriterion("is_occupied =", value, "isOccupied");
            return (Criteria) this;
        }

        public Criteria andIsOccupiedNotEqualTo(Boolean value) {
            addCriterion("is_occupied <>", value, "isOccupied");
            return (Criteria) this;
        }

        public Criteria andIsOccupiedGreaterThan(Boolean value) {
            addCriterion("is_occupied >", value, "isOccupied");
            return (Criteria) this;
        }

        public Criteria andIsOccupiedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_occupied >=", value, "isOccupied");
            return (Criteria) this;
        }

        public Criteria andIsOccupiedLessThan(Boolean value) {
            addCriterion("is_occupied <", value, "isOccupied");
            return (Criteria) this;
        }

        public Criteria andIsOccupiedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_occupied <=", value, "isOccupied");
            return (Criteria) this;
        }

        public Criteria andIsOccupiedIn(List<Boolean> values) {
            addCriterion("is_occupied in", values, "isOccupied");
            return (Criteria) this;
        }

        public Criteria andIsOccupiedNotIn(List<Boolean> values) {
            addCriterion("is_occupied not in", values, "isOccupied");
            return (Criteria) this;
        }

        public Criteria andIsOccupiedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_occupied between", value1, value2, "isOccupied");
            return (Criteria) this;
        }

        public Criteria andIsOccupiedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_occupied not between", value1, value2, "isOccupied");
            return (Criteria) this;
        }

        public Criteria andFakeXIsNull() {
            addCriterion("fake_x is null");
            return (Criteria) this;
        }

        public Criteria andFakeXIsNotNull() {
            addCriterion("fake_x is not null");
            return (Criteria) this;
        }

        public Criteria andFakeXEqualTo(Integer value) {
            addCriterion("fake_x =", value, "fakeX");
            return (Criteria) this;
        }

        public Criteria andFakeXNotEqualTo(Integer value) {
            addCriterion("fake_x <>", value, "fakeX");
            return (Criteria) this;
        }

        public Criteria andFakeXGreaterThan(Integer value) {
            addCriterion("fake_x >", value, "fakeX");
            return (Criteria) this;
        }

        public Criteria andFakeXGreaterThanOrEqualTo(Integer value) {
            addCriterion("fake_x >=", value, "fakeX");
            return (Criteria) this;
        }

        public Criteria andFakeXLessThan(Integer value) {
            addCriterion("fake_x <", value, "fakeX");
            return (Criteria) this;
        }

        public Criteria andFakeXLessThanOrEqualTo(Integer value) {
            addCriterion("fake_x <=", value, "fakeX");
            return (Criteria) this;
        }

        public Criteria andFakeXIn(List<Integer> values) {
            addCriterion("fake_x in", values, "fakeX");
            return (Criteria) this;
        }

        public Criteria andFakeXNotIn(List<Integer> values) {
            addCriterion("fake_x not in", values, "fakeX");
            return (Criteria) this;
        }

        public Criteria andFakeXBetween(Integer value1, Integer value2) {
            addCriterion("fake_x between", value1, value2, "fakeX");
            return (Criteria) this;
        }

        public Criteria andFakeXNotBetween(Integer value1, Integer value2) {
            addCriterion("fake_x not between", value1, value2, "fakeX");
            return (Criteria) this;
        }

        public Criteria andFakeYIsNull() {
            addCriterion("fake_y is null");
            return (Criteria) this;
        }

        public Criteria andFakeYIsNotNull() {
            addCriterion("fake_y is not null");
            return (Criteria) this;
        }

        public Criteria andFakeYEqualTo(Integer value) {
            addCriterion("fake_y =", value, "fakeY");
            return (Criteria) this;
        }

        public Criteria andFakeYNotEqualTo(Integer value) {
            addCriterion("fake_y <>", value, "fakeY");
            return (Criteria) this;
        }

        public Criteria andFakeYGreaterThan(Integer value) {
            addCriterion("fake_y >", value, "fakeY");
            return (Criteria) this;
        }

        public Criteria andFakeYGreaterThanOrEqualTo(Integer value) {
            addCriterion("fake_y >=", value, "fakeY");
            return (Criteria) this;
        }

        public Criteria andFakeYLessThan(Integer value) {
            addCriterion("fake_y <", value, "fakeY");
            return (Criteria) this;
        }

        public Criteria andFakeYLessThanOrEqualTo(Integer value) {
            addCriterion("fake_y <=", value, "fakeY");
            return (Criteria) this;
        }

        public Criteria andFakeYIn(List<Integer> values) {
            addCriterion("fake_y in", values, "fakeY");
            return (Criteria) this;
        }

        public Criteria andFakeYNotIn(List<Integer> values) {
            addCriterion("fake_y not in", values, "fakeY");
            return (Criteria) this;
        }

        public Criteria andFakeYBetween(Integer value1, Integer value2) {
            addCriterion("fake_y between", value1, value2, "fakeY");
            return (Criteria) this;
        }

        public Criteria andFakeYNotBetween(Integer value1, Integer value2) {
            addCriterion("fake_y not between", value1, value2, "fakeY");
            return (Criteria) this;
        }

        public Criteria andStudentnumIsNull() {
            addCriterion("studentNum is null");
            return (Criteria) this;
        }

        public Criteria andStudentnumIsNotNull() {
            addCriterion("studentNum is not null");
            return (Criteria) this;
        }

        public Criteria andStudentnumEqualTo(Integer value) {
            addCriterion("studentNum =", value, "studentnum");
            return (Criteria) this;
        }

        public Criteria andStudentnumNotEqualTo(Integer value) {
            addCriterion("studentNum <>", value, "studentnum");
            return (Criteria) this;
        }

        public Criteria andStudentnumGreaterThan(Integer value) {
            addCriterion("studentNum >", value, "studentnum");
            return (Criteria) this;
        }

        public Criteria andStudentnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("studentNum >=", value, "studentnum");
            return (Criteria) this;
        }

        public Criteria andStudentnumLessThan(Integer value) {
            addCriterion("studentNum <", value, "studentnum");
            return (Criteria) this;
        }

        public Criteria andStudentnumLessThanOrEqualTo(Integer value) {
            addCriterion("studentNum <=", value, "studentnum");
            return (Criteria) this;
        }

        public Criteria andStudentnumIn(List<Integer> values) {
            addCriterion("studentNum in", values, "studentnum");
            return (Criteria) this;
        }

        public Criteria andStudentnumNotIn(List<Integer> values) {
            addCriterion("studentNum not in", values, "studentnum");
            return (Criteria) this;
        }

        public Criteria andStudentnumBetween(Integer value1, Integer value2) {
            addCriterion("studentNum between", value1, value2, "studentnum");
            return (Criteria) this;
        }

        public Criteria andStudentnumNotBetween(Integer value1, Integer value2) {
            addCriterion("studentNum not between", value1, value2, "studentnum");
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