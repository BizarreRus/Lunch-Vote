package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.AuthorizedUser;
import net.bizare.lunchvoteapp.service.UserService;
import net.bizare.lunchvoteapp.to.UserTo;
import net.bizare.lunchvoteapp.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import javax.validation.Valid;

@Controller
class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("userTo", new UserTo());
        return "profile";
    }

    @PostMapping(value = "/register")
    public String register(@Valid UserTo userTo, BindingResult result) {
        if (result.hasErrors()) {
            return "profile";
        } else {
            try {
                userService.save(UserUtil.createNewFromTo(userTo));
            } catch (PersistenceException e) {
                result.rejectValue("email", null, "данный ящик уже используется");
                return "profile";
            }
            return "redirect:login?username=" + userTo.getEmail();
        }
    }

    @GetMapping(value = "/profile")
    public ModelAndView profile(ModelAndView modelAndView) {
        modelAndView.setViewName("profile");
        AuthorizedUser authorizedUser = AuthorizedUser.safeGet();
        if (authorizedUser != null) {
            UserTo userTo = authorizedUser.getUserTo();
            userTo.setPassword("");
            modelAndView.getModelMap().addAttribute("userTo", userTo);
        }
        return modelAndView;
    }

    @PostMapping(value = "/profile")
    public String profile(@Valid UserTo userTo, BindingResult result) {
        if (result.hasErrors()) {
            return "profile";
        }
        try {
            userService.update(userTo);
        } catch (DataIntegrityViolationException e) {
            result.rejectValue("email", null, "данный ящик уже используется");
            return "profile";
        }
        AuthorizedUser.get().update(userTo);
        return "redirect:/restaurants";
    }
}
