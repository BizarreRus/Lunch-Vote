package net.bizare.lunchvoteapp.repository;

import net.bizare.lunchvoteapp.model.Restaurant;

import java.time.LocalDate;
import java.util.Collection;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    boolean deleteAll();

    Restaurant get(int id);

    Collection<Restaurant> getAll();

    Collection<Restaurant> getAll(LocalDate date);
}
