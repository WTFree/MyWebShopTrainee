package ua.shop.vitaly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.shop.vitaly.models.user.NoSuchUserException;
import ua.shop.vitaly.models.user.UserAuth;
import ua.shop.vitaly.services.User.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAuth userAuth;
	
	private static final String VIEW_INDEX = "index";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginAction(@RequestParam(required = true) String login, String password) {
        if (userAuth.login(login, password))
            return "redirect:/";
        else
            return "redirect:/" + VIEW_INDEX + "?error=No such user!!!";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutAction() {
        userAuth.logout();
        return "redirect:/";
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerAction(@RequestParam(required = true) String login, String password) {
        if (userAuth.login(login, password))
            return "redirect:/";
        else
            return "redirect:/" + VIEW_INDEX + "?error=No such user!!!";
    }
	
}
