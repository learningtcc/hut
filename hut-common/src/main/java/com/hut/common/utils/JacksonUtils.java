package com.hut.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jared on 2016/12/15.
 */
public class JacksonUtils {

    private  static  final ObjectMapper sharedMapper = new ObjectMapper();

    static{
        //同意序列化不序列化 null 值
        sharedMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        sharedMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION	, false);
    }

    public static <T> T toBean(InputStream in, TypeReference<T> typeReference){
        T t ;
        try {
            t= sharedMapper.readValue(in, typeReference);
            return t;
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static <T> T toBean(InputStream in,Class<T> type){
        T t ;
        try {
            t= sharedMapper.readValue(in, type);
            return t;
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static <T> T toBean(String jsonString,TypeReference<T> typeReference){
        T t ;
        try {
            t= sharedMapper.readValue(jsonString, typeReference);
            return t;
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static <T> T toBean(String jsonString,Class<T> type){
        T t ;
        try {
            t= sharedMapper.readValue(jsonString, type);
            return t;
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static Map<String,Object> toMap(String jsonString){
        Map<String,Object> map;
        try {
            map = sharedMapper.readValue(jsonString,HashMap.class);
            return map;
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public static void  sendJson(HttpServletResponse response, Object bean) throws IOException{
        String  jsonstring  = toJsonString(bean);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonstring);
    }

    public static  String  toJsonString(Object bean){
        String temp;
        try {
            temp = sharedMapper.writeValueAsString(bean);
            return temp;
        } catch (JsonProcessingException e) {
            throw Throwables.propagate(e);
        }
    }

}
