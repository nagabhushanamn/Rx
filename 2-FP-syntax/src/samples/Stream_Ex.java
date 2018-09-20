package samples;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

class MyList<E> implements Iterable<E> {
	// ..
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public E next() {
				return null;
			}
		};
	}
}

public class Stream_Ex {

	public static void main(String[] args) {
		

		List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		// ---------------------------------------------------------------------
		// 1. External Iteration
		// ---------------------------------------------------------------------

		// way-1 : for-loop
		for (int i = 0; i < integers.size(); i++) {
			Integer n = integers.get(i);
			System.out.println(n);
		}

		// way-2 : iterator
		Iterator<Integer> iterator = integers.iterator();
		while (iterator.hasNext()) {
			Integer integer = (Integer) iterator.next();
			System.out.println(integer);

		}

		// way-3 : for-loop
		for (Integer i : integers) {
			System.out.println(i);
		}

		//
		MyList<Integer> myList = new MyList<Integer>();
		for (Integer e : myList) {

		}

		// ---------------------------------------------------------------------
		// 2. Internal Iteration
		// ---------------------------------------------------------------------

		System.out.println(Runtime.getRuntime().availableProcessors());

		// way-4 : by stream lib

		integers.stream() 
				.parallel().filter(n -> {
					//System.out.println(Thread.currentThread() + "=>" + n);
					return n % 2 == 0;
				}).map(n -> n * 2)
				.forEachOrdered(n -> System.out.println(n));

		// ----------------------------------------------------------------------

	}

}
