package ua.shop.vitaly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ua.shop.vitaly.services.Product.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	
}