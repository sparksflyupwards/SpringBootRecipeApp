/*********************************************************************************
 * Project: < COMP3095_Assignment1 >
 * Assignment: < assignment 1 >
 * Author(s): < Saad Khan >
 * Student Number: < 101157307 >
 * Date: 07-11-2021
 * Description: Interface for recipe service includes update method defintion
 *********************************************************************************/
package gbc.comp3095.recipeapp.services.Interfaces.recipe;

import gbc.comp3095.recipeapp.models.Ingredient;
import gbc.comp3095.recipeapp.models.Step;
import gbc.comp3095.recipeapp.models.Recipe;
import gbc.comp3095.recipeapp.services.Interfaces.CrudService;

import java.util.Set;

public interface RecipeService extends CrudService<Recipe, Long> {
    Iterable<Recipe> findByTitle(String searchQuery);

    //TODO:Convert to directions to description
    void updateRecipeTitleDirections(Long id, String newTitle, String newDirections, Set<Ingredient> newIngedients, Set<Step> newSteps);

}
