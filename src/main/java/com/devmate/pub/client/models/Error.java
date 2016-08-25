package com.devmate.pub.client.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Error {
    @JsonProperty("title")
    private String title;
    @JsonProperty("detail")
    private String detail;

    public Error() {}

    public Error(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Error{");
        sb.append("title='").append(title).append('\'');
        sb.append(", detail='").append(detail).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error error = (Error) o;
        return Objects.equal(title, error.title) &&
                Objects.equal(detail, error.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title, detail);
    }
}
