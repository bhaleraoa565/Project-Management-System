package com.Product.Management.System.Service;


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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = userRepo.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		
		UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.password(user.getPassword());
        userBuilder.roles("USER");
		return userBuilder.build();
	}
}
