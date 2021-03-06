package com.program.exception;

public class ExceptionChainingExp6 {

	public static void main(String[] args) {
		System.out.println("Start");
		try {
			test1();
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}
		System.out.println("main -end");
	}

	static void test1() {
		System.out.println("test1()");
		test2();
		System.out.println("1 end");
	}

	static void test2() {
		System.out.println("test2()");
		try{
			test3();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}

		System.out.println("2 end");
	}

	static void test3() throws ClassNotFoundException{
		System.out.println("test3()");

			test4();
		
		System.out.println("3 end");
	}

	static void test4() throws ClassNotFoundException{
		System.out.println("test4()");
		Class.forName(""); // Checked Exceptions.
		//int i = 10 / 0;
		System.out.println("4 end");
	}
}
