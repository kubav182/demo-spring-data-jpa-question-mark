package com.example.questionmark.repository;

import com.example.questionmark.domain.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@Sql("classpath:/sql/test-data.sql")
@AutoConfigureTestDatabase(replace = NONE)
@SpringBootTest
@ExtendWith(SpringExtension.class)
abstract class AbstractRepositoryTest {

    @Autowired
    PetRepository petRepository;

    @Test
    void shouldFindOnePetByBlueColourTest() {
        List<Pet> bluePets = petRepository.findAllByColour("blue");
        Assertions.assertEquals(1, bluePets.size());
        Assertions.assertEquals("Paul", bluePets.get(0).getName());
        Assertions.assertEquals(1, bluePets.get(0).getId());
        Assertions.assertEquals(2, bluePets.get(0).getCharacteristics().size());
        Assertions.assertEquals("Dog", bluePets.get(0).getCharacteristics().get("species"));
        Assertions.assertEquals(List.of("blue", "yellow"), bluePets.get(0).getCharacteristics().get("colours"));
    }

}
