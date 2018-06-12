package com.witshare.mars.constant;

/**
 * 返回信息国际化枚举
 */
public enum EnumResponseText {

    ErrorRequest(0, "请求错误", "Error request."),
    ErrorPassword(1, "密码错误", "Error Password."),
    ErrorEmailOrPassword(1, "邮箱或密码错误", "Error email or Password."),
    ErrorVerifyCode(2, "验证码错误", "Error verifyCode."),
    ErrorOriginPassword(3, "原密码错误", "Error originPassword."),
    ErrorEmail(4, "邮箱不合法", "Error email."),
    ErrorId(5, "id错误", "Error id."),
    ErrorLinkName(6, "名称错误", "Error linkName."),
    ErrorPicture(7, "图片错误", "Error picture."),
    ErrorNickname(8, "昵称不合法", "Error nickname."),
    ExistNickname(9, "昵称已存在", "The nickname already exists."),
    ExistEmail(10, "邮箱已存在", "Email already exists."),
    NeedLogUp(11, "请先注册该邮箱", "Please register the email first."),
    SysError(12, "系统异常", "System exception."),
    TooManyChoices(13, "选择了太多项", "Too many choices."),
    ErrorMaximumNumber(14, "请选择最大条数", "Please select the maximum number."),
    ContentOutOfRange(15, "内容太长", "content is out of range"),
    ErrorProjectId(16, "projectId错误", "Error projectId."),
    ErrorStorageContent(17, "存储内容错误", "Error storage content."),
    ErrorLevel(18, "level错误", "Error level."),
    NeedLogIn(19, "请先登陆", "Please login first."),
    ExistName(20, "名称已存在", "The name has already existed."),
    ExistNameOrToken(21, "名称或token已存在", "The name or token already exists."),
    HasRelate(22, "存在关联项", "Existence Association term."),
    AccountSuspended(22, "账户被冻结", "Account Suspended."),
    HAVEFOLLOWEDBEFORE(23, "已经关注过了", "have already followed"),
    CANNOTBLOCKEDYOURSELF(24, "不能冻结自己", "can't blocked yourself");

    private int value;
    private String zh;
    private String en;

    EnumResponseText() {
    }

    EnumResponseText(int value, String zh, String en) {
        this.value = value;
        this.zh = zh;
        this.en = en;
    }

    public static EnumResponseText getByValue(int value) {
        for (EnumResponseText Status : EnumResponseText.values()) {
            if (Status.value == value) {
                return Status;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }
}
