package net.bizare.lunchvoteapp;

import net.bizare.lunchvoteapp.matcher.ModelMatcher;
import net.bizare.lunchvoteapp.model.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
public class DishTestData {
    public static final ModelMatcher<Dish> MATCHER = new ModelMatcher<>();

    public static final int DISH1_ID = 10;
    public static final int NONEXISTED_DISH_ID = 100;

    public static final Dish DISH1 = new Dish(DISH1_ID, "Котлета", 15.0);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "Пюре", 7.0);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Подлива", 2.5);
    private static final Dish DISH4 = new Dish(DISH1_ID + 3, "Овсянка", 4.0);
    private static final Dish DISH5 = new Dish(DISH1_ID + 4, "Мясной стейк", 9.0);
    private static final Dish DISH6 = new Dish(DISH1_ID + 5, "Супчик", 5.0);

    public static List<Dish> getSortedDishes(List<Dish> dishes) {
        return dishes.stream()
                .sorted(Comparator.comparing(Dish::getName))
                .collect(Collectors.toList());
    }

    public static final List<Dish> DISHES = Arrays.asList(DISH1, DISH2, DISH3, DISH4, DISH5, DISH6);

    public static Dish getCreated() {
        return new Dish(null, "Созданное блюдо", 30.0);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Обновленное блюдо", 20.0);
    }
}