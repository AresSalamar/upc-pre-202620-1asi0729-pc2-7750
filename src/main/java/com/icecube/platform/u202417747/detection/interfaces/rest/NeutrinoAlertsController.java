package com.icecube.platform.u202417747.detection.interfaces.rest;

import com.icecube.platform.u202417747.detection.application.commandservices.NeutrinoAlertCommandService;
import com.icecube.platform.u202417747.detection.interfaces.rest.resources.CreateNeutrinoAlertResource;
import com.icecube.platform.u202417747.detection.interfaces.rest.transform.CreateNeutrinoAlertCommandFromResourceAssembler;
import com.icecube.platform.u202417747.detection.interfaces.rest.transform.ResponseEntityFromNeutrinoAlertResultAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Creation-only REST endpoint for neutrino detection alerts.
 *
 * @author Kevin Salazar
 */
@RestController
@RequestMapping("/api/v1/neutrino-alerts")
@Tag(name = "Neutrino Alerts", description = "Scientific neutrino alert operations")
public class NeutrinoAlertsController {
    private final NeutrinoAlertCommandService service;
    private final MessageSource messages;

    public NeutrinoAlertsController(NeutrinoAlertCommandService service, MessageSource messages) {
        this.service = service;
        this.messages = messages;
    }

    @PostMapping
    @Operation(summary = "Create a neutrino alert", description = "Registers a validated scientific alert")
    public ResponseEntity<?> create(@Valid @RequestBody CreateNeutrinoAlertResource resource,
                                    Locale locale) {
        var command = CreateNeutrinoAlertCommandFromResourceAssembler.toCommand(resource);
        var result = service.handle(command);
        return ResponseEntityFromNeutrinoAlertResultAssembler.toResponse(result, messages, locale);
    }
}
