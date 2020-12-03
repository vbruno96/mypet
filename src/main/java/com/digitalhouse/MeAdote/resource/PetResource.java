package com.digitalhouse.MeAdote.resource;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.digitalhouse.MeAdote.exception.DataIntegrityViolationException;
import com.digitalhouse.MeAdote.exception.ObjectAlreadyExistsException;
import com.digitalhouse.MeAdote.exception.ObjectNotFoundException;
import com.digitalhouse.MeAdote.model.Pet;
import com.digitalhouse.MeAdote.model.PetFiltro;
import com.digitalhouse.MeAdote.model.Usuario;
import com.digitalhouse.MeAdote.service.PetService;
import com.digitalhouse.MeAdote.service.UsuarioService;
import com.digitalhouse.MeAdote.utils.Utils;


@RestController
@RequestMapping("/pets")
public class PetResource {
	
	PetService petService;
	UsuarioService usuarioService;	
	ReentrantLock lock = new ReentrantLock();
	
	@Autowired
	public PetResource(PetService petService, UsuarioService usuarioService) {
		this.petService = petService;
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Pet pet) throws ObjectAlreadyExistsException, ObjectNotFoundException {
		Usuario loggedUser = this.usuarioService.getLoggedUser();
		
		pet.setUsuario(loggedUser);
		pet.setLink_imagem_1(null);
		pet.setLink_imagem_2(null);
		pet.setLink_imagem_3(null);
		
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
		
		lock.lock();
		
		Usuario loggedUser = this.usuarioService.getLoggedUser();
		
		if (!loggedUser.getPets().contains(pet)) {
			throw new ObjectNotFoundException();
		}
		
		this.petService.update(pet);
		
		lock.unlock();
		
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
	
	@PostMapping("/{id}/imagem")
	public ResponseEntity<Void> addImagemPet (@PathVariable Long id, @RequestParam MultipartFile petImage, @RequestParam (required = false, defaultValue = "1") String idImagem) throws ObjectNotFoundException, DataIntegrityViolationException, IllegalStateException, IOException {		
		lock.lock();
		
		Usuario loggedUser = this.usuarioService.getLoggedUser();
		Pet pet = this.petService.findById(id);
		
		if (pet.getUsuario().getId() != loggedUser.getId()) {
			throw new ObjectNotFoundException();
		}
		
		String link = Utils.uploadImage(petImage);
		
		switch (idImagem) {
			case "1": pet.setLink_imagem_1(link); break;
			case "2": pet.setLink_imagem_2(link); break;
			default: pet.setLink_imagem_3(link); break;
		}
				
		petService.update(pet);
		
		lock.unlock();
		
		return ResponseEntity.noContent().build();		
	}
	
	@GetMapping("/adote")
	public ResponseEntity<List<Pet>> filterPets(@RequestParam String especie, @RequestParam String tipoPelo, @RequestParam String personalidade, @RequestParam String sexo, @RequestParam String porte) throws ObjectNotFoundException {
		Usuario loggedUser = this.usuarioService.getLoggedUser();
		
		PetFiltro petFiltro = PetFiltro.builder().gato(especie.contains("gato")).cachorro(especie.contains("cachorro"))
												 .peloCurto(tipoPelo.contains("C")).peloLongo(tipoPelo.contains("L"))
									   			 .calmo(personalidade.contains("C")).brincalhao(personalidade.contains("B"))
											     .macho(sexo.contains("M")).femea(sexo.contains("F"))
												 .pequeno(porte.contains("P")).medio(porte.contains("M")).grande(porte.contains("G"))
												 .build();				
		
		List<Pet> pets = this.petService.getFromFilter(loggedUser, petFiltro);
	
		return ResponseEntity.ok(pets);
	}

}
