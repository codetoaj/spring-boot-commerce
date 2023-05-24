package com.amalan.test.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amalan.test.models.Categories;

public interface CategoryService extends JpaRepository<Categories, Integer> {

}
