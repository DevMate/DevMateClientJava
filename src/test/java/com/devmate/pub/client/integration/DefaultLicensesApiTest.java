package com.devmate.pub.client.integration;

import com.github.tomakehurst.wiremock.matching.ContainsPattern;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static java.lang.String.format;

public class DefaultLicensesApiTest extends BaseIntegrationTest {
    @Test
    public void resetFirstActivation() throws Exception {
        String key = "id123456789009odr";
        String url = format("/v2/licenses/%s/reset_first_activation", key);

        stubFor(post(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("")));

        testDevMateClient().licenses().resetFirstActivation(key);

        verify(postRequestedFor(urlEqualTo(url))
                .withHeader(AUTHORIZATION, new ContainsPattern(TOKEN)));
    }
}