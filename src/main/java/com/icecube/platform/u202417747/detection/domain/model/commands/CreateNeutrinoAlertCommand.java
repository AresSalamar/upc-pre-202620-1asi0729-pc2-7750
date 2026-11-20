package com.icecube.platform.u202417747.detection.domain.model.commands;

import com.icecube.platform.u202417747.detection.domain.model.valueobjects.*;
import java.math.BigDecimal;

/**
 * Command-side intent to register a newly detected NeutrinoAlert.
 *
 * @author Kevin Salazar
 */
public record CreateNeutrinoAlertCommand(AlertCode alertCode, DetectorId detectorId,
                                         FollowUpWindow followUpWindow, EnergyRange energyRange,
                                         BigDecimal confidenceScore, AlertStatus status, String scientificNotes) { }
