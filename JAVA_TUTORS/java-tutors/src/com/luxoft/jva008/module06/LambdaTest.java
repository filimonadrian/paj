package com.luxoft.jva008.module06;

import java.util.function.Function;

public class LambdaTest {
	String value = "hi";
	public String process(String s, Function<String, String> f) {
		return f.apply(s);
	}
	public String process2(LambdaTest t, Function<LambdaTest, String> f) {
		return f.apply(t);
	}

	public String addExclam(String s) {
		return s+"!";
	}
	public String addExclam2() {
		return value+"!";
	}
	public static String addQuestion(String s) {
		return s+"?";
	}

	public static void main(String[] args) {
		LambdaTest lambdaTest = new LambdaTest();
		String s = lambdaTest.process("hello",
				LambdaTest::addQuestion);
		System.out.println(lambdaTest.process2(lambdaTest, LambdaTest::addExclam2));
		System.out.println(s);
		System.out.println(lambdaTest.process("hello", x->x+"?"));
	}
}
