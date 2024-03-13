package ts.andrey.tm.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@Configuration
public class TestDatabaseConfiguration {

    private static final PostgreSQLContainer POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @Bean
    public DataSource dataSource() {
        POSTGRE_SQL_CONTAINER.start();
        return DataSourceBuilder.create()
                .driverClassName(POSTGRE_SQL_CONTAINER.getDriverClassName())
                .url(POSTGRE_SQL_CONTAINER.getJdbcUrl())
                .username(POSTGRE_SQL_CONTAINER.getUsername())
                .password(POSTGRE_SQL_CONTAINER.getPassword())
                .build();
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.yaml");
        liquibase.setContexts("test");
        liquibase.setDefaultSchema("public");
        return liquibase;
    }

}
