package com.icecube.platform.u202417747.detection.infrastructure.persistence.jpa.repositories;

import com.icecube.platform.u202417747.detection.infrastructure.persistence.jpa.entities.NeutrinoAlertJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/** Spring Data repository for the persistence entity.
 * @author Kevin Salazar
 */
public interface NeutrinoAlertJpaRepository extends JpaRepository<NeutrinoAlertJpaEntity, Long> {
    boolean existsByAlertCode(String alertCode);
}
