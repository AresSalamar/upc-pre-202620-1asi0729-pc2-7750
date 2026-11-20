package com.icecube.platform.u202417747.detection.application.commandservices;

import com.icecube.platform.u202417747.detection.domain.model.aggregates.NeutrinoAlert;
import com.icecube.platform.u202417747.detection.domain.model.commands.CreateNeutrinoAlertCommand;
import com.icecube.platform.u202417747.shared.application.result.Result;

/** Command side of the create-only CQRS application service.
 * @author Kevin Salazar
 */
public interface NeutrinoAlertCommandService {
    Result<NeutrinoAlert> handle(CreateNeutrinoAlertCommand command);
}
