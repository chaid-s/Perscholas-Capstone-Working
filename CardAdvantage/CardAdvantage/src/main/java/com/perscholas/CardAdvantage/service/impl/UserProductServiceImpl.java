package com.perscholas.CardAdvantage.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perscholas.CardAdvantage.dto.UserProductDto;
import com.perscholas.CardAdvantage.entities.Product;
import com.perscholas.CardAdvantage.entities.UserProduct;
import com.perscholas.CardAdvantage.mapper.ProductMapper;
import com.perscholas.CardAdvantage.mapper.UserProductMapper;
import com.perscholas.CardAdvantage.repository.ProductRepository;
import com.perscholas.CardAdvantage.repository.UserProductRepository;
import com.perscholas.CardAdvantage.service.UserProductService;

@Service
public class UserProductServiceImpl implements UserProductService{
	
	private UserProductRepository userProductRepository;

	public UserProductServiceImpl(UserProductRepository userProductRepository) 
	{
		this.userProductRepository = userProductRepository;
	}

	@Override
	public List<UserProductDto> findAllUserProducts() {
		List<UserProduct> userproducts = userProductRepository.findAll();
		return userproducts.stream().map(UserProductMapper::mapToUserProductDto)
				.collect(Collectors.toList());
	}

	@Override
	public void createUserProduct(UserProductDto userProductDto) {
		UserProduct userProduct = UserProductMapper.mapToUserProduct(userProductDto);
		userProductRepository.save(userProduct);
	}

	@Override
	public void deleteUserProduct(Long userProductId) {
		userProductRepository.deleteById(userProductId);
	}

	@Override
	public UserProductDto findUserProductById(Long userProductId) {
		UserProduct userProduct = userProductRepository.findById(userProductId).get();
		return UserProductMapper.mapToUserProductDto(userProduct);
	}

	@Override
	public void updateUserProduct(UserProductDto userProductDto) {
		UserProduct userProduct = UserProductMapper.mapToUserProduct(userProductDto);
		userProductRepository.save(userProduct);
	}

	@Override
	public void cartUserProduct(Long userProductId) {
		UserProduct userProduct = userProductRepository.findById(userProductId).get();
		userProduct.setInCart(true);
		userProductRepository.save(userProduct);
	}
	
	@Override
	public void uncartUserProduct(Long userProductId) {
		UserProduct userProduct = userProductRepository.findById(userProductId).get();
		userProduct.setInCart(false);
		userProductRepository.save(userProduct);
		
	}

	@Override
	public void purchaseUserProduct(Long userProductId) {
		UserProduct userProduct = userProductRepository.findById(userProductId).get();
		userProduct.setPurchased(true);
		userProductRepository.save(userProduct);
	}

	@Override
	public List<UserProductDto> findAllCartProducts() {
		List<UserProduct> userproducts = userProductRepository.findCartProducts();
		return userproducts.stream().map(UserProductMapper::mapToUserProductDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<UserProductDto> findAllActiveProducts() {
		List<UserProduct> userproducts = userProductRepository.findActiveProducts();
		return userproducts.stream().map(UserProductMapper::mapToUserProductDto)
				.collect(Collectors.toList());
	}

	@Override
	public void purchaseCart() {
		List<UserProduct> userproducts = userProductRepository.findCartProducts();
		for(UserProduct p: userproducts) {
			p.setPurchased(true);
		}
		userProductRepository.saveAll(userproducts);
	}

	

}
