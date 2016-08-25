package com.devmate.pub.client.impl;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.String.format;

@Provider
public final class DevMateAuthClientFilter implements ClientRequestFilter {
    private final String token;

    public DevMateAuthClientFilter(String token) {
        if (isNullOrEmpty(token)) {
            throw new IllegalArgumentException("Given token is null or empty!");
        }
        this.token = token;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        requestContext.getHeaders().add("Authorization", format("Token %s", token));
    }
}
