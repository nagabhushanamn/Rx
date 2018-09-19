package samples;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.ToIntFunction;

import samples.model.Employee;

public class MethodReference_Ex {

	public static void main(String[] args) {

		// ----------------------------------------------------------------
		// 1. Instance Methods
		// ----------------------------------------------------------------

		// a. specified instance

		String preString = "ibm";
		IntSupplier intSupplier1 = () -> preString.length(); // lambda syntax
		// or
		IntSupplier intSupplier2 = preString::length; // MR syntax

		System.out.println(intSupplier2.getAsInt());

		// b. un-specified instance

		ToIntFunction<String> toIntFunction1 = s -> s.length();
		// or
		ToIntFunction<String> toIntFunction2 = String::length; // MR syntax
		System.out.println(toIntFunction2.applyAsInt("ibm"));
		System.out.println(toIntFunction2.applyAsInt("Nag"));

		BiFunction<String, String, String> biFunction1 = (s1, s2) -> s1.concat(s2);
		// or
		BiFunction<String, String, String> biFunction2 = String::concat; // MR syntax
		System.out.println(biFunction2.apply("hello", "world"));

		// ------------------------------------------------------------------
		// static methods
		// ----------------------------------------------------------------

		BinaryOperator<Integer> binaryOperator1 = (x, y) -> Integer.compare(x, y);
		// or
		BinaryOperator<Integer> binaryOperator2 = Integer::compare;
		System.out.println(binaryOperator2.apply(12, 13));

		// ------------------------------------------------------------------
		// constructor
		// ----------------------------------------------------------------

		BiFunction<String, Integer, Employee> biFunction11 = (name, age) -> new Employee(name, age);
		// or
		BiFunction<String, Integer, Employee> biFunction22 = Employee::new;
		Function<String, Employee> function = Employee::new;

		// ------------------------------------------------------------------

	}

}
