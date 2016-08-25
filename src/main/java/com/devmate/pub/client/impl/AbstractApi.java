package com.devmate.pub.client.impl;

import javax.ws.rs.client.WebTarget;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

public abstract class AbstractApi {
    private final WebTarget target;

    public AbstractApi(WebTarget rootTarget) {
        this.target = rootTarget.path(servicePath());
    }

    protected abstract String servicePath();

    protected WebTarget target() {
        return target;
    }

    protected String idToPath(Integer id) {
        return format("/%d", checkNotNull(id, "Given id is null"));
    }

    protected WebTarget setQueryParamIfIsNotNull(WebTarget target, String name, Object value) {
        target = checkNotNull(target, "Given target is null");
        return value == null ? target : target.queryParam(name, value);
    }
}
