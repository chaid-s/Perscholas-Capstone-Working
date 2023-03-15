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
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//handler for displaying user products
	@GetMapping("/guest/userproducts")
	public String products(Model model) {
		List<UserProductDto> userproducts = userProductService.findAllActiveProducts();
		model.addAttribute("userproducts", userproducts);
		return "guest/userproducts";
	}
	
	
	@GetMapping("guest/userproducts/newproduct")
	public String newProductForm(Model model) {
		model.addAttribute("userproduct", new UserProductDto());
		List<ProductDto> products = productService.findAllProducts();
		model.addAttribute("products", products);
		return "guest/create_userproduct";
	}
	
	// handler for submitting new products
	@PostMapping("/guest/userproducts")
	public String createPost(@ModelAttribute("userproduct") UserProductDto userProductDto, 
		@RequestParam("file") MultipartFile file, BindingResult result, Model model) throws IOException {
			
		if (result.hasErrors()) {
			model.addAttribute("userproduct", userProductDto);
			return "guest/create_userproduct";
		}
		userProductDto.setVerificationPicture(Base64.getEncoder().encodeToString(file.getBytes()));
		userProductDto.setInCart(false);
		userProductDto.setPurchased(false);
			
		userProductService.createUserProduct(userProductDto);
		return "redirect:/guest/userproducts";
	}
	
	//handler for editing a userproduct page
	@GetMapping("/guest/userproducts/{userproductId}/edit")
	public String editUserProductForm(@PathVariable("userproductId") Long userProductId, Model model) 
	{
		UserProductDto userProductDto = userProductService.findUserProductById(userProductId);
		model.addAttribute("userproduct", userProductDto);
		return "guest/edit_userproduct";

	}
	
	@PostMapping("/guest/userproducts/{userproductId}")
	public String updateProduct(@PathVariable("userproductId") Long userProductId,
								@PathVariable("verificationPicture") String verificationPicture,
								@ModelAttribute("userproduct") UserProductDto userProduct,
								@RequestParam("file") MultipartFile file,
								BindingResult result,
								Model model) throws IOException {
		if(result.hasErrors()){
			model.addAttribute("userproduct", userProduct);
			return "guest/edit_userproduct";
		}
		
		//The product picture sent is always empty
		if(!file.isEmpty()) {
			userProduct.setVerificationPicture(verificationPicture);

		}else {
			userProduct.setVerificationPicture(verificationPicture);
		}
		
		userProduct.setId(userProductId);
		userProductService.updateUserProduct(userProduct);
		return "redirect:/guest/userproducts";
	}
	
	// handler for deleting user products
	@GetMapping("/guest/userproducts/{userproductId}/delete")
	public String deleteUserProduct(@PathVariable("userproductId") Long userproductId) {
		userProductService.deleteUserProduct(userproductId);
		return "redirect:/guest/userproducts";
		
	}
	
	//handler for moving a product to cart
	@GetMapping("/guest/userproducts/{userproductId}/cart")
	public String cartUserProduct(@PathVariable("userproductId") Long userproductId) {
		userProductService.cartUserProduct(userproductId);
		return "redirect:/guest/userproducts";
		
	}
	
	//handler for moving a product from cart
		@GetMapping("/guest/userproducts/{userproductId}/uncart")
		public String uncartUserProduct(@PathVariable("userproductId") Long userproductId) {
			userProductService.uncartUserProduct(userproductId);
			return "redirect:/guest/userproducts";
			
		}
		
	//handler for cart page
		@GetMapping("/cart")
		public String cartProducts(Model model) {
		List<UserProductDto> userproducts = userProductService.findAllCartProducts();
		model.addAttribute("userproducts", userproducts);
		return "cart.html";
		}
	//handler to clear cart
		@GetMapping("/guest/userproducts/purchase")
		public String purchaseCartUserProduct() {
			userProductService.purchaseCart();
			return "redirect:/guest/userproducts";
			
		}	
	
		
	//handler for index page
		@GetMapping("/")
		public String indexProducts(Model model) {
			List<UserProductDto> userproducts = userProductService.findAllActiveProducts();
			model.addAttribute("userproducts", userproducts);
			return "index.html";
		}
		

}
