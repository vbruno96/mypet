package com.digitalhouse.MeAdote.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.digitalhouse.MeAdote.exception.DataIntegrityViolationException;
import com.digitalhouse.MeAdote.exception.ObjectAlreadyExistsException;
import com.digitalhouse.MeAdote.exception.ObjectNotFoundException;
import com.digitalhouse.MeAdote.model.Pet;
import com.digitalhouse.MeAdote.model.PetFiltro;
import com.digitalhouse.MeAdote.model.Usuario;
import com.digitalhouse.MeAdote.service.PetService;
import com.digitalhouse.MeAdote.service.UsuarioService;


@RestController
@RequestMapping("/pets")
public class PetResource {
	
	PetService petService;
	UsuarioService usuarioService;
	
	@Autowired
	public PetResource(PetService petService, UsuarioService usuarioService) {
		this.petService = petService;
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Pet pet) throws ObjectAlreadyExistsException, ObjectNotFoundException {
		Usuario loggedUser = this.usuarioService.getLoggedUser();
		
		pet.setUsuario(loggedUser);
		
		pet = this.petService.create(pet);

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand( pet.getId() )
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Pet pet) throws ObjectNotFoundException, DataIntegrityViolationException {
		pet.setId(id);
		
		Usuario loggedUser = this.usuarioService.getLoggedUser();
		
		if (pet.getUsuario().getId() != loggedUser.getId()) {
			throw new ObjectNotFoundException();
		}
		
		this.petService.update(pet);
		
		return ResponseEntity.noContent().build();		
	}
	
	@GetMapping
	public ResponseEntity<List<Pet>> findAll() throws ObjectNotFoundException {
		Usuario loggedUser = this.usuarioService.getLoggedUser();
		
		List<Pet> pets = loggedUser.getPets();
	
		return ResponseEntity.ok(pets);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws ObjectNotFoundException, DataIntegrityViolationException {
		Usuario loggedUser = this.usuarioService.getLoggedUser();
		Pet pet = this.petService.findById(id);
		
		if (pet.getUsuario().getId() != loggedUser.getId()) {
			throw new ObjectNotFoundException();
		}
		
		this.petService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping
	public ResponseEntity<List<Pet>> filterPets(@RequestBody PetFiltro petFiltro) throws ObjectNotFoundException {
		List<Pet> pets = this.petService.getFromFilter(petFiltro);
	
		return ResponseEntity.ok(pets);
	}

}
