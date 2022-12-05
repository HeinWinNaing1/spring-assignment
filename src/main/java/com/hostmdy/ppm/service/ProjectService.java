package com.hostmdy.ppm.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.ppm.domain.Project;

public interface ProjectService {
	
	//Create
	Project saveOrUpdate(Project project);
	
	//Show all
	List<Project> findAll();
	
	//Show by id
	Optional<Project> findById(Long id);
	
	//with identifier
	Optional<Project> findByIdentifier(String identifier);
	
	//Delete with id
	void deleteById(Long id);

}
