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
import com.example.demo.model.Company;
import com.example.demo.repository.CompanyRepository;


@RestController
@RequestMapping("/api/v1")
public class CompanyController extends ConrolAdvice {
	
//	@Autowired
//	private FilterService<Customer, Integer> filterService;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	
	@CrossOrigin(origins = "http://localhost:3000/#/company_um")
	@GetMapping("/company_um")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Company> getAllCompanysTeste() throws ResourceNotFoundException{
		
		  Company company = companyRepository.findById(1l)
			      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: 1" ));
			    return ResponseEntity.ok().body(company);
		
	}
	
//	http://localhost:8080/api/v1/companys?_end=10&_order=ASC&_sort=id&_start=0'
	@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "\"header1\", \"header2\", \"header3\"", allowCredentials = "false" , maxAge = 100, exposedHeaders = "X-Total-Count")
	 @RequestMapping(value = "companys", method = RequestMethod.GET)
	    public Iterable<Company> filterBy(
	            @RequestParam(required = false, name = "_end") String end,
	            @RequestParam(required = false, name = "_order") String order,
	            @RequestParam(required = false, name = "_sort") String sort,
	            @RequestParam(required = false, name = "_start") String start) {
//	        QueryParamWrapper wrapper = QueryParamExtractor.extract(filterStr, rangeStr, sortStr);
		
//		Pageable pageable = new PageRequest(Integer.parseInt(start), Integer.parseInt(end));
//	    List<Company> pagePost = this.companyRepository.findAll();


		
	        return this.companyRepository.findAll();
	    }
	
	//pegar a conta pelo id
	@GetMapping("/companys/{id}")
	@CrossOrigin(origins = {"http://localhost:3000"})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Company> getCompanyById(@PathVariable(value = "id") Long companyId)
	    throws ResourceNotFoundException {
		
	    Company company = companyRepository.findById(companyId)
	      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + companyId));
	    return ResponseEntity.ok().body(company);
	    
	}
	
	//salvar conta
	@CrossOrigin(origins = {"http://localhost:3000"})
	@PostMapping("/companys")
	@ResponseStatus(HttpStatus.CREATED)
	public Company createCompany(@RequestBody Company company) {
		
		return this.companyRepository.save(company);
		
	}
	
	//atualizar conta
	@PutMapping("/companys/{id}")
	@CrossOrigin(origins = {"http://localhost:3000"})
	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Company> updateCompany(@PathVariable(value = "id") Long companyId,
    	@Validated @RequestBody Company companyCaracteristicas) throws ResourceNotFoundException {
        Company company = companyRepository.findById(companyId)
        .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + companyId));
        
//        company.setCpf(companyCaracteristicas.getCpf());
//        company.setEmail(companyCaracteristicas.getEmail());
//        company.setNome(companyCaracteristicas.getNome());
//        company.setDataNascimento(companyCaracteristicas.getDataNascimento());
        
        return ResponseEntity.ok(this.companyRepository.save(company));
        
    }
	
	//deletar conta
	@CrossOrigin(origins = {"http://localhost:3000"})
	@DeleteMapping("/companys/{id}")	
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Boolean> deleteCompany(@PathVariable(value = "id") Long companyId) 
			throws ResourceNotFoundException {
	    Company company = companyRepository.findById(companyId)
	   .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + companyId));
	
	    this.companyRepository.delete(company);
	    Map<String, Boolean> resposta = new HashMap<>();
	    resposta.put("company deletado", Boolean.TRUE);
	    return resposta;
	}

}
