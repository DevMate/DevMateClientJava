package com.devmate.pub.client.impl;

import com.devmate.pub.client.TestUtils;
import com.devmate.pub.client.exceptions.DevMateClientErrorException;
import com.devmate.pub.client.exceptions.DevMateConflictException;
import com.devmate.pub.client.exceptions.DevMateIncorrectParamsException;
import com.devmate.pub.client.exceptions.DevMateNotFoundException;
import com.devmate.pub.client.exceptions.DevMateRequestException;
import com.devmate.pub.client.exceptions.DevMateServerErrorException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static com.devmate.pub.client.TestUtils.assertDoesNotThrow;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DevMateErrorResponseFilterTest {
    private ClientResponseFilter filter;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        filter = new DevMateErrorResponseFilter();
    }

    @Test
    public void filterBadRequest() throws Exception {
        assertFilterWith(Response.Status.BAD_REQUEST, DevMateIncorrectParamsException.class, false);
    }

    @Test
    public void filterNotFound() throws Exception {
        assertFilterWith(Response.Status.NOT_FOUND, DevMateNotFoundException.class, false);
    }

    @Test
    public void filterConflict() throws Exception {
        assertFilterWith(Response.Status.CONFLICT, DevMateConflictException.class, false);
    }

    @Test
    public void filterClientError() throws Exception {
        assertFilterWith(Response.Status.METHOD_NOT_ALLOWED, DevMateClientErrorException.class, false);
    }

    @Test
    public void filterServerError() throws Exception {
        assertFilterWith(Response.Status.INTERNAL_SERVER_ERROR, DevMateServerErrorException.class, true);
    }

    @Test
    public void filterOtherError() throws Exception {
        assertFilterWith(Response.Status.MOVED_PERMANENTLY, DevMateRequestException.class, true);
    }

    @Test
    public void filterSuccess() throws Exception {
        final ClientRequestContext mockedRequestContext = mock(ClientRequestContext.class);
        final ClientResponseContext mockedResponseContext = mock(ClientResponseContext.class);
        when(mockedResponseContext.getStatus()).thenReturn(200);
        when(mockedRequestContext.hasEntity()).thenReturn(false);

        assertDoesNotThrow(new TestUtils.FailingRunnable() {
            @Override
            public void run() throws Exception {
                filter.filter(mockedRequestContext, mockedResponseContext);
            }
        });
    }

    private void assertFilterWith(Response.Status status,
                                  Class<? extends Throwable> expectedExceptionClass,
                                  boolean contactSupportInMessage) throws IOException {
        String expectedMessage = format(
                "Request to DevMate API has been failed. Status code : %d. Reason : %s.",
                status.getStatusCode(), status.getReasonPhrase()
        );

        if (contactSupportInMessage) {
            expectedMessage = expectedMessage + "\nPlease contact support -> support@devmate.com";
        }

        expectedException.expect(expectedExceptionClass);
        expectedException.expectMessage(is(expectedMessage));

        ClientRequestContext mockedRequestContext = mock(ClientRequestContext.class);
        ClientResponseContext mockedResponseContext = mock(ClientResponseContext.class);
        when(mockedResponseContext.getStatus()).thenReturn(status.getStatusCode());

        filter.filter(mockedRequestContext, mockedResponseContext);
    }
}