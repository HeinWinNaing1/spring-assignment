package com.hostmdy.ppm.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.ppm.domain.Backlog;

public interface BackLogRepository extends CrudRepository<Backlog,Long>{
	
	Optional<Backlog> findByProjectIdentifier(String projectIdentifier);

}
