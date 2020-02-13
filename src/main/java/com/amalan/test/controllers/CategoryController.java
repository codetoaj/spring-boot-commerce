package com.amalan.test.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amalan.test.models.Categories;
import com.amalan.test.models.Response;
import com.amalan.test.models.ResponseWithData;
import com.amalan.test.services.CategoryService;


@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/{id}")
	public Object getCategory(@PathVariable int id) {
		Optional<Categories> category = categoryService.findById(id);
		if (!category.isEmpty()) {
			ResponseWithData res = new ResponseWithData();
			res.setStatus("success");
			res.setMessage("Category Requested.");
			res.setData(category);
			return res;
		}
		Response res = new Response();
		res.setStatus("error");
		res.setMessage("Category not found.");
		return res;
	}
	
	@PostMapping("/create")
	public Response addCategory(@RequestBody Categories category) {
		Response res = new Response();
		try {
			category.id = null;
			categoryService.save(category);
			res.setStatus("success");
			res.setMessage("Category created successfully!");
		} catch(Exception e) {
			res.setStatus("error");
			res.setMessage("Failed to create category!");
		}
		return res;
	}
	
	@GetMapping("/list")
	public ResponseWithData getCategories() {
		ResponseWithData res = new ResponseWithData();
		Iterable<Categories> categories = categoryService.findAll();
		res.setStatus("success");
		res.setMessage("Available categories");
		res.setData(categories);
		return res;
	}
	
	@PutMapping("/update/{id}")
	public Response updateCategory(@PathVariable int id, @RequestBody Categories category) {
		Response res = new Response();
		try {
			category.id = id;
			categoryService.save(category);
			res.setStatus("success");
			res.setMessage("Category updated successfully!");
		} catch(Exception e) {
			res.setStatus("error");
			res.setMessage("Failed to update category.");
		}
		return res;
	}
}