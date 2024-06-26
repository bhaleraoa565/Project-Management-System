package com.Product.Management.System.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Product.Management.System.Model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>  {

	UserModel findByUsername(String username);
}
