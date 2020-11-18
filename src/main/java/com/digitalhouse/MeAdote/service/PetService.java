 package com.digitalhouse.MeAdote.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalhouse.MeAdote.exception.DataIntegrityViolationException;
import com.digitalhouse.MeAdote.exception.ObjectNotFoundException;
import com.digitalhouse.MeAdote.model.Pet;
import com.digitalhouse.MeAdote.model.PetFiltro;
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
	
	public List<Pet> getFromFilter(PetFiltro petFiltro) {
		List<Pet> pets = this.repository.findAll();
		
		if (!petFiltro.isGato()) {
			pets = pets.stream().filter(pet -> !pet.getEspecie().getNome().equals("Cat")).collect(Collectors.toList());
		}		
		if (!petFiltro.isCachorro()) {
			pets = pets.stream().filter(pet -> !pet.getEspecie().getNome().equals("Cachorro")).collect(Collectors.toList());
		}
		
		
		if (!petFiltro.isPeloCurto()) {
			pets = pets.stream().filter(pet -> !pet.getTipoPelo().equals("C")).collect(Collectors.toList());
		}		
		if (!petFiltro.isPeloLongo()) {
			pets = pets.stream().filter(pet -> !pet.getTipoPelo().equals("L")).collect(Collectors.toList());
		}
		
		
		if (!petFiltro.isBrincalhao()) {
			pets = pets.stream().filter(pet -> !pet.getPersonalidade().equals("B")).collect(Collectors.toList());
		}
		if (!petFiltro.isCalmo()) {
			pets = pets.stream().filter(pet -> !pet.getPersonalidade().equals("C")).collect(Collectors.toList());
		}
		
		if (!petFiltro.isMacho()) {
			pets = pets.stream().filter(pet -> !pet.getSexo().equals("M")).collect(Collectors.toList());
		}
		if (!petFiltro.isFemea()) {
			pets = pets.stream().filter(pet -> !pet.getSexo().equals("F")).collect(Collectors.toList());
		}
		
		
		if (!petFiltro.isPequeno()) {
			pets = pets.stream().filter(pet -> !pet.getPorte().equals("P")).collect(Collectors.toList());
		}
		if (!petFiltro.isMedio()) {
			pets = pets.stream().filter(pet -> !pet.getPorte().equals("M")).collect(Collectors.toList());
		}
		if (!petFiltro.isGrande()) {
			pets = pets.stream().filter(pet -> !pet.getPorte().equals("G")).collect(Collectors.toList());
		}		
		
		return pets;
	}

}
