package com.redhat.systems.neptune.model.catalogs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({ @NamedQuery(name = "Product.findProducts", query = "SELECT p FROM Product p") })
@SequenceGenerator(name = "PRODUCT_SEQ", sequenceName = "PRODUCTS_SEQUENCE", allocationSize = 1, initialValue = 1)
@Table(name = "PRODUCTS")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
	@Column(name = "product_id")
	private Long productId;

	@NotNull
	@Size(min = 1, max = 50, message = "1-50 letters and spaces")
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	@Column(name = "code")
	private String code;

	@NotNull
	@Size(min = 1, max = 50, message = "1-50 letters and spaces")
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	@Column(name = "name")
	private String name;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
