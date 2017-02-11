package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.model.Dish;
import net.bizare.lunchvoteapp.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("restaurants/{restaurantId}/menus/{menuId}/dishes")
class DishController {
    private static final String CREATE_OR_UPDATE_DISH_FORM = "createOrUpdateDishForm";
    private static final int ADMIN_ID = 2;

    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String create(@PathVariable("restaurantId") int restaurantId,
                         @PathVariable("menuId") int menuId, Model model) {
        Dish dish = new Dish();
        model.addAttribute("dish", dish);
        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("menuId", menuId);
        return CREATE_OR_UPDATE_DISH_FORM;
    }

    @RequestMapping(value = "/{dishId}/edit", method = RequestMethod.GET)
    public String update(@PathVariable("restaurantId") int restaurantId,
                         @PathVariable("menuId") int menuId,
                         @PathVariable("dishId") int dishId, Model model) {
        Dish dish = this.dishService.get(dishId, menuId, ADMIN_ID);
        model.addAttribute("dish", dish);
        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("menuId", menuId);
        return CREATE_OR_UPDATE_DISH_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createOrUpdateMenu(@Valid Dish dish, BindingResult result,
                                     @PathVariable("restaurantId") int restaurantId,
                                     @PathVariable("menuId") int menuId) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_DISH_FORM;
        } else {
            if (dish.isNew()) {
                dishService.save(dish, restaurantId, menuId, ADMIN_ID);
            } else {
                dishService.update(dish, restaurantId, menuId, ADMIN_ID);
            }
            return "redirect:/restaurants/" + restaurantId + "/menus/" + menuId + "/edit";
        }
    }

    @RequestMapping(value = "/deleteAll")
    public String deleteAll(@PathVariable("restaurantId") int restaurantId,
                            @PathVariable("menuId") int menuId) {
        dishService.deleteAll(menuId, ADMIN_ID);
        return "redirect:/restaurants/" + restaurantId + "/menus/" + menuId + "/edit";
    }
}
