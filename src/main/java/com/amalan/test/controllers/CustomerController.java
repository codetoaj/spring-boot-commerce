package com.amalan.test.controllers;

import com.amalan.test.models.Customers;
import com.amalan.test.models.Response;
import com.amalan.test.models.ResponseWithData;
import com.amalan.test.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public Response createProduct(@RequestBody Customers customer) {
        Response response = new Response();
        this.passwordEncoder = new BCryptPasswordEncoder();
        try {

            if (!customerService.existsByPhonenumber(customer.phonenumber)) {
                customer.id = null;
                customer.setPassword(this.passwordEncoder.encode(customer.getPassword()));
                customerService.save(customer);
                response.setStatus("success");
                response.setMessage("Customers created Successfully!");
            } else {
                response.setStatus("error");
                response.setMessage("Customer already exist with this phonenumber.");
            }

        } catch (Exception e) {
            System.out.println("error" + e);
            response.setStatus("error");
            response.setMessage("Error creating customer.");
        }

        return response;
    }

    @GetMapping("/list")
    public ResponseWithData getCategories() {
        ResponseWithData res = new ResponseWithData();
        Iterable<Customers> categories = customerService.findAll();
        res.setStatus("success");
        res.setMessage("Available categories");
        res.setData(categories);
        return res;
    }
}
