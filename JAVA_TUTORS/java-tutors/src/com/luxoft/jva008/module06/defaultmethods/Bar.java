package com.luxoft.jva008.module06.defaultmethods;

public interface Bar {
	
	default void someMethod(){
		System.out.println("Bar#someMethod");
	}
	
}
