package com.devmate.pub.client.impl;

import com.devmate.pub.client.CustomersApi;
import com.devmate.pub.client.DevMateClient;
import com.devmate.pub.client.LicensesApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class DefaultDevMateClient implements DevMateClient {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultLicensesApi.class);
    private final Client client;
    private final CustomersApi customersApi;
    private final LicensesApi licensesApi;

    public DefaultDevMateClient(String url, String token) {
        LOG.debug("Init default DevMate client with token : {}, target URL : {}", token, url);
        client = getClientWithToken(token);

        final WebTarget rootTarget = getRootTarget(client, url);
        customersApi = new DefaultCustomersApi(rootTarget);
        licensesApi = new DefaultLicensesApi(rootTarget);
    }

    protected Client getClientWithToken(String token) {
        return ClientBuilder.newBuilder()
                .register(ObjectMapperProvider.class)
                .register(DevMateErrorResponseFilter.class)
                .register(new DevMateAuthClientFilter(token))
                .build();
    }

    protected WebTarget getRootTarget(Client client, String url) {
        return client.target(url);
    }

    @Override
    public CustomersApi customers() {
        return customersApi;
    }

    @Override
    public LicensesApi licenses() {
        return licensesApi;
    }

    @Override
    public void close() {
        client.close();
    }
}
