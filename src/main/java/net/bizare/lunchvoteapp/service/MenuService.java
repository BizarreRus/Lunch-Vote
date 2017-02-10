package net.bizare.lunchvoteapp.service;

import net.bizare.lunchvoteapp.model.Menu;

import java.util.Collection;

public interface MenuService {
    Menu save(Menu menu, int restaurantId, int userId);

    Menu update(Menu menu, int restaurantId, int userId);

    void delete(int id, int restaurantId, int userId);

    void deleteAll(int restaurantId, int userId);

    Menu get(int id, int restaurantId, int userId);

    Collection<Menu> getAll(int restaurantId, int userId);
}
