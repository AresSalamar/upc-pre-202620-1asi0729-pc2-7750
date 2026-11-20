package com.icecube.platform.u202417747.detection.domain.model.aggregates;

import com.icecube.platform.u202417747.detection.domain.model.valueobjects.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Auditable domain aggregate root for one detected scientific alert.
 * The aggregate is independent of any JPA persistence annotations.
 *
 * @author Kevin Salazar
 */
public final class NeutrinoAlert {
    private final Long id;
    private final AlertCode alertCode;
    private final DetectorId detectorId;
    private final FollowUpWindow followUpWindow;
    private final EnergyRange energyRange;
    private final BigDecimal confidenceScore;
    private final AlertStatus status;
    private final String scientificNotes;
    private final Instant createdAt;
    private final Instant updatedAt;

    private NeutrinoAlert(Long id, AlertCode alertCode, DetectorId detectorId,
                          FollowUpWindow followUpWindow, EnergyRange energyRange,
                          BigDecimal confidenceScore, AlertStatus status, String scientificNotes,
                          Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.alertCode = Objects.requireNonNull(alertCode, "Alert code is required");
        this.detectorId = Objects.requireNonNull(detectorId, "Detector id is required");
        this.followUpWindow = Objects.requireNonNull(followUpWindow, "Follow-up window is required");
        this.energyRange = Objects.requireNonNull(energyRange, "Energy range is required");
        if (confidenceScore == null || confidenceScore.compareTo(BigDecimal.ZERO) < 0
                || confidenceScore.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("Confidence score must be between 0 and 1 inclusive");
        }
        this.confidenceScore = confidenceScore;
        this.status = status == null ? AlertStatus.CANDIDATE : status;
        this.scientificNotes = scientificNotes;
        this.createdAt = Objects.requireNonNull(createdAt, "Creation instant is required");
        this.updatedAt = Objects.requireNonNull(updatedAt, "Update instant is required");
    }

    /** Creates a new alert with synchronized initial audit instants. */
    public static NeutrinoAlert create(AlertCode code, DetectorId detectorId, FollowUpWindow window,
                                       EnergyRange range, BigDecimal score, AlertStatus status, String notes) {
        Instant now = Instant.now();
        return new NeutrinoAlert(null, code, detectorId, window, range, score, status, notes, now, now);
    }

    /** Reconstitutes persisted state without generating new audit values. */
    public static NeutrinoAlert rehydrate(Long id, AlertCode code, DetectorId detectorId,
                                          FollowUpWindow window, EnergyRange range, BigDecimal score,
                                          AlertStatus status, String notes, Instant createdAt, Instant updatedAt) {
        if (id == null) throw new IllegalArgumentException("Persisted alert id must not be null");
        return new NeutrinoAlert(id, code, detectorId, window, range, score,
                                 status, notes, createdAt, updatedAt);
    }

    public Long id() { return id; }
    public AlertCode alertCode() { return alertCode; }
    public DetectorId detectorId() { return detectorId; }
    public FollowUpWindow followUpWindow() { return followUpWindow; }
    public EnergyRange energyRange() { return energyRange; }
    public BigDecimal confidenceScore() { return confidenceScore; }
    public AlertStatus status() { return status; }
    public String scientificNotes() { return scientificNotes; }
    public Instant createdAt() { return createdAt; }
    public Instant updatedAt() { return updatedAt; }
}
