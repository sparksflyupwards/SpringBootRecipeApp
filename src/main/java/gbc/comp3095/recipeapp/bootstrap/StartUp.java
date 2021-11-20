/*********************************************************************************
 * Project: < COMP3095_Assignment1 >
 * Assignment: < assignment 1 >
 * Author(s): < Fred Pedersen , Saad Khan >
 * Student Number: < 101378456 , 101157307 >
 * Date: 07-11-2021
 * Description: Loads default data for the application
 *********************************************************************************/

package gbc.comp3095.recipeapp.bootstrap;

import gbc.comp3095.recipeapp.models.Meal;
import gbc.comp3095.recipeapp.models.Recipe;
import gbc.comp3095.recipeapp.models.User;
import gbc.comp3095.recipeapp.services.Implementations.meal.MealServiceImpl;
import gbc.comp3095.recipeapp.services.Implementations.recipe.RecipeServiceImpl;
import gbc.comp3095.recipeapp.services.Implementations.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class StartUp implements CommandLineRunner {

    private UserServiceImpl userService;
    private RecipeServiceImpl recipeService;
    private MealServiceImpl mealService;

    @Autowired
    public StartUp(UserServiceImpl userService, RecipeServiceImpl recipeService, MealServiceImpl mealService) {
        this.userService = userService;
        this.recipeService = recipeService;
        this.mealService = mealService;
    }

    public void generateRecipes(User user, int numRecipes){

        //TODO: add more adjectives, items, garnishes
        String [] adjectives = {"Orange" , "Pink" , "Seared" , "Toasted" , "Sauted", "Peeled", "Boiled", "Aged"};
        String [] items = {"Chicken", "Tea", "Cake", "Salmon", "Beef", "Ceviche", "Mink", "Curry" , "Quesadilla"};
        String [] garnishes = {"BBQ Sauce", "Icing Sugar" , "Chives", "Oregano", "Pink Salk" , "Nutritional Yeast" , "Maple Syrup" , "Gravy"};

        for(int i=0; i<numRecipes; i++){
            String recipeName = "";
            recipeName += adjectives[(int) Math.floor(Math.random()* adjectives.length)] +" ";
            recipeName += items[(int) Math.floor(Math.random()* items.length)] +" with ";
            recipeName += garnishes[(int) Math.floor(Math.random()* garnishes.length)];
            Recipe recipe = new Recipe(recipeName);
            recipe.setDirections("Make me a " + recipeName +" please");
            userService.createRecipe(user, recipe);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        /**
         * TODO: use cases to test
         * search all recipe in system by created date
         * get all meals for user within certain date range
         */

        User user1 = new User("Ryan Murphy", "12345");
        User userJ = new User("James Bond", "5432");
        User userM = new User("Marlon Brando", "password");
        User userN = new User("Nasrit Khan", "password");
        User userO = new User("Ophelia Diaz", "password");
        User userP = new User("Petro Peu", "password");

        Recipe recipe1 = new Recipe("Jam pudding");
        recipe1.setDirections("make me a jam pudding please");

        Recipe recipe2 = new Recipe("Toast Sandwich");
        recipe2.setDirections("Make me a Toast pease");

        Recipe recipeJ = new Recipe("Slime Stew");
        recipeJ.setDirections("make me a Slime Stew please");

        Recipe recipeM = new Recipe("Kashmiri Chai");
        recipeJ.setDirections("make me a Kashmiri Chai please");

        Recipe recipeN = new Recipe("Orange Lemonade");
        recipeJ.setDirections("make me a Orange Lemonade please");

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        Meal meal1 = new Meal("My first meal", today);
        Meal meal2 = new Meal("My second meal", today);
        Meal meal3 = new Meal("My third meal", today);



        userService.save(user1);
        userService.save(userJ);
        userService.save(userM);
        userService.save(userN);
        userService.save(userO);

        userService.createRecipe(user1, recipe1);
        userService.createRecipe(user1, recipe2);
        userService.createRecipe(userJ, recipeJ);
        userService.createRecipe(userM, recipeM);

        generateRecipes(user1, 6);
        generateRecipes(userJ, 4);
        generateRecipes(userM, 10);
        generateRecipes(userN, 12);
        generateRecipes(userO, 2);

        userService.createRecipe(userM, recipeN);
        userService.createMeal(user1, meal1);
        userService.createMeal(user1, meal2);
        userService.createMeal(user1, meal3);

        mealService.addRecipe(meal1, recipe1);
        userService.addFavouriteRecipe(user1, recipe2);












    }
}
