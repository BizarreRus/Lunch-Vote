package net.bizare.lunchvoteapp.service;

import net.bizare.lunchvoteapp.model.Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

public interface RestaurantService {

    Restaurant save(Restaurant restaurant, int userId);

    Restaurant update(Restaurant restaurant, int userId);

    Restaurant vote(int id, int userId, LocalDateTime localDateTime);

    void delete(int id, int userId);

    void deleteAll(int userId);

    Restaurant get(int id, int userId);

    Collection<Restaurant> getAll(int userId);

    Collection<Restaurant> getAllOfToday(LocalDate date);

}
