package com.icecube.platform.u202417747.detection.domain.model.valueobjects;

/**
 * Required nonblank unique scientific alert code.
 *
 * @param code alert code text
 * @author Kevin Salazar
 */
public record AlertCode(String code) {
    public AlertCode {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Alert code must not be null or blank");
        }
        code = code.trim();
    }
}
