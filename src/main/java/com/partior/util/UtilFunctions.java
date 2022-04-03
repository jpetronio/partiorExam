package com.partior.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class UtilFunctions {

    private static ObjectMapper objectMapper = new ObjectMapper();


    /**
     * <p>
     * Used to deserialize json string to java objects without validation.
     * </p>
     *
     * @param json  Json String.
     * @param clazz Class reference for deserialization.
     * @param <T>   Generic type for class reference.
     * @return Java Object
     * @throws Exception Deserialization failure.
     */
    public static <T> T jsonDeserialize(final String json, Class<T> clazz) throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return Optional.ofNullable(json).isPresent() && !StringUtils.isBlank(json) ? objectMapper.readValue(json, clazz) : null;
    }

    /**
     * <p>
     * Serialize java objects to json string.
     * </p>
     *
     * @param object Java Object to serialize.
     * @return Json String.
     * @throws JsonProcessingException serialization failure.
     */
    public static String jsonSerialize(Object object) throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.writeValueAsString(object);
    }
}
