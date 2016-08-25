package com.devmate.pub.client.exceptions;

import com.devmate.pub.client.models.Error;

import javax.ws.rs.core.Response;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;

final class ExceptionMessageBuilder {
    private static final String NS = "\n";

    private ExceptionMessageBuilder() {}

    public static String buildMessage(Response.Status status, List<Error> errors, boolean contactSupport) {
        StringBuilder stringBuilder = new StringBuilder()
                .append("Request to DevMate API has been failed. Status code : ").append(status.getStatusCode())
                .append(". Reason : ").append(status.getReasonPhrase()).append(".");

        if (errors != null && errors.size() != 0) {
            stringBuilder.append(NS).append("Errors :");
            for (Error error : errors) {
                stringBuilder.append(NS).append("*\t").append(error.getTitle());
                if (!isNullOrEmpty(error.getDetail())) {
                    stringBuilder.append(NS).append("\t").append(error.getDetail());
                }
            }
        }

        if (contactSupport) {
            stringBuilder.append(NS).append("Please contact support -> support@devmate.com");
        }

        return stringBuilder.toString();
    }
}
