package com.digitalhouse.MeAdote.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.digitalhouse.MeAdote.configuration.SwaggerPageable;
import com.digitalhouse.MeAdote.model.BaseModel;
import com.digitalhouse.MeAdote.service.BaseService;

public class BaseResource<Entity> {
	
	protected final BaseService<Entity> service;
	
	@Autowired
	public BaseResource(BaseService<Entity> service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Entity entity) {
		entity = this.service.create(entity);

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand( ((BaseModel) entity).getId() )
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Entity entity) {
		((BaseModel) entity).setId(id);
		
		this.service.update(entity);
		
		return ResponseEntity.noContent().build();		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Entity> findById(@PathVariable Long id) {
		Entity entity = this.service.findById(id);
		
		return ResponseEntity.ok(entity);
	}
	
	@GetMapping
	@SwaggerPageable
	public ResponseEntity<Page<Entity>> findAll(Pageable pageable) {
		Page<Entity> entities = this.service.findAll(pageable);
		
		return ResponseEntity.ok(entities);
	}
 	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.service.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}	
	

}
