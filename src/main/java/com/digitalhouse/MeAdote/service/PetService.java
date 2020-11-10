package com.digitalhouse.MeAdote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalhouse.MeAdote.exception.DataIntegrityViolationException;
import com.digitalhouse.MeAdote.exception.ObjectNotFoundException;
import com.digitalhouse.MeAdote.model.Pet;
import com.digitalhouse.MeAdote.repository.PetRepository;

@Service
public class PetService extends BaseService<Pet> {
	
	@Autowired
	public PetService(PetRepository repository) {
		super(repository);
	}

	@Override
	public Pet update(Pet novo) throws ObjectNotFoundException, DataIntegrityViolationException {
		Pet antigo = this.findById(novo.getId());
		
		antigo.setData_nascimento(novo.getData_nascimento());
		antigo.setDescricao(novo.getDescricao());
		antigo.setEspecie(novo.getEspecie());
		antigo.setNome(novo.getNome());
		antigo.setPeso(novo.getPeso());
		antigo.setPorte(novo.getPorte());
		antigo.setSexo(novo.getSexo());		
		antigo.setPetDesaparecido(novo.getPetDesaparecido());
		
		return this.repository.save(antigo);
	}

}
