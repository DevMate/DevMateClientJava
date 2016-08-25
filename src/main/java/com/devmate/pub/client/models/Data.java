package com.devmate.pub.client.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.Collections;
import java.util.List;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class Data<OBJ, META> {
    @JsonProperty("data")
    private OBJ data;
    @JsonProperty("errors")
    private List<Error> errors;
    @JsonProperty("meta")
    private META meta;

    public static <OBJ, META> Data<OBJ, META> of(OBJ data) {
        return new Data<>(data, null, null);
    }

    public static <OBJ, META> Data<OBJ, META> of(OBJ data, META meta) {
        return new Data<>(data, null, meta);
    }

    public static <OBJ, META> Data<OBJ, META> errors(List<Error> errors) {
        return new Data<>(null, errors, null);
    }

    protected Data() {}

    protected Data(OBJ data, List<Error> errors, META meta) {
        this.data = data;
        this.errors = errors;
        this.meta = meta;
    }

    /**
     *
     * @return
     */
    public OBJ getData() {
        return data;
    }

    /**
     *
     * @return
     */
    public META getMeta() {
        return meta;
    }

    /**
     *
     * @return
     */
    public List<Error> getErrors() {
        if (errors != null) {
            return errors;
        }
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Data{");
        sb.append("data=").append(data);
        sb.append(", errors=").append(errors);
        sb.append(", meta=").append(meta);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data<?, ?> data1 = (Data<?, ?>) o;
        return Objects.equal(data, data1.data) &&
                Objects.equal(errors, data1.errors) &&
                Objects.equal(meta, data1.meta);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data, errors, meta);
    }
}
