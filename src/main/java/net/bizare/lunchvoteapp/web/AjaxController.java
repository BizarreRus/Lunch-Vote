package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.AuthorizedUser;
import net.bizare.lunchvoteapp.service.DishService;
import net.bizare.lunchvoteapp.service.MenuService;
import net.bizare.lunchvoteapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class AjaxController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private DishService dishService;

    @GetMapping(value = "/restaurants/{id}/vote", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer vote(@PathVariable("id") int id) {
        Integer unvotedId = restaurantService.vote(id, AuthorizedUser.id(), LocalDateTime.now());
        return unvotedId == null ? null : unvotedId;
    }

    @DeleteMapping(value = "/restaurants/{restaurantId}")
    public void deleteRestaurant(@PathVariable("restaurantId") Integer restaurantId) {
        restaurantService.delete(restaurantId);
    }

    @DeleteMapping(value = "/restaurants/{restaurantId}/menus/{menuId}")
    public void deleteMenu(@PathVariable("restaurantId") Integer restaurantId,
                           @PathVariable("menuId") Integer menuId) {
        menuService.delete(menuId, restaurantId);
    }

    @DeleteMapping(value = "/restaurants/*/menus/{menuId}/dishes/{dishId}")
    public void deleteDish(@PathVariable("menuId") Integer menuId,
                           @PathVariable("dishId") Integer dishId) {
        dishService.delete(dishId, menuId);
    }
}
