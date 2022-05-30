package com.example.questionmark.repository;

import com.example.questionmark.domain.Pet;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Profile("jpa")
@Repository
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PetJpaRepository implements PetRepository {

    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<Pet> findAllByColour(String colour) {
        return entityManager
                .createNativeQuery("select * from pet where (characteristics -> 'colours')\\:\\:jsonb \\?\\? :colour", Pet.class)
                .setParameter("colour", colour)
                .getResultList();

    }

}
