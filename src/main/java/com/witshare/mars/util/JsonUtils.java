package com.witshare.mars.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 自定义Json工具
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Gson GSON_Pretty = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    private static final Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .serializeNulls()
            .registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
                @Override
                public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                    if (src == src.longValue())
                        return new JsonPrimitive(src.longValue());
                    return new JsonPrimitive(src);
                }
            }).create();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toGsonPretty(Object data) {
        return GSON_Pretty.toJson(data);
    }

    /**
     * 实体类转化为字符串
     *
     * @param data
     * @return
     */
    public static String objToJsonByGson(Object data) {
        return gson.toJson(data);
    }

    public static <T> T jsonToObjByGson(String json, Class<T> obj) {
        return gson.fromJson(json, obj);
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将json结果集转化为JsonNode
     *
     * @param jsonData json数据
     * @return
     */
    public static JsonNode jsonToNode(String jsonData) {
        try {
            JsonNode node = MAPPER.readTree(jsonData);
            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
