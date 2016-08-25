package com.devmate.pub.client.exceptions;

import com.devmate.pub.client.models.Error;

import javax.ws.rs.core.Response;
import java.util.List;

public class DevMateClientErrorException extends DevMateRequestException {
    public DevMateClientErrorException(Response.Status status, List<Error> errors) {
        super(status, errors, false);
    }
}
