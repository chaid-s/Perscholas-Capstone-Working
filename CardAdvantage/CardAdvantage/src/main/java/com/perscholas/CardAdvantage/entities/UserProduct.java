package com.perscholas.CardAdvantage.entities;

import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_products")
public class UserProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userid")
	private User user;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="productid")
	private Product product;
	@ManyToOne
    @JoinColumn(name="cartuserproductid")
	private CartUserProduct cartuserproduct;
	@Basic(fetch = FetchType.LAZY)
    private byte[] verificationPicture;
	
	

}
