package com.devmate.pub.client.impl;

import com.devmate.pub.client.exceptions.DevMateClientErrorException;
import com.devmate.pub.client.exceptions.DevMateConflictException;
import com.devmate.pub.client.exceptions.DevMateIncorrectParamsException;
import com.devmate.pub.client.exceptions.DevMateNotFoundException;
import com.devmate.pub.client.exceptions.DevMateRequestException;
import com.devmate.pub.client.exceptions.DevMateServerErrorException;
import com.devmate.pub.client.models.Data;
import com.devmate.pub.client.models.Error;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;
import static javax.ws.rs.core.Response.Status.Family.SUCCESSFUL;
import static javax.ws.rs.core.Response.Status.fromStatusCode;

@Provider
public final class DevMateErrorResponseFilter implements ClientResponseFilter {
    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperProvider.objectMapper();

    @Override
    public void filter(ClientRequestContext requestContext,
                       ClientResponseContext responseContext) throws IOException {
        final Response.Status status = fromStatusCode(responseContext.getStatus());
        if (!SUCCESSFUL.equals(status.getFamily())) {
            throw getDevMateException(status, getErrorsFromResponse(responseContext));
        }
    }

    private List<Error> getErrorsFromResponse(ClientResponseContext responseContext) throws IOException {
        if (responseContext.hasEntity()) {
            Data<?, ?> data = OBJECT_MAPPER.readValue(responseContext.getEntityStream(), Data.class);
            List<Error> errors = data.getErrors();
            if (errors != null) {
                return errors;
            }
        }

        return emptyList();
    }

    private DevMateRequestException getDevMateException(Response.Status status, List<Error> errors) {
        switch (status.getFamily()) {
            case CLIENT_ERROR:
                return setClientErrorException(status, errors);
            case SERVER_ERROR:
                return new DevMateServerErrorException(status, errors);
            default:
                return new DevMateRequestException(status, errors, true);
        }
    }

    private DevMateClientErrorException setClientErrorException(Response.Status status, List<Error> errors) {
        switch (status) {
            case BAD_REQUEST:
                return new DevMateIncorrectParamsException(status, errors);
            case NOT_FOUND:
                return new DevMateNotFoundException(status, errors);
            case CONFLICT:
                return new DevMateConflictException(status, errors);
            default:
                return new DevMateClientErrorException(status, errors);
        }
    }
}
