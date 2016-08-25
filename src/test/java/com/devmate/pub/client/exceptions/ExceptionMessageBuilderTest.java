package com.devmate.pub.client.exceptions;

import com.devmate.pub.client.models.Error;
import org.hamcrest.Matcher;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ExceptionMessageBuilderTest {
    @Test
    public void buildOnlyWithStatus() {
        assertThatErrorMessage(Response.Status.NOT_FOUND, null, false,
                is("Request to DevMate API has been failed. Status code : 404. Reason : Not Found."));
    }

    @Test
    public void buildWithContactSupport() {
        assertThatErrorMessage(Response.Status.NOT_FOUND, null, true,
                is("Request to DevMate API has been failed. Status code : 404. Reason : Not Found.\n" +
                        "Please contact support -> support@devmate.com"));
    }

    @Test
    public void buildWithErrorsSize0() {
        assertThatErrorMessage(Response.Status.NOT_FOUND, new ArrayList<Error>(), false,
                is("Request to DevMate API has been failed. Status code : 404. Reason : Not Found."));
    }

    @Test
    public void buildWithErrors() {
        assertThatErrorMessage(
                Response.Status.NOT_FOUND,
                asList(new Error("title", "details"), new Error("title", null)),
                false,
                is("Request to DevMate API has been failed. Status code : 404. Reason : Not Found.\n" +
                        "Errors :\n*\ttitle\n\tdetails\n*\ttitle")
        );
    }

    @Test
    public void buildWithErrorsAndContactSupport() {
        assertThatErrorMessage(
                Response.Status.NOT_FOUND,
                asList(new Error("title", "details"), new Error("title", null)),
                true,
                is("Request to DevMate API has been failed. Status code : 404. Reason : Not Found.\n" +
                        "Errors :\n*\ttitle\n\tdetails\n*\ttitle\nPlease contact support -> support@devmate.com"));
    }

    private static void assertThatErrorMessage(Response.Status status,
                                               List<Error> errors,
                                               boolean contactSupport,
                                               Matcher<? super String> matcher) {
        String message = ExceptionMessageBuilder.buildMessage(status, errors, contactSupport);
        assertThat(message, matcher);
    }
}