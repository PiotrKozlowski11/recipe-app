package org.kozlowski.recipeapp.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kozlowski.recipeapp.domain.UnitOfMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
        //@DirtiesContext
    void findByDescription() {
        Optional<UnitOfMeasure> unitOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        //fail();
        assertEquals("Teaspoon", unitOptional.get().getDescription());
    }

    @Test
    void findByDescriptionCup() {
        Optional<UnitOfMeasure> unitOptional = unitOfMeasureRepository.findByDescription("Cup");

        assertEquals("Cup", unitOptional.get().getDescription());
    }
}