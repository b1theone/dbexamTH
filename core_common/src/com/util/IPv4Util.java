package com.util;

import java.util.regex.Pattern;

public class IPv4Util {

	// 127.0.0.0/24, 192.168.10.0/28,...
	private final static String ipv4N = "^((\\d|[1-9]\\d|2[0-4]\\d|25[0-5]|1\\d\\d)(?:\\.(\\d|[1-9]\\d|2[0-4]\\d|25[0-5]|1\\d\\d)){3}(?:\\/(\\d|[1-2]\\d|3[0-1])){1})$";
	// 127.0.0.1, 192.168.10.220, 203.111.34.91,...
	private final static String ipv4S = "^((\\d|[1-9]\\d|2[0-4]\\d|25[0-5]|1\\d\\d)(?:\\.(\\d|[1-9]\\d|2[0-4]\\d|25[0-5]|1\\d\\d)){3})$";

	public static boolean isNetwork(String ip) {
		try {
			if (Pattern.matches(ipv4N, ip))
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isAddress(String ip) {
		try {
			if (Pattern.matches(ipv4S, ip))
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isIP(String ip) {
		try {
			if (isNetwork(ip)) {
				return true;
			} else if (isAddress(ip)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
