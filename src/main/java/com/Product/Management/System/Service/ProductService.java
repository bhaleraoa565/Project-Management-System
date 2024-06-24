package com.Product.Management.System.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Product.Management.System.Model.ProductModel;
import com.Product.Management.System.Repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	private String imgUrl = "/uploads";
	
	
	public List<ProductModel> getAllProducts(){
		return productRepo.findAll();
		}
	
	public ProductModel saveProduct(ProductModel productM , MultipartFile img) throws IOException {
		
		if(img != null && !img.isEmpty()) {
			String fileName = saveImage(img);
			productM.setImageUrl(fileName);
		}
		
		return productRepo.save(productM);
		
	}

	private String saveImage(MultipartFile img) throws IOException {
		byte[] bytes = img.getBytes();
		Path path = Paths.get(imgUrl + img.getOriginalFilename());
		Files.write(path, bytes);
		return img.getOriginalFilename();
	}
}
