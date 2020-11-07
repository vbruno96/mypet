package com.digitalhouse.MeAdote.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.digitalhouse.MeAdote.model.Especie;
import com.digitalhouse.MeAdote.service.EspecieService;

@RestController
@RequestMapping("/especies")
public class EspecieResource {

	EspecieService especieService;
	
	@Autowired
	public EspecieResource(EspecieService especieService) {
		this.especieService = especieService;
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Especie especie) {
		especie = this.especieService.create(especie);

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand( especie.getId() )
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Especie especie) {
		especie.setId(id);
		
		this.especieService.update(especie);
		
		return ResponseEntity.noContent().build();		
	}
	
	@GetMapping
	public ResponseEntity<List<Especie>> findAll() {
		List<Especie> especies = this.especieService.findAll();
		
		return ResponseEntity.ok(especies);
	}
 	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.especieService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
