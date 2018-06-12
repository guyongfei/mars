package com.witshare.mars.constant;


import org.apache.commons.lang3.StringUtils;

import static com.witshare.mars.constant.CacheConsts.LANGUAGE_CN;
import static com.witshare.mars.constant.CacheConsts.LANGUAGE_EN;
import static com.witshare.mars.constant.CommonStatisticItems.*;

/**
 * 项目国际化枚举
 */
public enum EnumI18NProject {

    PROJECT_DESCRIPTION_CN(LANGUAGE_CN, PROJECT_DES_TABLE_CN, PROJECT_DETAIL_CN),
    PROJECT_DESCRIPTION_EN(LANGUAGE_EN, PROJECT_DES_TABLE_EN, PROJECT_DETAIL_EN);

    private String requestLanguage;
    private String tableName;
    private String projectDetailName;

    EnumI18NProject(String requestLanguage, String tableName, String projectDetailName) {
        this.requestLanguage = requestLanguage;
        this.tableName = tableName;
        this.projectDetailName = projectDetailName;
    }

    /**
     * 默认 为 英文
     *
     * @param value
     * @return
     */
    public static EnumI18NProject getObjByLanguage(String value) {
        if (StringUtils.isEmpty(value)) {
            return PROJECT_DESCRIPTION_EN;
        }
        for (EnumI18NProject i18NProject : EnumI18NProject.values()) {
            if (i18NProject.requestLanguage.equals(value)) {
                return i18NProject;
            }
        }
        return PROJECT_DESCRIPTION_EN;
    }


    public String getRequestLanguage() {
        return requestLanguage;
    }

    public void setRequestLanguage(String requestLanguage) {
        this.requestLanguage = requestLanguage;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getProjectDetailName() {
        return projectDetailName;
    }

    public void setProjectDetailName(String projectDetailName) {
        this.projectDetailName = projectDetailName;
    }
}
