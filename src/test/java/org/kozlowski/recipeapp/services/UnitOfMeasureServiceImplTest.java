package org.kozlowski.recipeapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kozlowski.recipeapp.commands.UnitOfMeasureCommand;
import org.kozlowski.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import org.kozlowski.recipeapp.domain.UnitOfMeasure;
import org.kozlowski.recipeapp.repositories.UnitOfMeasureRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UnitOfMeasureServiceImplTest {

    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        unitOfMeasureService= new UnitOfMeasureServiceImpl(unitOfMeasureRepository,
                unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void findAll() {
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId(1L);
        unitOfMeasures.add(unitOfMeasure1);

        UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure2.setId(2L);
        unitOfMeasures.add(unitOfMeasure2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        Set<UnitOfMeasureCommand> unitOfMeasureCommandSet = unitOfMeasureService.findAll();

        assertEquals(unitOfMeasureCommandSet.size(),2);
        verify(unitOfMeasureRepository, times(1)).findAll();
        verify(unitOfMeasureRepository, never()).findById(anyLong());

    }
}