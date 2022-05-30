package com.example.questionmark.repository;


import com.example.questionmark.domain.Pet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Profile("jdbc")
@Repository
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PetJdbcRepository implements PetRepository {

    JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    @Override
    public List<Pet> findAllByColour(String colour) {
        return jdbcTemplate.query("select * from pet where (characteristics -> 'colours')::jsonb ?? ?", new PetRowMapper(), colour);
    }

    static class PetRowMapper implements RowMapper<Pet> {
        ObjectMapper objectMapper = new ObjectMapper();

        @SneakyThrows
        @Override
        public Pet mapRow(ResultSet rs, int rowNum) {
            Pet pet = new Pet();
            pet.setId(rs.getLong("id"));
            pet.setName(rs.getString("name"));
            pet.setCharacteristics(objectMapper.readValue(rs.getString("characteristics"), new TypeReference<Map<String, Object>>() {
            }));
            return pet;
        }
    }

}
