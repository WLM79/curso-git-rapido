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
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


@RestController
@RequestMapping("/api/v1")
public class UserController extends ConrolAdvice {
	
//	@Autowired
//	private FilterService<Customer, Integer> filterService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@CrossOrigin(origins = "http://localhost:3000/#/user_um")
	@GetMapping("/user_um")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<User> getAllUsersTeste() throws ResourceNotFoundException{
		
		  User user = userRepository.findById(1l)
			      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: 1" ));
			    return ResponseEntity.ok().body(user);
		
	}
	
//	http://localhost:8080/api/v1/users?_end=10&_order=ASC&_sort=id&_start=0'
	@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "\"header1\", \"header2\", \"header3\"", allowCredentials = "false" , maxAge = 100, exposedHeaders = "X-Total-Count")
	 @RequestMapping(value = "users", method = RequestMethod.GET)
	    public Iterable<User> filterBy(
	            @RequestParam(required = false, name = "_end") String end,
	            @RequestParam(required = false, name = "_order") String order,
	            @RequestParam(required = false, name = "_sort") String sort,
	            @RequestParam(required = false, name = "_start") String start) {
//	        QueryParamWrapper wrapper = QueryParamExtractor.extract(filterStr, rangeStr, sortStr);
		
//		Pageable pageable = new PageRequest(Integer.parseInt(start), Integer.parseInt(end));
//	    List<User> pagePost = this.userRepository.findAll();


		
	        return this.userRepository.findAll();
	    }
	
	//pegar a conta pelo id
	@GetMapping("/users/{id}")
	@CrossOrigin(origins = {"http://localhost:3000"})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
	    throws ResourceNotFoundException {
		
	    User user = userRepository.findById(userId)
	      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + userId));
	    return ResponseEntity.ok().body(user);
	    
	}
	
	//salvar conta
	@CrossOrigin(origins = {"http://localhost:3000"})
	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
		
		return this.userRepository.save(user);
		
	}
	
	//atualizar conta
	@PutMapping("/users/{id}")
	@CrossOrigin(origins = {"http://localhost:3000"})
	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
    	@Validated @RequestBody User userCaracteristicas) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + userId));
        
//        user.setCpf(userCaracteristicas.getCpf());
//        user.setEmail(userCaracteristicas.getEmail());
//        user.setNome(userCaracteristicas.getNome());
//        user.setDataNascimento(userCaracteristicas.getDataNascimento());
        
        return ResponseEntity.ok(this.userRepository.save(user));
        
    }
	
	//deletar conta
	@CrossOrigin(origins = {"http://localhost:3000"})
	@DeleteMapping("/users/{id}")	
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) 
			throws ResourceNotFoundException {
	    User user = userRepository.findById(userId)
	   .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + userId));
	
	    this.userRepository.delete(user);
	    Map<String, Boolean> resposta = new HashMap<>();
	    resposta.put("user deletado", Boolean.TRUE);
	    return resposta;
	}

}
