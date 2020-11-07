package com.digitalhouse.MeAdote.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalhouse.MeAdote.model.Pet;

public interface PetRepository extends JpaRepository<Pet,Long>{

}
