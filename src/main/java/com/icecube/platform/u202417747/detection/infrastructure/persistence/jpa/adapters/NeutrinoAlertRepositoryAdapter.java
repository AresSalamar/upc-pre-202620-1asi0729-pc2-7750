package com.icecube.platform.u202417747.detection.infrastructure.persistence.jpa.adapters;

import com.icecube.platform.u202417747.detection.domain.model.aggregates.NeutrinoAlert;
import com.icecube.platform.u202417747.detection.domain.model.valueobjects.AlertCode;
import com.icecube.platform.u202417747.detection.domain.repositories.NeutrinoAlertRepository;
import com.icecube.platform.u202417747.detection.infrastructure.persistence.jpa.entities.NeutrinoAlertJpaEntity;
import com.icecube.platform.u202417747.detection.infrastructure.persistence.jpa.repositories.NeutrinoAlertJpaRepository;
import org.springframework.stereotype.Repository;

/** Adapts the domain persistence port to Spring Data JPA and MySQL.
 * @author Kevin Salazar
 */
@Repository
public class NeutrinoAlertRepositoryAdapter implements NeutrinoAlertRepository {
    private final NeutrinoAlertJpaRepository jpa;

    public NeutrinoAlertRepositoryAdapter(NeutrinoAlertJpaRepository jpa) { this.jpa = jpa; }

    @Override
    public boolean existsByAlertCode(AlertCode code) { return jpa.existsByAlertCode(code.code()); }

    @Override
    public NeutrinoAlert save(NeutrinoAlert alert) {
        return jpa.saveAndFlush(NeutrinoAlertJpaEntity.fromDomain(alert)).toDomain();
    }
}
