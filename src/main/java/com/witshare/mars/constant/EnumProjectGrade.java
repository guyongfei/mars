package com.witshare.mars.constant;

import java.math.BigDecimal;

/**
 * 项目评级枚举
 */
public enum EnumProjectGrade {

    AAA("AAA", 9),
    AA("AA", 8),
    A("A", 7),
    BBB("BBB", 6),
    BB("BB", 5),
    B("B", 4),
    C("C", 3),
    D("D", 2),
    NP("NP", 1);

    Integer min;
    private String gradeStr;


    EnumProjectGrade(String gradeStr, Integer min) {
        this.gradeStr = gradeStr;
        this.min = min;
    }

    public static EnumProjectGrade getProGradeByScore(BigDecimal score) {
        for (EnumProjectGrade element : values()) {
            if (score.intValue() == element.getMin())
                return element;
        }
        return NP;
    }

    public static EnumProjectGrade getProGradeBygrade(String grade) {
        for (EnumProjectGrade element : values()) {
            if (element.getGradeStr().equals(grade))
                return element;
        }
        return NP;
    }

    public String getGradeStr() {
        return gradeStr;
    }

    public void setGradeStr(String gradeStr) {
        this.gradeStr = gradeStr;
    }

    public Integer getMin() {
        return min;
    }


}
