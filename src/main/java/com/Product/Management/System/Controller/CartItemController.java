package com.Product.Management.System.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Product.Management.System.Model.CartModel;
import com.Product.Management.System.Service.CartItemService;

@Controller
public class CartItemController {

	 @Autowired
	    private CartItemService cartItemService;

	    @GetMapping("/cart-items")
	    public List<CartModel> getAllCartItems() {
	        return cartItemService.getAllCartItems();
	    }
	    
	    @PostMapping("/add-to-cart")
	    public CartModel addCartItem(@RequestBody CartModel cartItem) {
	        return cartItemService.addCartItem(cartItem);
	    }
	    
	    @DeleteMapping("cart-item/{id}")
	    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
	        Optional<CartModel> cartItem = cartItemService.getCartItemById(id);
	        if (cartItem.isPresent()) {
	            cartItemService.deleteCartItem(id);
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
