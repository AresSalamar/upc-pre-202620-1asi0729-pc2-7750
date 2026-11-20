package com.icecube.platform.u202417747.detection.application.internal.commandservices;

import com.icecube.platform.u202417747.detection.application.commandservices.NeutrinoAlertCommandService;
import com.icecube.platform.u202417747.detection.domain.model.aggregates.NeutrinoAlert;
import com.icecube.platform.u202417747.detection.domain.model.commands.CreateNeutrinoAlertCommand;
import com.icecube.platform.u202417747.detection.domain.repositories.NeutrinoAlertRepository;
import com.icecube.platform.u202417747.shared.application.result.Result;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/** Coordinates creation while retaining invariant validation in the domain model.
 * @author Kevin Salazar
 */
@Service
public class NeutrinoAlertCommandServiceImpl implements NeutrinoAlertCommandService {
    private final NeutrinoAlertRepository repository;

    public NeutrinoAlertCommandServiceImpl(NeutrinoAlertRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<NeutrinoAlert> handle(CreateNeutrinoAlertCommand command) {
        if (repository.existsByAlertCode(command.alertCode())) {
            return Result.failure(Result.ErrorType.CONFLICT, "error.duplicateAlertCode");
        }
        try {
            NeutrinoAlert alert = NeutrinoAlert.create(command.alertCode(), command.detectorId(),
                    command.followUpWindow(), command.energyRange(), command.confidenceScore(),
                    command.status(), command.scientificNotes());
            return Result.success(repository.save(alert));
        } catch (IllegalArgumentException ex) {
            return Result.failure(Result.ErrorType.INVALID, "error.invalidRequest");
        } catch (DataIntegrityViolationException ex) {
            // Handles a concurrent insertion of the same unique alert code.
            if (repository.existsByAlertCode(command.alertCode())) {
                return Result.failure(Result.ErrorType.CONFLICT, "error.duplicateAlertCode");
            }
            return Result.failure(Result.ErrorType.INTERNAL, "error.internal");
        }
    }
}
