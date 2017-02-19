package net.bizare.lunchvoteapp.web;

import net.bizare.lunchvoteapp.AuthorizedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:/restaurants";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied(Principal user) {
        ModelAndView model = new ModelAndView();
        if (user != null) {
            model.addObject("errorMessage", "Hi " + AuthorizedUser.get().getUserTo().getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("errorMessage",
                    "You do not have permission to access this page!");
        }
        model.setViewName("error");
        return model;
    }
}
