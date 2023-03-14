package com.perscholas.CardAdvantage.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.perscholas.CardAdvantage.dto.UserProductDto;
import com.perscholas.CardAdvantage.entities.UserProduct;
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

}
