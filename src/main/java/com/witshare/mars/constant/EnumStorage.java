package com.witshare.mars.constant;

/**
 * 对象存储名枚举
 */
public enum EnumStorage {

    Log(0, "log", "log图片"),
    View(1, "view", "展示图片"),
    PdfEn(2, "pdfEn", "英文pdf"),
    PdfZh(3, "pdfZh", "中文pdf"),
    BillBoard(4, "billBoard", "广告牌"),
    Avatar(5, "avatar", "用户头像"),
    SocialImg(6, "social", "社交网站图片"),
    BaseInfo(7, "baseInfo", "基础信息图片"),
    Partner(8, "partner", "合作伙伴图片");

    public static final String PDF = "pdf";
    public static final String BILL_BOARD = "billBoard";
    public static final String SOCIAL = "social";
    public static final String BASE_INFO = "baseInfo";

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
