package com.boot.test.db;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractContainerBaseTest {

	protected static final PostgreSQLContainer postgres;

	static {
		postgres = new PostgreSQLContainer("postgres:9.6.15").withDatabaseName("socialmediasite")
				.withUsername("postgres").withPassword("password");
		// Mapped port can only be obtained after container is started.
		postgres.start();
	}

	static class PropertiesInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertyValues
					.of("spring.datasource.driver-class-name=" + postgres.getDriverClassName(),
							"spring.datasource.url=" + postgres.getJdbcUrl(),
							"spring.datasource.username=" + postgres.getUsername(),
							"spring.datasource.password=" + postgres.getPassword())
					.applyTo(configurableApplicationContext.getEnvironment());
		}
	}
}
