package com.Product.Management.System.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Product.Management.System.Model.ProductModel;
import com.Product.Management.System.Service.ProductService;

@RestController
@RequestMapping("/Products")
public class ProductController {
	
	@Autowired
	private ProductService productS;
	
	@GetMapping
	public List<ProductModel> getAllProducts(){
		return productS.getAllProducts();
		}
	
	@PostMapping
	public ProductModel createProduct(@RequestPart("productM") ProductModel productM,
			@RequestPart("img") MultipartFile img ) throws IOException{
		
				return productS.saveProduct(productM, img);}

}
