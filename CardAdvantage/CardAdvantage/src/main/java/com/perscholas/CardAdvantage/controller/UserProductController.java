package com.perscholas.CardAdvantage.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.perscholas.CardAdvantage.dto.ProductDto;
import com.perscholas.CardAdvantage.dto.UserProductDto;
import com.perscholas.CardAdvantage.service.ProductService;
import com.perscholas.CardAdvantage.service.UserProductService;

public class UserProductController {
	
	private UserProductService userProductService;
	
	private ProductService productService;
	
	public UserProductController(UserProductService userProductService, ProductService productService) {
		this.userProductService = userProductService;
		this.productService = productService;
	}
	
	@GetMapping("/guest/userproducts")
	public String products(Model model) {
		List<UserProductDto> userproducts = userProductService.findAllUserProducts();
		model.addAttribute("userproducts", userproducts);
		List<ProductDto> products = productService.findAllProducts();
		model.addAttribute("products", products);
		return "/guest/userproducts";
	}
	
	@GetMapping("guest/userproducts/newproduct")
	public String newProductForm(Model model) {
		model.addAttribute("userproduct", new UserProductDto());
		return "/admin/create_product";
	}

}
