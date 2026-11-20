package com.icecube.platform.u202417747.detection.domain.model.valueobjects;

import java.time.LocalDate;

/**
 * Inclusive scientific follow-up date window, with end strictly after start.
 *
 * @param startDate first valid day
 * @param endDate last valid day
 * @author Kevin Salazar
 */
public record FollowUpWindow(LocalDate startDate, LocalDate endDate) {
    public FollowUpWindow {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Follow-up dates must not be null");
        }
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("Follow-up end date must be after start date");
        }
    }

    /** @return true if the date lies in this window including both endpoints */
    public boolean isWithinWindow(LocalDate checkDate) {
        if (checkDate == null) throw new IllegalArgumentException("Check date must not be null");
        return !checkDate.isBefore(startDate) && !checkDate.isAfter(endDate);
    }
}
