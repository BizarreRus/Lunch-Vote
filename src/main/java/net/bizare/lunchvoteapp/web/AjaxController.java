package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.service.DishService;
import net.bizare.lunchvoteapp.service.MenuService;
import net.bizare.lunchvoteapp.service.RestaurantService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;

@RestController
public class AjaxController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private DishService dishService;

    private static final int ADMIN_ID = 2;

    //        todo delete mock data
    @GetMapping(value = "/restaurants/{id}/vote", produces = MediaType.APPLICATION_JSON_VALUE)
    public String vote(@PathVariable("id") int id) {
        LocalDateTime now = LocalDateTime.of(2016, Month.DECEMBER, 31, 10, 0);
//        LocalDateTime now = LocalDateTime.now();
        JSONObject obj = new JSONObject();
        Integer unvotedId = restaurantService.vote(id, ADMIN_ID, now);
        obj.put("unVotedId", unvotedId);
        return unvotedId == null ? null : obj.toString();
    }

    @DeleteMapping(value = "/restaurants/{restaurantId}")
    public void deleteRestaurant(@PathVariable("restaurantId") Integer restaurantId) {
        restaurantService.delete(restaurantId, ADMIN_ID);
    }

    @DeleteMapping(value = "/restaurants/{restaurantId}/menus/{menuId}")
    public void deleteMenu(@PathVariable("restaurantId") Integer restaurantId,
                           @PathVariable("menuId") Integer menuId) {
        menuService.delete(menuId, restaurantId, ADMIN_ID);
    }

    @DeleteMapping(value = "/restaurants/*/menus/{menuId}/dishes/{dishId}")
    public void deleteDish(@PathVariable("menuId") Integer menuId,
                           @PathVariable("dishId") Integer dishId) {
        dishService.delete(dishId, menuId, ADMIN_ID);
    }
}
