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
import com.example.demo.model.basic.Neighborhood;
import com.example.demo.repository.NeighborhoodRepository;


@RestController
@RequestMapping("/api/v1")
public class NeighborhoodController extends ConrolAdvice {
	
//	@Autowired
//	private FilterService<Customer, Integer> filterService;
	
	@Autowired
	private NeighborhoodRepository neighborhoodRepository;
	
	
	@CrossOrigin(origins = "http://localhost:3000/#/neighborhood_um")
	@GetMapping("/neighborhood_um")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Neighborhood> getAllNeighborhoodsTeste() throws ResourceNotFoundException{
		
		  Neighborhood neighborhood = neighborhoodRepository.findById(1l)
			      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: 1" ));
			    return ResponseEntity.ok().body(neighborhood);
		
	}
	
//	http://localhost:8080/api/v1/neighborhoods?_end=10&_order=ASC&_sort=id&_start=0'
	@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "\"header1\", \"header2\", \"header3\"", allowCredentials = "false" , maxAge = 100, exposedHeaders = "X-Total-Count")
	 @RequestMapping(value = "neighborhoods", method = RequestMethod.GET)
	    public Iterable<Neighborhood> filterBy(
	            @RequestParam(required = false, name = "_end") String end,
	            @RequestParam(required = false, name = "_order") String order,
	            @RequestParam(required = false, name = "_sort") String sort,
	            @RequestParam(required = false, name = "_start") String start) {
//	        QueryParamWrapper wrapper = QueryParamExtractor.extract(filterStr, rangeStr, sortStr);
		
//		Pageable pageable = new PageRequest(Integer.parseInt(start), Integer.parseInt(end));
//	    List<Neighborhood> pagePost = this.neighborhoodRepository.findAll();


		
	        return this.neighborhoodRepository.findAll();
	    }
	
	//pegar a conta pelo id
	@GetMapping("/neighborhoods/{id}")
	@CrossOrigin(origins = {"http://localhost:3000"})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Neighborhood> getNeighborhoodById(@PathVariable(value = "id") Long neighborhoodId)
	    throws ResourceNotFoundException {
		
	    Neighborhood neighborhood = neighborhoodRepository.findById(neighborhoodId)
	      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + neighborhoodId));
	    return ResponseEntity.ok().body(neighborhood);
	    
	}
	
	//salvar conta
	@CrossOrigin(origins = {"http://localhost:3000"})
	@PostMapping("/neighborhoods")
	@ResponseStatus(HttpStatus.CREATED)
	public Neighborhood createNeighborhood(@RequestBody Neighborhood neighborhood) {
		
		return this.neighborhoodRepository.save(neighborhood);
		
	}
	
	//atualizar conta
	@PutMapping("/neighborhoods/{id}")
	@CrossOrigin(origins = {"http://localhost:3000"})
	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Neighborhood> updateNeighborhood(@PathVariable(value = "id") Long neighborhoodId,
    	@Validated @RequestBody Neighborhood neighborhoodCaracteristicas) throws ResourceNotFoundException {
        Neighborhood neighborhood = neighborhoodRepository.findById(neighborhoodId)
        .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + neighborhoodId));
        
//        neighborhood.setCpf(neighborhoodCaracteristicas.getCpf());
//        neighborhood.setEmail(neighborhoodCaracteristicas.getEmail());
//        neighborhood.setNome(neighborhoodCaracteristicas.getNome());
//        neighborhood.setDataNascimento(neighborhoodCaracteristicas.getDataNascimento());
        
        return ResponseEntity.ok(this.neighborhoodRepository.save(neighborhood));
        
    }
	
	//deletar conta
	@CrossOrigin(origins = {"http://localhost:3000"})
	@DeleteMapping("/neighborhoods/{id}")	
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Boolean> deleteNeighborhood(@PathVariable(value = "id") Long neighborhoodId) 
			throws ResourceNotFoundException {
	    Neighborhood neighborhood = neighborhoodRepository.findById(neighborhoodId)
	   .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + neighborhoodId));
	
	    this.neighborhoodRepository.delete(neighborhood);
	    Map<String, Boolean> resposta = new HashMap<>();
	    resposta.put("neighborhood deletado", Boolean.TRUE);
	    return resposta;
	}

}
