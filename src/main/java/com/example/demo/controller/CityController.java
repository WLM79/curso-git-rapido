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
import com.example.demo.model.basic.City;
import com.example.demo.repository.CityRepository;


@RestController
@RequestMapping("/api/v1")
public class CityController extends ConrolAdvice {
	
//	@Autowired
//	private FilterService<Customer, Integer> filterService;
	
	@Autowired
	private CityRepository cityRepository;
	
	
	@CrossOrigin(origins = "http://localhost:3000/#/city_um")
	@GetMapping("/city_um")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<City> getAllCitysTeste() throws ResourceNotFoundException{
		
		  City city = cityRepository.findById(1l)
			      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: 1" ));
			    return ResponseEntity.ok().body(city);
		
	}
	
//	http://localhost:8080/api/v1/citys?_end=10&_order=ASC&_sort=id&_start=0'
	@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "\"header1\", \"header2\", \"header3\"", allowCredentials = "false" , maxAge = 100, exposedHeaders = "X-Total-Count")
	 @RequestMapping(value = "citys", method = RequestMethod.GET)
	    public Iterable<City> filterBy(
	            @RequestParam(required = false, name = "_end") String end,
	            @RequestParam(required = false, name = "_order") String order,
	            @RequestParam(required = false, name = "_sort") String sort,
	            @RequestParam(required = false, name = "_start") String start) {
//	        QueryParamWrapper wrapper = QueryParamExtractor.extract(filterStr, rangeStr, sortStr);
		
//		Pageable pageable = new PageRequest(Integer.parseInt(start), Integer.parseInt(end));
//	    List<City> pagePost = this.cityRepository.findAll();


		
	        return this.cityRepository.findAll();
	    }
	
	//pegar a conta pelo id
	@GetMapping("/citys/{id}")
	@CrossOrigin(origins = {"http://localhost:3000"})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<City> getCityById(@PathVariable(value = "id") Long cityId)
	    throws ResourceNotFoundException {
		
	    City city = cityRepository.findById(cityId)
	      .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + cityId));
	    return ResponseEntity.ok().body(city);
	    
	}
	
	//salvar conta
	@CrossOrigin(origins = {"http://localhost:3000"})
	@PostMapping("/citys")
	@ResponseStatus(HttpStatus.CREATED)
	public City createCity(@RequestBody City city) {
		
		return this.cityRepository.save(city);
		
	}
	
	//atualizar conta
	@PutMapping("/citys/{id}")
	@CrossOrigin(origins = {"http://localhost:3000"})
	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<City> updateCity(@PathVariable(value = "id") Long cityId,
    	@Validated @RequestBody City cityCaracteristicas) throws ResourceNotFoundException {
        City city = cityRepository.findById(cityId)
        .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + cityId));
        
//        city.setCpf(cityCaracteristicas.getCpf());
//        city.setEmail(cityCaracteristicas.getEmail());
//        city.setNome(cityCaracteristicas.getNome());
//        city.setDataNascimento(cityCaracteristicas.getDataNascimento());
        
        return ResponseEntity.ok(this.cityRepository.save(city));
        
    }
	
	//deletar conta
	@CrossOrigin(origins = {"http://localhost:3000"})
	@DeleteMapping("/citys/{id}")	
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Boolean> deleteCity(@PathVariable(value = "id") Long cityId) 
			throws ResourceNotFoundException {
	    City city = cityRepository.findById(cityId)
	   .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada para o ID :: " + cityId));
	
	    this.cityRepository.delete(city);
	    Map<String, Boolean> resposta = new HashMap<>();
	    resposta.put("city deletado", Boolean.TRUE);
	    return resposta;
	}

}
