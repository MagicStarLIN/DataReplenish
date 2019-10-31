package com.lcl.datareplenish.utils;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class JsonUtils {
    private static final ObjectMapper JSON = new ObjectMapper();

    static {
        JSON.setSerializationInclusion(Include.NON_NULL);
        JSON.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
    }

    public static String toJson(Object obj) {
        try {
            return JSON.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
    static ObjectMapper mapper = new ObjectMapper();

    // 对象转json
    public static String objToJson(Object obj) {

        String result = null;

        try {
            result = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }
    // json转对象
    public static Object jsonToObj(String json, Class clazz) {

        Object result = null;
        try {
            result = mapper.readValue(json, clazz);

        } catch (Exception e) {
//            e.printStackTrace();
        }
        return result;
    }


    // 对象转json
    public static String objToJsonWriteValue(Object obj) {

        String result = null;

        try {
            result = mapper.writeValueAsString(obj);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
