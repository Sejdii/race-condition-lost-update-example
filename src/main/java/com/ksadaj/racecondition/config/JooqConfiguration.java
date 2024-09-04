package com.ksadaj.racecondition.config;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JooqConfiguration {

    private final DataSource dataSource;

    @Bean
    public DSLContext dsl() {
        return new DefaultDSLContext(configuration());
    }

    public org.jooq.Configuration configuration() {
        return new DefaultConfiguration().set(dataSource).set(SQLDialect.H2);
    }
}
