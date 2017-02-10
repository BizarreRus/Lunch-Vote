package net.bizare.lunchvoteapp.service;

import net.bizare.lunchvoteapp.model.Dish;

import java.util.Collection;

public interface DishService {
    Dish save(Dish dish, int restaurantId, int menuId, int userId);

    Dish update(Dish dish, int restaurantId, int menuId, int userId);

    void delete(int id, int menuId, int userId);

    void deleteAll(int menuId, int userId);

    Dish get(int id, int menuId, int userId);

    Collection<Dish> getAll(int menuId, int userId);
}
