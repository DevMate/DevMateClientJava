package com.devmate.pub.client.exceptions;

import com.devmate.pub.client.models.Error;

import javax.ws.rs.core.Response;
import java.util.List;

public class DevMateServerErrorException extends DevMateRequestException {
    public DevMateServerErrorException(Response.Status status, List<Error> errors) {
        super(status, errors, true);
    }
}
