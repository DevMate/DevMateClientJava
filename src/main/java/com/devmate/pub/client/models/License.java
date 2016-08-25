package com.devmate.pub.client.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;

import java.util.Date;
import java.util.List;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Iterables.getFirst;
import static java.util.Arrays.asList;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class License {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("license_type_name")
    private String licenseTypeName;
    @JsonProperty("license_type_id")
    private Integer licenseTypeId;
    @JsonProperty("invoice")
    private String invoice;
    @JsonProperty("campaign")
    private String campaign;
    @JsonProperty("activations_used")
    private Integer activationsUsed;
    @JsonProperty("expiration_date")
    private Date expirationDate;
    @JsonProperty("date_created")
    private Date dateCreated;
    @JsonProperty("products")
    private List<Product> products;
    @JsonProperty("is_subscription")
    private Boolean isSubscription;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("activations_total")
    private Integer activationsTotal;
    @JsonProperty("activation_key")
    private String activationKey;
    @JsonProperty("history")
    private List<HistoryRecord> history;

    protected License() {}

    private License(Builder builder) {
        id = builder.id;
        licenseTypeName = builder.licenseTypeName;
        licenseTypeId = builder.licenseTypeId;
        invoice = builder.invoice;
        campaign = builder.campaign;
        activationsUsed = builder.activationsUsed;
        expirationDate = builder.expirationDate;
        dateCreated = builder.dateCreated;
        products = builder.products;
        isSubscription = builder.isSubscription;
        status = builder.status;
        activationsTotal = builder.activationsTotal;
        activationKey = builder.activationKey;
        history = builder.history;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(License copy) {
        Builder builder = new Builder();
        builder.id = copy.id;
        builder.licenseTypeName = copy.licenseTypeName;
        builder.licenseTypeId = copy.licenseTypeId;
        builder.invoice = copy.invoice;
        builder.campaign = copy.campaign;
        builder.activationsUsed = copy.activationsUsed;
        builder.expirationDate = copy.expirationDate;
        builder.dateCreated = copy.dateCreated;
        builder.products = copy.products;
        builder.isSubscription = copy.isSubscription;
        builder.status = copy.status;
        builder.activationsTotal = copy.activationsTotal;
        builder.activationKey = copy.activationKey;
        builder.history = copy.history;
        return builder;
    }

    public Integer getId() {
        return id;
    }

    public String getLicenseTypeName() {
        return licenseTypeName;
    }

    public Integer getLicenseTypeId() {
        return licenseTypeId;
    }

    public String getInvoice() {
        return invoice;
    }

    public String getCampaign() {
        return campaign;
    }

    public Integer getActivationsUsed() {
        return activationsUsed;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Boolean getSubscription() {
        return isSubscription;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getActivationsTotal() {
        return activationsTotal;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public List<HistoryRecord> getHistory() {
        return history;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("License{");
        sb.append("id=").append(id);
        sb.append(", licenseTypeName='").append(licenseTypeName).append('\'');
        sb.append(", licenseTypeId=").append(licenseTypeId);
        sb.append(", invoice='").append(invoice).append('\'');
        sb.append(", campaign='").append(campaign).append('\'');
        sb.append(", activationsUsed=").append(activationsUsed);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", dateCreated=").append(dateCreated);
        sb.append(", products=").append(products);
        sb.append(", isSubscription=").append(isSubscription);
        sb.append(", status=").append(status);
        sb.append(", activationsTotal=").append(activationsTotal);
        sb.append(", activationKey='").append(activationKey).append('\'');
        sb.append(", history=").append(history);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        License license = (License) o;
        return Objects.equal(id, license.id) &&
                Objects.equal(licenseTypeName, license.licenseTypeName) &&
                Objects.equal(licenseTypeId, license.licenseTypeId) &&
                Objects.equal(invoice, license.invoice) &&
                Objects.equal(campaign, license.campaign) &&
                Objects.equal(activationsUsed, license.activationsUsed) &&
                Objects.equal(expirationDate, license.expirationDate) &&
                Objects.equal(dateCreated, license.dateCreated) &&
                Objects.equal(products, license.products) &&
                Objects.equal(isSubscription, license.isSubscription) &&
                status == license.status &&
                Objects.equal(activationsTotal, license.activationsTotal) &&
                Objects.equal(activationKey, license.activationKey) &&
                Objects.equal(history, license.history);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, licenseTypeName, licenseTypeId, invoice, campaign, activationsUsed, expirationDate,
                dateCreated, products, isSubscription, status, activationsTotal, activationKey, history);
    }

    public enum Status {
        NOT_USED(1), ACTIVE(2), EXPIRED(3), BLOCKED(4), RETURNED(5);

        private final Integer apiIndex;

        Status(Integer apiIndex) {
            this.apiIndex = apiIndex;
        }

        @JsonValue
        public Integer getApiIndex() {
            return apiIndex;
        }

        @JsonCreator
        public static Status fromApiIndex(final Integer apiIndex) {
            return getFirst(filter(asList(Status.values()), new Predicate<Status>() {
                @Override
                public boolean apply(Status status) {
                    return status.getApiIndex().equals(apiIndex);
                }
            }), null);
        }
    }

    public static final class Builder {
        private Integer id;
        private String licenseTypeName;
        private Integer licenseTypeId;
        private String invoice;
        private String campaign;
        private Integer activationsUsed;
        private Date expirationDate;
        private Date dateCreated;
        private List<Product> products;
        private Boolean isSubscription;
        private Status status;
        private Integer activationsTotal;
        private String activationKey;
        private List<HistoryRecord> history;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder licenseTypeName(String val) {
            licenseTypeName = val;
            return this;
        }

        public Builder licenseTypeId(Integer val) {
            licenseTypeId = val;
            return this;
        }

        public Builder invoice(String val) {
            invoice = val;
            return this;
        }

        public Builder campaign(String val) {
            campaign = val;
            return this;
        }

        public Builder activationsUsed(Integer val) {
            activationsUsed = val;
            return this;
        }

        public Builder expirationDate(Date val) {
            expirationDate = val;
            return this;
        }

        public Builder dateCreated(Date val) {
            dateCreated = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Builder isSubscription(Boolean val) {
            isSubscription = val;
            return this;
        }

        public Builder status(Status val) {
            status = val;
            return this;
        }

        public Builder activationsTotal(Integer val) {
            activationsTotal = val;
            return this;
        }

        public Builder activationKey(String val) {
            activationKey = val;
            return this;
        }

        public Builder history(List<HistoryRecord> val) {
            history = val;
            return this;
        }

        public License build() {
            return new License(this);
        }
    }
}
