package com.util;

import java.util.Date;
import java.util.regex.Pattern;

import javax.mail.internet.InternetAddress;

public class ValidateUtil {

	private static String regexInteger = "^[\\-\\+]?\\d+$";
	private static String regexNumber = "^[\\-\\+]?((([0-9]{1,3})([,][0-9]{3})*)|([0-9]+))?([\\.]([0-9]+))?$";

	/**
	 * 
	 * @param data
	 * @return true/false
	 */
	public static boolean isInteger(String data) {
		try {
			if (data != null && !data.equals("")) {
				return Pattern.matches(regexInteger, data);
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param data
	 * @return true/false
	 */
	public static boolean isNumber(String data) {
		try {
			if (data != null && !data.equals("")) {
				return Pattern.matches(regexNumber, data);
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param email
	 *            (format only)
	 * @return true/false
	 */
	public static boolean isEmailAddress(String email) {
		try {
			if (email != null && !email.equals("")) {
				InternetAddress address = new InternetAddress(email);
				address.validate();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param data
	 * @return true/false
	 */
	public static boolean isString(String data) {
		try {
			if (data != null && !data.equals("")) {
				if (data.trim().length() > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param data
	 * @return true/false
	 */
	public static boolean isDate(String data) {
		try {
			if (data != null && !data.equals("")) {
				if (data.trim().length() > 0) {
					Date date = DateTimeUtil.parseInputCalendar(data);
					if (date != null) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param min
	 * @param max
	 * @param data
	 * @return true/false
	 */
	public static boolean isBetween(double min, double max, double data) {
		try {
			if (data >= min && data <= max) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
