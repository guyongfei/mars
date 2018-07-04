package com.witshare.mars.pojo.vo;

public class IndexBean {


    private SysProjectBeanFrontInfoVo defaultProject;

    public IndexBean() {
    }

    public static IndexBean newInstance() {
        return new IndexBean();
    }

    public SysProjectBeanFrontInfoVo getDefaultProject() {
        return defaultProject;
    }

    public IndexBean setDefaultProject(SysProjectBeanFrontInfoVo defaultProject) {
        this.defaultProject = defaultProject;
        return this;
    }
}
