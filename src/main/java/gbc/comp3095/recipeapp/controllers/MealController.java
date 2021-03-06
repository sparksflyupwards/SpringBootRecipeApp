/*********************************************************************************
 * Project: < COMP3095_Assignment1 >
 * Assignment: < assignment 1 >
 * Author(s): < Fred Pedersen >
 * Student Number: < 101378456 >
 * Date: 07-11-2021
 * Description: The PlannedMealController make sure that the right data from the model layer is being parsed to the right html page
 *********************************************************************************/
/*********************************************************************************
 * Project: < COMP3095_Assignment2 >
 * Assignment: < assignment 2 >
 * Author(s): < Gustavo Beltran >
 * Student Number: < 101225087 >
 * Date: 12-05-2021
 * Description: Split the controller into Create Meal and Edit Meal Controllers
 *********************************************************************************/
package gbc.comp3095.recipeapp.controllers;

import gbc.comp3095.recipeapp.models.Meal;
import gbc.comp3095.recipeapp.models.Recipe;
import gbc.comp3095.recipeapp.models.User;
import gbc.comp3095.recipeapp.services.Implementations.meal.MealServiceImpl;
import gbc.comp3095.recipeapp.services.Implementations.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class MealController {

    private final MealServiceImpl mealService;
    private final UserServiceImpl userService;


    public MealController(MealServiceImpl mealService, UserServiceImpl userService) {
        this.mealService = mealService;
        this.userService = userService;
    }

    @RequestMapping("/meals")
    public String getMeals(Model model){
        model.addAttribute("meals", mealService.findAll());
        model.addAttribute("meal", new Meal());
        User user = userService.findById(1L).get();
        model.addAttribute("userRecipes", user.getRecipes());
        Set<Recipe> selectedRecipes = new HashSet<Recipe>();
        model.addAttribute("selectedRecipes", selectedRecipes);
        return "meals/list";
    }

    @PostMapping("/editmeal/{id}")
    public String editMeal(@ModelAttribute Meal meal, @PathVariable("id") String pathId, Model model) {
        model.addAttribute("meal", meal);
        User user = userService.findById(1L).get();
        model.addAttribute("recipes", user.getRecipes());
        try{
            mealService.updateMeal(meal, user);
        }
        catch (Exception exception){
            throw new RuntimeException("failed to save meal");
        }
        return "redirect:/meals";
    }

    @PostMapping(value = "/addmeal")
    public String addMeal(@ModelAttribute Meal meal, Model model) {
        model.addAttribute("meal", meal);
        User user = userService.findById(1L).get();
        userService.createMeal(user, meal);
        return "redirect:/meals";
    }

}
