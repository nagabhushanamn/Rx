package com.examples;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;

public class Ex {

	public static void main(String[] args) throws InterruptedException {

		Observable<Integer> scan = Observable
				  .range(1, 10)
				  .scan((p, v) -> p + v);
		subscribePrint(scan, "Sum");
		subscribePrint(scan.last(), "Final sum");
		
		TimeUnit.SECONDS.sleep(10);

	}
	
	
	
	static <T> Subscription subscribePrint(Observable<T> observable, String name) {
		return observable.subscribe(
					(v) -> System.out.println(name + " : " + v), 
					(e) -> {
							System.err.println("Error from " + name + ":");
							System.err.println(e.getMessage());
					}, 
					() -> System.out.println(name + " ended!")
				);
	}
	

}
