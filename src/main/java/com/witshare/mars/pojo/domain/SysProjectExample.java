package com.witshare.mars.pojo.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SysProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysProjectExample() {
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

        public Criteria andTokenAddressIsNull() {
            addCriterion("token_address is null");
            return (Criteria) this;
        }

        public Criteria andTokenAddressIsNotNull() {
            addCriterion("token_address is not null");
            return (Criteria) this;
        }

        public Criteria andTokenAddressEqualTo(String value) {
            addCriterion("token_address =", value, "tokenAddress");
            return (Criteria) this;
        }

        public Criteria andTokenAddressNotEqualTo(String value) {
            addCriterion("token_address <>", value, "tokenAddress");
            return (Criteria) this;
        }

        public Criteria andTokenAddressGreaterThan(String value) {
            addCriterion("token_address >", value, "tokenAddress");
            return (Criteria) this;
        }

        public Criteria andTokenAddressGreaterThanOrEqualTo(String value) {
            addCriterion("token_address >=", value, "tokenAddress");
            return (Criteria) this;
        }

        public Criteria andTokenAddressLessThan(String value) {
            addCriterion("token_address <", value, "tokenAddress");
            return (Criteria) this;
        }

        public Criteria andTokenAddressLessThanOrEqualTo(String value) {
            addCriterion("token_address <=", value, "tokenAddress");
            return (Criteria) this;
        }

        public Criteria andTokenAddressLike(String value) {
            addCriterion("token_address like", value, "tokenAddress");
            return (Criteria) this;
        }

        public Criteria andTokenAddressNotLike(String value) {
            addCriterion("token_address not like", value, "tokenAddress");
            return (Criteria) this;
        }

        public Criteria andTokenAddressIn(List<String> values) {
            addCriterion("token_address in", values, "tokenAddress");
            return (Criteria) this;
        }

        public Criteria andTokenAddressNotIn(List<String> values) {
            addCriterion("token_address not in", values, "tokenAddress");
            return (Criteria) this;
        }

        public Criteria andTokenAddressBetween(String value1, String value2) {
            addCriterion("token_address between", value1, value2, "tokenAddress");
            return (Criteria) this;
        }

        public Criteria andTokenAddressNotBetween(String value1, String value2) {
            addCriterion("token_address not between", value1, value2, "tokenAddress");
            return (Criteria) this;
        }

        public Criteria andPlatformAddressIsNull() {
            addCriterion("platform_address is null");
            return (Criteria) this;
        }

        public Criteria andPlatformAddressIsNotNull() {
            addCriterion("platform_address is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformAddressEqualTo(String value) {
            addCriterion("platform_address =", value, "platformAddress");
            return (Criteria) this;
        }

        public Criteria andPlatformAddressNotEqualTo(String value) {
            addCriterion("platform_address <>", value, "platformAddress");
            return (Criteria) this;
        }

        public Criteria andPlatformAddressGreaterThan(String value) {
            addCriterion("platform_address >", value, "platformAddress");
            return (Criteria) this;
        }

        public Criteria andPlatformAddressGreaterThanOrEqualTo(String value) {
            addCriterion("platform_address >=", value, "platformAddress");
            return (Criteria) this;
        }

        public Criteria andPlatformAddressLessThan(String value) {
            addCriterion("platform_address <", value, "platformAddress");
            return (Criteria) this;
        }

        public Criteria andPlatformAddressLessThanOrEqualTo(String value) {
            addCriterion("platform_address <=", value, "platformAddress");
            return (Criteria) this;
        }

        public Criteria andPlatformAddressLike(String value) {
            addCriterion("platform_address like", value, "platformAddress");
            return (Criteria) this;
        }

        public Criteria andPlatformAddressNotLike(String value) {
            addCriterion("platform_address not like", value, "platformAddress");
            return (Criteria) this;
        }

        public Criteria andPlatformAddressIn(List<String> values) {
            addCriterion("platform_address in", values, "platformAddress");
            return (Criteria) this;
        }

        public Criteria andPlatformAddressNotIn(List<String> values) {
            addCriterion("platform_address not in", values, "platformAddress");
            return (Criteria) this;
        }

        public Criteria andPlatformAddressBetween(String value1, String value2) {
            addCriterion("platform_address between", value1, value2, "platformAddress");
            return (Criteria) this;
        }

        public Criteria andPlatformAddressNotBetween(String value1, String value2) {
            addCriterion("platform_address not between", value1, value2, "platformAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressIsNull() {
            addCriterion("project_address is null");
            return (Criteria) this;
        }

        public Criteria andProjectAddressIsNotNull() {
            addCriterion("project_address is not null");
            return (Criteria) this;
        }

        public Criteria andProjectAddressEqualTo(String value) {
            addCriterion("project_address =", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressNotEqualTo(String value) {
            addCriterion("project_address <>", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressGreaterThan(String value) {
            addCriterion("project_address >", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressGreaterThanOrEqualTo(String value) {
            addCriterion("project_address >=", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressLessThan(String value) {
            addCriterion("project_address <", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressLessThanOrEqualTo(String value) {
            addCriterion("project_address <=", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressLike(String value) {
            addCriterion("project_address like", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressNotLike(String value) {
            addCriterion("project_address not like", value, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressIn(List<String> values) {
            addCriterion("project_address in", values, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressNotIn(List<String> values) {
            addCriterion("project_address not in", values, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressBetween(String value1, String value2) {
            addCriterion("project_address between", value1, value2, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectAddressNotBetween(String value1, String value2) {
            addCriterion("project_address not between", value1, value2, "projectAddress");
            return (Criteria) this;
        }

        public Criteria andProjectLogoLinkIsNull() {
            addCriterion("project_logo_link is null");
            return (Criteria) this;
        }

        public Criteria andProjectLogoLinkIsNotNull() {
            addCriterion("project_logo_link is not null");
            return (Criteria) this;
        }

        public Criteria andProjectLogoLinkEqualTo(String value) {
            addCriterion("project_logo_link =", value, "projectLogoLink");
            return (Criteria) this;
        }

        public Criteria andProjectLogoLinkNotEqualTo(String value) {
            addCriterion("project_logo_link <>", value, "projectLogoLink");
            return (Criteria) this;
        }

        public Criteria andProjectLogoLinkGreaterThan(String value) {
            addCriterion("project_logo_link >", value, "projectLogoLink");
            return (Criteria) this;
        }

        public Criteria andProjectLogoLinkGreaterThanOrEqualTo(String value) {
            addCriterion("project_logo_link >=", value, "projectLogoLink");
            return (Criteria) this;
        }

        public Criteria andProjectLogoLinkLessThan(String value) {
            addCriterion("project_logo_link <", value, "projectLogoLink");
            return (Criteria) this;
        }

        public Criteria andProjectLogoLinkLessThanOrEqualTo(String value) {
            addCriterion("project_logo_link <=", value, "projectLogoLink");
            return (Criteria) this;
        }

        public Criteria andProjectLogoLinkLike(String value) {
            addCriterion("project_logo_link like", value, "projectLogoLink");
            return (Criteria) this;
        }

        public Criteria andProjectLogoLinkNotLike(String value) {
            addCriterion("project_logo_link not like", value, "projectLogoLink");
            return (Criteria) this;
        }

        public Criteria andProjectLogoLinkIn(List<String> values) {
            addCriterion("project_logo_link in", values, "projectLogoLink");
            return (Criteria) this;
        }

        public Criteria andProjectLogoLinkNotIn(List<String> values) {
            addCriterion("project_logo_link not in", values, "projectLogoLink");
            return (Criteria) this;
        }

        public Criteria andProjectLogoLinkBetween(String value1, String value2) {
            addCriterion("project_logo_link between", value1, value2, "projectLogoLink");
            return (Criteria) this;
        }

        public Criteria andProjectLogoLinkNotBetween(String value1, String value2) {
            addCriterion("project_logo_link not between", value1, value2, "projectLogoLink");
            return (Criteria) this;
        }

        public Criteria andProjectImgLinkIsNull() {
            addCriterion("project_img_link is null");
            return (Criteria) this;
        }

        public Criteria andProjectImgLinkIsNotNull() {
            addCriterion("project_img_link is not null");
            return (Criteria) this;
        }

        public Criteria andProjectImgLinkEqualTo(String value) {
            addCriterion("project_img_link =", value, "projectImgLink");
            return (Criteria) this;
        }

        public Criteria andProjectImgLinkNotEqualTo(String value) {
            addCriterion("project_img_link <>", value, "projectImgLink");
            return (Criteria) this;
        }

        public Criteria andProjectImgLinkGreaterThan(String value) {
            addCriterion("project_img_link >", value, "projectImgLink");
            return (Criteria) this;
        }

        public Criteria andProjectImgLinkGreaterThanOrEqualTo(String value) {
            addCriterion("project_img_link >=", value, "projectImgLink");
            return (Criteria) this;
        }

        public Criteria andProjectImgLinkLessThan(String value) {
            addCriterion("project_img_link <", value, "projectImgLink");
            return (Criteria) this;
        }

        public Criteria andProjectImgLinkLessThanOrEqualTo(String value) {
            addCriterion("project_img_link <=", value, "projectImgLink");
            return (Criteria) this;
        }

        public Criteria andProjectImgLinkLike(String value) {
            addCriterion("project_img_link like", value, "projectImgLink");
            return (Criteria) this;
        }

        public Criteria andProjectImgLinkNotLike(String value) {
            addCriterion("project_img_link not like", value, "projectImgLink");
            return (Criteria) this;
        }

        public Criteria andProjectImgLinkIn(List<String> values) {
            addCriterion("project_img_link in", values, "projectImgLink");
            return (Criteria) this;
        }

        public Criteria andProjectImgLinkNotIn(List<String> values) {
            addCriterion("project_img_link not in", values, "projectImgLink");
            return (Criteria) this;
        }

        public Criteria andProjectImgLinkBetween(String value1, String value2) {
            addCriterion("project_img_link between", value1, value2, "projectImgLink");
            return (Criteria) this;
        }

        public Criteria andProjectImgLinkNotBetween(String value1, String value2) {
            addCriterion("project_img_link not between", value1, value2, "projectImgLink");
            return (Criteria) this;
        }

        public Criteria andSoftCapIsNull() {
            addCriterion("soft_cap is null");
            return (Criteria) this;
        }

        public Criteria andSoftCapIsNotNull() {
            addCriterion("soft_cap is not null");
            return (Criteria) this;
        }

        public Criteria andSoftCapEqualTo(BigDecimal value) {
            addCriterion("soft_cap =", value, "softCap");
            return (Criteria) this;
        }

        public Criteria andSoftCapNotEqualTo(BigDecimal value) {
            addCriterion("soft_cap <>", value, "softCap");
            return (Criteria) this;
        }

        public Criteria andSoftCapGreaterThan(BigDecimal value) {
            addCriterion("soft_cap >", value, "softCap");
            return (Criteria) this;
        }

        public Criteria andSoftCapGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("soft_cap >=", value, "softCap");
            return (Criteria) this;
        }

        public Criteria andSoftCapLessThan(BigDecimal value) {
            addCriterion("soft_cap <", value, "softCap");
            return (Criteria) this;
        }

        public Criteria andSoftCapLessThanOrEqualTo(BigDecimal value) {
            addCriterion("soft_cap <=", value, "softCap");
            return (Criteria) this;
        }

        public Criteria andSoftCapIn(List<BigDecimal> values) {
            addCriterion("soft_cap in", values, "softCap");
            return (Criteria) this;
        }

        public Criteria andSoftCapNotIn(List<BigDecimal> values) {
            addCriterion("soft_cap not in", values, "softCap");
            return (Criteria) this;
        }

        public Criteria andSoftCapBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("soft_cap between", value1, value2, "softCap");
            return (Criteria) this;
        }

        public Criteria andSoftCapNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("soft_cap not between", value1, value2, "softCap");
            return (Criteria) this;
        }

        public Criteria andHardCapIsNull() {
            addCriterion("hard_cap is null");
            return (Criteria) this;
        }

        public Criteria andHardCapIsNotNull() {
            addCriterion("hard_cap is not null");
            return (Criteria) this;
        }

        public Criteria andHardCapEqualTo(BigDecimal value) {
            addCriterion("hard_cap =", value, "hardCap");
            return (Criteria) this;
        }

        public Criteria andHardCapNotEqualTo(BigDecimal value) {
            addCriterion("hard_cap <>", value, "hardCap");
            return (Criteria) this;
        }

        public Criteria andHardCapGreaterThan(BigDecimal value) {
            addCriterion("hard_cap >", value, "hardCap");
            return (Criteria) this;
        }

        public Criteria andHardCapGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("hard_cap >=", value, "hardCap");
            return (Criteria) this;
        }

        public Criteria andHardCapLessThan(BigDecimal value) {
            addCriterion("hard_cap <", value, "hardCap");
            return (Criteria) this;
        }

        public Criteria andHardCapLessThanOrEqualTo(BigDecimal value) {
            addCriterion("hard_cap <=", value, "hardCap");
            return (Criteria) this;
        }

        public Criteria andHardCapIn(List<BigDecimal> values) {
            addCriterion("hard_cap in", values, "hardCap");
            return (Criteria) this;
        }

        public Criteria andHardCapNotIn(List<BigDecimal> values) {
            addCriterion("hard_cap not in", values, "hardCap");
            return (Criteria) this;
        }

        public Criteria andHardCapBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hard_cap between", value1, value2, "hardCap");
            return (Criteria) this;
        }

        public Criteria andHardCapNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("hard_cap not between", value1, value2, "hardCap");
            return (Criteria) this;
        }

        public Criteria andMinPurchaseAmountIsNull() {
            addCriterion("min_purchase_amount is null");
            return (Criteria) this;
        }

        public Criteria andMinPurchaseAmountIsNotNull() {
            addCriterion("min_purchase_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMinPurchaseAmountEqualTo(BigDecimal value) {
            addCriterion("min_purchase_amount =", value, "minPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andMinPurchaseAmountNotEqualTo(BigDecimal value) {
            addCriterion("min_purchase_amount <>", value, "minPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andMinPurchaseAmountGreaterThan(BigDecimal value) {
            addCriterion("min_purchase_amount >", value, "minPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andMinPurchaseAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_purchase_amount >=", value, "minPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andMinPurchaseAmountLessThan(BigDecimal value) {
            addCriterion("min_purchase_amount <", value, "minPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andMinPurchaseAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_purchase_amount <=", value, "minPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andMinPurchaseAmountIn(List<BigDecimal> values) {
            addCriterion("min_purchase_amount in", values, "minPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andMinPurchaseAmountNotIn(List<BigDecimal> values) {
            addCriterion("min_purchase_amount not in", values, "minPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andMinPurchaseAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_purchase_amount between", value1, value2, "minPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andMinPurchaseAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_purchase_amount not between", value1, value2, "minPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Timestamp value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Timestamp value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Timestamp value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Timestamp value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Timestamp> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Timestamp> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
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

        public Criteria andEndTimeEqualTo(Timestamp value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Timestamp value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Timestamp value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Timestamp value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Timestamp> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Timestamp> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
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

        public Criteria andProjectStatusIsNull() {
            addCriterion("project_status is null");
            return (Criteria) this;
        }

        public Criteria andProjectStatusIsNotNull() {
            addCriterion("project_status is not null");
            return (Criteria) this;
        }

        public Criteria andProjectStatusEqualTo(Integer value) {
            addCriterion("project_status =", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusNotEqualTo(Integer value) {
            addCriterion("project_status <>", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusGreaterThan(Integer value) {
            addCriterion("project_status >", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_status >=", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusLessThan(Integer value) {
            addCriterion("project_status <", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusLessThanOrEqualTo(Integer value) {
            addCriterion("project_status <=", value, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusIn(List<Integer> values) {
            addCriterion("project_status in", values, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusNotIn(List<Integer> values) {
            addCriterion("project_status not in", values, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusBetween(Integer value1, Integer value2) {
            addCriterion("project_status between", value1, value2, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andProjectStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("project_status not between", value1, value2, "projectStatus");
            return (Criteria) this;
        }

        public Criteria andIsAvailableIsNull() {
            addCriterion("is_available is null");
            return (Criteria) this;
        }

        public Criteria andIsAvailableIsNotNull() {
            addCriterion("is_available is not null");
            return (Criteria) this;
        }

        public Criteria andIsAvailableEqualTo(Integer value) {
            addCriterion("is_available =", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableNotEqualTo(Integer value) {
            addCriterion("is_available <>", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableGreaterThan(Integer value) {
            addCriterion("is_available >", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_available >=", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableLessThan(Integer value) {
            addCriterion("is_available <", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableLessThanOrEqualTo(Integer value) {
            addCriterion("is_available <=", value, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableIn(List<Integer> values) {
            addCriterion("is_available in", values, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableNotIn(List<Integer> values) {
            addCriterion("is_available not in", values, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableBetween(Integer value1, Integer value2) {
            addCriterion("is_available between", value1, value2, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andIsAvailableNotBetween(Integer value1, Integer value2) {
            addCriterion("is_available not between", value1, value2, "isAvailable");
            return (Criteria) this;
        }

        public Criteria andDefaultProjectIsNull() {
            addCriterion("default_project is null");
            return (Criteria) this;
        }

        public Criteria andDefaultProjectIsNotNull() {
            addCriterion("default_project is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultProjectEqualTo(Integer value) {
            addCriterion("default_project =", value, "defaultProject");
            return (Criteria) this;
        }

        public Criteria andDefaultProjectNotEqualTo(Integer value) {
            addCriterion("default_project <>", value, "defaultProject");
            return (Criteria) this;
        }

        public Criteria andDefaultProjectGreaterThan(Integer value) {
            addCriterion("default_project >", value, "defaultProject");
            return (Criteria) this;
        }

        public Criteria andDefaultProjectGreaterThanOrEqualTo(Integer value) {
            addCriterion("default_project >=", value, "defaultProject");
            return (Criteria) this;
        }

        public Criteria andDefaultProjectLessThan(Integer value) {
            addCriterion("default_project <", value, "defaultProject");
            return (Criteria) this;
        }

        public Criteria andDefaultProjectLessThanOrEqualTo(Integer value) {
            addCriterion("default_project <=", value, "defaultProject");
            return (Criteria) this;
        }

        public Criteria andDefaultProjectIn(List<Integer> values) {
            addCriterion("default_project in", values, "defaultProject");
            return (Criteria) this;
        }

        public Criteria andDefaultProjectNotIn(List<Integer> values) {
            addCriterion("default_project not in", values, "defaultProject");
            return (Criteria) this;
        }

        public Criteria andDefaultProjectBetween(Integer value1, Integer value2) {
            addCriterion("default_project between", value1, value2, "defaultProject");
            return (Criteria) this;
        }

        public Criteria andDefaultProjectNotBetween(Integer value1, Integer value2) {
            addCriterion("default_project not between", value1, value2, "defaultProject");
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

        public Criteria andTokenDecimalIsNull() {
            addCriterion("token_decimal is null");
            return (Criteria) this;
        }

        public Criteria andTokenDecimalIsNotNull() {
            addCriterion("token_decimal is not null");
            return (Criteria) this;
        }

        public Criteria andTokenDecimalEqualTo(Integer value) {
            addCriterion("token_decimal =", value, "tokenDecimal");
            return (Criteria) this;
        }

        public Criteria andTokenDecimalNotEqualTo(Integer value) {
            addCriterion("token_decimal <>", value, "tokenDecimal");
            return (Criteria) this;
        }

        public Criteria andTokenDecimalGreaterThan(Integer value) {
            addCriterion("token_decimal >", value, "tokenDecimal");
            return (Criteria) this;
        }

        public Criteria andTokenDecimalGreaterThanOrEqualTo(Integer value) {
            addCriterion("token_decimal >=", value, "tokenDecimal");
            return (Criteria) this;
        }

        public Criteria andTokenDecimalLessThan(Integer value) {
            addCriterion("token_decimal <", value, "tokenDecimal");
            return (Criteria) this;
        }

        public Criteria andTokenDecimalLessThanOrEqualTo(Integer value) {
            addCriterion("token_decimal <=", value, "tokenDecimal");
            return (Criteria) this;
        }

        public Criteria andTokenDecimalIn(List<Integer> values) {
            addCriterion("token_decimal in", values, "tokenDecimal");
            return (Criteria) this;
        }

        public Criteria andTokenDecimalNotIn(List<Integer> values) {
            addCriterion("token_decimal not in", values, "tokenDecimal");
            return (Criteria) this;
        }

        public Criteria andTokenDecimalBetween(Integer value1, Integer value2) {
            addCriterion("token_decimal between", value1, value2, "tokenDecimal");
            return (Criteria) this;
        }

        public Criteria andTokenDecimalNotBetween(Integer value1, Integer value2) {
            addCriterion("token_decimal not between", value1, value2, "tokenDecimal");
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