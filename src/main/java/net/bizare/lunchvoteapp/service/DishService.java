package net.bizare.lunchvoteapp.service;

import net.bizare.lunchvoteapp.model.Dish;

import java.util.Collection;

public interface DishService {
    Dish save(Dish dish, int restaurantId, int menuId);

    Dish update(Dish dish, int restaurantId, int menuId);

    void delete(int id, int menuId);

    void deleteAll(int menuId);

    Dish get(int id, int menuId);

    Collection<Dish> getAll(int menuId);
}
