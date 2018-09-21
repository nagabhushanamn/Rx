package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.Productrepository;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

@Service
public class ProductService {

	@Autowired
	private Productrepository productrepository;

	public Flowable<List<Product>> getAll() {

		Flowable<List<Product>> flowable = Flowable.create(emitter -> {

			List<Product> products = productrepository.findAll();
			// Load Reviews
			// Load discounts
			// Load Images
			emitter.onNext(products);

		}, BackpressureStrategy.BUFFER);
		flowable.observeOn(Schedulers.io());

		return flowable;
	}

}
