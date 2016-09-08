package com.devmate.pub.client.impl;

import com.devmate.pub.client.LicensesApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.WebTarget;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.lang.String.format;
import static javax.ws.rs.client.Entity.text;

public class DefaultLicensesApi extends AbstractApi implements LicensesApi {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultLicensesApi.class);
    private static final String SERVICE_PATH = "/v2/licenses";
    private static final String RESET_FIRST_ACTIVATION_PATH = "/reset_first_activation";

    public DefaultLicensesApi(WebTarget rootTarget) {
        super(rootTarget);
    }

    @Override
    protected String servicePath() {
        return SERVICE_PATH;
    }

    @Override
    public void resetFirstActivation(String activationKey) {
        LOG.debug("Reset first activation by key : ", activationKey);

        if (isNullOrEmpty(activationKey)) {
            throw new IllegalArgumentException("Given activation key is null or empty.");
        }

        target()
                .path(format("/%s", activationKey))
                .path(RESET_FIRST_ACTIVATION_PATH)
                .request()
                .post(text(""));
    }
}
