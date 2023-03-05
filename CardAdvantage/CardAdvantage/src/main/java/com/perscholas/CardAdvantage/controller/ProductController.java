package com.perscholas.CardAdvantage.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.perscholas.CardAdvantage.dto.ProductDto;
import com.perscholas.CardAdvantage.service.ProductService;

@Controller
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/admin/products")
	public String products(Model model) {
		List<ProductDto> products = productService.findAllProducts();
		model.addAttribute("products", products);
		return "/admin/products";
	}

}
