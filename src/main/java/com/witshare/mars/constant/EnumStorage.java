package com.witshare.mars.constant;

/**
 * 对象存储名枚举
 */
public enum EnumStorage {

    Log(0, "log", "log图片", "pic"),
    View(1, "view", "展示图片", "pic"),
    Avatar(6, "avatar", "用户头像", "pic");

    public static final String PDF = "pdf";

    private int id;
    private String name;
    private String description;
    private String suffix;

    EnumStorage() {
    }

    EnumStorage(int id, String name, String description, String suffix) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.suffix = suffix;
    }


    public static String getPDF() {
        return PDF;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSuffix() {
        return suffix;
    }
}
