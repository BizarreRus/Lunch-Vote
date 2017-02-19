package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.model.Restaurant;
import net.bizare.lunchvoteapp.service.MenuService;
import net.bizare.lunchvoteapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

@Controller
class RestaurantController {
    private static final String CREATE_OR_UPDATE_RESTAURANT_FORM = "createOrUpdateRestaurantForm";

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/restaurants/new", method = RequestMethod.GET)
    public String create(Map<String, Object> model) {
        Restaurant restaurant = new Restaurant();
        restaurant.setVisitDate(LocalDate.now());
        model.put("restaurant", restaurant);
        return CREATE_OR_UPDATE_RESTAURANT_FORM;
    }


    @RequestMapping(value = "/restaurants/{restaurantId}/edit", method = RequestMethod.GET)
    public String update(@PathVariable("restaurantId") int restaurantId, Model model) {
        model.addAttribute(addTodayAttr(restaurantId, model));
        return CREATE_OR_UPDATE_RESTAURANT_FORM;
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.POST)
    public String createOrUpdateRestaurant(@Valid Restaurant restaurant, BindingResult result, Model model) {
        if (result.hasErrors()) {
            if (restaurant.getId() != null) {
                restaurant.setMenus(new HashSet<>(menuService.getAll(restaurant.getId())));
                addTodayAttr(restaurant.getId(), model);
            }
            return CREATE_OR_UPDATE_RESTAURANT_FORM;
        }
        if (restaurant.isNew()) {
            restaurantService.save(restaurant);
        } else {
            restaurantService.update(restaurant);
        }
        return "redirect:/restaurants";

    }

    private Restaurant addTodayAttr(int restaurantId, Model model) {
        Restaurant restaurant = restaurantService.get(restaurantId);
        if (restaurant.getVisitDate().equals(LocalDate.now())) {
            model.addAttribute("today", true);
        }
        return restaurant;
    }

    @RequestMapping(value = "/restaurants/history", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("restaurants", restaurantService.getAll());
        model.addAttribute("history", true);
        model.addAttribute("today", LocalDate.now());
        return "restaurants";
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public String getAllOfToday(Map<String, Object> model) {
        Collection<Restaurant> restaurants = restaurantService.getAllOfToday(LocalDate.now());
        model.put("restaurants", restaurants);
        return "restaurants";
    }
}
