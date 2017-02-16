package net.bizare.lunchvoteapp.service;

import net.bizare.lunchvoteapp.model.Menu;

import java.util.Collection;

public interface MenuService {
    Menu save(Menu menu, int restaurantId);

    Menu update(Menu menu, int restaurantId);

    void delete(int id, int restaurantId);

    void deleteAll(int restaurantId);

    Menu get(int id, int restaurantId);

    Collection<Menu> getAll(int restaurantId);
}
