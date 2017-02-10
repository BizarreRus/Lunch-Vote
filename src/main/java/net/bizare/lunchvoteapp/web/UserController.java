package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.model.Role;
import net.bizare.lunchvoteapp.model.User;
import net.bizare.lunchvoteapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

import static net.bizare.lunchvoteapp.util.ValidationUtil.checkNew;
import static net.bizare.lunchvoteapp.util.ValidationUtil.checkIdConsistent;

@Controller
class UserController {
    private static final int ADMIN_ID = 2;
    private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "createOrUpdateUserForm";
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users/new", method = RequestMethod.GET)
    public String create(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/users/new", method = RequestMethod.POST)
    public String create(@Valid User user, BindingResult result) {
        checkNew(user);
        if (result.hasErrors()) {
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        } else {
            this.userService.save(user);
            return "redirect:/users/" + user.getId();
        }
    }

    //todo realize functionality?
    /*@RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAll(Map<String, Object> model) {
        Collection<User> users = this.userService.getAll();
        model.put("users", users);
        return "users/usersList";
    }*/

    @RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.GET)
    public String update(@PathVariable("userId") int userId, Model model) {

        User user = this.userService.get(userId);
        model.addAttribute(user);
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/users/{userId}/edit", method = RequestMethod.POST)
    public String update(@Valid User user, BindingResult result, @PathVariable("userId") int userId) {
        checkIdConsistent(user, userId);
        if (result.hasErrors()) {
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        } else {
            user.setId(userId);
            this.userService.update(user);
            return "redirect:/users/{userId}";
        }
    }

    //todo realize usersList?
    /*@RequestMapping("/users/{userId}")
    public ModelAndView get(@PathVariable("userId") int userId) {
        ModelAndView mav = new ModelAndView("users/userDetails");
        mav.addObject(this.userService.get(userId));
        return mav;
    }*/

    @RequestMapping(value = "/users/{userId}/delete")
    public String delete(@PathVariable("userId") int userId) {
        userService.delete(userId);
        return "redirect:/users/";
    }


    //todo should be inserted id of authorized user
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model) {
        model.addAttribute("user", userService.get(ADMIN_ID));
        return "profile";
    }

    //todo authorized userId should be same of updated user. Add validation!
    @RequestMapping(value = {"/profile", "register"}, method = RequestMethod.POST)
    public String profile(@RequestParam(value = "id", required = false) Integer id,
                          @RequestParam("username") String name,
                          @RequestParam("password") String password,
                          @RequestParam("email") String email) {
        User user = new User(id, name, password, email);
        if (user.isNew()) {
            userService.save(user);
        } else {
            userService.update(user);
        }
        return "redirect:/login";
    }
}
