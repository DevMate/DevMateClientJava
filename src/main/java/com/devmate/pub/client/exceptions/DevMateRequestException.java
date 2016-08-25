package com.devmate.pub.client.exceptions;

import com.devmate.pub.client.models.Error;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.devmate.pub.client.exceptions.ExceptionMessageBuilder.buildMessage;

public class DevMateRequestException extends ProcessingException {
    public DevMateRequestException(Response.Status status, List<Error> errors, boolean contactSupport) {
        super(buildMessage(status, errors, contactSupport));
    }
}
