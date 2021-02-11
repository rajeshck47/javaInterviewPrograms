package com.stream.employee;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestEmployee {

	public static void main(String[] args) {

		List<Employee> employeeList = Arrays.asList(
				new Employee(1,"Rajesh", 32, "rajesh@gmail.com", 47000,"software",Arrays.asList("8169447937", "839409467")),
				new Employee(2,"Mahesh", 29, "mahesh@gmail.com", 6000,"finance",Arrays.asList("91445935", "7948559447")),
				new Employee(3,"Vidya", 27, "vidya@gmail.com", 17000,"admin",Arrays.asList("869493", "89454")),
				new Employee(4,"Rajesh", 18, "rajesh@gmail.com", 10000,"finance",Arrays.asList("94937", "8946069767")),
				new Employee(5,"Vikram", 6, "vikram@gmail.com", 15000,"software",Arrays.asList("81937", "94068")),
				new Employee(6,"Keshav", 22, "keshav@gmail.com", 25000,"finance",Arrays.asList("9164937447", "9478683067")),
				new Employee(7,"Kiran", 22, "kiran@gmail.com", 51000,"admin",Arrays.asList("91937447", "947683067")));

		// Given a list of employees, you need to filter all the employee whose age is
		// greater than 25 and print the employee names
		List<String> employeeFilteredList = employeeList.stream().filter(e -> e.getAge() > 25).map(Employee::getName)
				.collect(Collectors.toList());
		System.out.println(employeeFilteredList);
		System.out.println("--------------------------------------------------------------------------------1");
		// Given the list of employees, count number of employees with age 25
		long count = employeeList.stream().filter(e -> e.getAge() > 25).count();
		System.out.println("Number of employees with age 25 are : " + count);
		System.out.println("--------------------------------------------------------------------------------2");
		// Given the list of employees, find the employee with name �Rajesh�
		Optional<Employee> e1 = employeeList.stream().filter(e -> e.getName().equalsIgnoreCase("Mahesh")).findAny();
		if (e1.isPresent()) {
			System.out.println(e1.get());
		}
		System.out.println("--------------------------------------------------------------------------------3");
		// Given a list of employee, find maximum age of employee?
		OptionalInt max = employeeList.stream().mapToInt(Employee::getAge).max();
		if (max.isPresent()) {
			System.out.println("Maximum age of Employee: " + max.getAsInt());
		}
		System.out.println("--------------------------------------------------------------------------------4");
		// Given a list of employees, sort all the employee on the basis of age?
		employeeList.sort((e11, e22) -> e11.getAge() - e22.getAge());
		employeeList.forEach(System.out::println);
		System.out.println("--------------------------------------------------------------------------------5");
		// Join the all employee names with �,� using java 8?
		List<String> employeeNames = employeeList.stream().map(Employee::getName).collect(Collectors.toList());
		String employeeNamesStr = String.join(",", employeeNames);
		System.out.println("Employees are: " + employeeNamesStr);
		System.out.println("--------------------------------------------------------------------------------6");
		// Given the list of employee, group them by employee name?
		Map<String, List<Employee>> map = employeeList.stream().collect(Collectors.groupingBy(Employee::getName));
		map.forEach((name, employeeListTemp) -> System.out.println("Name: " + name + " ==>" + employeeListTemp));
		System.out.println("--------------------------------------------------------------------------------7");
		// Print all the phone numbers(Which is in List of List)
		List<List<String>> employeePhone = employeeList.stream().map(Employee::getPhone).collect(Collectors.toList());
		System.out.println(employeePhone);
		System.out.println("--------------------------------------------------------------------------------8");
		// Print all the phone numbers in one List(flattening)
		List<String> flatteredPhone = employeeList.stream().flatMap(employee ->employee.getPhone().stream()).collect(Collectors.toList());
		System.out.println(flatteredPhone);
		System.out.println("--------------------------------------------------------------------------------9");
		// Print all the phone numbers which is greater than equal to 10
		List<String> exact10DigitPhoneNo = employeeList.stream().flatMap(employee ->employee.getPhone().stream().filter(phone->phone.length() >=10)).collect(Collectors.toList());
		System.out.println(exact10DigitPhoneNo);
		System.out.println("--------------------------------------------------------------------------------10");
		// Print all the email present in employee list.
		List<String> employeeEmail = employeeList.stream().map(Employee::getEmail).collect(Collectors.toList());
		System.out.println(employeeEmail);
		System.out.println("--------------------------------------------------------------------------------11");
		//Get the all employees in each department with employee count
        Map<String ,Long> deptCount=employeeList.stream().
                collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
        System.out.println(deptCount);
		System.out.println("--------------------------------------------------------------------------------12");
		//Top paid salary of an employee from each department (for minimum paid salary use minBy() instead maxBy()
		Map<String,Employee> groupList = employeeList.stream().
		collect(Collectors.groupingBy(e->e.getDepartment(),
		Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(e->e.getSalary())), Optional::get)));
		System.out.println(groupList);
		System.out.println("--------------------------------------------------------------------------------13");
		//Group by Department
        Map<String ,List<Employee>> groupByDepartment=employeeList.stream().
                collect(Collectors.groupingBy(e->e.getDepartment()));
        System.out.println(groupByDepartment);
		System.out.println("--------------------------------------------------------------------------------14");
		//Retrieve the list of list using flatMap
        List<List<String>> dupList= Arrays.asList(
                Arrays.asList("Rajesh"),
                Arrays.asList("mahesh"),
                Arrays.asList("kawali"),
                Arrays.asList("koli"));
        System.out.println(dupList.stream().flatMap(Collection::stream).collect(Collectors.toList()));
		System.out.println("--------------------------------------------------------------------------------15");
		//To make upper case
		List<String> list= Stream.of("a","b","c").map(String::toUpperCase).collect(Collectors.toList());
		System.out.println(list);
		System.out.println("--------------------------------------------------------------------------------16");
		//To get the Average of given numbers
		List<Integer> numbers = Arrays.asList(2, 8, 3, 4, 6, 7, 3, 1);
        IntSummaryStatistics summaryStatistics=numbers.stream().mapToInt((x)->x).summaryStatistics();
        System.out.println(summaryStatistics.getAverage());
		System.out.println("--------------------------------------------------------------------------------17");
		// To generate Random number only 4 times
		Random random=new Random();
        random.ints().limit(4).sorted().forEach(System.out::println);		
		System.out.println("--------------------------------------------------------------------------------18");
		//Group by names
		System.out.println(employeeList.stream().collect(Collectors.groupingBy(e->e.getName())));
		System.out.println("--------------------------------------------------------------------------------19");
		
		System.out.println("--------------------------------------------------------------------------------20");
	}
}
/*

[Rajesh, Mahesh, Vidya]
--------------------------------------------------------------------------------1
Number of employees with age 25 are : 3
--------------------------------------------------------------------------------2
Employee [id=2, name=Mahesh, age=29, email=mahesh@gmail.com, phone=[91445935, 7948559447]]
--------------------------------------------------------------------------------3
Maximum age of Employee: 32
--------------------------------------------------------------------------------4
Employee [id=5, name=Vikram, age=6, email=vikram@gmail.com, phone=[81937, 94068]]
Employee [id=4, name=Rajesh, age=18, email=rajesh@gmail.com, phone=[94937, 8946069767]]
Employee [id=6, name=Keshav, age=22, email=keshav@gmail.com, phone=[9164937447, 9478683067]]
Employee [id=7, name=Kiran, age=22, email=kiran@gmail.com, phone=[91937447, 947683067]]
Employee [id=3, name=Vidya, age=27, email=vidya@gmail.com, phone=[869493, 89454]]
Employee [id=2, name=Mahesh, age=29, email=mahesh@gmail.com, phone=[91445935, 7948559447]]
Employee [id=1, name=Rajesh, age=32, email=rajesh@gmail.com, phone=[8169447937, 839409467]]
--------------------------------------------------------------------------------5
Employees are: Vikram,Rajesh,Keshav,Kiran,Vidya,Mahesh,Rajesh
--------------------------------------------------------------------------------6
Name: Kiran ==>[Employee [id=7, name=Kiran, age=22, email=kiran@gmail.com, phone=[91937447, 947683067]]]
Name: Vidya ==>[Employee [id=3, name=Vidya, age=27, email=vidya@gmail.com, phone=[869493, 89454]]]
Name: Keshav ==>[Employee [id=6, name=Keshav, age=22, email=keshav@gmail.com, phone=[9164937447, 9478683067]]]
Name: Rajesh ==>[Employee [id=4, name=Rajesh, age=18, email=rajesh@gmail.com, phone=[94937, 8946069767]], Employee [id=1, name=Rajesh, age=32, email=rajesh@gmail.com, phone=[8169447937, 839409467]]]
Name: Mahesh ==>[Employee [id=2, name=Mahesh, age=29, email=mahesh@gmail.com, phone=[91445935, 7948559447]]]
Name: Vikram ==>[Employee [id=5, name=Vikram, age=6, email=vikram@gmail.com, phone=[81937, 94068]]]
--------------------------------------------------------------------------------7
[[81937, 94068], [94937, 8946069767], [9164937447, 9478683067], [91937447, 947683067], [869493, 89454], [91445935, 7948559447], [8169447937, 839409467]]
--------------------------------------------------------------------------------8
[81937, 94068, 94937, 8946069767, 9164937447, 9478683067, 91937447, 947683067, 869493, 89454, 91445935, 7948559447, 8169447937, 839409467]
--------------------------------------------------------------------------------9
[8946069767, 9164937447, 9478683067, 7948559447, 8169447937]
--------------------------------------------------------------------------------10
[vikram@gmail.com, rajesh@gmail.com, keshav@gmail.com, kiran@gmail.com, vidya@gmail.com, mahesh@gmail.com, rajesh@gmail.com]
--------------------------------------------------------------------------------11
{software=2, admin=2, finance=3}
--------------------------------------------------------------------------------12
{software=Employee [id=1, name=Rajesh, age=32, email=rajesh@gmail.com, phone=[8169447937, 839409467]], admin=Employee [id=7, name=Kiran, age=22, email=kiran@gmail.com, phone=[91937447, 947683067]], finance=Employee [id=6, name=Keshav, age=22, email=keshav@gmail.com, phone=[9164937447, 9478683067]]}
--------------------------------------------------------------------------------13
{software=[Employee [id=5, name=Vikram, age=6, email=vikram@gmail.com, phone=[81937, 94068]], Employee [id=1, name=Rajesh, age=32, email=rajesh@gmail.com, phone=[8169447937, 839409467]]], admin=[Employee [id=7, name=Kiran, age=22, email=kiran@gmail.com, phone=[91937447, 947683067]], Employee [id=3, name=Vidya, age=27, email=vidya@gmail.com, phone=[869493, 89454]]], finance=[Employee [id=4, name=Rajesh, age=18, email=rajesh@gmail.com, phone=[94937, 8946069767]], Employee [id=6, name=Keshav, age=22, email=keshav@gmail.com, phone=[9164937447, 9478683067]], Employee [id=2, name=Mahesh, age=29, email=mahesh@gmail.com, phone=[91445935, 7948559447]]]}
--------------------------------------------------------------------------------14
[Rajesh, mahesh, kawali, koli]
--------------------------------------------------------------------------------15
[A, B, C]
--------------------------------------------------------------------------------16
4.25
--------------------------------------------------------------------------------17
-1553186722
-1256199495
-1146569637
-1099716978
--------------------------------------------------------------------------------18
{Kiran=[Employee [id=7, name=Kiran, age=22, email=kiran@gmail.com, phone=[91937447, 947683067]]], Vidya=[Employee [id=3, name=Vidya, age=27, email=vidya@gmail.com, phone=[869493, 89454]]], Keshav=[Employee [id=6, name=Keshav, age=22, email=keshav@gmail.com, phone=[9164937447, 9478683067]]], Rajesh=[Employee [id=4, name=Rajesh, age=18, email=rajesh@gmail.com, phone=[94937, 8946069767]], Employee [id=1, name=Rajesh, age=32, email=rajesh@gmail.com, phone=[8169447937, 839409467]]], Mahesh=[Employee [id=2, name=Mahesh, age=29, email=mahesh@gmail.com, phone=[91445935, 7948559447]]], Vikram=[Employee [id=5, name=Vikram, age=6, email=vikram@gmail.com, phone=[81937, 94068]]]}
--------------------------------------------------------------------------------19
--------------------------------------------------------------------------------20

*/
