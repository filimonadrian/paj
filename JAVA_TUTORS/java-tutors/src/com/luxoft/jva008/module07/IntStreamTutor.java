package com.luxoft.jva008.module07;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class IntStreamTutor {

	public static IntStream intStream() {
		return IntStream.of(2,3,3,4);
	}
	
	public static void log(int i) {
		System.out.println(i);
	}
	
	public static void log(String i) {
		System.out.println(i);
	}
	
	
	/**
	 * Find and print:
	 * - max number in intStream
	 * - average number in intStream
	 * - list of distinct values: "2,3,4" (use distinct())
	 */
	@Test
	public void testIntStream() {
		
		int max = 0;
		log(max);
		assertEquals(max, 4);
		
		int avg = 0;
		log(avg);
		assertEquals(avg, 3);		
		
		String distinct = "";
		log(distinct);
		assertEquals(distinct, "2,3,4");		
		
	}
}
