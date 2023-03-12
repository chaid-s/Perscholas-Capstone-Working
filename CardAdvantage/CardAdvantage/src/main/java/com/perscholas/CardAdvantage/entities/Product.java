package com.perscholas.CardAdvantage.entities;

import java.util.ArrayList;
import java.util.List;

import com.perscholas.CardAdvantage.dto.ProductDto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	private String description;
	@Lob
    @Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
    private String productPicture;
	
	@OneToMany(mappedBy = "product")
	private List<UserProduct> userProducts = new ArrayList<>();
	
}
