package com.icecube.platform.u202417747.detection.interfaces.rest.transform;

import com.icecube.platform.u202417747.detection.domain.model.aggregates.NeutrinoAlert;
import com.icecube.platform.u202417747.shared.application.result.Result;
import com.icecube.platform.u202417747.shared.interfaces.rest.ErrorResource;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** Translates the application Result into a localized REST response.
 * @author Kevin Salazar
 */
public final class ResponseEntityFromNeutrinoAlertResultAssembler {
    private ResponseEntityFromNeutrinoAlertResultAssembler() { }

    /** @return HTTP 201 with a resource or a localized 400/409/500 error */
    public static ResponseEntity<?> toResponse(Result<NeutrinoAlert> result,
                                               MessageSource messages, Locale locale) {
        if (result instanceof Result.Success<?> success) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(NeutrinoAlertResourceFromEntityAssembler.toResource(
                            (NeutrinoAlert) success.value()));
        }
        Result.Failure<?> failure = (Result.Failure<?>) result;
        HttpStatus status = switch (failure.type()) {
            case INVALID -> HttpStatus.BAD_REQUEST;
            case CONFLICT -> HttpStatus.CONFLICT;
            case INTERNAL -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
        return ResponseEntity.status(status)
                .body(new ErrorResource(messages.getMessage(failure.messageKey(), null, locale)));
    }
}
