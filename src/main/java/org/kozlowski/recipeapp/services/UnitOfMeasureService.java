package org.kozlowski.recipeapp.services;

import org.kozlowski.recipeapp.commands.UnitOfMeasureCommand;
import org.kozlowski.recipeapp.domain.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> findAll();
}
