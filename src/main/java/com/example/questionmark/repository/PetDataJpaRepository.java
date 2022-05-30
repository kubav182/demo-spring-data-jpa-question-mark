package com.example.questionmark.repository;

import com.example.questionmark.domain.Pet;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("data-jpa")
@Repository
public interface PetDataJpaRepository extends JpaRepository<Pet, Long>, PetRepository {

    @Query(value = "select * from pet where (characteristics -> 'colours')\\:\\:jsonb \\?\\? :colour", nativeQuery = true)
    List<Pet> findAllByColour(@Param("colour") String colour);

}
