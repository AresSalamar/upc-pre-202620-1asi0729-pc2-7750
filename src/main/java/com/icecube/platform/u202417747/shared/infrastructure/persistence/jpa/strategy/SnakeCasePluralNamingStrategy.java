package com.icecube.platform.u202417747.shared.infrastructure.persistence.jpa.strategy;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * Applies snake_case naming and pluralization for table identifiers.
 * Study replacement for the instructor-provided physical naming strategy.
 *
 * @author Kevin Salazar
 */
public class SnakeCasePluralNamingStrategy extends CamelCaseToUnderscoresNamingStrategy {
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        Identifier converted = super.toPhysicalTableName(name, context);
        if (converted == null) return null;
        String table = converted.getText();
        if (!table.endsWith("s")) table = table + "s";
        return Identifier.toIdentifier(table, converted.isQuoted());
    }
}
