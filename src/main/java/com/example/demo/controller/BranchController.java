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
import com.example.demo.model.Branch;
import com.example.demo.repository.BranchRepository;


@RestController
@RequestMapping("/api/v1")
public class BranchController extends ConrolAdvice {
	
//	@Autowired
//	private FilterService<Customer, Integer> filterService;
	
	@Autowired
	private BranchRepository branchRepository;
	
	
	@CrossOrigin(origins = "http://localhost:3000/#/branch_um")
	@GetMapping("/branch_um")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Branch> getAllBranchsTeste() throws ResourceNotFoundException{
		
		  Branch branch = branchRepository.findById(1l)
			      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: 1" ));
			    return ResponseEntity.ok().body(branch);
		
	}
	
//	http://localhost:8080/api/v1/branches?_end=10&_order=ASC&_sort=id&_start=0'
	@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "\"header1\", \"header2\", \"header3\"", allowCredentials = "false" , maxAge = 100, exposedHeaders = "X-Total-Count")
	 @RequestMapping(value = "branches", method = RequestMethod.GET)
	    public Iterable<Branch> filterBy(
	            @RequestParam(required = false, name = "_end") String end,
	            @RequestParam(required = false, name = "_order") String order,
	            @RequestParam(required = false, name = "_sort") String sort,
	            @RequestParam(required = false, name = "_start") String start) {
//	        QueryParamWrapper wrapper = QueryParamExtractor.extract(filterStr, rangeStr, sortStr);
		
//		Pageable pageable = new PageRequest(Integer.parseInt(start), Integer.parseInt(end));
//	    List<Branch> pagePost = this.branchRepository.findAll();


		
	        return this.branchRepository.findAll();
	    }
	
	//pegar a conta pelo id
	@GetMapping("/branches/{id}")
	@CrossOrigin(origins = {"http://localhost:3000"})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Branch> getBranchById(@PathVariable(value = "id") Long branchId)
	    throws ResourceNotFoundException {
		
	    Branch branch = branchRepository.findById(branchId)
	      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + branchId));
	    return ResponseEntity.ok().body(branch);
	    
	}
	
	//salvar conta
	@CrossOrigin(origins = {"http://localhost:3000"})
	@PostMapping("/branches")
	@ResponseStatus(HttpStatus.CREATED)
	public Branch createBranch(@RequestBody Branch branch) {
		
		return this.branchRepository.save(branch);
		
	}
	
	//atualizar conta
	@PutMapping("/branches/{id}")
	@CrossOrigin(origins = {"http://localhost:3000"})
	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Branch> updateBranch(@PathVariable(value = "id") Long branchId,
    	@Validated @RequestBody Branch branchCaracteristicas) throws ResourceNotFoundException {
        Branch branch = branchRepository.findById(branchId)
        .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + branchId));
        
//        branch.setCpf(branchCaracteristicas.getCpf());
//        branch.setEmail(branchCaracteristicas.getEmail());
//        branch.setNome(branchCaracteristicas.getNome());
//        branch.setDataNascimento(branchCaracteristicas.getDataNascimento());
        
        return ResponseEntity.ok(this.branchRepository.save(branch));
        
    }
	
	//deletar conta
	@CrossOrigin(origins = {"http://localhost:3000"})
	@DeleteMapping("/branches/{id}")	
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Boolean> deleteBranch(@PathVariable(value = "id") Long branchId) 
			throws ResourceNotFoundException {
	    Branch branch = branchRepository.findById(branchId)
	   .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + branchId));
	
	    this.branchRepository.delete(branch);
	    Map<String, Boolean> resposta = new HashMap<>();
	    resposta.put("branch deletado", Boolean.TRUE);
	    return resposta;
	}

}
