package net.roxia.scheduler.common.utils;

import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangjunwei01
 * @date 2020-01-11 17:10
 **/
public class JsonUtil {
    private static Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    private static JsonParser jsonParser = new JsonParser();
    private static Gson gson = new Gson();

    /**
     * 解析json字符串
     *
     * @param jsonStr
     * @return
     */
    public static JsonObject parseJsonObj(String jsonStr) {
        try {
            JsonElement jsonElement = jsonParser.parse(jsonStr);
            return jsonElement.getAsJsonObject();
        } catch (Exception ex) {
            LOG.info("Exception occurred in parsing JSON. Please check whether the format of JSON string is correct! JSON:{}", jsonStr);
            return null;
        }
    }

    /**
     * 解析json字符串
     *
     * @param jsonStr
     * @return
     */
    public static <T> T parseJsonObj(String jsonStr, Class<T> clazz) {
        try {
            return gson.fromJson(jsonStr, clazz);
        } catch (Exception ex) {
            LOG.info("Exception occurred in parsing JSON. Please check whether the format of JSON string is correct! JSON:{}", jsonStr);
            return null;
        }
    }

    /**
     * 解析json字符串
     *
     * @param jsonStr
     * @return
     */
    public static JsonArray parseJsonArray(String jsonStr) {
        try {
            JsonElement jsonElement = jsonParser.parse(jsonStr);
            return jsonElement.getAsJsonArray();
        } catch (Exception ex) {
            LOG.info("Exception occurred in parsing JSON. Please check whether the format of JSON string is correct! JSON:{}", jsonStr);
            return null;
        }
    }

    public static JsonArray getJsonArray(JsonObject jsonObject, String key) {
        if (jsonObject == null || jsonObject.isJsonNull()) {
            return null;
        }
        try {
            return jsonObject.getAsJsonArray(key);
        } catch (Exception ex) {
            LOG.info("The type of this key-value[key={}] is not JsonArray, please use correct type!", key);
            return null;
        }
    }

    public static JsonObject getJsonObject(JsonObject jsonObject, String key) {
        if (jsonObject == null || jsonObject.isJsonNull()) {
            return null;
        }
        try {
            return jsonObject.getAsJsonObject(key);
        } catch (Exception ex) {
            LOG.info("The type of this key-value[key={}] is not JsonArray, please use correct type!", key);
            return null;
        }
    }

    public static Byte getByte(JsonObject jsonObject, String key) {
        if (jsonObject == null || jsonObject.isJsonNull()) {
            return null;
        }
        try {
            return jsonObject.get(key).getAsByte();
        } catch (Exception ex) {
            LOG.info("The type of this key-value[key={}] is not Byte, please use correct type!", key);
            return null;
        }
    }

    public static Integer getInteger(JsonObject jsonObject, String key) {
        if (jsonObject == null || jsonObject.isJsonNull()) {
            return null;
        }
        try {
            return jsonObject.get(key).getAsInt();
        } catch (Exception ex) {
            LOG.info("The type of this key-value[key={}] is not Integer, please use correct type!", key);
            return null;
        }
    }

    public static Long getLong(JsonObject jsonObject, String key) {
        if (jsonObject == null || jsonObject.isJsonNull()) {
            return null;
        }
        try {
            return jsonObject.get(key).getAsLong();
        } catch (Exception ex) {
            LOG.info("The type of this key-value[key={}] is not Long, please use correct type!", key);
            return null;
        }
    }

    public static Float getFloat(JsonObject jsonObject, String key) {
        if (jsonObject == null || jsonObject.isJsonNull()) {
            return null;
        }
        try {
            return jsonObject.get(key).getAsFloat();
        } catch (Exception ex) {
            LOG.info("The type of this key-value[key={}] is not Float, please use correct type!", key);
            return null;
        }
    }

    public static Double getDouble(JsonObject jsonObject, String key) {
        if (jsonObject == null || jsonObject.isJsonNull()) {
            return null;
        }
        try {
            return jsonObject.get(key).getAsDouble();
        } catch (Exception ex) {
            LOG.info("The type of this key-value[key={}] is not Double, please use correct type!", key);
            return null;
        }
    }

    public static String getString(JsonObject jsonObject, String key) {
        if (jsonObject == null || jsonObject.isJsonNull()) {
            return null;
        }
        return jsonObject.get(key).getAsString();
    }

    public static String toJsonString(Object obj) {
        return new Gson().toJson(obj);
    }
}
