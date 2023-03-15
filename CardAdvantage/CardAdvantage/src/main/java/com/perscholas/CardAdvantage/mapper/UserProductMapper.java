package com.perscholas.CardAdvantage.mapper;

import com.perscholas.CardAdvantage.dto.UserProductDto;
import com.perscholas.CardAdvantage.entities.UserProduct;

public class UserProductMapper {
	
	public static UserProductDto mapToUserProductDto(UserProduct userproduct){
		return UserProductDto.builder()
				.id(userproduct.getId())
				.name(userproduct.getName())
				.price(userproduct.getPrice())
				.inCart(userproduct.isInCart())
				.purchased(userproduct.isPurchased())
				.verificationPicture(userproduct.getVerificationPicture())
				.build();
	}
	
	public static UserProduct mapToUserProduct(UserProductDto userproductdto) {
		return UserProduct.builder()
				.id(userproductdto.getId())
				.name(userproductdto.getName())
				.price(userproductdto.getPrice())
				.inCart(userproductdto.isInCart())
				.purchased(userproductdto.isPurchased())
				.verificationPicture(userproductdto.getVerificationPicture())
				.build();
	}

}
