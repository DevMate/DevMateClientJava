package com.devmate.pub.client.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;

import java.util.Date;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Iterables.getFirst;
import static java.util.Arrays.asList;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class HistoryRecord {
    @JsonProperty("type")
    private Type type;
    @JsonProperty("timestamp")
    private Date timestamp;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("note")
    private String note;

    protected HistoryRecord() {}

    private HistoryRecord(Builder builder) {
        type = builder.type;
        timestamp = builder.timestamp;
        userName = builder.userName;
        note = builder.note;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(HistoryRecord copy) {
        Builder builder = new Builder();
        builder.type = copy.type;
        builder.timestamp = copy.timestamp;
        builder.userName = copy.userName;
        builder.note = copy.note;
        return builder;
    }

    public Type getType() {
        return type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getUserName() {
        return userName;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HistoryRecord{");
        sb.append("type=").append(type);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", note='").append(note).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryRecord that = (HistoryRecord) o;
        return type == that.type &&
                Objects.equal(timestamp, that.timestamp) &&
                Objects.equal(userName, that.userName) &&
                Objects.equal(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type, timestamp, userName, note);
    }

    public enum Type {
        ACTIVATION(1), CREATING(2), EXPIRING(3), BLOCKING(4), RETURNING(5), RESETTING(6);

        private final Integer apiIndex;

        Type(Integer apiIndex) {
            this.apiIndex = apiIndex;
        }

        @JsonValue
        public Integer getApiIndex() {
            return apiIndex;
        }

        @JsonCreator
        public static Type fromApiIndex(final Integer apiIndex) {
            return getFirst(filter(asList(Type.values()), new Predicate<Type>() {
                @Override
                public boolean apply(Type type) {
                    return type.getApiIndex().equals(apiIndex);
                }
            }), null);
        }
    }

    public static final class Builder {
        private Type type;
        private Date timestamp;
        private String userName;
        private String note;

        private Builder() {
        }

        public Builder type(Type val) {
            type = val;
            return this;
        }

        public Builder timestamp(Date val) {
            timestamp = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder note(String val) {
            note = val;
            return this;
        }

        public HistoryRecord build() {
            return new HistoryRecord(this);
        }
    }
}
