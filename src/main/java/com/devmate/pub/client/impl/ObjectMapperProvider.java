package com.devmate.pub.client.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Date;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public final class ObjectMapperProvider implements ContextResolver<ObjectMapper> {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .registerModule(new SimpleModule()
                        .addSerializer(Date.class, new UnixTimestampSerializer())
                        .addDeserializer(Date.class, new UnixTimestampDeserializer()));
    }

    public static ObjectMapper objectMapper() {
        return OBJECT_MAPPER;
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return OBJECT_MAPPER;
    }

    private static class UnixTimestampSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeNumber(toSecondsSinceEpoch(value));
        }

        private static long toSecondsSinceEpoch(Date date) {
            return date.getTime() / 1000;
        }
    }

    private static class UnixTimestampDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
            final JsonToken token = p.getCurrentToken();
            if (token == JsonToken.VALUE_STRING) {
                final String str = p.getText().trim();
                return toDate(Long.parseLong(str));
            } else if (token == JsonToken.VALUE_NUMBER_INT) {
                return toDate(p.getLongValue());
            }
            throw ctxt.wrongTokenException(p, JsonToken.VALUE_STRING, "Expected a string or numeric value");
        }

        private static Date toDate(long secondsSinceEpoch) {
            return new Date(secondsSinceEpoch * 1000);
        }
    }
}
