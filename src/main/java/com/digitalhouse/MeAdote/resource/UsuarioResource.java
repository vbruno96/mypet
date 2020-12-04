package com.digitalhouse.MeAdote.resource;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Set;

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
import com.digitalhouse.MeAdote.model.Login;
import com.digitalhouse.MeAdote.model.Pet;
import com.digitalhouse.MeAdote.model.Role;
import com.digitalhouse.MeAdote.model.Usuario;
import com.digitalhouse.MeAdote.model.UsuarioCreate;
import com.digitalhouse.MeAdote.service.PetService;
import com.digitalhouse.MeAdote.service.UsuarioService;
import com.digitalhouse.MeAdote.utils.Utils;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	private UsuarioService usuarioService;
	private PetService petService;
	
	@Autowired
	public UsuarioResource(UsuarioService usuarioService, PetService petService) {
		this.usuarioService = usuarioService;
		this.petService = petService;
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody UsuarioCreate usuarioCreate) throws ObjectAlreadyExistsException {
		Login login = Login.builder().email(usuarioCreate.getEmail()).senha(usuarioCreate.getSenha()).build();
		Usuario usuario = Usuario.builder().nome(usuarioCreate.getNome()).login(login).build();
		
		usuario.setLink_imagem("defaultImage.png");

		usuario = this.usuarioService.create(usuario);

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand( usuario.getId() )
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody Usuario novo) throws ObjectNotFoundException, DataIntegrityViolationException {
		Usuario antigo = usuarioService.getLoggedUser();
		
		novo.setId(antigo.getId());
		
		this.usuarioService.update(novo);
		
		return ResponseEntity.noContent().build();		
	}
	
	@GetMapping
	public ResponseEntity<Usuario> find() throws ObjectNotFoundException {
		Usuario usuario = this.usuarioService.getLoggedUser();
		
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/password")
	public ResponseEntity<Void> updatePassword(@RequestBody String senha) throws ObjectNotFoundException, DataIntegrityViolationException {
		Usuario usuario = usuarioService.getLoggedUser();
		
		this.usuarioService.updatePassword(usuario, senha);
		
		return ResponseEntity.noContent().build();		
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete() throws ObjectNotFoundException, DataIntegrityViolationException {
		Usuario usuario = this.usuarioService.getLoggedUser();
		
		this.usuarioService.deleteById(usuario.getId());
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/likes")
	public ResponseEntity<Void> addLike (@RequestBody Long idPet) throws ObjectNotFoundException, DataIntegrityViolationException {		
		Usuario usuario= usuarioService.getLoggedUser();
		
		Pet pet = petService.findById(idPet);
		
		pet.getLikes().add(usuario);
		this.petService.update(pet);
		
		return ResponseEntity.noContent().build();		
	}
	
	@DeleteMapping("/likes")
	public ResponseEntity<Void> deleteLike (@RequestBody Long idPet) throws ObjectNotFoundException, DataIntegrityViolationException {		
		Usuario usuario= usuarioService.getLoggedUser();
		
		Pet petRemover = petService.findById(idPet);		
		
		List<Usuario> likes = petRemover.getLikes();		
		likes.remove(usuario);
		
		this.petService.update(petRemover);
		
		return ResponseEntity.noContent().build();		
	}
	
	@GetMapping("/likes")
	public ResponseEntity<List<Pet>> getLikes () throws ObjectNotFoundException {		
		Usuario usuario= usuarioService.getLoggedUser();	
		
		List<Pet> likes = usuario.getLikes();
		
		return ResponseEntity.ok(likes);	
	}
	
	@PostMapping("/matches")
	public ResponseEntity<Void> addMatch (@RequestBody Long idPet) throws ObjectNotFoundException, DataIntegrityViolationException {		
		Usuario usuario= usuarioService.getLoggedUser();
		
		Pet pet = petService.findById(idPet);
		
		pet.getMatches().add(usuario);
		this.petService.update(pet);
		
		return ResponseEntity.noContent().build();		
	}
	
	@DeleteMapping("/matches")
	public ResponseEntity<Void> deleteMatch (@RequestBody Long idPet) throws ObjectNotFoundException, DataIntegrityViolationException {		
		Usuario usuario= usuarioService.getLoggedUser();
		
		Pet petRemover = petService.findById(idPet);		
		
		List<Usuario> matches = petRemover.getMatches();		
		matches.remove(usuario);
		
		this.petService.update(petRemover);
		
		return ResponseEntity.noContent().build();		
	}
	
	@GetMapping("/matches")
	public ResponseEntity<List<Pet>> getMatches () throws ObjectNotFoundException {		
		Usuario usuario= usuarioService.getLoggedUser();	
		
		List<Pet> matches = usuario.getMatches();
		
		return ResponseEntity.ok(matches);	
	}
	
	@PostMapping("/adocoes")
	public ResponseEntity<Void> addAdocao (@RequestBody Long idPet) throws ObjectNotFoundException, DataIntegrityViolationException {		
		Usuario usuario= usuarioService.getLoggedUser();
		
		Pet pet = petService.findById(idPet);
		
		usuario.getAdocoes().add(pet);
		
		this.usuarioService.update(usuario);
		
		return ResponseEntity.noContent().build();		
	}
	
	@DeleteMapping("/adocoes")
	public ResponseEntity<Void> deleteAdocao (@RequestBody Long idPet) throws ObjectNotFoundException, DataIntegrityViolationException {		
		Usuario usuario= usuarioService.getLoggedUser();
		
		List<Pet> adocoes = usuario.getAdocoes();
		
		Pet petRemover = adocoes.stream().filter(adocao -> adocao.getId() == idPet).findFirst().orElseThrow(() -> new ObjectNotFoundException("idPet"));
		adocoes.remove(petRemover);
		
		this.usuarioService.update(usuario);
		
		return ResponseEntity.noContent().build();		
	}
	
	@GetMapping("/adocoes")
	public ResponseEntity<List<Pet>> getAdocoes () throws ObjectNotFoundException {		
		Usuario usuario= usuarioService.getLoggedUser();	
		
		List<Pet> adocoes = usuario.getAdocoes();
		
		return ResponseEntity.ok(adocoes);	
	}
	
	@PostMapping("/{id}/roles")
	public ResponseEntity<Void> addRole (@PathVariable Long id, @RequestBody Role role) throws ObjectNotFoundException, DataIntegrityViolationException {		
		Usuario usuario= usuarioService.findById(id);
		
		usuario.getLogin().getRoles().add(role);
		
		this.usuarioService.update(usuario);
		
		return ResponseEntity.noContent().build();		
	}
	
	@DeleteMapping("/{id}/roles")
	public ResponseEntity<Void> deleteRole (@PathVariable Long id, @RequestBody Role role) throws ObjectNotFoundException, DataIntegrityViolationException {		
		Usuario usuario= usuarioService.findById(id);
		
		Set<Role> roles = usuario.getLogin().getRoles();
		roles.remove(role);
		
		this.usuarioService.update(usuario);
		
		return ResponseEntity.noContent().build();		
	}
	
	@GetMapping("/{id}/roles")
	public ResponseEntity<Set<Role>> getRoles (@PathVariable Long id) throws ObjectNotFoundException, DataIntegrityViolationException {		
		Usuario usuario= usuarioService.findById(id);	
		
		Set<Role> roles = usuario.getLogin().getRoles();
		
		return ResponseEntity.ok(roles);	
	}
	
	@PostMapping("/imagemPerfil")
	public ResponseEntity<Void> addImagemPerfil (@RequestParam MultipartFile userImage) throws ObjectNotFoundException, DataIntegrityViolationException, IllegalStateException, IOException {		
		Usuario usuario= usuarioService.getLoggedUser();	
		
		String link = Utils.uploadImage(userImage);
		
		usuario.setLink_imagem(link);		
		usuarioService.update(usuario);		
		
		return ResponseEntity.noContent().build();		
	}

}
