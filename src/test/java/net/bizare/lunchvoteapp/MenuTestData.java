package net.bizare.lunchvoteapp;

import net.bizare.lunchvoteapp.matcher.ModelMatcher;
import net.bizare.lunchvoteapp.model.Menu;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MenuTestData {
    public static final ModelMatcher<Menu> MATCHER = new ModelMatcher<>();

    public static final int MENU_ID1 = 8;
    public static final int NONEXISTED_MENU_ID = 100;

    public static final Menu MENU1 = new Menu(MENU_ID1, "Первое меню");
    public static final Menu MENU2 = new Menu(MENU_ID1 + 1, "Второе меню");


//  (8, 'Первое меню', 3),
//          (9, 'Второе меню', 3);

    public static List<Menu> getSortedMenus(List<Menu> menus) {
        return menus.stream()
                .sorted(Comparator.comparing(Menu::getName))
                .collect(Collectors.toList());
    }

    public static Menu getCreated() {
        return new Menu(null, "Созданное меню");
    }

    public static Menu getUpdated() {
        return new Menu(MENU_ID1, "Обновленное меню");
    }
}