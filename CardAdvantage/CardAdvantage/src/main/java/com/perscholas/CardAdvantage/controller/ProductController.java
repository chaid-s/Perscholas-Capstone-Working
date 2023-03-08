package com.perscholas.CardAdvantage.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

	// handler for new products page
	@GetMapping("admin/products/newproduct")
	public String newProductForm(Model model) {
		model.addAttribute("product", new ProductDto());
		return "admin/create_product";
	}

	// handler for submitting new products
	@PostMapping("/admin/products")
	public String createPost(@ModelAttribute("product") ProductDto productDto, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("product", productDto);
			return "admin/create_product";
		}
		productService.createProduct(productDto);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/products/{productId}/edit")
	public String editPostForm(@PathVariable("productId") Long productId, Model model) 
	{
		ProductDto productDto = productService.findProductById(productId);
		model.addAttribute("product", productDto);
		return "admin/edit_product";

	}
	
	//handler method to handle edit product post request
	@PostMapping("/admin/products/{productId}")
	public String updateProduct(@PathVariable("productId") Long productId, 
								@ModelAttribute("product") ProductDto product,
								BindingResult result,
								Model model) {
		if(result.hasErrors()){
			model.addAttribute("product", product);
			return "admin/edit_post";
		}
		
		product.setId(productId);
		productService.updateProduct(product);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/products/{productId}/delete")
	public String deletePost(@PathVariable("productId") Long productId) {
		productService.deleteProduct(productId);
		return "redirect:/admin/products";
		
	}
	
	

}
