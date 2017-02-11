package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.model.Restaurant;
import net.bizare.lunchvoteapp.service.MenuService;
import net.bizare.lunchvoteapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import static net.bizare.lunchvoteapp.util.ValidationUtil.checkNew;
import static net.bizare.lunchvoteapp.util.ValidationUtil.checkIdConsistent;

@Controller
class RestaurantController {
    private static final String CREATE_OR_UPDATE_RESTAURANT_FORM = "createOrUpdateRestaurantForm";
    private static final int ADMIN_ID = 2;

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
        model.addAttribute(restaurantService.get(restaurantId, ADMIN_ID));
        return CREATE_OR_UPDATE_RESTAURANT_FORM;
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.POST)
    public String createOrUpdateRestaurant(@Valid Restaurant restaurant, BindingResult result) {
        if (result.hasErrors()) {
            if (restaurant.getId() != null) {
                restaurant.setMenus(new HashSet<>(menuService.getAll(restaurant.getId(), ADMIN_ID)));
            }
            return CREATE_OR_UPDATE_RESTAURANT_FORM;
        } else {
            if (restaurant.isNew()) {
                restaurantService.save(restaurant, ADMIN_ID);
            } else {
                restaurantService.update(restaurant, ADMIN_ID);
            }
            return "redirect:/restaurants";
        }
    }

    @RequestMapping(value = "/restaurants/history", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("restaurants", restaurantService.getAll(ADMIN_ID));
        return "restaurants";
    }

    //        todo delete mock data
    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public String getAllOfToday(Map<String, Object> model) {
        LocalDate today = LocalDate.of(2016, Month.DECEMBER, 31);
//        LocalDate today = LocalDate.now();
        Collection<Restaurant> restaurants = restaurantService.getAllOfToday(today);
        model.put("restaurants", restaurants);
        return "restaurants";
    }
}
