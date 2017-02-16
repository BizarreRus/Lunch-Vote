package net.bizare.lunchvoteapp.service.impl;

import net.bizare.lunchvoteapp.model.Dish;
import net.bizare.lunchvoteapp.model.Menu;
import net.bizare.lunchvoteapp.repository.DishRepository;
import net.bizare.lunchvoteapp.repository.MenuRepository;
import net.bizare.lunchvoteapp.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

import static net.bizare.lunchvoteapp.util.ValidationUtil.*;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId, int menuId) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(mapEntities(dish, restaurantId, menuId), menuId);
        return dishRepository.save(dish, menuId);
    }

    @Override
    public Dish update(Dish dish, int restaurantId, int menuId) {
        Assert.notNull(dish, "dish must not be null");
        mapEntities(dish, restaurantId, menuId);
        get(dish.getId(), menuId);
        return dishRepository.save(dish, menuId);
    }

    private Menu mapEntities(Dish dish, int restaurantId, int menuId) {
        Menu menu = menuRepository.get(menuId, restaurantId);
        dish.setMenu(menu);
        return menu;
    }

    @Override
    public void delete(int id, int menuId) {
        checkNotFoundWithId(dishRepository.delete(id, menuId), id);
    }

    @Override
    public void deleteAll(int menuId) {
        checkNotFoundWithId(dishRepository.deleteAll(menuId), menuId);
    }

    @Override
    public Dish get(int id, int menuId) {
        return checkNotFoundWithId(dishRepository.get(id, menuId), id);
    }

    @Override
    public Collection<Dish> getAll(int menuId) {
        return dishRepository.getAll(menuId);
    }
}
