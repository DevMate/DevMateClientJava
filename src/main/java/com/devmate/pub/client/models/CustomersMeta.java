package com.devmate.pub.client.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class CustomersMeta {
    @JsonProperty("total")
    private Integer total;

    protected CustomersMeta() {}

    public CustomersMeta(Integer total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomersMeta{");
        sb.append("total=").append(total);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomersMeta that = (CustomersMeta) o;
        return Objects.equal(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(total);
    }
}
