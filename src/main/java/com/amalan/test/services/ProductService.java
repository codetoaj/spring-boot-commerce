package com.amalan.test.services;

import org.springframework.data.repository.CrudRepository;

import com.amalan.test.models.Products;

public interface ProductService extends CrudRepository<Products, Integer> {
	
}