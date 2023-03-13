package com.util;

import java.util.Random;

public class RandomUtil {

	public static String passwordGenerator(int length) throws Exception {
		String password = null;
		try {
			char[] chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
			StringBuilder sb = new StringBuilder();
			Random random = new Random();
			for (int i = 0; i < length; i++) {
				char c = chars[random.nextInt(chars.length)];
				sb.append(c);
			}

			password = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return password;
	}

	public static String randomNumber(String number, int length) throws Exception {
		String password = null;
		try {
			char[] chars = number.toCharArray();
			StringBuilder sb = new StringBuilder();
			Random random = new Random();
			for (int i = 0; i < length; i++) {
				char c = chars[random.nextInt(chars.length)];
				sb.append(c);
			}

			password = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return password;
	}
}
