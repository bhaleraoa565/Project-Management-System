package com.Product.Management.System.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Product.Management.System.Model.CartModel;
import com.Product.Management.System.Repository.CartItemRepository;

@Service
public class CartItemService {
	
	@Autowired
    private CartItemRepository cartItemRepository;

    public List<CartModel> getAllCartItems() {
        return cartItemRepository.findAll();
    }
    

    public CartModel addCartItem(CartModel cartItem) {
        return cartItemRepository.save(cartItem);
    }
    
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }
    
    public Optional<CartModel> getCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }
}
