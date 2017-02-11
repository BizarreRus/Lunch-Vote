package net.bizare.lunchvoteapp.web;

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
    private static final int ADMIN_ID = 2;

    @DeleteMapping(value = "/restaurants/{id}")
    public void deleteRestaurant(@PathVariable("id") Integer id) {
        restaurantService.delete(id, ADMIN_ID);
    }

    //        todo delete mock data
    @GetMapping(value = "/restaurants/{id}/vote")
    public String vote(@PathVariable("id") int id) {
        LocalDateTime now = LocalDateTime.of(2016, Month.DECEMBER, 31, 10, 0);
//        LocalDateTime now = LocalDateTime.now();
        JSONObject obj = new JSONObject();
        Integer unvotedId = restaurantService.vote(id, ADMIN_ID, now);
        if (unvotedId != null) {
            obj.put("unVotedId", unvotedId);
            return obj.toString();
        }
        return null;
    }
}
