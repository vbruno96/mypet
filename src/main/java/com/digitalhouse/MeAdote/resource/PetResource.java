package com.digitalhouse.MeAdote.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalhouse.MeAdote.model.Pet;
import com.digitalhouse.MeAdote.service.PetService;


@RestController
@RequestMapping("/pets")
public class PetResource extends BaseResource<Pet> {

	public PetResource(PetService service) {
		super(service);
	}

}
