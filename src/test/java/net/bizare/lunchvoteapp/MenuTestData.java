package net.bizare.lunchvoteapp;

import net.bizare.lunchvoteapp.matcher.ModelMatcher;
import net.bizare.lunchvoteapp.model.Menu;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MenuTestData {
    public static final ModelMatcher<Menu> MATCHER = new ModelMatcher<>();

    public static final int MENU_ID1 = 6;
    public static final int NON_EXISTED_MENU_ID = 100;

    public static final Menu MENU1 = new Menu(MENU_ID1, "Menu turistico");
    public static final Menu MENU2 = new Menu(MENU_ID1 + 1, "Menu di pesce");
    public static final Menu MENU3 = new Menu(MENU_ID1 + 2, "Menu di carne");
    public static final Menu MENU4 = new Menu(MENU_ID1 + 3, "Menu del giorno");

    public static List<Menu> getSortedMenus(List<Menu> menus) {
        return menus.stream()
                .sorted(Comparator.comparing(Menu::getName))
                .collect(Collectors.toList());
    }

    public static Menu getCreated() {
        return new Menu(null, "Created menu");
    }

    public static Menu getUpdated() {
        return new Menu(MENU_ID1, "Updated menu");
    }
}