package net.bizare.lunchvoteapp.repository;

import net.bizare.lunchvoteapp.model.Menu;

import java.util.Collection;

public interface MenuRepository {
    Menu save(Menu menu);

    boolean delete(int id, int restaurantId);

    boolean deleteAll(int restaurantId);

    Menu get(int id, int restaurantId);

    Collection<Menu> getAll(int restaurantId);
}
