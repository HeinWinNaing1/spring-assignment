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

import com.hostmdy.ppm.domain.Backlog;
import com.hostmdy.ppm.service.BackLogService;
import com.hostmdy.ppm.service.MapValidationErrorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
public class BackLogController {
	
	private final BackLogService  backLogService;
	private final MapValidationErrorService errorService;
	
	
	public BackLogController(BackLogService backLogService, MapValidationErrorService errorService) {
		super();
		this.backLogService = backLogService;
		this.errorService = errorService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createBackLog(@Valid @RequestBody Backlog backlog,BindingResult bindingResult){
		
		ResponseEntity<?> responseErrorObject = errorService.validate(bindingResult);
		
		if(responseErrorObject != null)
			return responseErrorObject;
		
		Backlog createBacklog = backLogService.saveOrUpdate(backlog);
		
		return new ResponseEntity<Backlog>(createBacklog,HttpStatus.OK);
	}
	
	@GetMapping("/identifier/{identifier}")
	public ResponseEntity<?> findByProjectIdentifier(String Identifier){
		Optional<Backlog> optionalBacklog = backLogService.findByProjectIdentifier(Identifier);
		
		if(optionalBacklog.isEmpty())
			return new  ResponseEntity<String>("Backlog identifer = "+Identifier+" is not found",HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Backlog>(optionalBacklog.get(),HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<Backlog> optionalBacklog = backLogService.findById(id);
		
		if(optionalBacklog.isEmpty())
			return new ResponseEntity<String>("Backlog with id ="+ id +" is not found",HttpStatus.NOT_FOUND);
		
		
		return new ResponseEntity<Backlog>(optionalBacklog.get(),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<Backlog> findAll(){
		return backLogService.findAll();
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id){
		backLogService.deleteById(id);
		return new ResponseEntity<String>("Backlog id ="+id+" is deleted",HttpStatus.OK);
	}
	

}
