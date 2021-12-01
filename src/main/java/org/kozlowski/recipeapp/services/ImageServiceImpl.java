package org.kozlowski.recipeapp.services;

import lombok.extern.slf4j.Slf4j;
import org.kozlowski.recipeapp.domain.Recipe;
import org.kozlowski.recipeapp.exceptions.NotFoundException;
import org.kozlowski.recipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isPresent()) {

            try {
                Recipe recipe = recipeOptional.get();
                Byte[] byteObjects = new Byte[file.getBytes().length];

                for (int i = 0; i < byteObjects.length; i++) {
                    byteObjects[i] = file.getBytes()[i];
                }
                recipe.setImage(byteObjects);
                recipeRepository.save(recipe);

            } catch (IOException e) {
                e.printStackTrace();

                //todo handle better
                log.error("Error occurred", e);
            }

        } else {
            log.error("Recipe for id :" + recipeOptional + " not found");
            throw new NotFoundException("Recipe for id :" + recipeOptional + " not found");
        }

    }
}
