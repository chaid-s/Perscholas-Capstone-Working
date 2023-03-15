package com.perscholas.CardAdvantage.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.perscholas.CardAdvantage.dto.ProductDto;
import com.perscholas.CardAdvantage.dto.UserProductDto;
import com.perscholas.CardAdvantage.service.ProductService;
import com.perscholas.CardAdvantage.service.UserProductService;

@Controller
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
		return "guest/userproducts";
	}
	
	@GetMapping("guest/userproducts/newproduct")
	public String newProductForm(Model model) {
		model.addAttribute("userproduct", new UserProductDto());
		return "/guest/create_product";
	}
	
	// handler for submitting new products
	@PostMapping("/guest/userproducts")
	public String createPost(@ModelAttribute("userproduct") UserProductDto userProductDto, 
		@RequestParam("file") MultipartFile file, BindingResult result, Model model) throws IOException {
			
		if (result.hasErrors()) {
			model.addAttribute("userproduct", userProductDto);
			return "admin/create_product";
		}
		userProductDto.setVerificationPicture(Base64.getEncoder().encodeToString(file.getBytes()));
		userProductDto.setInCart(false);
		userProductDto.setPurchased(false);
			
		userProductService.createUserProduct(userProductDto);
		return "redirect:/admin/products";
	}

}
