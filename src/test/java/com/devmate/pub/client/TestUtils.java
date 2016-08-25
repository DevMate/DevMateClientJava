package com.devmate.pub.client;

import com.devmate.pub.client.impl.ObjectMapperProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.matching.EqualToJsonPattern;

public final class TestUtils {
    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperProvider.objectMapper();

    private TestUtils() {}

    public static String toJson(Object value) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(value);
    }

    public static EqualToJsonPattern equalToObjectInJson(Object object) throws JsonProcessingException {
        return new EqualToJsonPattern(toJson(object), false, false);
    }

    public static void assertDoesNotThrow(FailingRunnable action){
        try {
            action.run();
        } catch(Exception e) {
            throw new AssertionError("Expected action not to throw, but it did!", e);
        }
    }

    public interface FailingRunnable {
        void run() throws Exception;
    }
}
