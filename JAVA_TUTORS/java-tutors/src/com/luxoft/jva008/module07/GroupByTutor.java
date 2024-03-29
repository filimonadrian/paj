package com.luxoft.jva008.module07;

import java.util.Map;
import java.util.Set;

import java.util.stream.Stream;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GroupByTutor {

	public static Stream<String> getStream() {
		return Stream.of("John", "Paul", "George", "Ringo", "Paul", "John", "Paul");
	}

	@Test
	public void testGroupBy() {
		
		// 1) Create a map (string value->amount of these values)
		// use Function.identity() as a classifier function for groupingBy
		Map<String, Long> collect = null;
		
		System.out.println(collect);
		assertEquals(collect.toString(), "{George=1, John=2, Ringo=1, Paul=3}");
		
		// 2) Create a map (string length->set of strings of this length)
		// use Collectors.toSet()
		Map<Integer, Set<String>> collect2 = null;
		
		System.out.println(collect2);
		assertEquals(collect2.toString(), "{4=[John, Paul], 5=[Ringo], 6=[George]}");
		
		// 3) Create a map (string length->string of comma separated values of this length)
		// use Collectors.joining() 
		// use stream.distinct() to remove duplicates
		Map<Integer, String> collect3 = null;
		
		System.out.println(collect3);
		assertEquals(collect3.toString(), "{4=John,Paul, 5=Ringo, 6=George}");
	}
	
}
