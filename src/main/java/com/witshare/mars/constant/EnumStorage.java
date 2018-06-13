package com.witshare.mars.constant;

/**
 * 对象存储名枚举
 */
public enum EnumStorage {

    Log(0, "log", "log图片"),
    View(1, "view", "展示图片"),
    PdfEn(2, "pdfEn", "英文pdf"),
    PdfCn(3, "pdfCn", "繁体pdf"),
    PdfKo(4, "pdfKo", "韩文pdf"),
    PdfJa(5, "pdfJa", "日文pdf"),
    Avatar(6, "avatar", "用户头像");

    public static final String PDF = "pdf";

    private int id;
    private String name;
    private String description;

    EnumStorage() {
    }

    EnumStorage(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
