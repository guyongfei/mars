package com.witshare.mars.job.coindata;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.witshare.mars.job.coindata.ExchangeSpiderUtil.EnumMoneyUnit.*;

/**
 * +
 * 工具类
 * <p>
 * Created by user on 2018/4/11.
 */
public class ExchangeSpiderUtil {

/*
一下分别是 11 行和12行的数据

1<html>
2<body>
3    [320,
4    <a href="/currencies/ico/"> <img src="//static.feixiaohao.com/coin/998ef9e2996eb7cdbb2c224c06ff04_small.png" alt="ICO-ico币网">ICO</a>, ,
6    <a href="/currencies/ico/#markets" class="price" data-usd="0.1378" data-cny="0.9078" data-btc="0.00003188">¥0.9078</a>,
7    <div class="text-green">
8            0%
9    </div>, , ¥0.9078亿, ,
10    <a href="https://etherscan.io/token/0xa33e729bf4fdeb868b534e1f20523463d9c46bee" rel="nofollow">10,000万*</a>, ,
11    <a href="/currencies/ico/#markets" class="volume" data-usd="3008.3351056" data-cny="18896.856965289" data-btc="0.42992">¥18,897</a>]
12 </body>
15</html>


--------------------------------------------------------------------------------
1<html>
3<body>
4    [310,
5    <a href="/currencies/ugchain/">
6        <img src="//static.feixiaohao.com/coin/20180109/92e05c22084a47bfb23c9cf8345ccdc4.png" alt="UGC">UGC</a>, ,
7    <a href="/currencies/ugchain/#markets" class="price" data-usd="0.0304" data-cny="0.1907" data-btc="0.00000444">¥0.1907</a>,
8    <div class="text-green">
9            5.23%
10    </div>, , ¥0.9536亿, , 50,000万 , ,
11    <a href="/currencies/ugchain/#markets" class="volume" data-usd="2589069.62227343" data-cny="16263240.8333046" data-btc="378.212867161304">¥1,626万</a>]
12</body>
14</html>
*/


 /*   @Test
    public void test() {
        String index2 = "  [310,";
        String index3 = "  <img src=\"//static.feixiaohao.com/coin/20180109/92e05c22084a47bfb23c9cf8345ccdc4.png\" alt=\"UGC\">UGC</a>, ,";
        String index4 = "  <a href=\"/currencies/ugcha-in/#markets\" class=\"price\" data-usd=\"0.0304\" data-cny=\"0.1907\" data-btc=\"0.00000444\">¥0.1907</a>,";
        String index6 = "   5.23%";
        String index07 = "   </div>, , ¥0.9078亿, ,";
        String index70 = "  </div>, , ¥0.9536亿, , 50,000万 , ,";
        String index08 = "  <a href=\"https://etherscan.io/token/0xa33e729bf4fdeb868b534e1f20523463d9c46bee\" rel=\"nofollow\">10,000万*</a>, ,";
        String index89 = "  <a href=\"/currencies/ugchain/#markets\" class=\"volume\" data-usd=\"2589069.62227343\" data-cny=\"16263240.8333046\" data-btc=\"378.212867161304\">¥1,626万</a>]";
        System.out.println(getOtherName(index4));
    }*/

