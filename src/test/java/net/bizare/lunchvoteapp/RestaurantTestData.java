package net.bizare.lunchvoteapp;

import net.bizare.lunchvoteapp.matcher.ModelMatcher;
import net.bizare.lunchvoteapp.model.Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static net.bizare.lunchvoteapp.model.BaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final ModelMatcher<Restaurant> MATCHER = new ModelMatcher<>();

    public static final int RESTAURANT1_ID = START_SEQ + 2;
    public static final int NON_EXISTED_RESTAURANT_ID = 100;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Ristorante Isolabella", 7, LocalDate.of(2016, Month.DECEMBER, 31));
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Ristorante Da Agostino", 13, LocalDate.of(2016, Month.DECEMBER, 31));
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Trattoria Pizzeria SPQR", 9, LocalDate.of(2016, Month.DECEMBER, 31));

    public static final Restaurant RESTAURANT22 = new Restaurant(RESTAURANT1_ID + 19, "Ресторан MAFIA", 0, LocalDate.now(ZoneId.of("UTC+02:00")));
    public static final Restaurant RESTAURANT23 = new Restaurant(RESTAURANT1_ID + 20, "Ресторан Panorama Lounge", 0, LocalDate.now(ZoneId.of("UTC+02:00")));

    public static final LocalDateTime PERMISSIBLE_DATE_TIME = LocalDateTime.of(2016, Month.DECEMBER, 31, 10, 0);
    public static final LocalDateTime NON_PERMISSIBLE_DATE_TIME = LocalDateTime.of(2016, Month.DECEMBER, 31, 12, 0);

    public static List<Restaurant> getSortedRestaurants(List<Restaurant> restaurants) {
        return restaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getVisitDate).reversed())
                .collect(Collectors.toList());
    }

    public static Restaurant getCreated() {
        return new Restaurant(null, "Created restaurant", 0, LocalDate.of(2016, Month.DECEMBER, 31));
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Updated restaurant", 0, LocalDate.of(2016, Month.DECEMBER, 31));
    }
}