package com.amalan.test.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amalan.test.models.Products;
import com.amalan.test.models.Response;
import com.amalan.test.models.ResponseWithData;
import com.amalan.test.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductsController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/{id}")
	public Object getProduct(@PathVariable int id) {		
		Optional<Products> product = productService.findById(id); 
		if (!product.isPresent()) {
			Response res = new Response();
			res.setStatus("error");
			res.setMessage("Product not found!");
			return res;
		}
		ResponseWithData res = new ResponseWithData();
		res.setStatus("success");
		res.setMessage("Product retrived!");
		res.setData(product);
		return res;
	}
	
	@GetMapping("/")
	public Object listProducts() {		
		Iterable<Products> products= productService.findAll();
		ResponseWithData res = new ResponseWithData();
		res.setStatus("success");
		res.setMessage("List of available products!");
		res.setData(products);
		return res;
	}
	
	@PostMapping("/create")
	public Response createProduct(@RequestBody Products product) {
		Response response = new Response();
		try {
			product.id = null;
			productService.save(product);
			response.setStatus("success");
			response.setMessage("Products Created Successfully!");
		} catch(Exception e) {
			System.out.println("error"+ e);
			response.setStatus("error");
			response.setMessage("Error creating product.");
		}

		return response;
	}
	
	@PutMapping("/update/{id}")
	public Response updateProduct(@PathVariable int id,@RequestBody Products product) {
		Response response = new Response();
		try {
			product.id = id;
			productService.save(product);
			response.setStatus("success");
			response.setMessage("Products Updated Successfully!");
		} catch(Exception e) {
			response.setStatus("error");
			response.setMessage("Unable to update product!");
		}
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public Response deleteProduct(@PathVariable int id) {
		Response response = new Response();
		try {
			productService.deleteById(id);
			response.setStatus("success");
			response.setMessage("Products Deleted Successfully!");
		} catch(Exception e) {
			response.setStatus("error");
			response.setMessage("Unable to delete product!");
		}
		return response;
	}
	
}