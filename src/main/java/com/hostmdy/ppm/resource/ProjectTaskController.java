package com.hostmdy.ppm.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.ppm.domain.ProjectTask;
import com.hostmdy.ppm.service.MapValidationErrorService;
import com.hostmdy.ppm.service.ProjectTaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/task")
public class ProjectTaskController {
	
	private final ProjectTaskService projectTaskService;
	private final MapValidationErrorService mapErrorService;

	public ProjectTaskController(ProjectTaskService projectTaskService, MapValidationErrorService mapErrorService) {
		super();
		this.projectTaskService = projectTaskService;
		this.mapErrorService = mapErrorService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createProjectTask(@Valid @RequestBody ProjectTask projectTask,
			BindingResult bindingResult){
		ResponseEntity<?> errorResponse = mapErrorService.validate(bindingResult);
		
		if(errorResponse != null)
			return errorResponse;
		 
		ProjectTask createdProjectTask = projectTaskService.saveOrUpdate(projectTask);
		
		return new ResponseEntity<ProjectTask>(createdProjectTask,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/assign/identifier/{identifier}")
	public ResponseEntity<?> addProjectTaskToBacklog(@PathVariable String identifier,
			@Valid @RequestBody ProjectTask projectTask, BindingResult bindingResult) {
		ResponseEntity<?> errorResponse = mapErrorService.validate(bindingResult);

		if (errorResponse != null)
			return errorResponse;
		
		ProjectTask assignedProjectTask = projectTaskService.addProjectToBacklog(identifier, projectTask);
		
		return new  ResponseEntity<ProjectTask>(assignedProjectTask,HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){                                            
		
		Optional<ProjectTask> optionalTask = projectTaskService.findById(id);
		
		if(optionalTask.isEmpty())
			return new ResponseEntity<String>("ProjectTask with id = "+id+" is not found",HttpStatus.NOT_FOUND);
	
		return new ResponseEntity<ProjectTask>(optionalTask.get(),HttpStatus.OK);
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id){
		 projectTaskService.deleteById(id);
		 
		 return new ResponseEntity<String>("Project task with id="+id+" is deleted",HttpStatus.OK);
			
	}
	
	@GetMapping("/all")
	public List<ProjectTask> findAll(){
		return projectTaskService.findAll();
	}
	
	@GetMapping("/sequence/{sequence}")
	public ResponseEntity<?> findByProjectSequence(@PathVariable String sequence){
		Optional<ProjectTask> optionalTask = projectTaskService.findByProjectSequence(sequence);
		
		if(optionalTask.isEmpty())
			return new ResponseEntity<String>("Project Task with sequence ="+sequence+" is not found",HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<ProjectTask>(optionalTask.get(),HttpStatus.OK);
	}
	

}
