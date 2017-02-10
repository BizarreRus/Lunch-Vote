package net.bizare.lunchvoteapp.repository;

import net.bizare.lunchvoteapp.model.Dish;

import java.util.Collection;

public interface DishRepository {

    Dish save(Dish dish, int menuId);

    boolean delete(int id, int menuId);

    boolean deleteAll(int menuId);

    Dish get(int id, int menuId);

    Collection<Dish> getAll(int menuId);
}
