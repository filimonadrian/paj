package com.luxoft.effectivejava.module02.item09;

import java.util.HashMap;
import java.util.Map;

public final class PhoneNumber {
	private final short areaCode;
	private final short prefix;
	private final short lineNumber;

	public PhoneNumber(int areaCode, int prefix, int lineNumber) {
		rangeCheck(areaCode, 999, "area code");
		rangeCheck(prefix, 999, "prefix");
		rangeCheck(lineNumber, 9999, "line number");
		this.areaCode = (short) areaCode;
		this.prefix = (short) prefix;
		this.lineNumber = (short) lineNumber;
	}

	private static void rangeCheck(int arg, int max, String name) {
		if (arg < 0 || arg > max)
			throw new IllegalArgumentException(name + ": " + arg);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneNumber other = (PhoneNumber) obj;
		if (areaCode != other.areaCode)
			return false;
		if (lineNumber != other.lineNumber)
			return false;
		if (prefix != other.prefix)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + areaCode;
		result = prime * result + lineNumber;
		result = prime * result + prefix;
		return result;
	}
	
	/**
	 * Returns the string representation of this phone number. The string
	 * consists of fourteen characters whose format is "(XXX) YYY-ZZZZ", where
	 * XXX is the area code, YYY is the prefix, and ZZZZ is the line number.
	 * (Each of the capital letters represents a single decimal digit.)
	 * 
	 * If any of the three parts of this phone number is too small to fill up
	 * its field, the field is padded with leading zeros. For example, if the
	 * value of the line number is 123, the last four characters of the string
	 * representation will be "0123".
	 * 
	 * Note that there is a single space separating the closing parenthesis
	 * after the area code from the first digit of the prefix.
	 */
	@Override
	public String toString() {
		return String.format("(%03d) %03d-%04d", areaCode, prefix, lineNumber);
	}

	public static void main(String[] args) {
		Map<PhoneNumber, String> m = new HashMap<PhoneNumber, String>();
		m.put(new PhoneNumber(707, 867, 5309), "Jenny");
		System.out.println(m);
	}
	
	


}
