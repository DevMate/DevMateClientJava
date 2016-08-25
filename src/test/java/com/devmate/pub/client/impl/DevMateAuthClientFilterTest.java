package com.devmate.pub.client.impl;

import org.junit.Test;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import java.io.IOException;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DevMateAuthClientFilterTest {
    @Test
    public void filterWithValidToken() throws Exception {
        final String token = "TEST";
        final MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();

        filterWithGivenHeaders(headers, token);

        assertThat(headers, hasKey("Authorization"));
        assertThat(headers.getFirst("Authorization"), is((Object) ("Token " + token)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void initFilterWithNullToken() throws Exception {
        filterWithGivenHeaders(new MultivaluedHashMap<String, Object>(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void initFilterWithBlankToken() throws Exception {
        filterWithGivenHeaders(new MultivaluedHashMap<String, Object>(), "");
    }

    private void filterWithGivenHeaders(MultivaluedMap<String, Object> headers, String token) throws IOException {
        ClientRequestContext mockedRequestContext = mock(ClientRequestContext.class);
        when(mockedRequestContext.getHeaders()).thenReturn(headers);

        ClientRequestFilter filter = new DevMateAuthClientFilter(token);
        filter.filter(mockedRequestContext);
    }
}