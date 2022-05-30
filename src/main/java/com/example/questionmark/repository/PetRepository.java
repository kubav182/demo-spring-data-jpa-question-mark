package com.example.questionmark.repository;

import com.example.questionmark.domain.Pet;

import java.util.List;

public interface PetRepository {

    List<Pet> findAllByColour(String colour);

}
