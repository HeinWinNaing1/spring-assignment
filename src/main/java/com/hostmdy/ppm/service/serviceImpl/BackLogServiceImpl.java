package com.hostmdy.ppm.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostmdy.ppm.domain.Backlog;
import com.hostmdy.ppm.repository.BackLogRepository;
import com.hostmdy.ppm.service.BackLogService;

@Service
public class BackLogServiceImpl implements BackLogService{
	
	private final BackLogRepository backLogRepository;
	
	@Autowired
	public BackLogServiceImpl(BackLogRepository backLogRepository) {
		super();
		this.backLogRepository = backLogRepository;
	}

	@Override
	public Optional<Backlog> findByProjectIdentifier(String projectIdentifier) {
		// TODO Auto-generated method stub
		return backLogRepository.findByProjectIdentifier(projectIdentifier);
	}

	@Override
	public Backlog saveOrUpdate(Backlog backlog) {
		// TODO Auto-generated method stub
		return backLogRepository.save(backlog);
	}

	@Override
	public List<Backlog> findAll() {
		// TODO Auto-generated method stub
		return (List<Backlog>) backLogRepository.findAll();
	}

	@Override
	public Optional<Backlog> findById(Long id) {
		// TODO Auto-generated method stub
		return backLogRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		backLogRepository.deleteById(id);
	}

}