    public static String getId(String string) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(string);
        String code;
        if (matcher.find()) {
            matcher.reset();
            if (matcher.find()) {
                code = matcher.group(0);
                return code;
            }
        }
        return "";
    }


    public static String getOtherName(String string) {
        Pattern pattern = Pattern.compile("/currencies/[-\\w]+/#markets");
        Matcher matcher = pattern.matcher(string);
        String otherName;
        if (matcher.find()) {
            matcher.reset();
            if (matcher.find()) {
                otherName = matcher.group(0).replace("/currencies/", "").replace("/#markets", "");
                return otherName;
            }
        }
        return "";
    }

    public static String getDataBtc(String string) {
        Pattern pattern = Pattern.compile("data-btc=\"[\\d.]*\"");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            matcher.reset();
            if (matcher.find()) {
                String code = matcher.group(0).replace("data-btc=\"", "").replace("\"", "");
                return code;
            }
        }
        return "";
    }

    public static String getDataCny(String string) {
        Pattern pattern = Pattern.compile("data-cny=\"[\\d.]*\"");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            matcher.reset();
            if (matcher.find()) {
                String code = matcher.group(0).replace("data-cny=\"", "").replace("\"", "");
                return code;
            }
        }
        return "";
    }

    public static String getDataUsd(String string) {
        Pattern pattern = Pattern.compile("data-usd=\"[\\d.]*\"");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            matcher.reset();
            while (matcher.find()) {
                String code = matcher.group(0).replace("data-usd=\"", "").replace("\"", "");
                return code;
            }
        }
        return "";
    }

    //流通数量
    public static String getMarketNum(String string) {
        Pattern pattern = Pattern.compile(">[\\d\\u4e00-\\u9fa5,\\*]*<\\/");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            matcher.reset();
            if (matcher.find()) {
                String code = matcher.group(0).replace(">", "").replace("</", "").replace("*", "").replace(",", "");
                return code;
            }
        }
        return "";
    }

    public static String index72(String string) {
        Pattern pattern = Pattern.compile("\\s[\\d,]+[\\u4e00-\\u9fa5]*\\*?\\s,");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            matcher.reset();
            if (matcher.find()) {
                String code = matcher.group(0).replace(" ", "").replace(",", "");
                return code;
            }
        }
        return "";
    }

    public static String getMarketCap(String string) {
        Pattern pattern = Pattern.compile("¥[\\d.,]*[\\u4e00-\\u9fa5]*");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            matcher.reset();
            if (matcher.find()) {
                String code = matcher.group(0).replace(",", "").replace("¥", "");
                return code;
            }
        }
        return "";
    }

    public static String getChange(String string) {
        Pattern pattern = Pattern.compile("[-\\d.]*%");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            matcher.reset();
            if (matcher.find()) {
                String code = matcher.group(0);
                return code;
            }
        }
        return "";
    }

    public static String getName(String string) {
        Pattern pattern = Pattern.compile(">[\\w-[\\u4e00-\\u9fa5]]+<\\/");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            matcher.reset();
            if (matcher.find()) {
                String code = matcher.group(0).replace(">", "").replace("</", "");
                return code;
            }
        }
        return "";
    }


    public static void removeNodes(Document doc) {
        for (Element element : doc.select("*")) {
            if (!element.hasText() && element.isBlock()) {
                element.remove();
            }
        }
    }

    /**
     * 将 文本数字转化成单位为1的数字
     *
     * @param string
     * @return
     */
    public static BigDecimal moneyUtilToNum(String string) {
        string = string.replace("*", "").replace(",", "").trim();
        BigDecimal unit = BigDecimal.ONE;
        BigDecimal result = BigDecimal.ZERO;
        if (StringUtils.isEmpty(string)) {
            return result;
        }

        for (EnumMoneyUnit enumMoneyUnit : EnumMoneyUnit.values()) {
            if (StringUtils.contains(string, enumMoneyUnit.name)) {
                string = string.replace(enumMoneyUnit.name, "");
                unit = enumMoneyUnit.value;
                break;
            }
        }
        if (NumberUtils.isNumber(string)) {
            result = new BigDecimal(string).multiply(unit);
        }
        return result;
    }

    /**
     * @param bigDecimal 需要处理的数据
     * @param toUnit     转化成的单位
     * @param country    货币对应国家
     * @param sign       是否显示货币符号
     * @param rate       转化费率
     * @return
     */
    public static String numToMoneyUnit(BigDecimal bigDecimal, EnumCountry country, EnumMoneyUnit toUnit, Boolean sign, BigDecimal rate) {
        String result = "";
        if (bigDecimal == null) {
            return result;
        }
        country = country == null ? EnumCountry.Zh : country;
        sign = sign == null ? Boolean.FALSE : Boolean.TRUE;
        rate = rate == null ? BigDecimal.ONE : rate;
        bigDecimal = bigDecimal.multiply(rate);
        if (toUnit == null) {
            switch (country) {
                case En:
                    toUnit = bigDecimal.compareTo(B.value) >= 0 ? B : (bigDecimal.compareTo(M.value) >= 0 ? M : (bigDecimal.compareTo(K.value) >= 0 ? K : One));
                    break;
                case Zh:
                    toUnit = bigDecimal.compareTo(Yi.value) >= 0 ? Yi : (bigDecimal.compareTo(Wan.value) >= 0 ? Wan : One);
                    break;
                default:
                    toUnit = One;
                    break;
            }
        }

        //根据数据的大小来判断显示的长度
        BigDecimal divide = bigDecimal.divide(toUnit.value);
        String partnerStr = "#,##0" + (divide.compareTo(new BigDecimal(Math.pow(10, 2))) < 0 ? ".00" : "");
        DecimalFormat df = new DecimalFormat(partnerStr);
        String formatStr = df.format(divide);

        if (bigDecimal.compareTo(BigDecimal.ZERO) == 0) {
            formatStr = "";
        } else if (bigDecimal.compareTo(new BigDecimal(0.000099)) < 0) {
            formatStr = bigDecimal.setScale(8, BigDecimal.ROUND_FLOOR).toString();
        } else if (bigDecimal.compareTo(new BigDecimal(0.0099)) < 0) {
            formatStr = bigDecimal.setScale(6, BigDecimal.ROUND_FLOOR).toString();
        } else if (bigDecimal.compareTo(BigDecimal.ONE) < 0) {
            formatStr = bigDecimal.setScale(4, BigDecimal.ROUND_FLOOR).stripTrailingZeros().toPlainString();
        }
        result = StringUtils.isEmpty(formatStr) ? "" : ((sign ? country.getValue() : "") + formatStr + toUnit.name);
        return result;
    }

  /*  @Test
    public void test() {
        String s = numToMoneyUnit(new BigDecimal(0.23456789), EnumCountry.Zh, null, false, null);
        System.out.println(s);
        s = numToMoneyUnit(new BigDecimal(0.008955642), EnumCountry.Zh, null, false, null);
        System.out.println(s);
        s = numToMoneyUnit(new BigDecimal(0.00005214), EnumCountry.Zh, null, false, null);
        System.out.println(s);
        s = numToMoneyUnit(new BigDecimal(0), EnumCountry.Zh, null, false, null);
        System.out.println(s);

    }
*/

    enum EnumMoneyUnit {
        B("B", new BigDecimal(Math.pow(10, 9))),
        Yi("亿", new BigDecimal(Math.pow(10, 8))),
        b("b", new BigDecimal(Math.pow(10, 9))),
        M("M", new BigDecimal(Math.pow(10, 6))),
        m("m", new BigDecimal(Math.pow(10, 6))),
        Wan("万", new BigDecimal(Math.pow(10, 4))),
        K("K", new BigDecimal(Math.pow(10, 3))),
        k("k", new BigDecimal(Math.pow(10, 3))),
        One("", BigDecimal.ONE),;

        private String name;
        private BigDecimal value;

        EnumMoneyUnit() {
        }

        EnumMoneyUnit(String name, BigDecimal value) {
            this.name = name;
            this.value = value;
        }
    }

    enum EnumCountry {
        Zh("CNY", "￥"),
        En("USD", "$");
        private String name;
        private String value;

        EnumCountry() {
        }

        EnumCountry(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
