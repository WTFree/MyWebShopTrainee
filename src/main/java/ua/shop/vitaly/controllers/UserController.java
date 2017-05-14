package ua.shop.vitaly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ua.shop.vitaly.services.User.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
}
