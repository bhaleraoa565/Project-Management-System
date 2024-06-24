package com.Product.Management.System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Product.Management.System.Model.CartModel;

@Repository
public interface CartItemRepository extends JpaRepository<CartModel, Long> {

}
