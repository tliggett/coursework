//TJ Liggett
//10_9_2020
//Homework 6
//Demo.java

/*
 *	Class Demo prints all the employees by department sorted by salary      
 *
 */

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.function.Function;

public class Demo
{
	public static void main(String[] args)
   	{
		Employee[] employees = {new Employee("Jason", "Red", 5000, "IT"),
         				new Employee("Ashley", "Green", 7600, "IT"),
         				new Employee("Matthew", "Indigo", 3587.5, "Sales"),
         				new Employee("James", "Indigo", 4700.77, "Marketing"),
         				new Employee("Luke", "Indigo", 6200, "IT"),
         				new Employee("Jason", "Blue", 3200, "Sales"),
         				new Employee("Wendy", "Brown", 4236.4, "Marketing")};
      		List<Employee> list = Arrays.asList(employees);
      		
		Function<Employee, Double> bySalary = Employee::getSalary;
		Comparator<Employee> salary = Comparator.comparing(bySalary);
		
		System.out.printf("\nEmployees by department:\n");
      		Map<String, List<Employee>> m =
         		list.stream().collect(Collectors.groupingBy(Employee::getDepartment));
		m.forEach((department, employeesInDepartment) ->
			{
            			System.out.printf("\n%s\n", department);
           			employeesInDepartment
					.stream()
					.sorted(salary)
					.forEach(employee ->
						System.out.printf("   %s\n", employee));
         		}
      		);
   	}
}
