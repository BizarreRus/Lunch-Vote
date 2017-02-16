package net.bizare.lunchvoteapp;

import net.bizare.lunchvoteapp.matcher.ModelMatcher;
import net.bizare.lunchvoteapp.model.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DishTestData {
    public static final ModelMatcher<Dish> MATCHER = new ModelMatcher<>();

    public static final int DISH1_ID = 10;
    public static final int NON_EXISTED_DISH_ID = 100;

    public static final Dish DISH1 = new Dish(DISH1_ID, "Paccheri al ragù di cernia", 252.0);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "Pesce spada alla siciliana", 336.0);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Piselli stufati con pancetta", 98.0);

    private static final Dish DISH4 = new Dish(DISH1_ID + 3, "Linguine al nero di seppia", 336.0);
    private static final Dish DISH5 = new Dish(DISH1_ID + 4, "Involtini di spada al sugo con capperi", 448.0);
    private static final Dish DISH6 = new Dish(DISH1_ID + 5, "Patate novelle al forno", 84.0);

    public static final Dish DISH7 = new Dish(DISH1_ID + 6, "Spaghetti alla carbonara", 224.0);
    public static final Dish DISH8 = new Dish(DISH1_ID + 7, "Bocconcini di tacchino e pancetta", 420.0);
    public static final Dish DISH9 = new Dish(DISH1_ID + 8, "Insalata capricciosa", 84.0);

    private static final Dish DISH10 = new Dish(DISH1_ID + 9, "Crocchette di patate e salmone con salsa allo yogurt", 168.0);
    private static final Dish DISH11 = new Dish(DISH1_ID + 10, "Lasagne con scampi pomodorini e pesto di fave", 364.0);
    private static final Dish DISH12 = new Dish(DISH1_ID + 11, "Baba' al rum", 154.0);

    public static List<Dish> getSortedDishes(List<Dish> dishes) {
        return dishes.stream()
                .sorted(Comparator.comparing(Dish::getName))
                .collect(Collectors.toList());
    }

    public static Dish getCreated() {
        return new Dish(null, "Created dish", 30.0);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Updated dish", 20.0);
    }
}