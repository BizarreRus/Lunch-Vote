package net.bizare.lunchvoteapp.service.impl;

import net.bizare.lunchvoteapp.model.Menu;
import net.bizare.lunchvoteapp.model.Restaurant;
import net.bizare.lunchvoteapp.repository.MenuRepository;
import net.bizare.lunchvoteapp.repository.RestaurantRepository;
import net.bizare.lunchvoteapp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;

import static net.bizare.lunchvoteapp.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Menu save(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        checkNotFoundWithId(mapEntities(menu, restaurantId), restaurantId);
        return menuRepository.save(menu);
    }

    @Override
    public Menu update(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        mapEntities(menu, restaurantId);
        get(menu.getId(), restaurantId);
        return menuRepository.save(menu);
    }

    private Restaurant mapEntities(Menu menu, int restaurantId) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        menu.setRestaurant(restaurant);
        return restaurant;
    }

    @Override
    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(menuRepository.delete(id, restaurantId), id);
    }

    @Override
    public void deleteAll(int restaurantId) {
        checkNotFoundWithId(menuRepository.deleteAll(restaurantId), restaurantId);
    }

    @Override
    public Menu get(int id, int restaurantId) {
        return checkNotFoundWithId(menuRepository.get(id, restaurantId), id);
    }

    @Override
    public Collection<Menu> getAll(int restaurantId) {
        return menuRepository.getAll(restaurantId);
    }
}
