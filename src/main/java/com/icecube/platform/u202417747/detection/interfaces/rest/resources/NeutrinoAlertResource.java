package com.icecube.platform.u202417747.detection.interfaces.rest.resources;

import java.math.BigDecimal;

/**
 * Public success resource with flattened value-object attributes.
 *
 * @author Kevin Salazar
 */
public record NeutrinoAlertResource(Long id, String alertCode, String detectorId,
                                    String followUpStartDate, String followUpEndDate,
                                    BigDecimal minimumEnergy, BigDecimal maximumEnergy,
                                    BigDecimal confidenceScore, String status, String scientificNotes) { }
