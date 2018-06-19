package com.witshare.mars.pojo.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProjectDescriptionJaExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    public ProjectDescriptionJaExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
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

        public Criteria andProjectNameIsNull() {
            addCriterion("project_name is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("project_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("project_name =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("project_name <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("project_name >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("project_name >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("project_name <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("project_name <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("project_name like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("project_name not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("project_name in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("project_name not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("project_name between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("project_name not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectInstructionIsNull() {
            addCriterion("project_instruction is null");
            return (Criteria) this;
        }

        public Criteria andProjectInstructionIsNotNull() {
            addCriterion("project_instruction is not null");
            return (Criteria) this;
        }

        public Criteria andProjectInstructionEqualTo(String value) {
            addCriterion("project_instruction =", value, "projectInstruction");
            return (Criteria) this;
        }

        public Criteria andProjectInstructionNotEqualTo(String value) {
            addCriterion("project_instruction <>", value, "projectInstruction");
            return (Criteria) this;
        }

        public Criteria andProjectInstructionGreaterThan(String value) {
            addCriterion("project_instruction >", value, "projectInstruction");
            return (Criteria) this;
        }

        public Criteria andProjectInstructionGreaterThanOrEqualTo(String value) {
            addCriterion("project_instruction >=", value, "projectInstruction");
            return (Criteria) this;
        }

        public Criteria andProjectInstructionLessThan(String value) {
            addCriterion("project_instruction <", value, "projectInstruction");
            return (Criteria) this;
        }

        public Criteria andProjectInstructionLessThanOrEqualTo(String value) {
            addCriterion("project_instruction <=", value, "projectInstruction");
            return (Criteria) this;
        }

        public Criteria andProjectInstructionLike(String value) {
            addCriterion("project_instruction like", value, "projectInstruction");
            return (Criteria) this;
        }

        public Criteria andProjectInstructionNotLike(String value) {
            addCriterion("project_instruction not like", value, "projectInstruction");
            return (Criteria) this;
        }

        public Criteria andProjectInstructionIn(List<String> values) {
            addCriterion("project_instruction in", values, "projectInstruction");
            return (Criteria) this;
        }

        public Criteria andProjectInstructionNotIn(List<String> values) {
            addCriterion("project_instruction not in", values, "projectInstruction");
            return (Criteria) this;
        }

        public Criteria andProjectInstructionBetween(String value1, String value2) {
            addCriterion("project_instruction between", value1, value2, "projectInstruction");
            return (Criteria) this;
        }

        public Criteria andProjectInstructionNotBetween(String value1, String value2) {
            addCriterion("project_instruction not between", value1, value2, "projectInstruction");
            return (Criteria) this;
        }

        public Criteria andWhitePaperLinkIsNull() {
            addCriterion("white_paper_link is null");
            return (Criteria) this;
        }

        public Criteria andWhitePaperLinkIsNotNull() {
            addCriterion("white_paper_link is not null");
            return (Criteria) this;
        }

        public Criteria andWhitePaperLinkEqualTo(String value) {
            addCriterion("white_paper_link =", value, "whitePaperLink");
            return (Criteria) this;
        }

        public Criteria andWhitePaperLinkNotEqualTo(String value) {
            addCriterion("white_paper_link <>", value, "whitePaperLink");
            return (Criteria) this;
        }

        public Criteria andWhitePaperLinkGreaterThan(String value) {
            addCriterion("white_paper_link >", value, "whitePaperLink");
            return (Criteria) this;
        }

        public Criteria andWhitePaperLinkGreaterThanOrEqualTo(String value) {
            addCriterion("white_paper_link >=", value, "whitePaperLink");
            return (Criteria) this;
        }

        public Criteria andWhitePaperLinkLessThan(String value) {
            addCriterion("white_paper_link <", value, "whitePaperLink");
            return (Criteria) this;
        }

        public Criteria andWhitePaperLinkLessThanOrEqualTo(String value) {
            addCriterion("white_paper_link <=", value, "whitePaperLink");
            return (Criteria) this;
        }

        public Criteria andWhitePaperLinkLike(String value) {
            addCriterion("white_paper_link like", value, "whitePaperLink");
            return (Criteria) this;
        }

        public Criteria andWhitePaperLinkNotLike(String value) {
            addCriterion("white_paper_link not like", value, "whitePaperLink");
            return (Criteria) this;
        }

        public Criteria andWhitePaperLinkIn(List<String> values) {
            addCriterion("white_paper_link in", values, "whitePaperLink");
            return (Criteria) this;
        }

        public Criteria andWhitePaperLinkNotIn(List<String> values) {
            addCriterion("white_paper_link not in", values, "whitePaperLink");
            return (Criteria) this;
        }

        public Criteria andWhitePaperLinkBetween(String value1, String value2) {
            addCriterion("white_paper_link between", value1, value2, "whitePaperLink");
            return (Criteria) this;
        }

        public Criteria andWhitePaperLinkNotBetween(String value1, String value2) {
            addCriterion("white_paper_link not between", value1, value2, "whitePaperLink");
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

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table project_description_ja
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table project_description_ja
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

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
    }
}