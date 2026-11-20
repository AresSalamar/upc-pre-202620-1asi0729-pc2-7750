package com.icecube.platform.u202417747.detection.domain.repositories;

import com.icecube.platform.u202417747.detection.domain.model.aggregates.NeutrinoAlert;
import com.icecube.platform.u202417747.detection.domain.model.valueobjects.AlertCode;

/** Domain port for alert uniqueness and persistence, independent of Spring Data JPA.
 * @author Kevin Salazar
 */
public interface NeutrinoAlertRepository {
    /** @return whether an alert already uses the code */
    boolean existsByAlertCode(AlertCode alertCode);

    /** @return the saved aggregate containing the generated identifier */
    NeutrinoAlert save(NeutrinoAlert alert);
}
