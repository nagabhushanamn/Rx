package com.example.demo.web;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.repository.Productrepository;
import com.example.demo.service.ProductService;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/v1/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public void get(Model model) {
		// UI thread
		Flowable<List<Product>> flowable = productService.getAll();
		flowable
		.subscribeOn(Schedulers.newThread())
		.subscribe(data -> {
			System.out.println(data.size());
		});
		
		System.out.println("UI thread not blocked when loading products by service layer");
	}

}
