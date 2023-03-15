package com.perscholas.CardAdvantage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.perscholas.CardAdvantage.dto.ProductDto;
import com.perscholas.CardAdvantage.dto.UserProductDto;
import com.perscholas.CardAdvantage.entities.User;
import com.perscholas.CardAdvantage.repository.ProductRepository;
import com.perscholas.CardAdvantage.repository.RoleRepository;
import com.perscholas.CardAdvantage.repository.UserProductRepository;
import com.perscholas.CardAdvantage.repository.UserRepository;
import com.perscholas.CardAdvantage.service.impl.ProductServiceImpl;
import com.perscholas.CardAdvantage.service.impl.UserProductServiceImpl;

@SpringBootTest
class CardAdvantageApplicationTests {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserProductRepository userProductRepository;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void assertRepoNotNull(){
		assertThat(userRepository).isNotNull();
		assertThat(productRepository).isNotNull();
		assertThat(roleRepository).isNotNull();
		assertThat(userProductRepository).isNotNull();
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"admin"})
	void adminExists(String input) {
		User user = userRepository.findByuName("admin");
		assertTrue(user.getUName().equals(input));
	}
	
	@Test
	void productsExist() {
		ProductServiceImpl productService = new ProductServiceImpl(productRepository);
		List<ProductDto> products= productService.findAllProducts();
		assertThat(products).isNotEmpty();
	}
	
	@Test
	void testCartIsEmpty() {
		UserProductServiceImpl userProductService = new UserProductServiceImpl(userProductRepository);
		List<UserProductDto> userProducts = userProductService.findAllCartProducts();
		assertThat(userProducts).isEmpty();
	}
	

}
