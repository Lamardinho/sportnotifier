package com.lamardinho.sportnotifier.config;

import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;

public class H2IdentifierNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    /*@Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        if (name != null) {
            name = new Identifier(name.getText().toLowerCase(), true);
        }
        return name;
    }*/
}
