package com.Product.Management.System.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.Product.Management.System.Model.UserModel;
import com.Product.Management.System.Service.UserService;


@RestController
@RequestMapping("/auth")
public class UserAuthController {

	@Autowired
	UserService userService;
	
	
	
	@PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserModel user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
        userService.registerUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
	
	@PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserModel user) {
        if (userService.checkCredentials(user.getUsername(), user.getPassword())) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }

}
