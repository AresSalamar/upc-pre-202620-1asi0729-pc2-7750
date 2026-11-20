package com.icecube.platform.u202417747.detection.domain.model.valueobjects;

import java.math.BigDecimal;

/**
 * Inclusive positive energy interval, expressed in TeV.
 *
 * @param minimumEnergy strictly positive lower bound
 * @param maximumEnergy strictly greater upper bound
 * @author Kevin Salazar
 */
public record EnergyRange(BigDecimal minimumEnergy, BigDecimal maximumEnergy) {
    public EnergyRange {
        if (minimumEnergy == null || maximumEnergy == null) {
            throw new IllegalArgumentException("Both energy values are required");
        }
        if (minimumEnergy.compareTo(BigDecimal.ZERO) <= 0 ||
                maximumEnergy.compareTo(BigDecimal.ZERO) <= 0 ||
                maximumEnergy.compareTo(minimumEnergy) <= 0) {
            throw new IllegalArgumentException("Energy bounds must be positive and maximum must exceed minimum");
        }
    }

    /** @return true if energy is inside this interval including both endpoints */
    public boolean containsEnergy(BigDecimal energy) {
        if (energy == null) throw new IllegalArgumentException("Energy to check is required");
        return energy.compareTo(minimumEnergy) >= 0 && energy.compareTo(maximumEnergy) <= 0;
    }
}
