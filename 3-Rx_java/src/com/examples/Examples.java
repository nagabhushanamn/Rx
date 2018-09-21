package com.examples;

import java.beans.Introspector;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import rx.Notification;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class Examples {

	public static void main(String[] args) throws InterruptedException, IOException {

		// -------------------------------------------------------------------
		// iterator pattern ( pull model )
		// -------------------------------------------------------------------

		List<String> list1 = Arrays.asList("One", "Two", "Three", "Four", "Five"); // (1)

		Iterator<String> iterator = list1.iterator(); // (2)

		while (iterator.hasNext()) { // 3
			// Prints elements (4)
			System.out.println(iterator.next()); // It pulls the data,
													// and the current thread blocks until the requested data is ready
													// and received
		}

		/**
		 * 
		 * if the Iterator instance was firing a request to a web server on every next()
		 * method call, the main thread of our program would be blocked while waiting
		 * for each of the responses to arrive.
		 * 
		 */
		// -------------------------------------------------------------------

		
		
		
		// -------------------------------------------------------------------
		// Observable pattern ( push model )
		// -------------------------------------------------------------------

		List<String> list2 = Arrays.asList("One", "Two", "Three", "Four", "Five"); // (1)

		Observable<String> observable = Observable.from(list2); // (2)

		observable.subscribe(new Action1<String>() { // (3)
			@Override
			public void call(String element) {
				System.out.println(element); // Prints the element (4)
			}
		});

		/**
		 * 
		 * Reactive Programming using RxJava
		 * 
		 * 
		 * - Observable instances used for building asynchronous streams and pushing
		 * data updates to their subscribers
		 * 
		 * - a stream is a sequence of ongoing messages/events
		 * 
		 * - The data is being propagated 
		 *   to all the interested parties—the subscribers.
		 * 
		 * - On top of that, consumers can be notified that the stream is closed, or
		 * that there has been an error.So, by using these streams, our applications can
		 * react to failure.
		 * 
		 * 
		 * 
		 * 
		 * - using RxJava, we can create data streams from anything—file input, sockets,
		 * responses, variables, caches, user inputs, and so on
		 * 
		 * 
		 * 
		 * 
		 */
		
		
		// -------------------------------------------------------------------
		//Creating and Connecting Observables, Observers
		// -------------------------------------------------------------------
		
		
		/**
		 * 
		 *    Observable.from  => convert various other objects and data types into Observables
		 *    
		 *    Marble : 
		 *    http://reactivex.io/documentation/operators/images/from.c.png
		 *    
		 * 
		 */
		
		List<String> list = Arrays.asList("blue", "red", "green", "yellow", "orange", "cyan", "purple");
		Observable<String> listObservable = Observable.from(list);
		
		listObservable
		.subscribe(System.out::println)
		;
		
		
		listObservable
				.subscribe(
				  color -> System.out.print(color + "|"),
				  System.out::println,
				  System.out::println
				)
				;
		
		listObservable.subscribe(color -> System.out.print(color + "/"));
		
		
		
		
		
		
		/**
		 * 
		 * 	Observable.just  => create an Observable that emits a particular item
		 * 
		 *  Marble: 
		 *  http://reactivex.io/documentation/operators/images/just.c.png
		 *  
		 *  	
		 * 	
		 */
		
		
		Observable.just('S')
		.subscribe(System.out::println,System.out::println);
		
		Observable
		  .just('R', 'x', 'J', 'a', 'v', 'a')
		  .subscribe(
		    System.out::print,
		    System.err::println,
		    System.out::println
		  );
		
		
		Observable
		  .just("hello","world")
		  .map(s -> s.toUpperCase())
		  .subscribe(System.out::println);
		
		
		/**
		 * 
		 * 
		 * Observable.interval => create an Observable that emits a sequence of integers spaced by a given time interval
		 * 
		 * Marble:
		 * http://reactivex.io/documentation/operators/images/interval.c.png
		 * 
		 * 
		 */
		
		subscribePrint(
				  Observable.interval(500L, TimeUnit.MILLISECONDS),
				  "Interval Observable"
				);
		
		/**
		 * 
		 * Observable.timer => create an Observable that emits a particular item after a given delay
		 * 
		 * Marble:
		 * http://reactivex.io/documentation/operators/images/timer.c.png
		 * 
		 */
		
		subscribePrint(
				  Observable.timer(0L, 1L, TimeUnit.SECONDS),
				  "Timed Interval Observable"
				);
		
		subscribePrint(
				  Observable.timer(1L, TimeUnit.SECONDS),
				  "Timer Observable"
				);
		
		/**
		 * 
		 * Observable.error
		 * 
		 */
		
		
		subscribePrint(
				  Observable.error(new Exception("Test Error!")),
				  "Error Observable"
				);
		
		
		/**
		 * 
		 * 
		 *  Observable.empty() => create an Observable that emits no items but terminates normally
		 *  Marble:
		 *  http://reactivex.io/documentation/operators/images/never.c.png
		 *  
		 *  
		 * 	Observable.never() => create an Observable that emits no items and terminates with an error
		 * 	Marble:
		 * 	http://reactivex.io/documentation/operators/images/throw.c.png
		 * 
		 *  Observable.range() => create an Observable that emits a particular range of sequential integers
		 *  Marble:
		 *  http://reactivex.io/documentation/operators/images/range.c.png
		 *  
		 *  
		 */
		
		subscribePrint(Observable.empty(), "Empty Observable");
		subscribePrint(Observable.never(), "Never Observable");
		subscribePrint(Observable.range(10, 3), "Range Observable");
		
		
		
		/**
		 * 
		 * Observable.create => create an Observable from scratch by means of a function
		 * Marble:
		 * http://reactivex.io/documentation/operators/images/create.c.png
		 * 
		 */
		
		
		Observable<Integer> observable2=Observable.create(new OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> observer) {
				try {
					if (!observer.isUnsubscribed()) {
						for (int i = 1; i < 5; i++) {
							TimeUnit.SECONDS.sleep(3);
							observer.onNext(i);
						}
						observer.onCompleted();
					}
				} catch (Exception e) {
					observer.onError(e);
				}
			}
		});
		
		
		Subscription subscription= observable2.subscribe(System.out::println,System.out::println,System.out::println);
		subscription.unsubscribe();
		
		
		// -------------------------------------------------------------------
		//Subscribing and unsubscribing
		// -------------------------------------------------------------------
		
		
		
		Path path = Paths.get("resources", "lorem_big.txt"); // (1)
		List<String> data = Files.readAllLines(path);
		
		Observable<String> observable3 = fromIterable(data)
				.subscribeOn(Schedulers.computation()); // (2)
		Subscription subscription1 = subscribePrint(observable3, "File");// (3)
		System.out.println("Before unsubscribe!");
		System.out.println("-------------------");
		subscription1.unsubscribe(); // (4)
		System.out.println("-------------------");
		System.out.println("After unsubscribe!");
		
		
		// -------------------------------------------------------------------
		//Hot and cold Observable instances
		//-------------------------------------------------------------------
		
		

		Observable<Long> interval = Observable.interval(100L, TimeUnit.MILLISECONDS);
		//ConnectableObservable<Long> published = interval.publish();
		//ConnectableObservable<Long> published = interval.replay();
		Observable<Long> refCount = interval.publish().refCount();
		Subscription sub1 = subscribePrint(refCount, "First");
		Subscription sub2 = subscribePrint(refCount, "Second");
		//published.connect();
		Subscription sub3 = null;
		try {
			Thread.sleep(500L);
			sub3 = subscribePrint(refCount, "Third");
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
		}
		
		sub1.unsubscribe();
		sub2.unsubscribe();
		sub3.unsubscribe();
		
		try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Subscription sub4 = subscribePrint(refCount, "Fourth");
		
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		sub4.unsubscribe();
		
		
		// -------------------------------------------------------------------
		//Transforming, Filtering, and Accumulating Your Data
		// -------------------------------------------------------------------
		
		
		/**
		 * 
		 *  Map  => transform the items emitted by an Observable by applying a function to each item
		 *  
		 *  
		 *  Marble:
		 *  https://d1ldz4te4covpm.cloudfront.net/graphics/9781785288722/graphics/4305_04_01.jpg
		 * 
		 */
		
		Observable<String> mapped = Observable
				  .just(2, 3, 5, 8)
				  .map(v -> v * 3)
				  .map(v -> (v % 2 == 0) ? "even" : "odd");
		subscribePrint(mapped, "map");
		
		
		/**
		 * 
		 * 
		 * FlapMap => transform the items emitted by an Observable into Observables, 
		 *            then flatten the emissions from those into a single Observable
		 * 
		 * Marble:
		 * http://reactivex.io/documentation/operators/images/flatMap.c.png
		 * 
		 * 
		 */
		
		Observable<Integer> flatMapped = Observable
				  .just(-1, 0, 1)
				  .map(v -> 2 / v)
				  .flatMap(
				    v -> Observable.just(v),
				    e -> Observable.just(0),
				    () -> Observable.just(42)
				  );
		subscribePrint(flatMapped, "flatMap");
		
		
		Observable<Integer> flatMapped2 = Observable
				.just(5, 432)
				.flatMap(
				  v -> Observable.range(v, 2),
				  (x, y) -> x + y);
		subscribePrint(flatMapped2, "flatMap");
		
		
		
		/**
		 * 
		 *  Filter  ==> emit only those items from an Observable that pass a predicate test
		 *  
		 *  Marble:
		 *  https://d1ldz4te4covpm.cloudfront.net/graphics/9781785288722/graphics/4305_04_07.jpg
		 *  
		 * 
		 */
		
		Observable<Integer> numbers = Observable
				  .just(1, 13, 32, 45, 21, 8, 98, 103, 55);
		Observable<Integer> filter = numbers
				  .filter(n -> n % 2 == 0);
		
		subscribePrint(filter, "Filter");
		
		
		/**
		 * 
		 * Takelast ==> emit only the final n items emitted by an Observable
		 * Marble:
		 * 
		 */
		
		
		subscribePrint(numbers.takeLast(4), "Last 4");
		
		/**
		 * 
		 * Last ==> 
		 * 
		 */
		
		
		subscribePrint(numbers.last(), "Last");
		
		
		/**
		 * 
		 * TakeLastBuffer ==>
		 * 
		 */
		
		subscribePrint(
				  numbers.takeLastBuffer(4), "Last buffer"
				);
		
		/**
		 * 
		 */
		
		subscribePrint(
				  numbers.lastOrDefault(200), "Last or default"
				);
		
		subscribePrint(
		  Observable.empty().lastOrDefault(200), "Last or default"
		);
		
		/**
		 * 
		 */
		
		subscribePrint(numbers.skipLast(4), "Skip last 4");
		
		/**
		 * 
		 * 
		 */
		
		subscribePrint(numbers.skip(4), "Skip 4");
		
		/**
		 * 
		 */
		
		subscribePrint(numbers.take(4), "First 4");
		
		/**
		 * 
		 */
		
		subscribePrint(numbers.first(), "First");
		
		/**
		 * 
		 */
		
		subscribePrint(numbers.elementAt(5), "At 5");
		
		/**
		 * 
		 */
		
		Observable<String> words = Observable
				  .just(
				    "One", "of", "the", "few", "of",
				    "the", "crew", "crew"
				  );
		
		subscribePrint(words.distinct(), "Distinct");
		
		subscribePrint(
				  words.distinctUntilChanged(), "Distinct until changed"
				);
		
		
		
		//-------------------------------------------------------------------
		//Accumulating Data
		//-------------------------------------------------------------------
		
		/**
		 * 
		 * Scan => apply a function to each item emitted by an Observable, sequentially, 
		 * and emit each successive value
		 * 
		 */
		

		Observable<Integer> scan = Observable
				  .range(1, 10)
				  .scan((p, v) -> p + v);
		subscribePrint(scan, "Sum");
		subscribePrint(scan.last(), "Final sum");
		
		

		// BIO , NIO
		Observable<String> file = from(Paths.get("resources", "operators.txt"));
		
		Observable<String> multy = 
				 file.flatMap(line -> Observable.from(line.split("\\."))) // (1)
			
				.map(String::trim) // (2)
				.map(sentence -> sentence.split(" ")) // (3)
				.observeOn(Schedulers.io())
				.filter(array -> array.length > 0) // (4)
				.map(array -> array[0]) // (5)
				.observeOn(Schedulers.computation())
				.distinct() // (6)
				.groupBy(word -> word.contains("'")) // (7)
				.flatMap(observablee -> observablee.getKey() ? observable : // (8)
						observable.map(Introspector::decapitalize))
				.map(String::trim) // (9)
				.observeOn(Schedulers.newThread())
				.filter(word -> !word.isEmpty()) // (10)
				.scan((current, word) -> current + " " + word) // (11)
				.last() // (12)
				.map(sentence -> sentence + "."); // (13)
		subscribePrint(multy, "Multiple operators"); // (14)
		
		
		//-----------------------------------------------
		//Combinators, Conditionals, and Error Handling
		//-----------------------------------------------
		
		
		/**
		 * 
		 * The zip operator => combine the emissions of multiple Observables together
		 * via a specified function and emit single items for each combination based on
		 * the results of this function
		 * 
		 * Marble:
		 * https://d1ldz4te4covpm.cloudfront.net/graphics/9781785288722/graphics/4305_05_01.jpg
		 * 
		 */
		
		

		
		Observable<Integer> zip = Observable
				.zip(
				  Observable.just(1, 3, 4),
				  Observable.just(5, 2, 6),
				  (a, b) -> a + b
				);
		subscribePrint(zip, "Simple zip");
		
		
		Observable<String> timedZip = Observable
				.zip(
				  Observable.from(Arrays.asList("Z", "I", "P", "P")),
				  Observable.interval(300L, TimeUnit.MILLISECONDS),
				  (value, i) -> value
				);
		subscribePrint(timedZip, "Timed zip");
		
		

		Observable<String> timedZip2 = Observable
				.from(Arrays.asList("Z", "I", "P", "P"))
				.zipWith(
				  Observable.interval(300L, TimeUnit.MILLISECONDS),
				  (value, skip) -> value
				);
		subscribePrint(timedZip2, "Timed zip");
		
		TimeUnit.SECONDS.sleep(5);
		
		
		
		/**
		 * 
		 * CombineLatest => when an item is emitted by either of two Observables,
		 * combine the latest item emitted by each Observable via a specified function
		 * and emit items based on the results of this function
		 * 
		 * Marble : 
		 * https://dz13w8afd47il.cloudfront.net/graphics/9781785288722/graphics/4305_05_02.jpg
		 * 
		 */
		
		
		//..
		
		/**
		 * 
		 * Merge ==> combine multiple Observables into one by merging their emissions
		 * 
		 * Marble :
		 * https://d255esdrn735hr.cloudfront.net/graphics/9781785288722/graphics/4305_05_03.jpg
		 * 
		 */
		
		
		/**
		 * 
		 * Concat ==> emit the emissions from two or more Observables without interleaving them
		 * 
		 * https://d1ldz4te4covpm.cloudfront.net/graphics/9781785288722/graphics/4305_05_04.jpg
		 * 
		 */
		
		
		//------------------------------------------------------------------------------
		//The conditional operators
		//------------------------------------------------------------------------------
		
		
		/**
		 * 
		 * amb operator ==> given two or more source Observables, emit all of the items
		 * from only the first of these Observables to emit an item or notification
		 * 
		 * Marble:
		 * https://dz13w8afd47il.cloudfront.net/graphics/9781785288722/graphics/4305_05_05.jpg
		 * 
		 */
		
		
		
		/**
		 * 
		 * takeUntil(), takeWhile(), skipUntil(), and skipWhile() 
		 * defaultIfEmpty( )
		 * 
		 */
		
		
		//------------------------------------------------------------------------------
		// Handling errors
		//------------------------------------------------------------------------------
		
		
		/**
		 * 
		 * onErrorReturn
		 * 
		 * 
		 * 
		 * 
		 */
		
		
		Observable<Integer> numbers1 = Observable
				  .just("1", "2", "three", "4", "5")
				  .map(Integer::parseInt)
				  .onErrorReturn(e -> -1);
		subscribePrint(numbers1, "Error returned");
	
		
		Observable<Integer> defaultOnError =
				  Observable.just(5, 4, 3, 2, 1);
		Observable<Integer> numbers2 = Observable
				  .just("1", "2", "three", "4", "5")
				  .map(Integer::parseInt)
				  .onExceptionResumeNext(defaultOnError);
		subscribePrint(numbers2, "Exception resumed");
		
		
		/**
		 * 
		 * retry
		 * 
		 * 
		 * 
		 */
		
		
		//---------------------------------------------------
		//Using Concurrency and Parallelism with Schedulers
		//---------------------------------------------------
		
		
		/**
		 * 
		 * RxJava's schedulers
		 * 
		 */
		
		/**
		 * 
		 */
		
		Observable
		  .range(5, 5)
		  .doOnEach(debug("Test", ""))
		  .subscribe();
		
		/**
		 * 
		 */
		
		
		Observable
		  .interval(500L, TimeUnit.MILLISECONDS,Schedulers.immediate())
		  .take(10)
		  .doOnEach(debug("Test", ""))
		  .subscribe();
		
		
		/**
		 * 
		 */
		
		CountDownLatch latch = new CountDownLatch(1);
		
		Observable<Integer> range = Observable
		  .range(20, 4)
		  .doOnEach(debug("Source"))
		  .subscribeOn(Schedulers.computation())
		  .finallyDo(() -> latch.countDown());
		
		range.subscribe();
		
		System.out.println("Hey!");
		
		latch.await();
		
		
		/**
		 * 
		 */
		
		
		
		CountDownLatch latch1 = new CountDownLatch(1);
		Observable<Integer> range1 = Observable
		  .range(20, 3)
		  .subscribeOn(Schedulers.newThread())
		  .doOnEach(debug("Source"));
		Observable<Character> chars = range1
		  .observeOn(Schedulers.io())
		  .map(n -> n + 48)
		  .doOnEach(debug("+48 ", "    "))
		  .observeOn(Schedulers.computation())
		  .map(n -> Character.toChars(n))
		  .map(c -> c[0])
		  .doOnEach(debug("Chars ", "    "))
		  .finallyDo(() -> latch.countDown());
		chars.subscribe();
		latch1.await();
		
		
		/**
		 * 
		 */
		

	}
	
	static <T> Observable<T> fromIterable(final Iterable<T> iterable) {
		return Observable.create(new OnSubscribe<T>() {
			@Override
			public void call(Subscriber<? super T> subscriber) {
				try {
					Iterator<T> iterator = iterable.iterator();
					while (iterator.hasNext()) {
						if (subscriber.isUnsubscribed()) {
							return;
						}
						subscriber.onNext(iterator.next());
					}
					if (!subscriber.isUnsubscribed()) {
						subscriber.onCompleted();
					}
				} catch (Exception e) {
					if (!subscriber.isUnsubscribed()) {
						subscriber.onError(e);
					}
				}
			}
		});
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
	
	public static Observable<String> from(final Path path) {
		return Observable.<String>create(subscriber -> {
			try {
				BufferedReader reader = Files.newBufferedReader(path);
				subscriber.add(Subscriptions.create(() -> {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}));
				
				String line = null;
				while ((line = reader.readLine()) != null && !subscriber.isUnsubscribed()) {
					subscriber.onNext(line);
				}
				if (!subscriber.isUnsubscribed()) {
					subscriber.onCompleted();
				}
			} catch (IOException ioe) {
				if (!subscriber.isUnsubscribed()) {
					subscriber.onError(ioe);
				}
			}
		});
	}
	
	static <T> Action1<Notification<? super T>> debug(String description) {
		return debug(description,"");
	}

	static <T> Action1<Notification<? super T>> debug(String description, String offset) {
		AtomicReference<String> nextOffset = new AtomicReference<String>(">");
		return (Notification<? super T> notification) -> {
			switch (notification.getKind()) {
			case OnNext:
				System.out.println(Thread.currentThread().getName() + "|" + description + ": " + offset
						+ nextOffset.get() + notification.getValue());
				break;
			case OnError:
				System.err.println(Thread.currentThread().getName() + "|" + description + ": " + offset
						+ nextOffset.get() + " X " + notification.getThrowable());
				break;
			case OnCompleted:
				System.out.println(
						Thread.currentThread().getName() + "|" + description + ": " + offset + nextOffset.get() + "|");
			default:
				break;
			}
			nextOffset.getAndUpdate(p -> "-" + p);
		};
	}
	
	
}
