package samples;

import java.util.Comparator;
import java.util.function.Predicate;

import samples.model.Employee;

public class FP_Ex {

	public static Predicate<Integer> or(Predicate<Integer> predicate1, Predicate<Integer> predicate2) {
		return n -> predicate1.test(n) || predicate2.test(n);
	}

	public static void main(String[] args) {

		// Higher-Order-Programming by function-composition

		// Ex.1
		//----------------------------------------------------
		Predicate<Integer> predicate1 = n -> n < 100;
		Predicate<Integer> predicate2 = n -> n > 1000;

		Predicate<Integer> predicate = or(predicate1, predicate2);

		boolean b = predicate.test(500);
		System.out.println(b);

		// or
		
		Predicate<Integer> orPredicate = predicate1.or(predicate2);
		Predicate<Integer> andpredicate = predicate1.and(predicate2);

		// ----------------------------------------------------

		// Ex.2
		// ----------------------------------------------------

		Employee employee1 = new Employee("Nag", 36);
		Employee employee2 = new Employee("Nag", 35);

		Comparator<Employee> nameComparator = (o1, o2) -> {
			System.out.println("comparing by name...");
			return o1.getName().compareTo(o2.getName());
		};
		Comparator<Employee> ageComparator = (o1, o2) -> {
			System.out.println("comparing by age...");
			return Integer.compare(o1.getAge(), o2.getAge());
		};

		Comparator<Employee> newF = nameComparator.thenComparing(ageComparator);
		Comparator<Employee> reverseNewF = newF.reversed();

		int diff = reverseNewF.compare(employee1, employee2);
		System.out.println(diff);

		// ----------------------------------------------------

	}

}
