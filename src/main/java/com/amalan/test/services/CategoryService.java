package com.amalan.test.services;

import org.springframework.data.repository.CrudRepository;

import com.amalan.test.models.Categories;

public interface CategoryService extends CrudRepository<Categories,Integer> {

}
