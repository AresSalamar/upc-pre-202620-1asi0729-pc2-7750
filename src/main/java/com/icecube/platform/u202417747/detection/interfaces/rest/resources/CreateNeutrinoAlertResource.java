package com.icecube.platform.u202417747.detection.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Request body containing flattened primitives rather than embedded value objects.
 * Date strings in JSON use ISO-8601 yyyy-MM-dd.
 *
 * @author Kevin Salazar
 */
public record CreateNeutrinoAlertResource(
        @NotBlank String alertCode,
        @NotBlank String detectorId,
        @NotNull LocalDate followUpStartDate,
        @NotNull LocalDate followUpEndDate,
        @NotNull BigDecimal minimumEnergy,
        @NotNull BigDecimal maximumEnergy,
        @NotNull BigDecimal confidenceScore,
        String status,
        String scientificNotes) { }
