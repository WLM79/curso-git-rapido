package com.example.demo.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.AccessType.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
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
import com.example.demo.model.Cadastro;
import com.example.demo.repository.CadastroRepository;


@RestController
@RequestMapping("/api/v1")
public class CadastroController extends ConrolAdvice {
	
//	@Autowired
//	private FilterService<Customer, Integer> filterService;
	
	@Autowired
	private CadastroRepository cadastroRepository;
	
	//pegar todas as contas
	@GetMapping("/cadastros_old")
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseStatus(HttpStatus.OK)	
	public List<Cadastro> getAllCadastros(){		
		return this.cadastroRepository.findAll();
		
	}
	
	
	@CrossOrigin(origins = "http://localhost:3000/#/cadastro_um")
	@GetMapping("/cadastro_um")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Cadastro> getAllCadastrosTeste() throws ResourceNotFoundException{
		
		  Cadastro cadastro = cadastroRepository.findById(1l)
			      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: 1" ));
			    return ResponseEntity.ok().body(cadastro);
		
	}
	
//	http://localhost:8080/api/v1/cadastros?_end=10&_order=ASC&_sort=id&_start=0'
	@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "\"header1\", \"header2\", \"header3\"", allowCredentials = "false" , maxAge = 100, exposedHeaders = "X-Total-Count")
	 @RequestMapping(value = "cadastros", method = RequestMethod.GET)
	    public Iterable<Cadastro> filterBy(
	            @RequestParam(required = false, name = "_end") String end,
	            @RequestParam(required = false, name = "_order") String order,
	            @RequestParam(required = false, name = "_sort") String sort,
	            @RequestParam(required = false, name = "_start") String start) {
//	        QueryParamWrapper wrapper = QueryParamExtractor.extract(filterStr, rangeStr, sortStr);
		
//		Pageable pageable = new PageRequest(Integer.parseInt(start), Integer.parseInt(end));
//	    List<Cadastro> pagePost = this.cadastroRepository.findAll();


		
	        return this.cadastroRepository.findAll();
	    }
	
	//pegar a conta pelo id
	@GetMapping("/cadastros/{id}")
	@CrossOrigin(origins = {"http://localhost:3000"})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Cadastro> getCadastroById(@PathVariable(value = "id") Long cadastroId)
	    throws ResourceNotFoundException {
		
	    Cadastro cadastro = cadastroRepository.findById(cadastroId)
	      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + cadastroId));
	    return ResponseEntity.ok().body(cadastro);
	    
	}
	
	//salvar conta
	@CrossOrigin(origins = {"http://localhost:3000"})
	@PostMapping("/cadastros")
	@ResponseStatus(HttpStatus.CREATED)
	public Cadastro createCadastro(@RequestBody Cadastro cadastro) {
		
		return this.cadastroRepository.save(cadastro);
		
	}
	
	//atualizar conta
	@PutMapping("/cadastros/{id}")
	@CrossOrigin(origins = {"http://localhost:3000"})
	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cadastro> updateCadastro(@PathVariable(value = "id") Long cadastroId,
    	@Validated @RequestBody Cadastro cadastroCaracteristicas) throws ResourceNotFoundException {
        Cadastro cadastro = cadastroRepository.findById(cadastroId)
        .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + cadastroId));
        
        cadastro.setCpf(cadastroCaracteristicas.getCpf());
        cadastro.setEmail(cadastroCaracteristicas.getEmail());
        cadastro.setNome(cadastroCaracteristicas.getNome());
        cadastro.setDataNascimento(cadastroCaracteristicas.getDataNascimento());
        
        return ResponseEntity.ok(this.cadastroRepository.save(cadastro));
        
    }
	
	//deletar conta
	@CrossOrigin(origins = {"http://localhost:3000"})
	@DeleteMapping("/cadastros/{id}")	
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Boolean> deleteCadastro(@PathVariable(value = "id") Long cadastroId) 
			throws ResourceNotFoundException {
	    Cadastro cadastro = cadastroRepository.findById(cadastroId)
	   .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + cadastroId));
	
	    this.cadastroRepository.delete(cadastro);
	    Map<String, Boolean> resposta = new HashMap<>();
	    resposta.put("cadastro deletado", Boolean.TRUE);
	    return resposta;
	}

}
