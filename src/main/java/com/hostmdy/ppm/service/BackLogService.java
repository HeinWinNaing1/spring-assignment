package com.hostmdy.ppm.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.ppm.domain.Backlog;

public interface BackLogService {
	
	Backlog saveOrUpdate(Backlog backlog);
	
	Optional<Backlog> findByProjectIdentifier(String projectIdentifier);
	
	List<Backlog> findAll();
	
	Optional<Backlog> findById(Long id);
	
	void deleteById(Long id);
}
