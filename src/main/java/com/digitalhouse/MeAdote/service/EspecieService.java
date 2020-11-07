package com.digitalhouse.MeAdote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalhouse.MeAdote.model.Especie;
import com.digitalhouse.MeAdote.repository.EspecieRepository;

@Service
public class EspecieService extends BaseService<Especie> {

	@Autowired
	public EspecieService(EspecieRepository repository) {
		super(repository);
	}

	@Override
	public Especie update(Especie novo) {
		Especie antigo = this.findById(novo.getId());
		
		antigo.setNome(novo.getNome());
		
		return this.repository.save(antigo);
	}

}
