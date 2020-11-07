package com.digitalhouse.MeAdote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalhouse.MeAdote.model.Role;
import com.digitalhouse.MeAdote.repository.RoleRepository;

@Service
public class RoleService extends BaseService<Role> {
	
	@Autowired
	public RoleService(RoleRepository repository) {
		super(repository);
	}

	@Override
	public Role update(Role novo) {
		Role antigo = this.findById(novo.getId());

		antigo.setName(novo.getName());
		
		return this.repository.save(antigo);
	}

}
