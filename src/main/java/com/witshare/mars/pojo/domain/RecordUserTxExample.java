package com.witshare.mars.pojo.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RecordUserTxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecordUserTxExample() {
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

        public Criteria andUserGidIsNull() {
            addCriterion("user_gid is null");
            return (Criteria) this;
        }

        public Criteria andUserGidIsNotNull() {
            addCriterion("user_gid is not null");
            return (Criteria) this;
        }

        public Criteria andUserGidEqualTo(String value) {
            addCriterion("user_gid =", value, "userGid");
            return (Criteria) this;
        }

        public Criteria andUserGidNotEqualTo(String value) {
            addCriterion("user_gid <>", value, "userGid");
            return (Criteria) this;
        }

        public Criteria andUserGidGreaterThan(String value) {
            addCriterion("user_gid >", value, "userGid");
            return (Criteria) this;
        }

        public Criteria andUserGidGreaterThanOrEqualTo(String value) {
            addCriterion("user_gid >=", value, "userGid");
            return (Criteria) this;
        }

        public Criteria andUserGidLessThan(String value) {
            addCriterion("user_gid <", value, "userGid");
            return (Criteria) this;
        }

        public Criteria andUserGidLessThanOrEqualTo(String value) {
            addCriterion("user_gid <=", value, "userGid");
            return (Criteria) this;
        }

        public Criteria andUserGidLike(String value) {
            addCriterion("user_gid like", value, "userGid");
            return (Criteria) this;
        }

        public Criteria andUserGidNotLike(String value) {
            addCriterion("user_gid not like", value, "userGid");
            return (Criteria) this;
        }

        public Criteria andUserGidIn(List<String> values) {
            addCriterion("user_gid in", values, "userGid");
            return (Criteria) this;
        }

        public Criteria andUserGidNotIn(List<String> values) {
            addCriterion("user_gid not in", values, "userGid");
            return (Criteria) this;
        }

        public Criteria andUserGidBetween(String value1, String value2) {
            addCriterion("user_gid between", value1, value2, "userGid");
            return (Criteria) this;
        }

        public Criteria andUserGidNotBetween(String value1, String value2) {
            addCriterion("user_gid not between", value1, value2, "userGid");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNull() {
            addCriterion("user_email is null");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNotNull() {
            addCriterion("user_email is not null");
            return (Criteria) this;
        }

        public Criteria andUserEmailEqualTo(String value) {
            addCriterion("user_email =", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotEqualTo(String value) {
            addCriterion("user_email <>", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThan(String value) {
            addCriterion("user_email >", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThanOrEqualTo(String value) {
            addCriterion("user_email >=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThan(String value) {
            addCriterion("user_email <", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThanOrEqualTo(String value) {
            addCriterion("user_email <=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLike(String value) {
            addCriterion("user_email like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotLike(String value) {
            addCriterion("user_email not like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailIn(List<String> values) {
            addCriterion("user_email in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotIn(List<String> values) {
            addCriterion("user_email not in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailBetween(String value1, String value2) {
            addCriterion("user_email between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotBetween(String value1, String value2) {
            addCriterion("user_email not between", value1, value2, "userEmail");
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

        public Criteria andPayCoinTypeIsNull() {
            addCriterion("pay_coin_type is null");
            return (Criteria) this;
        }

        public Criteria andPayCoinTypeIsNotNull() {
            addCriterion("pay_coin_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayCoinTypeEqualTo(Integer value) {
            addCriterion("pay_coin_type =", value, "payCoinType");
            return (Criteria) this;
        }

        public Criteria andPayCoinTypeNotEqualTo(Integer value) {
            addCriterion("pay_coin_type <>", value, "payCoinType");
            return (Criteria) this;
        }

        public Criteria andPayCoinTypeGreaterThan(Integer value) {
            addCriterion("pay_coin_type >", value, "payCoinType");
            return (Criteria) this;
        }

        public Criteria andPayCoinTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_coin_type >=", value, "payCoinType");
            return (Criteria) this;
        }

        public Criteria andPayCoinTypeLessThan(Integer value) {
            addCriterion("pay_coin_type <", value, "payCoinType");
            return (Criteria) this;
        }

        public Criteria andPayCoinTypeLessThanOrEqualTo(Integer value) {
            addCriterion("pay_coin_type <=", value, "payCoinType");
            return (Criteria) this;
        }

        public Criteria andPayCoinTypeIn(List<Integer> values) {
            addCriterion("pay_coin_type in", values, "payCoinType");
            return (Criteria) this;
        }

        public Criteria andPayCoinTypeNotIn(List<Integer> values) {
            addCriterion("pay_coin_type not in", values, "payCoinType");
            return (Criteria) this;
        }

        public Criteria andPayCoinTypeBetween(Integer value1, Integer value2) {
            addCriterion("pay_coin_type between", value1, value2, "payCoinType");
            return (Criteria) this;
        }

        public Criteria andPayCoinTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_coin_type not between", value1, value2, "payCoinType");
            return (Criteria) this;
        }

        public Criteria andPayTxIsNull() {
            addCriterion("pay_tx is null");
            return (Criteria) this;
        }

        public Criteria andPayTxIsNotNull() {
            addCriterion("pay_tx is not null");
            return (Criteria) this;
        }

        public Criteria andPayTxEqualTo(String value) {
            addCriterion("pay_tx =", value, "payTx");
            return (Criteria) this;
        }

        public Criteria andPayTxNotEqualTo(String value) {
            addCriterion("pay_tx <>", value, "payTx");
            return (Criteria) this;
        }

        public Criteria andPayTxGreaterThan(String value) {
            addCriterion("pay_tx >", value, "payTx");
            return (Criteria) this;
        }

        public Criteria andPayTxGreaterThanOrEqualTo(String value) {
            addCriterion("pay_tx >=", value, "payTx");
            return (Criteria) this;
        }

        public Criteria andPayTxLessThan(String value) {
            addCriterion("pay_tx <", value, "payTx");
            return (Criteria) this;
        }

        public Criteria andPayTxLessThanOrEqualTo(String value) {
            addCriterion("pay_tx <=", value, "payTx");
            return (Criteria) this;
        }

        public Criteria andPayTxLike(String value) {
            addCriterion("pay_tx like", value, "payTx");
            return (Criteria) this;
        }

        public Criteria andPayTxNotLike(String value) {
            addCriterion("pay_tx not like", value, "payTx");
            return (Criteria) this;
        }

        public Criteria andPayTxIn(List<String> values) {
            addCriterion("pay_tx in", values, "payTx");
            return (Criteria) this;
        }

        public Criteria andPayTxNotIn(List<String> values) {
            addCriterion("pay_tx not in", values, "payTx");
            return (Criteria) this;
        }

        public Criteria andPayTxBetween(String value1, String value2) {
            addCriterion("pay_tx between", value1, value2, "payTx");
            return (Criteria) this;
        }

        public Criteria andPayTxNotBetween(String value1, String value2) {
            addCriterion("pay_tx not between", value1, value2, "payTx");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNull() {
            addCriterion("pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNotNull() {
            addCriterion("pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountEqualTo(BigDecimal value) {
            addCriterion("pay_amount =", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotEqualTo(BigDecimal value) {
            addCriterion("pay_amount <>", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThan(BigDecimal value) {
            addCriterion("pay_amount >", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount >=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThan(BigDecimal value) {
            addCriterion("pay_amount <", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pay_amount <=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIn(List<BigDecimal> values) {
            addCriterion("pay_amount in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotIn(List<BigDecimal> values) {
            addCriterion("pay_amount not in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pay_amount not between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPriceRateIsNull() {
            addCriterion("price_rate is null");
            return (Criteria) this;
        }

        public Criteria andPriceRateIsNotNull() {
            addCriterion("price_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPriceRateEqualTo(BigDecimal value) {
            addCriterion("price_rate =", value, "priceRate");
            return (Criteria) this;
        }

        public Criteria andPriceRateNotEqualTo(BigDecimal value) {
            addCriterion("price_rate <>", value, "priceRate");
            return (Criteria) this;
        }

        public Criteria andPriceRateGreaterThan(BigDecimal value) {
            addCriterion("price_rate >", value, "priceRate");
            return (Criteria) this;
        }

        public Criteria andPriceRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price_rate >=", value, "priceRate");
            return (Criteria) this;
        }

        public Criteria andPriceRateLessThan(BigDecimal value) {
            addCriterion("price_rate <", value, "priceRate");
            return (Criteria) this;
        }

        public Criteria andPriceRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price_rate <=", value, "priceRate");
            return (Criteria) this;
        }

        public Criteria andPriceRateIn(List<BigDecimal> values) {
            addCriterion("price_rate in", values, "priceRate");
            return (Criteria) this;
        }

        public Criteria andPriceRateNotIn(List<BigDecimal> values) {
            addCriterion("price_rate not in", values, "priceRate");
            return (Criteria) this;
        }

        public Criteria andPriceRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price_rate between", value1, value2, "priceRate");
            return (Criteria) this;
        }

        public Criteria andPriceRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price_rate not between", value1, value2, "priceRate");
            return (Criteria) this;
        }

        public Criteria andHopeGetAmountIsNull() {
            addCriterion("hope_get_amount is null");
            return (Criteria) this;
        }

        public Criteria andHopeGetAmountIsNotNull() {
            addCriterion("hope_get_amount is not null");
            return (Criteria) this;
        }

        public Criteria andHopeGetAmountEqualTo(BigDecimal value) {
            addCriterion("hope_get_amount =", value, "hopeGetAmount");
            return (Criteria) this;
        }

        public Criteria andHopeGetAmountNotEqualTo(BigDecimal value) {
            addCriterion("hope_get_amount <>", value, "hopeGetAmount");
            return (Criteria) this;
        }

        public Criteria andHopeGetAmountGreaterThan(BigDecimal value) {
            addCriterion("hope_get_amount >", value, "hopeGetAmount");
            return (Criteria) this;
        }

        public Criteria andHopeGetAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("hope_get_amount >=", value, "hopeGetAmount");
            return (Criteria) this;
        }

        public Criteria andHopeGetAmountLessThan(BigDecimal value) {
            addCriterion("hope_get_amount <", value, "hopeGetAmount");
            return (Criteria) this;
        }

        public Criteria andHopeGetAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("hope_get_amount <=", value, "hopeGetAmount");
            return (Criteria) this;
        }

        public Criteria andHopeGetAmountIn(List<BigDecimal> values) {
            addCriterion("hope_get_amount in", values, "hopeGetAmount");
            return (Criteria) this;
        }

        public Criteria andHopeGetAmountNotIn(List<BigDecimal> values) {
            addCriterion("hope_get_amount not in", values, "hopeGetAmount");
            return (Criteria) this;
        }

        public Criteria andHopeGetAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hope_get_amount between", value1, value2, "hopeGetAmount");
            return (Criteria) this;
        }

        public Criteria andHopeGetAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hope_get_amount not between", value1, value2, "hopeGetAmount");
            return (Criteria) this;
        }

        public Criteria andShouldGetAmountIsNull() {
            addCriterion("should_get_amount is null");
            return (Criteria) this;
        }

        public Criteria andShouldGetAmountIsNotNull() {
            addCriterion("should_get_amount is not null");
            return (Criteria) this;
        }

        public Criteria andShouldGetAmountEqualTo(BigDecimal value) {
            addCriterion("should_get_amount =", value, "shouldGetAmount");
            return (Criteria) this;
        }

        public Criteria andShouldGetAmountNotEqualTo(BigDecimal value) {
            addCriterion("should_get_amount <>", value, "shouldGetAmount");
            return (Criteria) this;
        }

        public Criteria andShouldGetAmountGreaterThan(BigDecimal value) {
            addCriterion("should_get_amount >", value, "shouldGetAmount");
            return (Criteria) this;
        }

        public Criteria andShouldGetAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("should_get_amount >=", value, "shouldGetAmount");
            return (Criteria) this;
        }

        public Criteria andShouldGetAmountLessThan(BigDecimal value) {
            addCriterion("should_get_amount <", value, "shouldGetAmount");
            return (Criteria) this;
        }

        public Criteria andShouldGetAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("should_get_amount <=", value, "shouldGetAmount");
            return (Criteria) this;
        }

        public Criteria andShouldGetAmountIn(List<BigDecimal> values) {
            addCriterion("should_get_amount in", values, "shouldGetAmount");
            return (Criteria) this;
        }

        public Criteria andShouldGetAmountNotIn(List<BigDecimal> values) {
            addCriterion("should_get_amount not in", values, "shouldGetAmount");
            return (Criteria) this;
        }

        public Criteria andShouldGetAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("should_get_amount between", value1, value2, "shouldGetAmount");
            return (Criteria) this;
        }

        public Criteria andShouldGetAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("should_get_amount not between", value1, value2, "shouldGetAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayAmountIsNull() {
            addCriterion("actual_pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andActualPayAmountIsNotNull() {
            addCriterion("actual_pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andActualPayAmountEqualTo(BigDecimal value) {
            addCriterion("actual_pay_amount =", value, "actualPayAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayAmountNotEqualTo(BigDecimal value) {
            addCriterion("actual_pay_amount <>", value, "actualPayAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayAmountGreaterThan(BigDecimal value) {
            addCriterion("actual_pay_amount >", value, "actualPayAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_pay_amount >=", value, "actualPayAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayAmountLessThan(BigDecimal value) {
            addCriterion("actual_pay_amount <", value, "actualPayAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_pay_amount <=", value, "actualPayAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayAmountIn(List<BigDecimal> values) {
            addCriterion("actual_pay_amount in", values, "actualPayAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayAmountNotIn(List<BigDecimal> values) {
            addCriterion("actual_pay_amount not in", values, "actualPayAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_pay_amount between", value1, value2, "actualPayAmount");
            return (Criteria) this;
        }

        public Criteria andActualPayAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_pay_amount not between", value1, value2, "actualPayAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetAmountIsNull() {
            addCriterion("actual_get_amount is null");
            return (Criteria) this;
        }

        public Criteria andActualGetAmountIsNotNull() {
            addCriterion("actual_get_amount is not null");
            return (Criteria) this;
        }

        public Criteria andActualGetAmountEqualTo(BigDecimal value) {
            addCriterion("actual_get_amount =", value, "actualGetAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetAmountNotEqualTo(BigDecimal value) {
            addCriterion("actual_get_amount <>", value, "actualGetAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetAmountGreaterThan(BigDecimal value) {
            addCriterion("actual_get_amount >", value, "actualGetAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_get_amount >=", value, "actualGetAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetAmountLessThan(BigDecimal value) {
            addCriterion("actual_get_amount <", value, "actualGetAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_get_amount <=", value, "actualGetAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetAmountIn(List<BigDecimal> values) {
            addCriterion("actual_get_amount in", values, "actualGetAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetAmountNotIn(List<BigDecimal> values) {
            addCriterion("actual_get_amount not in", values, "actualGetAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_get_amount between", value1, value2, "actualGetAmount");
            return (Criteria) this;
        }

        public Criteria andActualGetAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_get_amount not between", value1, value2, "actualGetAmount");
            return (Criteria) this;
        }

        public Criteria andActualSendingAddressIsNull() {
            addCriterion("actual_sending_address is null");
            return (Criteria) this;
        }

        public Criteria andActualSendingAddressIsNotNull() {
            addCriterion("actual_sending_address is not null");
            return (Criteria) this;
        }

        public Criteria andActualSendingAddressEqualTo(String value) {
            addCriterion("actual_sending_address =", value, "actualSendingAddress");
            return (Criteria) this;
        }

        public Criteria andActualSendingAddressNotEqualTo(String value) {
            addCriterion("actual_sending_address <>", value, "actualSendingAddress");
            return (Criteria) this;
        }

        public Criteria andActualSendingAddressGreaterThan(String value) {
            addCriterion("actual_sending_address >", value, "actualSendingAddress");
            return (Criteria) this;
        }

        public Criteria andActualSendingAddressGreaterThanOrEqualTo(String value) {
            addCriterion("actual_sending_address >=", value, "actualSendingAddress");
            return (Criteria) this;
        }

        public Criteria andActualSendingAddressLessThan(String value) {
            addCriterion("actual_sending_address <", value, "actualSendingAddress");
            return (Criteria) this;
        }

        public Criteria andActualSendingAddressLessThanOrEqualTo(String value) {
            addCriterion("actual_sending_address <=", value, "actualSendingAddress");
            return (Criteria) this;
        }

        public Criteria andActualSendingAddressLike(String value) {
            addCriterion("actual_sending_address like", value, "actualSendingAddress");
            return (Criteria) this;
        }

        public Criteria andActualSendingAddressNotLike(String value) {
            addCriterion("actual_sending_address not like", value, "actualSendingAddress");
            return (Criteria) this;
        }

        public Criteria andActualSendingAddressIn(List<String> values) {
            addCriterion("actual_sending_address in", values, "actualSendingAddress");
            return (Criteria) this;
        }

        public Criteria andActualSendingAddressNotIn(List<String> values) {
            addCriterion("actual_sending_address not in", values, "actualSendingAddress");
            return (Criteria) this;
        }

        public Criteria andActualSendingAddressBetween(String value1, String value2) {
            addCriterion("actual_sending_address between", value1, value2, "actualSendingAddress");
            return (Criteria) this;
        }

        public Criteria andActualSendingAddressNotBetween(String value1, String value2) {
            addCriterion("actual_sending_address not between", value1, value2, "actualSendingAddress");
            return (Criteria) this;
        }

        public Criteria andActualReceivingAddressIsNull() {
            addCriterion("actual_receiving_address is null");
            return (Criteria) this;
        }

        public Criteria andActualReceivingAddressIsNotNull() {
            addCriterion("actual_receiving_address is not null");
            return (Criteria) this;
        }

        public Criteria andActualReceivingAddressEqualTo(String value) {
            addCriterion("actual_receiving_address =", value, "actualReceivingAddress");
            return (Criteria) this;
        }

        public Criteria andActualReceivingAddressNotEqualTo(String value) {
            addCriterion("actual_receiving_address <>", value, "actualReceivingAddress");
            return (Criteria) this;
        }

        public Criteria andActualReceivingAddressGreaterThan(String value) {
            addCriterion("actual_receiving_address >", value, "actualReceivingAddress");
            return (Criteria) this;
        }

        public Criteria andActualReceivingAddressGreaterThanOrEqualTo(String value) {
            addCriterion("actual_receiving_address >=", value, "actualReceivingAddress");
            return (Criteria) this;
        }

        public Criteria andActualReceivingAddressLessThan(String value) {
            addCriterion("actual_receiving_address <", value, "actualReceivingAddress");
            return (Criteria) this;
        }

        public Criteria andActualReceivingAddressLessThanOrEqualTo(String value) {
            addCriterion("actual_receiving_address <=", value, "actualReceivingAddress");
            return (Criteria) this;
        }

        public Criteria andActualReceivingAddressLike(String value) {
            addCriterion("actual_receiving_address like", value, "actualReceivingAddress");
            return (Criteria) this;
        }

        public Criteria andActualReceivingAddressNotLike(String value) {
            addCriterion("actual_receiving_address not like", value, "actualReceivingAddress");
            return (Criteria) this;
        }

        public Criteria andActualReceivingAddressIn(List<String> values) {
            addCriterion("actual_receiving_address in", values, "actualReceivingAddress");
            return (Criteria) this;
        }

        public Criteria andActualReceivingAddressNotIn(List<String> values) {
            addCriterion("actual_receiving_address not in", values, "actualReceivingAddress");
            return (Criteria) this;
        }

        public Criteria andActualReceivingAddressBetween(String value1, String value2) {
            addCriterion("actual_receiving_address between", value1, value2, "actualReceivingAddress");
            return (Criteria) this;
        }

        public Criteria andActualReceivingAddressNotBetween(String value1, String value2) {
            addCriterion("actual_receiving_address not between", value1, value2, "actualReceivingAddress");
            return (Criteria) this;
        }

        public Criteria andActualTxTimeIsNull() {
            addCriterion("actual_tx_time is null");
            return (Criteria) this;
        }

        public Criteria andActualTxTimeIsNotNull() {
            addCriterion("actual_tx_time is not null");
            return (Criteria) this;
        }

        public Criteria andActualTxTimeEqualTo(Timestamp value) {
            addCriterion("actual_tx_time =", value, "actualTxTime");
            return (Criteria) this;
        }

        public Criteria andActualTxTimeNotEqualTo(Timestamp value) {
            addCriterion("actual_tx_time <>", value, "actualTxTime");
            return (Criteria) this;
        }

        public Criteria andActualTxTimeGreaterThan(Timestamp value) {
            addCriterion("actual_tx_time >", value, "actualTxTime");
            return (Criteria) this;
        }

        public Criteria andActualTxTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("actual_tx_time >=", value, "actualTxTime");
            return (Criteria) this;
        }

        public Criteria andActualTxTimeLessThan(Timestamp value) {
            addCriterion("actual_tx_time <", value, "actualTxTime");
            return (Criteria) this;
        }

        public Criteria andActualTxTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("actual_tx_time <=", value, "actualTxTime");
            return (Criteria) this;
        }

        public Criteria andActualTxTimeIn(List<Timestamp> values) {
            addCriterion("actual_tx_time in", values, "actualTxTime");
            return (Criteria) this;
        }

        public Criteria andActualTxTimeNotIn(List<Timestamp> values) {
            addCriterion("actual_tx_time not in", values, "actualTxTime");
            return (Criteria) this;
        }

        public Criteria andActualTxTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("actual_tx_time between", value1, value2, "actualTxTime");
            return (Criteria) this;
        }

        public Criteria andActualTxTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("actual_tx_time not between", value1, value2, "actualTxTime");
            return (Criteria) this;
        }

        public Criteria andTxVerificationTimeIsNull() {
            addCriterion("tx_verification_time is null");
            return (Criteria) this;
        }

        public Criteria andTxVerificationTimeIsNotNull() {
            addCriterion("tx_verification_time is not null");
            return (Criteria) this;
        }

        public Criteria andTxVerificationTimeEqualTo(Timestamp value) {
            addCriterion("tx_verification_time =", value, "txVerificationTime");
            return (Criteria) this;
        }

        public Criteria andTxVerificationTimeNotEqualTo(Timestamp value) {
            addCriterion("tx_verification_time <>", value, "txVerificationTime");
            return (Criteria) this;
        }

        public Criteria andTxVerificationTimeGreaterThan(Timestamp value) {
            addCriterion("tx_verification_time >", value, "txVerificationTime");
            return (Criteria) this;
        }

        public Criteria andTxVerificationTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("tx_verification_time >=", value, "txVerificationTime");
            return (Criteria) this;
        }

        public Criteria andTxVerificationTimeLessThan(Timestamp value) {
            addCriterion("tx_verification_time <", value, "txVerificationTime");
            return (Criteria) this;
        }

        public Criteria andTxVerificationTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("tx_verification_time <=", value, "txVerificationTime");
            return (Criteria) this;
        }

        public Criteria andTxVerificationTimeIn(List<Timestamp> values) {
            addCriterion("tx_verification_time in", values, "txVerificationTime");
            return (Criteria) this;
        }

        public Criteria andTxVerificationTimeNotIn(List<Timestamp> values) {
            addCriterion("tx_verification_time not in", values, "txVerificationTime");
            return (Criteria) this;
        }

        public Criteria andTxVerificationTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("tx_verification_time between", value1, value2, "txVerificationTime");
            return (Criteria) this;
        }

        public Criteria andTxVerificationTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("tx_verification_time not between", value1, value2, "txVerificationTime");
            return (Criteria) this;
        }

        public Criteria andUserTxStatusIsNull() {
            addCriterion("user_tx_status is null");
            return (Criteria) this;
        }

        public Criteria andUserTxStatusIsNotNull() {
            addCriterion("user_tx_status is not null");
            return (Criteria) this;
        }

        public Criteria andUserTxStatusEqualTo(Integer value) {
            addCriterion("user_tx_status =", value, "userTxStatus");
            return (Criteria) this;
        }

        public Criteria andUserTxStatusNotEqualTo(Integer value) {
            addCriterion("user_tx_status <>", value, "userTxStatus");
            return (Criteria) this;
        }

        public Criteria andUserTxStatusGreaterThan(Integer value) {
            addCriterion("user_tx_status >", value, "userTxStatus");
            return (Criteria) this;
        }

        public Criteria andUserTxStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_tx_status >=", value, "userTxStatus");
            return (Criteria) this;
        }

        public Criteria andUserTxStatusLessThan(Integer value) {
            addCriterion("user_tx_status <", value, "userTxStatus");
            return (Criteria) this;
        }

        public Criteria andUserTxStatusLessThanOrEqualTo(Integer value) {
            addCriterion("user_tx_status <=", value, "userTxStatus");
            return (Criteria) this;
        }

        public Criteria andUserTxStatusIn(List<Integer> values) {
            addCriterion("user_tx_status in", values, "userTxStatus");
            return (Criteria) this;
        }

        public Criteria andUserTxStatusNotIn(List<Integer> values) {
            addCriterion("user_tx_status not in", values, "userTxStatus");
            return (Criteria) this;
        }

        public Criteria andUserTxStatusBetween(Integer value1, Integer value2) {
            addCriterion("user_tx_status between", value1, value2, "userTxStatus");
            return (Criteria) this;
        }

        public Criteria andUserTxStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("user_tx_status not between", value1, value2, "userTxStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformTxIsNull() {
            addCriterion("platform_tx is null");
            return (Criteria) this;
        }

        public Criteria andPlatformTxIsNotNull() {
            addCriterion("platform_tx is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformTxEqualTo(String value) {
            addCriterion("platform_tx =", value, "platformTx");
            return (Criteria) this;
        }

        public Criteria andPlatformTxNotEqualTo(String value) {
            addCriterion("platform_tx <>", value, "platformTx");
            return (Criteria) this;
        }

        public Criteria andPlatformTxGreaterThan(String value) {
            addCriterion("platform_tx >", value, "platformTx");
            return (Criteria) this;
        }

        public Criteria andPlatformTxGreaterThanOrEqualTo(String value) {
            addCriterion("platform_tx >=", value, "platformTx");
            return (Criteria) this;
        }

        public Criteria andPlatformTxLessThan(String value) {
            addCriterion("platform_tx <", value, "platformTx");
            return (Criteria) this;
        }

        public Criteria andPlatformTxLessThanOrEqualTo(String value) {
            addCriterion("platform_tx <=", value, "platformTx");
            return (Criteria) this;
        }

        public Criteria andPlatformTxLike(String value) {
            addCriterion("platform_tx like", value, "platformTx");
            return (Criteria) this;
        }

        public Criteria andPlatformTxNotLike(String value) {
            addCriterion("platform_tx not like", value, "platformTx");
            return (Criteria) this;
        }

        public Criteria andPlatformTxIn(List<String> values) {
            addCriterion("platform_tx in", values, "platformTx");
            return (Criteria) this;
        }

        public Criteria andPlatformTxNotIn(List<String> values) {
            addCriterion("platform_tx not in", values, "platformTx");
            return (Criteria) this;
        }

        public Criteria andPlatformTxBetween(String value1, String value2) {
            addCriterion("platform_tx between", value1, value2, "platformTx");
            return (Criteria) this;
        }

        public Criteria andPlatformTxNotBetween(String value1, String value2) {
            addCriterion("platform_tx not between", value1, value2, "platformTx");
            return (Criteria) this;
        }

        public Criteria andEthFeeIsNull() {
            addCriterion("eth_fee is null");
            return (Criteria) this;
        }

        public Criteria andEthFeeIsNotNull() {
            addCriterion("eth_fee is not null");
            return (Criteria) this;
        }

        public Criteria andEthFeeEqualTo(BigDecimal value) {
            addCriterion("eth_fee =", value, "ethFee");
            return (Criteria) this;
        }

        public Criteria andEthFeeNotEqualTo(BigDecimal value) {
            addCriterion("eth_fee <>", value, "ethFee");
            return (Criteria) this;
        }

        public Criteria andEthFeeGreaterThan(BigDecimal value) {
            addCriterion("eth_fee >", value, "ethFee");
            return (Criteria) this;
        }

        public Criteria andEthFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("eth_fee >=", value, "ethFee");
            return (Criteria) this;
        }

        public Criteria andEthFeeLessThan(BigDecimal value) {
            addCriterion("eth_fee <", value, "ethFee");
            return (Criteria) this;
        }

        public Criteria andEthFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("eth_fee <=", value, "ethFee");
            return (Criteria) this;
        }

        public Criteria andEthFeeIn(List<BigDecimal> values) {
            addCriterion("eth_fee in", values, "ethFee");
            return (Criteria) this;
        }

        public Criteria andEthFeeNotIn(List<BigDecimal> values) {
            addCriterion("eth_fee not in", values, "ethFee");
            return (Criteria) this;
        }

        public Criteria andEthFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eth_fee between", value1, value2, "ethFee");
            return (Criteria) this;
        }

        public Criteria andEthFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("eth_fee not between", value1, value2, "ethFee");
            return (Criteria) this;
        }

        public Criteria andPlatformTxStatusIsNull() {
            addCriterion("platform_tx_status is null");
            return (Criteria) this;
        }

        public Criteria andPlatformTxStatusIsNotNull() {
            addCriterion("platform_tx_status is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformTxStatusEqualTo(Integer value) {
            addCriterion("platform_tx_status =", value, "platformTxStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformTxStatusNotEqualTo(Integer value) {
            addCriterion("platform_tx_status <>", value, "platformTxStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformTxStatusGreaterThan(Integer value) {
            addCriterion("platform_tx_status >", value, "platformTxStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformTxStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform_tx_status >=", value, "platformTxStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformTxStatusLessThan(Integer value) {
            addCriterion("platform_tx_status <", value, "platformTxStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformTxStatusLessThanOrEqualTo(Integer value) {
            addCriterion("platform_tx_status <=", value, "platformTxStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformTxStatusIn(List<Integer> values) {
            addCriterion("platform_tx_status in", values, "platformTxStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformTxStatusNotIn(List<Integer> values) {
            addCriterion("platform_tx_status not in", values, "platformTxStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformTxStatusBetween(Integer value1, Integer value2) {
            addCriterion("platform_tx_status between", value1, value2, "platformTxStatus");
            return (Criteria) this;
        }

        public Criteria andPlatformTxStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("platform_tx_status not between", value1, value2, "platformTxStatus");
            return (Criteria) this;
        }

        public Criteria andDistributionTimeIsNull() {
            addCriterion("distribution_time is null");
            return (Criteria) this;
        }

        public Criteria andDistributionTimeIsNotNull() {
            addCriterion("distribution_time is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionTimeEqualTo(Timestamp value) {
            addCriterion("distribution_time =", value, "distributionTime");
            return (Criteria) this;
        }

        public Criteria andDistributionTimeNotEqualTo(Timestamp value) {
            addCriterion("distribution_time <>", value, "distributionTime");
            return (Criteria) this;
        }

        public Criteria andDistributionTimeGreaterThan(Timestamp value) {
            addCriterion("distribution_time >", value, "distributionTime");
            return (Criteria) this;
        }

        public Criteria andDistributionTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("distribution_time >=", value, "distributionTime");
            return (Criteria) this;
        }

        public Criteria andDistributionTimeLessThan(Timestamp value) {
            addCriterion("distribution_time <", value, "distributionTime");
            return (Criteria) this;
        }

        public Criteria andDistributionTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("distribution_time <=", value, "distributionTime");
            return (Criteria) this;
        }

        public Criteria andDistributionTimeIn(List<Timestamp> values) {
            addCriterion("distribution_time in", values, "distributionTime");
            return (Criteria) this;
        }

        public Criteria andDistributionTimeNotIn(List<Timestamp> values) {
            addCriterion("distribution_time not in", values, "distributionTime");
            return (Criteria) this;
        }

        public Criteria andDistributionTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("distribution_time between", value1, value2, "distributionTime");
            return (Criteria) this;
        }

        public Criteria andDistributionTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("distribution_time not between", value1, value2, "distributionTime");
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