package com.digitalhouse.MeAdote.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.digitalhouse.MeAdote.exception.DataIntegrityViolationException;
import com.digitalhouse.MeAdote.exception.ObjectNotFoundException;
import com.digitalhouse.MeAdote.model.BaseModel;

@Service
public abstract class BaseService<Entity>{
	
	protected final JpaRepository<Entity, Long> repository;
	
	@Autowired
	public BaseService(JpaRepository<Entity, Long> repository) {
		this.repository = repository;
	}
	
	public Entity create(Entity entity) {
		((BaseModel) entity).setId(null);
		
		return repository.save(entity);
	}
	
	public abstract Entity update(Entity entity) throws ObjectNotFoundException, DataIntegrityViolationException;
	
	public Entity findById(Long id) throws ObjectNotFoundException, DataIntegrityViolationException {
		Optional
			.ofNullable(id)
			.orElseThrow( () -> new DataIntegrityViolationException("O id não pode ser nulo."));
		
		return this.repository.findById(id)
				.orElseThrow( () -> new ObjectNotFoundException("id"));
	}
	
	public List<Entity> findAll() {
		return this.repository.findAll();
	}
	
	public Page<Entity> findAll(Pageable pageable) {
		return this.repository.findAll(pageable);
	}
	
	public void deleteById(Long id) throws ObjectNotFoundException, DataIntegrityViolationException {
		this.findById(id);		
		this.repository.deleteById(id);
	}

}
