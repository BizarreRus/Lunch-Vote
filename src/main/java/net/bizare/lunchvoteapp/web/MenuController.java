package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.model.Menu;
import net.bizare.lunchvoteapp.model.Restaurant;
import net.bizare.lunchvoteapp.service.DishService;
import net.bizare.lunchvoteapp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import static net.bizare.lunchvoteapp.util.ValidationUtil.checkNew;
import static net.bizare.lunchvoteapp.util.ValidationUtil.checkIdConsistent;

@Controller
@RequestMapping("/restaurants/{restaurantId}/menus")
class MenuController {
    private static final String CREATE_OR_UPDATE_MENU_FORM = "createOrUpdateMenuForm";
    private static final int ADMIN_ID = 2;
    @Autowired
    private MenuService menuService;
    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String create(@PathVariable("restaurantId") int restaurantId, Model model) {
        Menu menu = new Menu();
        model.addAttribute("menu", menu);
        model.addAttribute("restaurantId", restaurantId);
        return CREATE_OR_UPDATE_MENU_FORM;
    }

    @RequestMapping(value = "/{menuId}/edit", method = RequestMethod.GET)
    public String update(@PathVariable("menuId") int menuId,
                         @PathVariable("restaurantId") int restaurantId, Model model) {
        Menu menu = menuService.get(menuId, restaurantId, ADMIN_ID);
        model.addAttribute("menu", menu);
        model.addAttribute("restaurantId", restaurantId);
        return CREATE_OR_UPDATE_MENU_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createOrUpdateMenu(@Valid Menu menu, BindingResult result,
                                     @PathVariable("restaurantId") int restaurantId) {
        if (result.hasErrors()) {
            if (menu.getId() != null) {
                menu.setDishes(new HashSet<>(dishService.getAll(menu.getId(), ADMIN_ID)));
            }
            return CREATE_OR_UPDATE_MENU_FORM;
        } else {
            if (menu.isNew()) {
                menuService.save(menu, restaurantId, ADMIN_ID);
            } else {
                menuService.update(menu, restaurantId, ADMIN_ID);
            }
            return "redirect:/restaurants/" + restaurantId + "/edit";
        }
    }

    @RequestMapping(value = "/deleteAll")
    public String deleteAll(@PathVariable("restaurantId") int restaurantId) {
        menuService.deleteAll(restaurantId, ADMIN_ID);
        return "redirect:/restaurants/" + restaurantId + "/edit/";
    }
}
