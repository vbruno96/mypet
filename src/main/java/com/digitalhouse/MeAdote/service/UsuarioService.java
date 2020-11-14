package com.digitalhouse.MeAdote.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digitalhouse.MeAdote.exception.DataIntegrityViolationException;
import com.digitalhouse.MeAdote.exception.ObjectNotFoundException;
import com.digitalhouse.MeAdote.model.Login;
import com.digitalhouse.MeAdote.model.Role;
import com.digitalhouse.MeAdote.model.Usuario;
import com.digitalhouse.MeAdote.repository.LoginRepository;
import com.digitalhouse.MeAdote.repository.RoleRepository;
import com.digitalhouse.MeAdote.repository.UsuarioRepository;

@Service
public class UsuarioService extends BaseService<Usuario>{
	
	LoginRepository loginRepository;
	RoleRepository roleRepository;
	BCryptPasswordEncoder encoder;
	
	@Autowired
	public UsuarioService(UsuarioRepository repository, LoginRepository loginRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
		super(repository);
		this.loginRepository = loginRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
	}

	@Override
	public Usuario update(Usuario novo) throws ObjectNotFoundException, DataIntegrityViolationException {
		Usuario antigo = this.findById(novo.getId());
			
		antigo.setBairro(novo.getBairro());
		antigo.setCelular(novo.getCelular());
		antigo.setCep(novo.getCep());
		antigo.setCidade(novo.getCidade());
		antigo.setDescricao(novo.getDescricao());
		antigo.getLogin().setEmail( novo.getLogin().getEmail() );
		antigo.setLink_imagem(novo.getLink_imagem());
		antigo.setLogradouro(novo.getLogradouro());
		antigo.setNome(novo.getNome());
		antigo.setNum_residencia(novo.getNum_residencia());
		antigo.setTelefone(novo.getTelefone());
		
		return this.repository.save(antigo);
	}
	
	@Override
	public Usuario create(Usuario usuario) {
		usuario.setId(null);
		usuario.getLogin().setId(null);
		
		usuario.getLogin().setSenha(
				this.encoder.encode(
						usuario.getLogin().getSenha()));
		
		String nomeRolePadrao = "user";		
		Role rolePadrao = roleRepository.getByName(nomeRolePadrao).orElseGet(() -> this.createRole(nomeRolePadrao));
		
		HashSet<Role> roles = new HashSet<>();		
		roles.add(rolePadrao);
		
		usuario.getLogin().setRoles(roles);
		
		return this.repository.save(usuario);			
	}
	
	public Usuario findByUsername(String username) throws ObjectNotFoundException {
		Login login = this.loginRepository.findByEmail(username).orElseThrow( () -> new ObjectNotFoundException("e-mail") );
		
		return login.getUsuario();
	}
	
	public Usuario getLoggedUser() throws ObjectNotFoundException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;		
		
		if (principal instanceof Login) {
			username = ((Login) principal).getEmail();
		}
		else if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}
		else {
			username = principal.toString();
		}
		
		return findByUsername(username);
	}
	
	public Usuario updatePassword(Usuario usuario, String password) throws ObjectNotFoundException, DataIntegrityViolationException {
		usuario = this.findById(usuario.getId());
		
		usuario.getLogin().setSenha(encoder.encode(password));
		
		return this.repository.save(usuario);
	}
	
	private Role createRole(String nome) {
		Role role = new Role();
		role.setName(nome);
		
		return this.roleRepository.save(role);
	}

}
