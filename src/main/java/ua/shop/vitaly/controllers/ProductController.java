package ua.shop.vitaly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.shop.vitaly.services.Product.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/product", method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("index.jsp");
		return modelAndView;
	}
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/";
	}
	
}
