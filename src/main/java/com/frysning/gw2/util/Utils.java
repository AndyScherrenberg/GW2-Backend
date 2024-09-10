package com.frysning.gw2.util;

public class Utils {

	public static String withToken(String apiKey) {
		return "Bearer " + apiKey;
	}

	public static String removeLastChar(String s) {
		return (s == null || s.isEmpty())
				? null
				: (s.substring(0, s.length() - 1));
	}

}
