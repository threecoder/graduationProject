package com.lutayy.campbackend.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberSubscriptionOrderExample {
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
    public MemberSubscriptionOrderExample() {
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

        public Criteria andOrderKeyIdIsNull() {
            addCriterion("order_key_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderKeyIdIsNotNull() {
            addCriterion("order_key_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderKeyIdEqualTo(Integer value) {
            addCriterion("order_key_id =", value, "orderKeyId");
            return (Criteria) this;
        }

        public Criteria andOrderKeyIdNotEqualTo(Integer value) {
            addCriterion("order_key_id <>", value, "orderKeyId");
            return (Criteria) this;
        }

        public Criteria andOrderKeyIdGreaterThan(Integer value) {
            addCriterion("order_key_id >", value, "orderKeyId");
            return (Criteria) this;
        }

        public Criteria andOrderKeyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_key_id >=", value, "orderKeyId");
            return (Criteria) this;
        }

        public Criteria andOrderKeyIdLessThan(Integer value) {
            addCriterion("order_key_id <", value, "orderKeyId");
            return (Criteria) this;
        }

        public Criteria andOrderKeyIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_key_id <=", value, "orderKeyId");
            return (Criteria) this;
        }

        public Criteria andOrderKeyIdIn(List<Integer> values) {
            addCriterion("order_key_id in", values, "orderKeyId");
            return (Criteria) this;
        }

        public Criteria andOrderKeyIdNotIn(List<Integer> values) {
            addCriterion("order_key_id not in", values, "orderKeyId");
            return (Criteria) this;
        }

        public Criteria andOrderKeyIdBetween(Integer value1, Integer value2) {
            addCriterion("order_key_id between", value1, value2, "orderKeyId");
            return (Criteria) this;
        }

        public Criteria andOrderKeyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_key_id not between", value1, value2, "orderKeyId");
            return (Criteria) this;
        }

        public Criteria andSubscriptionOrderIdIsNull() {
            addCriterion("subscription_order_id is null");
            return (Criteria) this;
        }

        public Criteria andSubscriptionOrderIdIsNotNull() {
            addCriterion("subscription_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubscriptionOrderIdEqualTo(String value) {
            addCriterion("subscription_order_id =", value, "subscriptionOrderId");
            return (Criteria) this;
        }

        public Criteria andSubscriptionOrderIdNotEqualTo(String value) {
            addCriterion("subscription_order_id <>", value, "subscriptionOrderId");
            return (Criteria) this;
        }

        public Criteria andSubscriptionOrderIdGreaterThan(String value) {
            addCriterion("subscription_order_id >", value, "subscriptionOrderId");
            return (Criteria) this;
        }

        public Criteria andSubscriptionOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("subscription_order_id >=", value, "subscriptionOrderId");
            return (Criteria) this;
        }

        public Criteria andSubscriptionOrderIdLessThan(String value) {
            addCriterion("subscription_order_id <", value, "subscriptionOrderId");
            return (Criteria) this;
        }

        public Criteria andSubscriptionOrderIdLessThanOrEqualTo(String value) {
            addCriterion("subscription_order_id <=", value, "subscriptionOrderId");
            return (Criteria) this;
        }

        public Criteria andSubscriptionOrderIdLike(String value) {
            addCriterion("subscription_order_id like", value, "subscriptionOrderId");
            return (Criteria) this;
        }

        public Criteria andSubscriptionOrderIdNotLike(String value) {
            addCriterion("subscription_order_id not like", value, "subscriptionOrderId");
            return (Criteria) this;
        }

        public Criteria andSubscriptionOrderIdIn(List<String> values) {
            addCriterion("subscription_order_id in", values, "subscriptionOrderId");
            return (Criteria) this;
        }

        public Criteria andSubscriptionOrderIdNotIn(List<String> values) {
            addCriterion("subscription_order_id not in", values, "subscriptionOrderId");
            return (Criteria) this;
        }

        public Criteria andSubscriptionOrderIdBetween(String value1, String value2) {
            addCriterion("subscription_order_id between", value1, value2, "subscriptionOrderId");
            return (Criteria) this;
        }

        public Criteria andSubscriptionOrderIdNotBetween(String value1, String value2) {
            addCriterion("subscription_order_id not between", value1, value2, "subscriptionOrderId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdIsNull() {
            addCriterion("member_key_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdIsNotNull() {
            addCriterion("member_key_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdEqualTo(Integer value) {
            addCriterion("member_key_id =", value, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdNotEqualTo(Integer value) {
            addCriterion("member_key_id <>", value, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdGreaterThan(Integer value) {
            addCriterion("member_key_id >", value, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_key_id >=", value, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdLessThan(Integer value) {
            addCriterion("member_key_id <", value, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_key_id <=", value, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdIn(List<Integer> values) {
            addCriterion("member_key_id in", values, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdNotIn(List<Integer> values) {
            addCriterion("member_key_id not in", values, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdBetween(Integer value1, Integer value2) {
            addCriterion("member_key_id between", value1, value2, "memberKeyId");
            return (Criteria) this;
        }

        public Criteria andMemberKeyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_key_id not between", value1, value2, "memberKeyId");
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

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(BigDecimal value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(BigDecimal value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(BigDecimal value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(BigDecimal value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<BigDecimal> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<BigDecimal> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andPaymentStateIsNull() {
            addCriterion("payment_state is null");
            return (Criteria) this;
        }

        public Criteria andPaymentStateIsNotNull() {
            addCriterion("payment_state is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentStateEqualTo(Boolean value) {
            addCriterion("payment_state =", value, "paymentState");
            return (Criteria) this;
        }

        public Criteria andPaymentStateNotEqualTo(Boolean value) {
            addCriterion("payment_state <>", value, "paymentState");
            return (Criteria) this;
        }

        public Criteria andPaymentStateGreaterThan(Boolean value) {
            addCriterion("payment_state >", value, "paymentState");
            return (Criteria) this;
        }

        public Criteria andPaymentStateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("payment_state >=", value, "paymentState");
            return (Criteria) this;
        }

        public Criteria andPaymentStateLessThan(Boolean value) {
            addCriterion("payment_state <", value, "paymentState");
            return (Criteria) this;
        }

        public Criteria andPaymentStateLessThanOrEqualTo(Boolean value) {
            addCriterion("payment_state <=", value, "paymentState");
            return (Criteria) this;
        }

        public Criteria andPaymentStateIn(List<Boolean> values) {
            addCriterion("payment_state in", values, "paymentState");
            return (Criteria) this;
        }

        public Criteria andPaymentStateNotIn(List<Boolean> values) {
            addCriterion("payment_state not in", values, "paymentState");
            return (Criteria) this;
        }

        public Criteria andPaymentStateBetween(Boolean value1, Boolean value2) {
            addCriterion("payment_state between", value1, value2, "paymentState");
            return (Criteria) this;
        }

        public Criteria andPaymentStateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("payment_state not between", value1, value2, "paymentState");
            return (Criteria) this;
        }

        public Criteria andCloseIsNull() {
            addCriterion("close is null");
            return (Criteria) this;
        }

        public Criteria andCloseIsNotNull() {
            addCriterion("close is not null");
            return (Criteria) this;
        }

        public Criteria andCloseEqualTo(Boolean value) {
            addCriterion("close =", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseNotEqualTo(Boolean value) {
            addCriterion("close <>", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseGreaterThan(Boolean value) {
            addCriterion("close >", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseGreaterThanOrEqualTo(Boolean value) {
            addCriterion("close >=", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseLessThan(Boolean value) {
            addCriterion("close <", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseLessThanOrEqualTo(Boolean value) {
            addCriterion("close <=", value, "close");
            return (Criteria) this;
        }

        public Criteria andCloseIn(List<Boolean> values) {
            addCriterion("close in", values, "close");
            return (Criteria) this;
        }

        public Criteria andCloseNotIn(List<Boolean> values) {
            addCriterion("close not in", values, "close");
            return (Criteria) this;
        }

        public Criteria andCloseBetween(Boolean value1, Boolean value2) {
            addCriterion("close between", value1, value2, "close");
            return (Criteria) this;
        }

        public Criteria andCloseNotBetween(Boolean value1, Boolean value2) {
            addCriterion("close not between", value1, value2, "close");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andIsDeleteUserIsNull() {
            addCriterion("is_delete_user is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteUserIsNotNull() {
            addCriterion("is_delete_user is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteUserEqualTo(Boolean value) {
            addCriterion("is_delete_user =", value, "isDeleteUser");
            return (Criteria) this;
        }

        public Criteria andIsDeleteUserNotEqualTo(Boolean value) {
            addCriterion("is_delete_user <>", value, "isDeleteUser");
            return (Criteria) this;
        }

        public Criteria andIsDeleteUserGreaterThan(Boolean value) {
            addCriterion("is_delete_user >", value, "isDeleteUser");
            return (Criteria) this;
        }

        public Criteria andIsDeleteUserGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete_user >=", value, "isDeleteUser");
            return (Criteria) this;
        }

        public Criteria andIsDeleteUserLessThan(Boolean value) {
            addCriterion("is_delete_user <", value, "isDeleteUser");
            return (Criteria) this;
        }

        public Criteria andIsDeleteUserLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete_user <=", value, "isDeleteUser");
            return (Criteria) this;
        }

        public Criteria andIsDeleteUserIn(List<Boolean> values) {
            addCriterion("is_delete_user in", values, "isDeleteUser");
            return (Criteria) this;
        }

        public Criteria andIsDeleteUserNotIn(List<Boolean> values) {
            addCriterion("is_delete_user not in", values, "isDeleteUser");
            return (Criteria) this;
        }

        public Criteria andIsDeleteUserBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete_user between", value1, value2, "isDeleteUser");
            return (Criteria) this;
        }

        public Criteria andIsDeleteUserNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete_user not between", value1, value2, "isDeleteUser");
            return (Criteria) this;
        }

        public Criteria andIsDeleteAdminIsNull() {
            addCriterion("is_delete_admin is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteAdminIsNotNull() {
            addCriterion("is_delete_admin is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteAdminEqualTo(Boolean value) {
            addCriterion("is_delete_admin =", value, "isDeleteAdmin");
            return (Criteria) this;
        }

        public Criteria andIsDeleteAdminNotEqualTo(Boolean value) {
            addCriterion("is_delete_admin <>", value, "isDeleteAdmin");
            return (Criteria) this;
        }

        public Criteria andIsDeleteAdminGreaterThan(Boolean value) {
            addCriterion("is_delete_admin >", value, "isDeleteAdmin");
            return (Criteria) this;
        }

        public Criteria andIsDeleteAdminGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete_admin >=", value, "isDeleteAdmin");
            return (Criteria) this;
        }

        public Criteria andIsDeleteAdminLessThan(Boolean value) {
            addCriterion("is_delete_admin <", value, "isDeleteAdmin");
            return (Criteria) this;
        }

        public Criteria andIsDeleteAdminLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete_admin <=", value, "isDeleteAdmin");
            return (Criteria) this;
        }

        public Criteria andIsDeleteAdminIn(List<Boolean> values) {
            addCriterion("is_delete_admin in", values, "isDeleteAdmin");
            return (Criteria) this;
        }

        public Criteria andIsDeleteAdminNotIn(List<Boolean> values) {
            addCriterion("is_delete_admin not in", values, "isDeleteAdmin");
            return (Criteria) this;
        }

        public Criteria andIsDeleteAdminBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete_admin between", value1, value2, "isDeleteAdmin");
            return (Criteria) this;
        }

        public Criteria andIsDeleteAdminNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete_admin not between", value1, value2, "isDeleteAdmin");
            return (Criteria) this;
        }

        public Criteria andOpManNameIsNull() {
            addCriterion("op_man_name is null");
            return (Criteria) this;
        }

        public Criteria andOpManNameIsNotNull() {
            addCriterion("op_man_name is not null");
            return (Criteria) this;
        }

        public Criteria andOpManNameEqualTo(String value) {
            addCriterion("op_man_name =", value, "opManName");
            return (Criteria) this;
        }

        public Criteria andOpManNameNotEqualTo(String value) {
            addCriterion("op_man_name <>", value, "opManName");
            return (Criteria) this;
        }

        public Criteria andOpManNameGreaterThan(String value) {
            addCriterion("op_man_name >", value, "opManName");
            return (Criteria) this;
        }

        public Criteria andOpManNameGreaterThanOrEqualTo(String value) {
            addCriterion("op_man_name >=", value, "opManName");
            return (Criteria) this;
        }

        public Criteria andOpManNameLessThan(String value) {
            addCriterion("op_man_name <", value, "opManName");
            return (Criteria) this;
        }

        public Criteria andOpManNameLessThanOrEqualTo(String value) {
            addCriterion("op_man_name <=", value, "opManName");
            return (Criteria) this;
        }

        public Criteria andOpManNameLike(String value) {
            addCriterion("op_man_name like", value, "opManName");
            return (Criteria) this;
        }

        public Criteria andOpManNameNotLike(String value) {
            addCriterion("op_man_name not like", value, "opManName");
            return (Criteria) this;
        }

        public Criteria andOpManNameIn(List<String> values) {
            addCriterion("op_man_name in", values, "opManName");
            return (Criteria) this;
        }

        public Criteria andOpManNameNotIn(List<String> values) {
            addCriterion("op_man_name not in", values, "opManName");
            return (Criteria) this;
        }

        public Criteria andOpManNameBetween(String value1, String value2) {
            addCriterion("op_man_name between", value1, value2, "opManName");
            return (Criteria) this;
        }

        public Criteria andOpManNameNotBetween(String value1, String value2) {
            addCriterion("op_man_name not between", value1, value2, "opManName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIsNull() {
            addCriterion("business_name is null");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIsNotNull() {
            addCriterion("business_name is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessNameEqualTo(String value) {
            addCriterion("business_name =", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotEqualTo(String value) {
            addCriterion("business_name <>", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameGreaterThan(String value) {
            addCriterion("business_name >", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameGreaterThanOrEqualTo(String value) {
            addCriterion("business_name >=", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLessThan(String value) {
            addCriterion("business_name <", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLessThanOrEqualTo(String value) {
            addCriterion("business_name <=", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLike(String value) {
            addCriterion("business_name like", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotLike(String value) {
            addCriterion("business_name not like", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIn(List<String> values) {
            addCriterion("business_name in", values, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotIn(List<String> values) {
            addCriterion("business_name not in", values, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameBetween(String value1, String value2) {
            addCriterion("business_name between", value1, value2, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotBetween(String value1, String value2) {
            addCriterion("business_name not between", value1, value2, "businessName");
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