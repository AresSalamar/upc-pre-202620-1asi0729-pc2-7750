package com.icecube.platform.u202417747.detection.domain.model.valueobjects;

import java.util.UUID;

/**
 * Identifier of a detector belonging to a different bounded context.
 * The detector's existence is deliberately not checked here.
 *
 * @param value detector UUID
 * @author Kevin Salazar
 */
public record DetectorId(UUID value) {
    public DetectorId {
        if (value == null) throw new IllegalArgumentException("Detector id is required");
    }
}
