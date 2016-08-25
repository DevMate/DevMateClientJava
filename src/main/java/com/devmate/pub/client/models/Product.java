package com.devmate.pub.client.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Product {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("bundle_id")
    private String bundleId;
    @JsonProperty("use_offline_license")
    private Boolean useOfflineLicense;

    protected Product() {}

    private Product(Builder builder) {
        id = builder.id;
        name = builder.name;
        bundleId = builder.bundleId;
        useOfflineLicense = builder.useOfflineLicense;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Product copy) {
        Builder builder = new Builder();
        builder.id = copy.id;
        builder.name = copy.name;
        builder.bundleId = copy.bundleId;
        builder.useOfflineLicense = copy.useOfflineLicense;
        return builder;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBundleId() {
        return bundleId;
    }

    public Boolean getUseOfflineLicense() {
        return useOfflineLicense;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", bundleId='").append(bundleId).append('\'');
        sb.append(", useOfflineLicense=").append(useOfflineLicense);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equal(id, product.id) &&
                Objects.equal(name, product.name) &&
                Objects.equal(bundleId, product.bundleId) &&
                Objects.equal(useOfflineLicense, product.useOfflineLicense);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, bundleId, useOfflineLicense);
    }

    public static final class Builder {
        private Integer id;
        private String name;
        private String bundleId;
        private Boolean useOfflineLicense;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder bundleId(String val) {
            bundleId = val;
            return this;
        }

        public Builder useOfflineLicense(Boolean val) {
            useOfflineLicense = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
