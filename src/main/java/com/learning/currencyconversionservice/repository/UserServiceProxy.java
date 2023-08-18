package com.learning.currencyconversionservice.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.learning.currencyconversionservice.entities.User;

@FeignClient(name = "user-service")
public interface UserServiceProxy {

	@GetMapping(value = "/user-service/all")
	public List<User> getAllUsers();
	
}
