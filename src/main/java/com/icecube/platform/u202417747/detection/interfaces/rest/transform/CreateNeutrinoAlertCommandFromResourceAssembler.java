package com.icecube.platform.u202417747.detection.interfaces.rest.transform;

import com.icecube.platform.u202417747.detection.domain.model.commands.CreateNeutrinoAlertCommand;
import com.icecube.platform.u202417747.detection.domain.model.valueobjects.*;
import com.icecube.platform.u202417747.detection.interfaces.rest.resources.CreateNeutrinoAlertResource;
import java.util.UUID;

/** Builds strongly typed domain command objects from incoming REST primitives.
 * @author Kevin Salazar
 */
public final class CreateNeutrinoAlertCommandFromResourceAssembler {
    private CreateNeutrinoAlertCommandFromResourceAssembler() { }

    /** @return a validated command or throws IllegalArgumentException */
    public static CreateNeutrinoAlertCommand toCommand(CreateNeutrinoAlertResource resource) {
        AlertStatus status = resource.status() == null
                ? AlertStatus.CANDIDATE : AlertStatus.valueOf(resource.status());
        return new CreateNeutrinoAlertCommand(
                new AlertCode(resource.alertCode()),
                new DetectorId(UUID.fromString(resource.detectorId())),
                new FollowUpWindow(resource.followUpStartDate(), resource.followUpEndDate()),
                new EnergyRange(resource.minimumEnergy(), resource.maximumEnergy()),
                resource.confidenceScore(), status, resource.scientificNotes());
    }
}
