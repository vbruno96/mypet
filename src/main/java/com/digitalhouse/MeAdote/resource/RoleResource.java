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

import com.digitalhouse.MeAdote.exception.DataIntegrityViolationException;
import com.digitalhouse.MeAdote.exception.ObjectNotFoundException;
import com.digitalhouse.MeAdote.model.Role;
import com.digitalhouse.MeAdote.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleResource {
	
	RoleService roleService;
	
	@Autowired
	public RoleResource(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Role role) {
		role = this.roleService.create(role);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand( role.getId() )
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Role role) throws ObjectNotFoundException, DataIntegrityViolationException {
		role.setId(id);
		
		this.roleService.update(role);
		
		return ResponseEntity.noContent().build();		
	}
	
	@GetMapping
	public ResponseEntity<List<Role>> findAll() {
		List<Role> roles = this.roleService.findAll();
		
		return ResponseEntity.ok(roles);
	}
 	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws ObjectNotFoundException, DataIntegrityViolationException {
		this.roleService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	

}
