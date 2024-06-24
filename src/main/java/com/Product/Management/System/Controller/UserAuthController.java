package com.Product.Management.System.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.Product.Management.System.Model.UserModel;
import com.Product.Management.System.Service.UserService;


@RestController
@RequestMapping("/auth")
public class UserAuthController {

	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	
	
	@PostMapping("/register")
	public String registerUser(@RequestBody UserModel userM) {
		userM.setPassword(passwordEncoder.encode(userM.getPassword()));
		userService.registerUser(userM);
		return "User Register Successfully";
		
	}
	 @PostMapping("/login")
	    public ResponseEntity<String> userLogin(@RequestBody LoginRequest loginReq) {
	        try {
	            Authentication authentication = authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword())
	            );
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	            return ResponseEntity.ok("Login successful");
	        } catch (Exception e) {
	            return ResponseEntity.status(401).body("Invalid username or password");
	        }
	    }
}
