package com.icecube.platform.u202417747.detection.interfaces.rest.transform;

import com.icecube.platform.u202417747.detection.domain.model.aggregates.NeutrinoAlert;
import com.icecube.platform.u202417747.detection.interfaces.rest.resources.NeutrinoAlertResource;

/** Converts a created domain aggregate to its flattened API resource.
 * @author Kevin Salazar
 */
public final class NeutrinoAlertResourceFromEntityAssembler {
    private NeutrinoAlertResourceFromEntityAssembler() { }

    public static NeutrinoAlertResource toResource(NeutrinoAlert alert) {
        return new NeutrinoAlertResource(alert.id(), alert.alertCode().code(),
                alert.detectorId().value().toString(),
                alert.followUpWindow().startDate().toString(),
                alert.followUpWindow().endDate().toString(),
                alert.energyRange().minimumEnergy(), alert.energyRange().maximumEnergy(),
                alert.confidenceScore(), alert.status().name(), alert.scientificNotes());
    }
}
