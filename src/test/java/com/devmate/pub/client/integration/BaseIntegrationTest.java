package com.devmate.pub.client.integration;

import com.devmate.pub.client.DevMateClient;
import com.devmate.pub.client.impl.DefaultDevMateClient;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;

public abstract class BaseIntegrationTest {
    protected static final int PORT = 8089;
    protected static final String TEST_URL = "http://127.0.0.1:" + PORT;

    protected static final String CONTENT_TYPE = "Content-Type";
    protected static final String APPLICATION_JSON = "application/json";
    protected static final String AUTHORIZATION = "Authorization";
    protected static final String TOKEN_VALUE = "TEST";
    protected static final String TOKEN = "Token " + TOKEN_VALUE;

    private DevMateClient testDevMateClient;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Before
    public void setUp() {
        testDevMateClient = new DefaultDevMateClient(TEST_URL, TOKEN_VALUE);
    }

    protected DevMateClient testDevMateClient() {
        return testDevMateClient;
    }
}
