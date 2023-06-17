package com.luxoft.effectivejava.module04.item21;

public enum Nationality {
	ENGLISH("English"), FRENCH("French");
	
	private String nationality;
	
	Nationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String getNationality() {
		return nationality;
	}

}
