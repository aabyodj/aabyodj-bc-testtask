package bootcamp.testtask.service;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import bootcamp.testtask.repository.RepositoryConfig;

@Import(RepositoryConfig.class)
@SpringBootApplication
public class ServiceConfig {
}
