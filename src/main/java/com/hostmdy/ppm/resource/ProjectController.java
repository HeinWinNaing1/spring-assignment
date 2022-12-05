package com.hostmdy.ppm.resource;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hostmdy.ppm.domain.Project;
import com.hostmdy.ppm.service.MapValidationErrorService;
import com.hostmdy.ppm.service.ProjectService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	private final ProjectService projectService;
	private final MapValidationErrorService mapErrorService;

	@Autowired
	public ProjectController(ProjectService projectService, MapValidationErrorService mapErrorService) {
		super();
		this.projectService = projectService;
		this.mapErrorService = mapErrorService;
	}

	// @RequestMapping(value = "/create" ,method = RequestMethod.POST)
	@PostMapping("/create")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result) {
		ResponseEntity<?> resposeErrorObject = mapErrorService.validate(result);
		if(resposeErrorObject != null)
			return resposeErrorObject;
		
		Project createProject = projectService.saveOrUpdate(project);

		return new ResponseEntity<Project>(createProject, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public List<Project> findAll(){
		return projectService.findAll();
	}
	
	@GetMapping("/id/{id}")
	
	public ResponseEntity<?> findByid(/*@RequestParam*/@PathVariable Long id) {
		Optional<Project> projectOptional = projectService.findById(id);
		
		if(projectOptional.isEmpty())
		 return	new ResponseEntity<String>("Project with id="+id+"is not found",HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Project>(projectOptional.get(),HttpStatus.OK);
	}
	
	@GetMapping("/identifier/{identifier}")
	public ResponseEntity<?> findByIdentifier(@PathVariable String identifier){
		Optional<Project> projectOptional = projectService.findByIdentifier(identifier);
		
		if(projectOptional.isEmpty())
			return new ResponseEntity<String>("Project with identifier"+identifier+"is not found",HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Project>(projectOptional.get(),HttpStatus.OK);
					
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id){
		projectService.deleteById(id);
		return new ResponseEntity<String>("Deleted id="+id,HttpStatus.OK);
	}

}
