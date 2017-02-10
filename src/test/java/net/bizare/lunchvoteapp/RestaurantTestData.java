package net.bizare.lunchvoteapp;

import net.bizare.lunchvoteapp.matcher.ModelMatcher;
import net.bizare.lunchvoteapp.model.Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class RestaurantTestData {

    public static final ModelMatcher<Restaurant> MATCHER = new ModelMatcher<>();

    public static final int RESTAURANT1_ID = 3;
    public static final int NONEXISTED_RESTAURANT_ID = 100;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Pizzeria del cazzo", 2, LocalDate.of(2016, Month.DECEMBER, 31));
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Trattoria della fava", 10, LocalDate.of(2016, Month.DECEMBER, 31));
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "SPQR", 1, LocalDate.of(2016, Month.DECEMBER, 31));
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT1_ID + 3, "Ristorante Isolabella", 15, LocalDate.of(2016, Month.DECEMBER, 31));


    public static final LocalDateTime AUTHORIZED_DATE_TIME = LocalDateTime.of(2016, Month.DECEMBER, 31, 10, 0);
    public static final LocalDateTime FORBIDEN_DATE_TIME = LocalDateTime.of(2016, Month.DECEMBER, 31, 12, 0);
    public static final LocalDate TEST_DATE = LocalDate.of(2016, Month.DECEMBER, 31);

    public static List<Restaurant> getSortedRestaurants(List<Restaurant> restorants) {
        return restorants.stream()
                .sorted(Comparator.comparing(Restaurant::getName))
                .collect(Collectors.toList());
    }

    public static Restaurant getCreated() {
        return new Restaurant(null, "Созданный ресторан", 300, LocalDate.of(2016, Month.DECEMBER, 31));
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Обновленный ресторан", 200, LocalDate.of(2016, Month.DECEMBER, 31));
    }
}