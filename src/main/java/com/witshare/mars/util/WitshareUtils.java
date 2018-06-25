package com.witshare.mars.util;

import com.witshare.mars.config.CurrentThreadContext;
import com.witshare.mars.constant.EnumI18NProject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import static com.witshare.mars.constant.CacheConsts.PARAM_PASSWORD_ENCRYPTION_ALGORITHM;
import static com.witshare.mars.constant.CacheConsts.PARAM_PASSWORD_ENCRYPTION_TIMES;


public class WitshareUtils {
    public static final Logger LOGGER = LoggerFactory.getLogger(WitshareUtils.class);


    /**
     * 读取properties文件
     *
     * @param propertiesPath
     * @return
     */
    public static Properties getProperties(String propertiesPath) {
        Properties prop = null;
        try {
            prop = new Properties();
            prop.load(Object.class.getClassLoader().getResourceAsStream(propertiesPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static Date getDateByLocalDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }


    public static Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp getTodayZeroTimeStamp() {
        return new Timestamp(System.currentTimeMillis() / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset());
    }

    public static boolean rangeInDefined(double current, double min, double max) {
        return Math.max(min, current) == Math.min(current, max);
    }

    public static void copyProperties(Object src, Object trg, Iterable<String> props) {

        BeanWrapper srcWrap = PropertyAccessorFactory.forBeanPropertyAccess(src);
        BeanWrapper trgWrap = PropertyAccessorFactory.forBeanPropertyAccess(trg);

        props.forEach(p -> trgWrap.setPropertyValue(p, srcWrap.getPropertyValue(p)));

    }

    public static void copyProperties2(Object src, Object trg, Set<String> props) {
        String[] excludedProperties =
                Arrays.stream(BeanUtils.getPropertyDescriptors(src.getClass()))
                        .map(PropertyDescriptor::getName)
                        .filter(name -> !props.contains(name))
                        .toArray(String[]::new);

        BeanUtils.copyProperties(src, trg, excludedProperties);
    }

    /**
     * 加密用户密码。
     */
    public static String encryptPassword(String loginName, String salt, String originPassword) {
        String cryptograph = new SimpleHash(
                PARAM_PASSWORD_ENCRYPTION_ALGORITHM,
                originPassword,
                ByteSource.Util.bytes(loginName + salt),
                PARAM_PASSWORD_ENCRYPTION_TIMES
        ).toString();
        return cryptograph;
    }

    /**
     * 生成UUID
     *
     * @return
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }


    /**
     * 获取随机字符串
     *
     * @param length
     * @return
     */
    public static String generateRandomChars(Integer length) {
        //避免一些有歧义的字符
        final String pattern = "acdefhkmnprstuvwxy34578";
        if (length == null) {
            length = 6;
        }
        Random random = new Random();
        return random.ints(0, pattern.length())
                .mapToObj(i -> pattern.charAt(i))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    /**
     * 获取随机字符串
     *
     * @param length
     * @return
     */
    public static String generateRandomEnChar(Integer length) {
        //避免一些有歧义的字符
        final String pattern = "QWERTYUIOPASDFGHJKLZXCVBNM";
        if (length == null) {
            length = 6;
        }
        Random random = new Random();
        return random.ints(0, pattern.length())
                .mapToObj(i -> pattern.charAt(i))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }


    /**
     * 获取随机数字
     *
     * @param length
     * @return
     */
    public static String generateRandomNums(Integer length) {
        //避免一些有歧义的字符
        final String pattern = "0123456789";
        if (length == null) {
            length = 6;
        }
        Random random = new Random();
        return random.ints(0, pattern.length())
                .mapToObj(i -> pattern.charAt(i))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public static boolean isObjectEmpty(Object object) {
        if (object == null) return true;
        else if (object instanceof String) {
            if (((String) object).trim().length() == 0) {
                return true;
            }
        } else if (object instanceof Collection) {
            return isCollectionEmpty((Collection<?>) object);
        }
        return false;
    }

    private static boolean isCollectionEmpty(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
        if (map == null)
            return null;
        Object obj = null;
        try {
            obj = beanClass.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }

                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }

        return map;
    }


    public static int ObjToInteger(Object obj) {
        int i = 0;
        if (obj instanceof Number) {
            if (obj instanceof Double) {
                i = ((Double) obj).intValue();
            } else if (obj instanceof Long) {
                i = ((Long) obj).intValue();
            } else if (obj instanceof Float) {
                i = ((Float) obj).intValue();
            } else {
                i = (int) obj;
            }
        } else if (obj instanceof String) {
            i = new Double((String) obj).intValue();
        } else {
            throw new RuntimeException("ObjToInteger error.");
        }
        return i;
    }

    public static String getDataStr(SimpleDateFormat sdf, Date date) {
        if (date == null) {
            date = new Date();
        }
        if (sdf == null) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        return sdf.format(date);
    }

    /**
     *  * 返回昨天
     *  * @param today
     *  * @return
     *  
     */
    public static Date yesterday(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        return calendar.getTime();
    }

    /**
     *  * 返回明天
     *  * @param today
     *  * @return
     *  
     */
    public static Date tomorrow(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        return calendar.getTime();
    }

    final static Base64.Decoder decoder = Base64.getDecoder();
    final static Base64.Encoder encoder = Base64.getEncoder();

    //base64字符串转化成图片
    public static byte[] base64ToByte(String imgStr) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return null;
        try {
            //Base64解码
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            return b;
        } catch (Exception e) {
            throw new RuntimeException("base64ToByte error.");
        }
    }

    public static String inputStreamToBase64(InputStream inputStream) {
        String strBase64 = null;
        try {
            // in.available()返回文件的字节长度
            byte[] bytes = new byte[inputStream.available()];
            // 将文件中的内容读入到数组中
            inputStream.read(bytes);
            strBase64 = encoder.encodeToString(bytes);      //将字节流数组转换为字符串
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("inputStreamToBase64 error.");
        }
        return strBase64;
    }

    public static InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    public static byte[] inputStreamToByte(InputStream inStream) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        try {
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            outStream.close();
            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return outStream.toByteArray();
    }

    // 从服务器获得一个输入流(本例是指从服务器获得一个image输入流)
    public static InputStream getInputStreamFromUrl(String urlPath) {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(urlPath);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置网络连接超时时间
            httpURLConnection.setConnectTimeout(3000);
            // 设置应用程序要从网络连接读取数据
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                // 从服务器返回一个输入流
                inputStream = httpURLConnection.getInputStream();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("getInputStreamFromUrl error.");
        }
        return inputStream;
    }

}

