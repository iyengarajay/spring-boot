package com.boot.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectInitRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(ProjectInitRunner.class);

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public void run(String... args) throws Exception {

		// Save
		projectRepository.save(new Project("Java", "Java based project for a bank"));
		projectRepository.save(new Project("Scala", "Scala based project for an oil company"));
		projectRepository.save(new Project("Apache Kafka", "Kafka based project for a data mining company"));

		// Find all projects.
		List<Project> projects = projectRepository.findAll();
		projects.forEach(project -> logger.info(project.toString()));

		// Get a project by name
		logger.info("Getting project by name");
		Project project = projectRepository.findByName("Scala");
		logger.info("Scala based project id " + project.getId());

	}

}
