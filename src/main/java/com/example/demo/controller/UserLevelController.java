package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserLevel;
import com.example.demo.repository.UserLevelRepository;


@RestController
@RequestMapping("/api/v1")
public class UserLevelController extends ConrolAdvice {
	
//	@Autowired
//	private FilterService<Customer, Integer> filterService;
	
	@Autowired
	private UserLevelRepository userLevelRepository;
	
	
	@CrossOrigin(origins = "http://localhost:3000/#/userLevel_um")
	@GetMapping("/userLevel_um")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UserLevel> getAllUserLevelsTeste() throws ResourceNotFoundException{
		
		  UserLevel userLevel = userLevelRepository.findById(1l)
			      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: 1" ));
			    return ResponseEntity.ok().body(userLevel);
		
	}
	
//	http://localhost:8080/api/v1/userLevels?_end=10&_order=ASC&_sort=id&_start=0'
	@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "\"header1\", \"header2\", \"header3\"", allowCredentials = "false" , maxAge = 100, exposedHeaders = "X-Total-Count")
	 @RequestMapping(value = "userLevels", method = RequestMethod.GET)
	    public Iterable<UserLevel> filterBy(
	            @RequestParam(required = false, name = "_end") String end,
	            @RequestParam(required = false, name = "_order") String order,
	            @RequestParam(required = false, name = "_sort") String sort,
	            @RequestParam(required = false, name = "_start") String start) {
//	        QueryParamWrapper wrapper = QueryParamExtractor.extract(filterStr, rangeStr, sortStr);
		
//		Pageable pageable = new PageRequest(Integer.parseInt(start), Integer.parseInt(end));
//	    List<UserLevel> pagePost = this.userLevelRepository.findAll();


		
	        return this.userLevelRepository.findAll();
	    }
	
	//pegar a conta pelo id
	@GetMapping("/userLevels/{id}")
	@CrossOrigin(origins = {"http://localhost:3000"})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UserLevel> getUserLevelById(@PathVariable(value = "id") Long userLevelId)
	    throws ResourceNotFoundException {
		
	    UserLevel userLevel = userLevelRepository.findById(userLevelId)
	      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + userLevelId));
	    return ResponseEntity.ok().body(userLevel);
	    
	}
	
	//salvar conta
	@CrossOrigin(origins = {"http://localhost:3000"})
	@PostMapping("/userLevels")
	@ResponseStatus(HttpStatus.CREATED)
	public UserLevel createUserLevel(@RequestBody UserLevel userLevel) {
		
		return this.userLevelRepository.save(userLevel);
		
	}
	
	//atualizar conta
	@PutMapping("/userLevels/{id}")
	@CrossOrigin(origins = {"http://localhost:3000"})
	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserLevel> updateUserLevel(@PathVariable(value = "id") Long userLevelId,
    	@Validated @RequestBody UserLevel userLevelCaracteristicas) throws ResourceNotFoundException {
        UserLevel userLevel = userLevelRepository.findById(userLevelId)
        .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + userLevelId));
               
        return ResponseEntity.ok(this.userLevelRepository.save(userLevel));
        
    }
	
	//deletar conta
	@CrossOrigin(origins = {"http://localhost:3000"})
	@DeleteMapping("/userLevels/{id}")	
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Boolean> deleteUserLevel(@PathVariable(value = "id") Long userLevelId) 
			throws ResourceNotFoundException {
	    UserLevel userLevel = userLevelRepository.findById(userLevelId)
	   .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + userLevelId));
	
	    this.userLevelRepository.delete(userLevel);
	    Map<String, Boolean> resposta = new HashMap<>();
	    resposta.put("userLevel deletado", Boolean.TRUE);
	    return resposta;
	}

}
