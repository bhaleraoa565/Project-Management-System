package com.Product.Management.System.Service;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import com.Product.Management.System.Model.UserModel;
import com.Product.Management.System.Repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	
	@Autowired
	UserRepository userRepo;
	
	public UserModel registerUser(UserModel userM) {
		return userRepo.save(userM);
	}
	
	public UserModel findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

	
	public boolean checkCredentials(String username, String password) {
        UserModel user = userRepo.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	 UserModel user = userRepo.findByUsername(username);
     if (user == null) {
         throw new UsernameNotFoundException("User not found");
     }
     return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
}
}
