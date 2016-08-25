package com.devmate.pub.client.exceptions;

import com.devmate.pub.client.models.Error;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by paulakimenko on 17.08.16.
 */
public class DevMateNotFoundException extends DevMateClientErrorException {
    public DevMateNotFoundException(Response.Status status, List<Error> errors) {
        super(status, errors);
    }
}
