package com.witshare.mars.pojo.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RecordPlatformTxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecordPlatformTxExample() {
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

        public Criteria andTxHashIsNull() {
            addCriterion("tx_hash is null");
            return (Criteria) this;
        }

        public Criteria andTxHashIsNotNull() {
            addCriterion("tx_hash is not null");
            return (Criteria) this;
        }

        public Criteria andTxHashEqualTo(String value) {
            addCriterion("tx_hash =", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashNotEqualTo(String value) {
            addCriterion("tx_hash <>", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashGreaterThan(String value) {
            addCriterion("tx_hash >", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashGreaterThanOrEqualTo(String value) {
            addCriterion("tx_hash >=", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashLessThan(String value) {
            addCriterion("tx_hash <", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashLessThanOrEqualTo(String value) {
            addCriterion("tx_hash <=", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashLike(String value) {
            addCriterion("tx_hash like", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashNotLike(String value) {
            addCriterion("tx_hash not like", value, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashIn(List<String> values) {
            addCriterion("tx_hash in", values, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashNotIn(List<String> values) {
            addCriterion("tx_hash not in", values, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashBetween(String value1, String value2) {
            addCriterion("tx_hash between", value1, value2, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxHashNotBetween(String value1, String value2) {
            addCriterion("tx_hash not between", value1, value2, "txHash");
            return (Criteria) this;
        }

        public Criteria andTxTypeIsNull() {
            addCriterion("tx_type is null");
            return (Criteria) this;
        }

        public Criteria andTxTypeIsNotNull() {
            addCriterion("tx_type is not null");
            return (Criteria) this;
        }

        public Criteria andTxTypeEqualTo(Integer value) {
            addCriterion("tx_type =", value, "txType");
            return (Criteria) this;
        }

        public Criteria andTxTypeNotEqualTo(Integer value) {
            addCriterion("tx_type <>", value, "txType");
            return (Criteria) this;
        }

        public Criteria andTxTypeGreaterThan(Integer value) {
            addCriterion("tx_type >", value, "txType");
            return (Criteria) this;
        }

        public Criteria andTxTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("tx_type >=", value, "txType");
            return (Criteria) this;
        }

        public Criteria andTxTypeLessThan(Integer value) {
            addCriterion("tx_type <", value, "txType");
            return (Criteria) this;
        }

        public Criteria andTxTypeLessThanOrEqualTo(Integer value) {
            addCriterion("tx_type <=", value, "txType");
            return (Criteria) this;
        }

        public Criteria andTxTypeIn(List<Integer> values) {
            addCriterion("tx_type in", values, "txType");
            return (Criteria) this;
        }

        public Criteria andTxTypeNotIn(List<Integer> values) {
            addCriterion("tx_type not in", values, "txType");
            return (Criteria) this;
        }

        public Criteria andTxTypeBetween(Integer value1, Integer value2) {
            addCriterion("tx_type between", value1, value2, "txType");
            return (Criteria) this;
        }

        public Criteria andTxTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("tx_type not between", value1, value2, "txType");
            return (Criteria) this;
        }

        public Criteria andFromNameIsNull() {
            addCriterion("from_name is null");
            return (Criteria) this;
        }

        public Criteria andFromNameIsNotNull() {
            addCriterion("from_name is not null");
            return (Criteria) this;
        }

        public Criteria andFromNameEqualTo(String value) {
            addCriterion("from_name =", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotEqualTo(String value) {
            addCriterion("from_name <>", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameGreaterThan(String value) {
            addCriterion("from_name >", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameGreaterThanOrEqualTo(String value) {
            addCriterion("from_name >=", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLessThan(String value) {
            addCriterion("from_name <", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLessThanOrEqualTo(String value) {
            addCriterion("from_name <=", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLike(String value) {
            addCriterion("from_name like", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotLike(String value) {
            addCriterion("from_name not like", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameIn(List<String> values) {
            addCriterion("from_name in", values, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotIn(List<String> values) {
            addCriterion("from_name not in", values, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameBetween(String value1, String value2) {
            addCriterion("from_name between", value1, value2, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotBetween(String value1, String value2) {
            addCriterion("from_name not between", value1, value2, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromAddressIsNull() {
            addCriterion("from_address is null");
            return (Criteria) this;
        }

        public Criteria andFromAddressIsNotNull() {
            addCriterion("from_address is not null");
            return (Criteria) this;
        }

        public Criteria andFromAddressEqualTo(String value) {
            addCriterion("from_address =", value, "fromAddress");
            return (Criteria) this;
        }

        public Criteria andFromAddressNotEqualTo(String value) {
            addCriterion("from_address <>", value, "fromAddress");
            return (Criteria) this;
        }

        public Criteria andFromAddressGreaterThan(String value) {
            addCriterion("from_address >", value, "fromAddress");
            return (Criteria) this;
        }

        public Criteria andFromAddressGreaterThanOrEqualTo(String value) {
            addCriterion("from_address >=", value, "fromAddress");
            return (Criteria) this;
        }

        public Criteria andFromAddressLessThan(String value) {
            addCriterion("from_address <", value, "fromAddress");
            return (Criteria) this;
        }

        public Criteria andFromAddressLessThanOrEqualTo(String value) {
            addCriterion("from_address <=", value, "fromAddress");
            return (Criteria) this;
        }

        public Criteria andFromAddressLike(String value) {
            addCriterion("from_address like", value, "fromAddress");
            return (Criteria) this;
        }

        public Criteria andFromAddressNotLike(String value) {
            addCriterion("from_address not like", value, "fromAddress");
            return (Criteria) this;
        }

        public Criteria andFromAddressIn(List<String> values) {
            addCriterion("from_address in", values, "fromAddress");
            return (Criteria) this;
        }

        public Criteria andFromAddressNotIn(List<String> values) {
            addCriterion("from_address not in", values, "fromAddress");
            return (Criteria) this;
        }

        public Criteria andFromAddressBetween(String value1, String value2) {
            addCriterion("from_address between", value1, value2, "fromAddress");
            return (Criteria) this;
        }

        public Criteria andFromAddressNotBetween(String value1, String value2) {
            addCriterion("from_address not between", value1, value2, "fromAddress");
            return (Criteria) this;
        }

        public Criteria andToNameIsNull() {
            addCriterion("to_name is null");
            return (Criteria) this;
        }

        public Criteria andToNameIsNotNull() {
            addCriterion("to_name is not null");
            return (Criteria) this;
        }

        public Criteria andToNameEqualTo(String value) {
            addCriterion("to_name =", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameNotEqualTo(String value) {
            addCriterion("to_name <>", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameGreaterThan(String value) {
            addCriterion("to_name >", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameGreaterThanOrEqualTo(String value) {
            addCriterion("to_name >=", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameLessThan(String value) {
            addCriterion("to_name <", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameLessThanOrEqualTo(String value) {
            addCriterion("to_name <=", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameLike(String value) {
            addCriterion("to_name like", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameNotLike(String value) {
            addCriterion("to_name not like", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameIn(List<String> values) {
            addCriterion("to_name in", values, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameNotIn(List<String> values) {
            addCriterion("to_name not in", values, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameBetween(String value1, String value2) {
            addCriterion("to_name between", value1, value2, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameNotBetween(String value1, String value2) {
            addCriterion("to_name not between", value1, value2, "toName");
            return (Criteria) this;
        }

        public Criteria andToAddressIsNull() {
            addCriterion("to_address is null");
            return (Criteria) this;
        }

        public Criteria andToAddressIsNotNull() {
            addCriterion("to_address is not null");
            return (Criteria) this;
        }

        public Criteria andToAddressEqualTo(String value) {
            addCriterion("to_address =", value, "toAddress");
            return (Criteria) this;
        }

        public Criteria andToAddressNotEqualTo(String value) {
            addCriterion("to_address <>", value, "toAddress");
            return (Criteria) this;
        }

        public Criteria andToAddressGreaterThan(String value) {
            addCriterion("to_address >", value, "toAddress");
            return (Criteria) this;
        }

        public Criteria andToAddressGreaterThanOrEqualTo(String value) {
            addCriterion("to_address >=", value, "toAddress");
            return (Criteria) this;
        }

        public Criteria andToAddressLessThan(String value) {
            addCriterion("to_address <", value, "toAddress");
            return (Criteria) this;
        }

        public Criteria andToAddressLessThanOrEqualTo(String value) {
            addCriterion("to_address <=", value, "toAddress");
            return (Criteria) this;
        }

        public Criteria andToAddressLike(String value) {
            addCriterion("to_address like", value, "toAddress");
            return (Criteria) this;
        }

        public Criteria andToAddressNotLike(String value) {
            addCriterion("to_address not like", value, "toAddress");
            return (Criteria) this;
        }

        public Criteria andToAddressIn(List<String> values) {
            addCriterion("to_address in", values, "toAddress");
            return (Criteria) this;
        }

        public Criteria andToAddressNotIn(List<String> values) {
            addCriterion("to_address not in", values, "toAddress");
            return (Criteria) this;
        }

        public Criteria andToAddressBetween(String value1, String value2) {
            addCriterion("to_address between", value1, value2, "toAddress");
            return (Criteria) this;
        }

        public Criteria andToAddressNotBetween(String value1, String value2) {
            addCriterion("to_address not between", value1, value2, "toAddress");
            return (Criteria) this;
        }

        public Criteria andTxTokenTypeIsNull() {
            addCriterion("tx_token_type is null");
            return (Criteria) this;
        }

        public Criteria andTxTokenTypeIsNotNull() {
            addCriterion("tx_token_type is not null");
            return (Criteria) this;
        }

        public Criteria andTxTokenTypeEqualTo(String value) {
            addCriterion("tx_token_type =", value, "txTokenType");
            return (Criteria) this;
        }

        public Criteria andTxTokenTypeNotEqualTo(String value) {
            addCriterion("tx_token_type <>", value, "txTokenType");
            return (Criteria) this;
        }

        public Criteria andTxTokenTypeGreaterThan(String value) {
            addCriterion("tx_token_type >", value, "txTokenType");
            return (Criteria) this;
        }

        public Criteria andTxTokenTypeGreaterThanOrEqualTo(String value) {
            addCriterion("tx_token_type >=", value, "txTokenType");
            return (Criteria) this;
        }

        public Criteria andTxTokenTypeLessThan(String value) {
            addCriterion("tx_token_type <", value, "txTokenType");
            return (Criteria) this;
        }

        public Criteria andTxTokenTypeLessThanOrEqualTo(String value) {
            addCriterion("tx_token_type <=", value, "txTokenType");
            return (Criteria) this;
        }

        public Criteria andTxTokenTypeLike(String value) {
            addCriterion("tx_token_type like", value, "txTokenType");
            return (Criteria) this;
        }

        public Criteria andTxTokenTypeNotLike(String value) {
            addCriterion("tx_token_type not like", value, "txTokenType");
            return (Criteria) this;
        }

        public Criteria andTxTokenTypeIn(List<String> values) {
            addCriterion("tx_token_type in", values, "txTokenType");
            return (Criteria) this;
        }

        public Criteria andTxTokenTypeNotIn(List<String> values) {
            addCriterion("tx_token_type not in", values, "txTokenType");
            return (Criteria) this;
        }

        public Criteria andTxTokenTypeBetween(String value1, String value2) {
            addCriterion("tx_token_type between", value1, value2, "txTokenType");
            return (Criteria) this;
        }

        public Criteria andTxTokenTypeNotBetween(String value1, String value2) {
            addCriterion("tx_token_type not between", value1, value2, "txTokenType");
            return (Criteria) this;
        }

        public Criteria andTxAmountIsNull() {
            addCriterion("tx_amount is null");
            return (Criteria) this;
        }

        public Criteria andTxAmountIsNotNull() {
            addCriterion("tx_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTxAmountEqualTo(BigDecimal value) {
            addCriterion("tx_amount =", value, "txAmount");
            return (Criteria) this;
        }

        public Criteria andTxAmountNotEqualTo(BigDecimal value) {
            addCriterion("tx_amount <>", value, "txAmount");
            return (Criteria) this;
        }

        public Criteria andTxAmountGreaterThan(BigDecimal value) {
            addCriterion("tx_amount >", value, "txAmount");
            return (Criteria) this;
        }

        public Criteria andTxAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tx_amount >=", value, "txAmount");
            return (Criteria) this;
        }

        public Criteria andTxAmountLessThan(BigDecimal value) {
            addCriterion("tx_amount <", value, "txAmount");
            return (Criteria) this;
        }

        public Criteria andTxAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tx_amount <=", value, "txAmount");
            return (Criteria) this;
        }

        public Criteria andTxAmountIn(List<BigDecimal> values) {
            addCriterion("tx_amount in", values, "txAmount");
            return (Criteria) this;
        }

        public Criteria andTxAmountNotIn(List<BigDecimal> values) {
            addCriterion("tx_amount not in", values, "txAmount");
            return (Criteria) this;
        }

        public Criteria andTxAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tx_amount between", value1, value2, "txAmount");
            return (Criteria) this;
        }

        public Criteria andTxAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tx_amount not between", value1, value2, "txAmount");
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

        public Criteria andTxStatusIsNull() {
            addCriterion("tx_status is null");
            return (Criteria) this;
        }

        public Criteria andTxStatusIsNotNull() {
            addCriterion("tx_status is not null");
            return (Criteria) this;
        }

        public Criteria andTxStatusEqualTo(Integer value) {
            addCriterion("tx_status =", value, "txStatus");
            return (Criteria) this;
        }

        public Criteria andTxStatusNotEqualTo(Integer value) {
            addCriterion("tx_status <>", value, "txStatus");
            return (Criteria) this;
        }

        public Criteria andTxStatusGreaterThan(Integer value) {
            addCriterion("tx_status >", value, "txStatus");
            return (Criteria) this;
        }

        public Criteria andTxStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("tx_status >=", value, "txStatus");
            return (Criteria) this;
        }

        public Criteria andTxStatusLessThan(Integer value) {
            addCriterion("tx_status <", value, "txStatus");
            return (Criteria) this;
        }

        public Criteria andTxStatusLessThanOrEqualTo(Integer value) {
            addCriterion("tx_status <=", value, "txStatus");
            return (Criteria) this;
        }

        public Criteria andTxStatusIn(List<Integer> values) {
            addCriterion("tx_status in", values, "txStatus");
            return (Criteria) this;
        }

        public Criteria andTxStatusNotIn(List<Integer> values) {
            addCriterion("tx_status not in", values, "txStatus");
            return (Criteria) this;
        }

        public Criteria andTxStatusBetween(Integer value1, Integer value2) {
            addCriterion("tx_status between", value1, value2, "txStatus");
            return (Criteria) this;
        }

        public Criteria andTxStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("tx_status not between", value1, value2, "txStatus");
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

        public Criteria andTxTimeIsNull() {
            addCriterion("tx_time is null");
            return (Criteria) this;
        }

        public Criteria andTxTimeIsNotNull() {
            addCriterion("tx_time is not null");
            return (Criteria) this;
        }

        public Criteria andTxTimeEqualTo(Timestamp value) {
            addCriterion("tx_time =", value, "txTime");
            return (Criteria) this;
        }

        public Criteria andTxTimeNotEqualTo(Timestamp value) {
            addCriterion("tx_time <>", value, "txTime");
            return (Criteria) this;
        }

        public Criteria andTxTimeGreaterThan(Timestamp value) {
            addCriterion("tx_time >", value, "txTime");
            return (Criteria) this;
        }

        public Criteria andTxTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("tx_time >=", value, "txTime");
            return (Criteria) this;
        }

        public Criteria andTxTimeLessThan(Timestamp value) {
            addCriterion("tx_time <", value, "txTime");
            return (Criteria) this;
        }

        public Criteria andTxTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("tx_time <=", value, "txTime");
            return (Criteria) this;
        }

        public Criteria andTxTimeIn(List<Timestamp> values) {
            addCriterion("tx_time in", values, "txTime");
            return (Criteria) this;
        }

        public Criteria andTxTimeNotIn(List<Timestamp> values) {
            addCriterion("tx_time not in", values, "txTime");
            return (Criteria) this;
        }

        public Criteria andTxTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("tx_time between", value1, value2, "txTime");
            return (Criteria) this;
        }

        public Criteria andTxTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("tx_time not between", value1, value2, "txTime");
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