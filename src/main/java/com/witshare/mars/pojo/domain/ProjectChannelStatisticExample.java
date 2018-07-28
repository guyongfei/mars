package com.witshare.mars.pojo.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProjectChannelStatisticExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectChannelStatisticExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProjectGidIsNull() {
            addCriterion("project_gid is null");
            return (Criteria) this;
        }

        public Criteria andProjectGidIsNotNull() {
            addCriterion("project_gid is not null");
            return (Criteria) this;
        }

        public Criteria andProjectGidEqualTo(String value) {
            addCriterion("project_gid =", value, "projectGid");
            return (Criteria) this;
        }

        public Criteria andProjectGidNotEqualTo(String value) {
            addCriterion("project_gid <>", value, "projectGid");
            return (Criteria) this;
        }

        public Criteria andProjectGidGreaterThan(String value) {
            addCriterion("project_gid >", value, "projectGid");
            return (Criteria) this;
        }

        public Criteria andProjectGidGreaterThanOrEqualTo(String value) {
            addCriterion("project_gid >=", value, "projectGid");
            return (Criteria) this;
        }

        public Criteria andProjectGidLessThan(String value) {
            addCriterion("project_gid <", value, "projectGid");
            return (Criteria) this;
        }

        public Criteria andProjectGidLessThanOrEqualTo(String value) {
            addCriterion("project_gid <=", value, "projectGid");
            return (Criteria) this;
        }

        public Criteria andProjectGidLike(String value) {
            addCriterion("project_gid like", value, "projectGid");
            return (Criteria) this;
        }

        public Criteria andProjectGidNotLike(String value) {
            addCriterion("project_gid not like", value, "projectGid");
            return (Criteria) this;
        }

        public Criteria andProjectGidIn(List<String> values) {
            addCriterion("project_gid in", values, "projectGid");
            return (Criteria) this;
        }

        public Criteria andProjectGidNotIn(List<String> values) {
            addCriterion("project_gid not in", values, "projectGid");
            return (Criteria) this;
        }

        public Criteria andProjectGidBetween(String value1, String value2) {
            addCriterion("project_gid between", value1, value2, "projectGid");
            return (Criteria) this;
        }

        public Criteria andProjectGidNotBetween(String value1, String value2) {
            addCriterion("project_gid not between", value1, value2, "projectGid");
            return (Criteria) this;
        }

        public Criteria andProjectTokenIsNull() {
            addCriterion("project_token is null");
            return (Criteria) this;
        }

        public Criteria andProjectTokenIsNotNull() {
            addCriterion("project_token is not null");
            return (Criteria) this;
        }

        public Criteria andProjectTokenEqualTo(String value) {
            addCriterion("project_token =", value, "projectToken");
            return (Criteria) this;
        }

        public Criteria andProjectTokenNotEqualTo(String value) {
            addCriterion("project_token <>", value, "projectToken");
            return (Criteria) this;
        }

        public Criteria andProjectTokenGreaterThan(String value) {
            addCriterion("project_token >", value, "projectToken");
            return (Criteria) this;
        }

        public Criteria andProjectTokenGreaterThanOrEqualTo(String value) {
            addCriterion("project_token >=", value, "projectToken");
            return (Criteria) this;
        }

        public Criteria andProjectTokenLessThan(String value) {
            addCriterion("project_token <", value, "projectToken");
            return (Criteria) this;
        }

        public Criteria andProjectTokenLessThanOrEqualTo(String value) {
            addCriterion("project_token <=", value, "projectToken");
            return (Criteria) this;
        }

        public Criteria andProjectTokenLike(String value) {
            addCriterion("project_token like", value, "projectToken");
            return (Criteria) this;
        }

        public Criteria andProjectTokenNotLike(String value) {
            addCriterion("project_token not like", value, "projectToken");
            return (Criteria) this;
        }

        public Criteria andProjectTokenIn(List<String> values) {
            addCriterion("project_token in", values, "projectToken");
            return (Criteria) this;
        }

        public Criteria andProjectTokenNotIn(List<String> values) {
            addCriterion("project_token not in", values, "projectToken");
            return (Criteria) this;
        }

        public Criteria andProjectTokenBetween(String value1, String value2) {
            addCriterion("project_token between", value1, value2, "projectToken");
            return (Criteria) this;
        }

        public Criteria andProjectTokenNotBetween(String value1, String value2) {
            addCriterion("project_token not between", value1, value2, "projectToken");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNull() {
            addCriterion("channel_name is null");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNotNull() {
            addCriterion("channel_name is not null");
            return (Criteria) this;
        }

        public Criteria andChannelNameEqualTo(String value) {
            addCriterion("channel_name =", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotEqualTo(String value) {
            addCriterion("channel_name <>", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThan(String value) {
            addCriterion("channel_name >", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThanOrEqualTo(String value) {
            addCriterion("channel_name >=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThan(String value) {
            addCriterion("channel_name <", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThanOrEqualTo(String value) {
            addCriterion("channel_name <=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLike(String value) {
            addCriterion("channel_name like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotLike(String value) {
            addCriterion("channel_name not like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameIn(List<String> values) {
            addCriterion("channel_name in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotIn(List<String> values) {
            addCriterion("channel_name not in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameBetween(String value1, String value2) {
            addCriterion("channel_name between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotBetween(String value1, String value2) {
            addCriterion("channel_name not between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(String value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(String value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(String value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(String value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(String value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<String> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andGetEthAmountIsNull() {
            addCriterion("get_eth_amount is null");
            return (Criteria) this;
        }

        public Criteria andGetEthAmountIsNotNull() {
            addCriterion("get_eth_amount is not null");
            return (Criteria) this;
        }

        public Criteria andGetEthAmountEqualTo(BigDecimal value) {
            addCriterion("get_eth_amount =", value, "getEthAmount");
            return (Criteria) this;
        }

        public Criteria andGetEthAmountNotEqualTo(BigDecimal value) {
            addCriterion("get_eth_amount <>", value, "getEthAmount");
            return (Criteria) this;
        }

        public Criteria andGetEthAmountGreaterThan(BigDecimal value) {
            addCriterion("get_eth_amount >", value, "getEthAmount");
            return (Criteria) this;
        }

        public Criteria andGetEthAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("get_eth_amount >=", value, "getEthAmount");
            return (Criteria) this;
        }

        public Criteria andGetEthAmountLessThan(BigDecimal value) {
            addCriterion("get_eth_amount <", value, "getEthAmount");
            return (Criteria) this;
        }

        public Criteria andGetEthAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("get_eth_amount <=", value, "getEthAmount");
            return (Criteria) this;
        }

        public Criteria andGetEthAmountIn(List<BigDecimal> values) {
            addCriterion("get_eth_amount in", values, "getEthAmount");
            return (Criteria) this;
        }

        public Criteria andGetEthAmountNotIn(List<BigDecimal> values) {
            addCriterion("get_eth_amount not in", values, "getEthAmount");
            return (Criteria) this;
        }

        public Criteria andGetEthAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("get_eth_amount between", value1, value2, "getEthAmount");
            return (Criteria) this;
        }

        public Criteria andGetEthAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("get_eth_amount not between", value1, value2, "getEthAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetEthAmountIsNull() {
            addCriterion("actual_get_eth_amount is null");
            return (Criteria) this;
        }

        public Criteria andActualGetEthAmountIsNotNull() {
            addCriterion("actual_get_eth_amount is not null");
            return (Criteria) this;
        }

        public Criteria andActualGetEthAmountEqualTo(BigDecimal value) {
            addCriterion("actual_get_eth_amount =", value, "actualGetEthAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetEthAmountNotEqualTo(BigDecimal value) {
            addCriterion("actual_get_eth_amount <>", value, "actualGetEthAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetEthAmountGreaterThan(BigDecimal value) {
            addCriterion("actual_get_eth_amount >", value, "actualGetEthAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetEthAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_get_eth_amount >=", value, "actualGetEthAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetEthAmountLessThan(BigDecimal value) {
            addCriterion("actual_get_eth_amount <", value, "actualGetEthAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetEthAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_get_eth_amount <=", value, "actualGetEthAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetEthAmountIn(List<BigDecimal> values) {
            addCriterion("actual_get_eth_amount in", values, "actualGetEthAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetEthAmountNotIn(List<BigDecimal> values) {
            addCriterion("actual_get_eth_amount not in", values, "actualGetEthAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetEthAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_get_eth_amount between", value1, value2, "actualGetEthAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetEthAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_get_eth_amount not between", value1, value2, "actualGetEthAmount");
            return (Criteria) this;
        }

        public Criteria andPayTokenAmountIsNull() {
            addCriterion("pay_token_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayTokenAmountIsNotNull() {
            addCriterion("pay_token_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayTokenAmountEqualTo(BigDecimal value) {
            addCriterion("pay_token_amount =", value, "payTokenAmount");
            return (Criteria) this;
        }

        public Criteria andPayTokenAmountNotEqualTo(BigDecimal value) {
            addCriterion("pay_token_amount <>", value, "payTokenAmount");
            return (Criteria) this;
        }

        public Criteria andPayTokenAmountGreaterThan(BigDecimal value) {
            addCriterion("pay_token_amount >", value, "payTokenAmount");
            return (Criteria) this;
        }

        public Criteria andPayTokenAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_token_amount >=", value, "payTokenAmount");
            return (Criteria) this;
        }

        public Criteria andPayTokenAmountLessThan(BigDecimal value) {
            addCriterion("pay_token_amount <", value, "payTokenAmount");
            return (Criteria) this;
        }

        public Criteria andPayTokenAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_token_amount <=", value, "payTokenAmount");
            return (Criteria) this;
        }

        public Criteria andPayTokenAmountIn(List<BigDecimal> values) {
            addCriterion("pay_token_amount in", values, "payTokenAmount");
            return (Criteria) this;
        }

        public Criteria andPayTokenAmountNotIn(List<BigDecimal> values) {
            addCriterion("pay_token_amount not in", values, "payTokenAmount");
            return (Criteria) this;
        }

        public Criteria andPayTokenAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_token_amount between", value1, value2, "payTokenAmount");
            return (Criteria) this;
        }

        public Criteria andPayTokenAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_token_amount not between", value1, value2, "payTokenAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayTokenAmountIsNull() {
            addCriterion("actual_pay_token_amount is null");
            return (Criteria) this;
        }

        public Criteria andActualPayTokenAmountIsNotNull() {
            addCriterion("actual_pay_token_amount is not null");
            return (Criteria) this;
        }

        public Criteria andActualPayTokenAmountEqualTo(BigDecimal value) {
            addCriterion("actual_pay_token_amount =", value, "actualPayTokenAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayTokenAmountNotEqualTo(BigDecimal value) {
            addCriterion("actual_pay_token_amount <>", value, "actualPayTokenAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayTokenAmountGreaterThan(BigDecimal value) {
            addCriterion("actual_pay_token_amount >", value, "actualPayTokenAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayTokenAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_pay_token_amount >=", value, "actualPayTokenAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayTokenAmountLessThan(BigDecimal value) {
            addCriterion("actual_pay_token_amount <", value, "actualPayTokenAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayTokenAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_pay_token_amount <=", value, "actualPayTokenAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayTokenAmountIn(List<BigDecimal> values) {
            addCriterion("actual_pay_token_amount in", values, "actualPayTokenAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayTokenAmountNotIn(List<BigDecimal> values) {
            addCriterion("actual_pay_token_amount not in", values, "actualPayTokenAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayTokenAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_pay_token_amount between", value1, value2, "actualPayTokenAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayTokenAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_pay_token_amount not between", value1, value2, "actualPayTokenAmount");
            return (Criteria) this;
        }

        public Criteria andUserCountIsNull() {
            addCriterion("user_count is null");
            return (Criteria) this;
        }

        public Criteria andUserCountIsNotNull() {
            addCriterion("user_count is not null");
            return (Criteria) this;
        }

        public Criteria andUserCountEqualTo(Integer value) {
            addCriterion("user_count =", value, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountNotEqualTo(Integer value) {
            addCriterion("user_count <>", value, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountGreaterThan(Integer value) {
            addCriterion("user_count >", value, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_count >=", value, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountLessThan(Integer value) {
            addCriterion("user_count <", value, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountLessThanOrEqualTo(Integer value) {
            addCriterion("user_count <=", value, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountIn(List<Integer> values) {
            addCriterion("user_count in", values, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountNotIn(List<Integer> values) {
            addCriterion("user_count not in", values, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountBetween(Integer value1, Integer value2) {
            addCriterion("user_count between", value1, value2, "userCount");
            return (Criteria) this;
        }

        public Criteria andUserCountNotBetween(Integer value1, Integer value2) {
            addCriterion("user_count not between", value1, value2, "userCount");
            return (Criteria) this;
        }

        public Criteria andActualUserCountIsNull() {
            addCriterion("actual_user_count is null");
            return (Criteria) this;
        }

        public Criteria andActualUserCountIsNotNull() {
            addCriterion("actual_user_count is not null");
            return (Criteria) this;
        }

        public Criteria andActualUserCountEqualTo(Integer value) {
            addCriterion("actual_user_count =", value, "actualUserCount");
            return (Criteria) this;
        }

        public Criteria andActualUserCountNotEqualTo(Integer value) {
            addCriterion("actual_user_count <>", value, "actualUserCount");
            return (Criteria) this;
        }

        public Criteria andActualUserCountGreaterThan(Integer value) {
            addCriterion("actual_user_count >", value, "actualUserCount");
            return (Criteria) this;
        }

        public Criteria andActualUserCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("actual_user_count >=", value, "actualUserCount");
            return (Criteria) this;
        }

        public Criteria andActualUserCountLessThan(Integer value) {
            addCriterion("actual_user_count <", value, "actualUserCount");
            return (Criteria) this;
        }

        public Criteria andActualUserCountLessThanOrEqualTo(Integer value) {
            addCriterion("actual_user_count <=", value, "actualUserCount");
            return (Criteria) this;
        }

        public Criteria andActualUserCountIn(List<Integer> values) {
            addCriterion("actual_user_count in", values, "actualUserCount");
            return (Criteria) this;
        }

        public Criteria andActualUserCountNotIn(List<Integer> values) {
            addCriterion("actual_user_count not in", values, "actualUserCount");
            return (Criteria) this;
        }

        public Criteria andActualUserCountBetween(Integer value1, Integer value2) {
            addCriterion("actual_user_count between", value1, value2, "actualUserCount");
            return (Criteria) this;
        }

        public Criteria andActualUserCountNotBetween(Integer value1, Integer value2) {
            addCriterion("actual_user_count not between", value1, value2, "actualUserCount");
            return (Criteria) this;
        }

        public Criteria andTxCountIsNull() {
            addCriterion("tx_count is null");
            return (Criteria) this;
        }

        public Criteria andTxCountIsNotNull() {
            addCriterion("tx_count is not null");
            return (Criteria) this;
        }

        public Criteria andTxCountEqualTo(Integer value) {
            addCriterion("tx_count =", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountNotEqualTo(Integer value) {
            addCriterion("tx_count <>", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountGreaterThan(Integer value) {
            addCriterion("tx_count >", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("tx_count >=", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountLessThan(Integer value) {
            addCriterion("tx_count <", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountLessThanOrEqualTo(Integer value) {
            addCriterion("tx_count <=", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountIn(List<Integer> values) {
            addCriterion("tx_count in", values, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountNotIn(List<Integer> values) {
            addCriterion("tx_count not in", values, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountBetween(Integer value1, Integer value2) {
            addCriterion("tx_count between", value1, value2, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountNotBetween(Integer value1, Integer value2) {
            addCriterion("tx_count not between", value1, value2, "txCount");
            return (Criteria) this;
        }

        public Criteria andActualTxCountIsNull() {
            addCriterion("actual_tx_count is null");
            return (Criteria) this;
        }

        public Criteria andActualTxCountIsNotNull() {
            addCriterion("actual_tx_count is not null");
            return (Criteria) this;
        }

        public Criteria andActualTxCountEqualTo(Integer value) {
            addCriterion("actual_tx_count =", value, "actualTxCount");
            return (Criteria) this;
        }

        public Criteria andActualTxCountNotEqualTo(Integer value) {
            addCriterion("actual_tx_count <>", value, "actualTxCount");
            return (Criteria) this;
        }

        public Criteria andActualTxCountGreaterThan(Integer value) {
            addCriterion("actual_tx_count >", value, "actualTxCount");
            return (Criteria) this;
        }

        public Criteria andActualTxCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("actual_tx_count >=", value, "actualTxCount");
            return (Criteria) this;
        }

        public Criteria andActualTxCountLessThan(Integer value) {
            addCriterion("actual_tx_count <", value, "actualTxCount");
            return (Criteria) this;
        }

        public Criteria andActualTxCountLessThanOrEqualTo(Integer value) {
            addCriterion("actual_tx_count <=", value, "actualTxCount");
            return (Criteria) this;
        }

        public Criteria andActualTxCountIn(List<Integer> values) {
            addCriterion("actual_tx_count in", values, "actualTxCount");
            return (Criteria) this;
        }

        public Criteria andActualTxCountNotIn(List<Integer> values) {
            addCriterion("actual_tx_count not in", values, "actualTxCount");
            return (Criteria) this;
        }

        public Criteria andActualTxCountBetween(Integer value1, Integer value2) {
            addCriterion("actual_tx_count between", value1, value2, "actualTxCount");
            return (Criteria) this;
        }

        public Criteria andActualTxCountNotBetween(Integer value1, Integer value2) {
            addCriterion("actual_tx_count not between", value1, value2, "actualTxCount");
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

        public Criteria andCreateTimeEqualTo(Timestamp value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Timestamp value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Timestamp value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Timestamp value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Timestamp> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Timestamp> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Timestamp value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Timestamp value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Timestamp value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Timestamp value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Timestamp> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Timestamp> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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