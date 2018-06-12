package com.witshare.mars.constant;

/**
 * 项目排序条件枚举
 */
public enum OrderCondition {

    PROJECT_NAME("projectName", "pd.project_name"),
    PROJECT_GRADE("projectGrade", "sp.project_grade"),
    PROJECT_FOLLOW_NUM("followerCount", "sp.follower_count"),
    PROJECT_UPDATE_TIME("createTime", "sp.update_time");


    private String code;

    private String condition;


    OrderCondition(String code, String condition) {
        this.code = code;
        this.condition = condition;
    }

    public static String getCondition(String code) {
        for (OrderCondition orderCondition : values()) {
            if (orderCondition.getCode().equals(code))
                return orderCondition.getCondition();
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
