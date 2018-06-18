package com.witshare.mars.constant;

/**
 * 项目排序条件枚举
 */
public enum OrderCondition {

    PROJECT_NAME("projectName", "pd.project_name"),
    PROJECT_START_TIME("startTime", "sp.start_time"),
    PROJECT_END_TIME("endTime", "sp.end_time"),;


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
