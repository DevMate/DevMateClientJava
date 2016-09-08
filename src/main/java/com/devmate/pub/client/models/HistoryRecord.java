package com.devmate.pub.client.models;

import com.devmate.pub.client.impl.ObjectMapperProvider;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("license_id")
    private Long licenseId;
    @JsonProperty("activation_id")
    private Long activationId;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("identifiers")
    private String identifiers;
    @JsonProperty("offline_license")
    private String offlineLicense;
    @JsonProperty("activation_name")
    private String activationName;
    @JsonProperty("activation_email")
    private String activationEmail;
    @JsonProperty("deactivated")
    @JsonSerialize(using=ObjectMapperProvider.NumericBooleanSerializer.class)
    @JsonDeserialize(using=ObjectMapperProvider.NumericBooleanDeserializer.class)
    private Boolean deactivated;

    protected HistoryRecord() {}

    private HistoryRecord(Builder builder) {
        type = builder.type;
        timestamp = builder.timestamp;
        userName = builder.userName;
        note = builder.note;
        id = builder.id;
        licenseId = builder.licenseId;
        activationId = builder.activationId;
        productName = builder.productName;
        identifiers = builder.identifiers;
        offlineLicense = builder.offlineLicense;
        activationName = builder.activationName;
        activationEmail = builder.activationEmail;
        deactivated = builder.deactivated;
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
        builder.id = copy.id;
        builder.licenseId = copy.licenseId;
        builder.activationId = copy.activationId;
        builder.productName = copy.productName;
        builder.identifiers = copy.identifiers;
        builder.offlineLicense = copy.offlineLicense;
        builder.activationName = copy.activationName;
        builder.activationEmail = copy.activationEmail;
        builder.deactivated = copy.deactivated;
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

    public Integer getId() {
        return id;
    }

    public Long getLicenseId() {
        return licenseId;
    }

    public Long getActivationId() {
        return activationId;
    }

    public String getProductName() {
        return productName;
    }

    public String getIdentifiers() {
        return identifiers;
    }

    public String getOfflineLicense() {
        return offlineLicense;
    }

    public String getActivationName() {
        return activationName;
    }

    public String getActivationEmail() {
        return activationEmail;
    }

    public Boolean getDeactivated() {
        return deactivated;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HistoryRecord{");
        sb.append("type=").append(type);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", note='").append(note).append('\'');
        sb.append(", id=").append(id);
        sb.append(", licenseId=").append(licenseId);
        sb.append(", activationId=").append(activationId);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", identifiers='").append(identifiers).append('\'');
        sb.append(", offlineLicense='").append(offlineLicense).append('\'');
        sb.append(", activationName='").append(activationName).append('\'');
        sb.append(", activationEmail='").append(activationEmail).append('\'');
        sb.append(", deactivated=").append(deactivated);
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
                Objects.equal(note, that.note) &&
                Objects.equal(id, that.id) &&
                Objects.equal(licenseId, that.licenseId) &&
                Objects.equal(activationId, that.activationId) &&
                Objects.equal(productName, that.productName) &&
                Objects.equal(identifiers, that.identifiers) &&
                Objects.equal(offlineLicense, that.offlineLicense) &&
                Objects.equal(activationName, that.activationName) &&
                Objects.equal(activationEmail, that.activationEmail) &&
                Objects.equal(deactivated, that.deactivated);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                type, timestamp, userName, note, id, licenseId, activationId, productName, identifiers, offlineLicense,
                activationName, activationEmail, deactivated
        );
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
        private Integer id;
        private Long licenseId;
        private Long activationId;
        private String productName;
        private String identifiers;
        private String offlineLicense;
        private String activationName;
        private String activationEmail;
        private Boolean deactivated;

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

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder licenseId(Long val) {
            licenseId = val;
            return this;
        }

        public Builder activationId(Long val) {
            activationId = val;
            return this;
        }

        public Builder productName(String val) {
            productName = val;
            return this;
        }

        public Builder identifiers(String val) {
            identifiers = val;
            return this;
        }

        public Builder offlineLicense(String val) {
            offlineLicense = val;
            return this;
        }

        public Builder activationName(String val) {
            activationName = val;
            return this;
        }

        public Builder activationEmail(String val) {
            activationEmail = val;
            return this;
        }

        public Builder deactivated(Boolean val) {
            deactivated = val;
            return this;
        }

        public HistoryRecord build() {
            return new HistoryRecord(this);
        }
    }
}
