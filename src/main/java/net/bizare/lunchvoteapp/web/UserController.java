package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.AuthorizedUser;
import net.bizare.lunchvoteapp.service.UserService;
import net.bizare.lunchvoteapp.to.UserTo;
import net.bizare.lunchvoteapp.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
class UserController {
    @Autowired
    private UserService userService;

    //todo realize
    /*@RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAll(Map<String, Object> model) {
        Collection<User> users = this.userService.getAll();
        model.put("users", users);
        return "users/usersList";
    }*/

    //todo realize
    /*@RequestMapping("/users/{userId}")
    public ModelAndView get(@PathVariable("userId") int userId) {
        ModelAndView mav = new ModelAndView("users/userDetails");
        mav.addObject(this.userService.get(userId));
        return mav;
    }*/

    //todo realize
    /*@RequestMapping(value = "/users/{userId}/delete")
    public String delete(@PathVariable("userId") int userId) {
        userService.delete(userId);
        return "redirect:/users/";
    }*/

    @GetMapping(value = "/register")
    public String register(Model model){
        model.addAttribute("userTo", new UserTo());
        return "profile";
    }

    @GetMapping(value = "/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping(value = "/register")
    public String register(@Valid UserTo userTo, BindingResult result) {
        if (result.hasErrors()) {
            return "profile";
        }
        if (userTo.isNew()){
            userService.save(UserUtil.createNewFromTo(userTo));
            return "redirect:login?username=" + userTo.getEmail();
        } else {
            userService.update(userTo);
            AuthorizedUser.get().update(userTo);
            return "redirect:/restaurants";
        }
    }
}
