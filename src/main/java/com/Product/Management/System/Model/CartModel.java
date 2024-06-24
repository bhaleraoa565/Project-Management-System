package com.Product.Management.System.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartModel {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "product_id", nullable = false)
	    private ProductModel product;

	    @Column(nullable = false)
	    private Integer quantity;

		public CartModel() {
			super();
			// TODO Auto-generated constructor stub
		}

		public CartModel(Long id, ProductModel product, Integer quantity) {
			super();
			this.id = id;
			this.product = product;
			this.quantity = quantity;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public ProductModel getProduct() {
			return product;
		}

		public void setProduct(ProductModel product) {
			this.product = product;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		@Override
		public String toString() {
			return "CartModel [id=" + id + ", product=" + product + ", quantity=" + quantity + "]";
		}
	    
	    
}
