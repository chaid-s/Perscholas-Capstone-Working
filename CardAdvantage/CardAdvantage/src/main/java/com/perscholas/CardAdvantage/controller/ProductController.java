package com.perscholas.CardAdvantage.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String createPost(@ModelAttribute("product") ProductDto productDto, 
			@RequestParam("file") MultipartFile file, BindingResult result, Model model) throws IOException {
		
		if (result.hasErrors()) {
			model.addAttribute("product", productDto);
			return "admin/create_product";
		}
		productDto.setProductPicture(Base64.getEncoder().encodeToString(file.getBytes()));
		
		productService.createProduct(productDto);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/products/{productId}/edit")
	public String editProductForm(@PathVariable("productId") Long productId, Model model) 
	{
		ProductDto productDto = productService.findProductById(productId);
		model.addAttribute("product", productDto);
		return "admin/edit_product";

	}
	
	//handler method to handle edit product post request
	@PostMapping("/admin/products/{productId}")
	public String updateProduct(@PathVariable("productId") Long productId,
								@PathVariable("productPicture") String productPicture,
								@ModelAttribute("product") ProductDto product,
								@RequestParam("file") MultipartFile file,
								BindingResult result,
								Model model) throws IOException {
		if(result.hasErrors()){
			model.addAttribute("product", product);
			return "admin/edit_post";
		}
		
		//The product picture sent is always empty
		if(!file.isEmpty()) {
			product.setProductPicture(Base64.getEncoder().encodeToString(file.getBytes()));

		}else {
			product.setProductPicture(productPicture);
		}
		
		product.setId(productId);
		productService.updateProduct(product);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/products/{productId}/delete")
	public String deleteProduct(@PathVariable("productId") Long productId) {
		productService.deleteProduct(productId);
		return "redirect:/admin/products";
		
	}
	
	@GetMapping("/admin/products/{productId}/view")
	public String viewProduct(@PathVariable("productId") Long productId, Model model){
		ProductDto productDto = productService.findProductById(productId);
		model.addAttribute("product", productDto);
		return "admin/view_product";
	}
	
	//handler for searching products
	@GetMapping("/admin/products/search")
	public String searchProducts(@RequestParam(value="query") String query, Model model) 
	{
		List<ProductDto> products = productService.searchProducts(query);
		model.addAttribute("products", products);
		return "admin/products";
	}
	
	

}
