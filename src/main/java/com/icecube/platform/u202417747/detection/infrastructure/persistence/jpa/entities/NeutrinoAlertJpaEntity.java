package com.icecube.platform.u202417747.detection.infrastructure.persistence.jpa.entities;

import com.icecube.platform.u202417747.detection.domain.model.aggregates.NeutrinoAlert;
import com.icecube.platform.u202417747.detection.domain.model.valueobjects.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Flattened JPA persistence representation of NeutrinoAlert.
 * Its table has a unique constraint on alert_code.
 *
 * @author Kevin Salazar
 */
@Entity
@Table(name = "neutrino_alerts", uniqueConstraints =
        @UniqueConstraint(name = "uk_neutrino_alerts_alert_code", columnNames = "alert_code"))
public class NeutrinoAlertJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alert_code", nullable = false, unique = true, length = 160)
    private String alertCode;

    @Column(name = "detector_id", nullable = false, length = 36)
    private String detectorId;

    @Column(name = "follow_up_start_date", nullable = false)
    private LocalDate followUpStartDate;

    @Column(name = "follow_up_end_date", nullable = false)
    private LocalDate followUpEndDate;

    @Column(name = "minimum_energy", nullable = false, precision = 38, scale = 18)
    private BigDecimal minimumEnergy;

    @Column(name = "maximum_energy", nullable = false, precision = 38, scale = 18)
    private BigDecimal maximumEnergy;

    @Column(name = "confidence_score", nullable = false, precision = 38, scale = 18)
    private BigDecimal confidenceScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 32)
    private AlertStatus status;

    @Column(name = "scientific_notes", length = 4000)
    private String scientificNotes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected NeutrinoAlertJpaEntity() { }

    /** Converts a domain object into a flat JPA entity for insertion. */
    public static NeutrinoAlertJpaEntity fromDomain(NeutrinoAlert alert) {
        NeutrinoAlertJpaEntity entity = new NeutrinoAlertJpaEntity();
        entity.id = alert.id();
        entity.alertCode = alert.alertCode().code();
        entity.detectorId = alert.detectorId().value().toString();
        entity.followUpStartDate = alert.followUpWindow().startDate();
        entity.followUpEndDate = alert.followUpWindow().endDate();
        entity.minimumEnergy = alert.energyRange().minimumEnergy();
        entity.maximumEnergy = alert.energyRange().maximumEnergy();
        entity.confidenceScore = alert.confidenceScore();
        entity.status = alert.status();
        entity.scientificNotes = alert.scientificNotes();
        entity.createdAt = alert.createdAt();
        entity.updatedAt = alert.updatedAt();
        return entity;
    }

    /** Reconstitutes the aggregate after writing to or reading from MySQL. */
    public NeutrinoAlert toDomain() {
        return NeutrinoAlert.rehydrate(id, new AlertCode(alertCode), new DetectorId(UUID.fromString(detectorId)),
                new FollowUpWindow(followUpStartDate, followUpEndDate),
                new EnergyRange(minimumEnergy, maximumEnergy), confidenceScore, status,
                scientificNotes, createdAt, updatedAt);
    }
}
