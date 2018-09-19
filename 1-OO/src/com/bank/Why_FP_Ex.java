package com.bank;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.UnaryOperator;

class Lib {
	public static String replaceNonVeg(String item) {
		return item.equals("non-veg") ? "*" : item;
	}
}

public class Why_FP_Ex {

	public static void main(String[] args) {

		List<String> list = Arrays.asList("veg", "non-veg", "veg", "non-veg", "veg");

		// => replace all non-veg items by '*'

		// --------------------------------------------------------
		// imperative style
		// ------------------------------------------------------

		Iterator<String> iterator = list.iterator();
		int idx = 0;
		while (iterator.hasNext()) {
			String item = (String) iterator.next();
			if (item.equals("non-veg"))
				list.set(idx, "*");
			idx++;
		}

		// ------------------------------------------------------
		// declarative-style by Local-Inner-class
		// ------------------------------------------------------

		class ReplaceNonVegItem implements UnaryOperator<String> {
			@Override
			public String apply(String t) {
				if (t.equals("non-veg"))
					return "*";
				else
					return t;
			}
		}
		list.replaceAll(new ReplaceNonVegItem());

		// ------------------------------------------------------
		// declarative-style by Anonymous-Inner-class
		// ------------------------------------------------------
		UnaryOperator<String> operator = new UnaryOperator<String>() {
			@Override
			public String apply(String t) {
				if (t.equals("non-veg"))
					return "*";
				else
					return t;
			}
		};
		list.replaceAll(operator);

		// ------------------------------------------------------
		// declarative-style by function
		// ------------------------------------------------------

		UnaryOperator<String> operator1 = (String item) -> item.equals("non-veg") ? "*" : item;
		list.replaceAll(operator1);
		// or
		UnaryOperator<String> operator2 = (item) -> item.equals("non-veg") ? "*" : item;
		list.replaceAll(operator2);
		// or
		UnaryOperator<String> operator3 = item -> item.equals("non-veg") ? "*" : item;
		list.replaceAll(operator3);
		// or
		list.replaceAll(item -> item.equals("non-veg") ? "*" : item);

		// ------------------------------------------------------
		// declarative-style by Method-Reference
		// ------------------------------------------------------

		list.replaceAll(item -> Lib.replaceNonVeg(item));
		// or
		list.replaceAll(Lib::replaceNonVeg); // Method reference

		System.out.println(list);

		// ------------------------------------------------------

	}

}
