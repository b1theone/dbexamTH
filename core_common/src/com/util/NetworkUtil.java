package com.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class NetworkUtil {

	/**
	 * 
	 * @param ip
	 * @return String
	 */
	public static String ipv4Check(String ip) {
		String result = "false";
		try {
			// 127.0.0.0/24, 192.168.10.0/28,...
			String patternIPv4N = "^((\\d|[1-9]\\d|2[0-4]\\d|25[0-5]|1\\d\\d)(?:\\.(\\d|[1-9]\\d|2[0-4]\\d|25[0-5]|1\\d\\d)){3}(?:\\/(\\d|[1-2]\\d|3[0-1])){1})$";
			// 127.0.0.1, 192.168.10.220, 203.111.34.91,...
			String patternIPv4S = "^((\\d|[1-9]\\d|2[0-4]\\d|25[0-5]|1\\d\\d)(?:\\.(\\d|[1-9]\\d|2[0-4]\\d|25[0-5]|1\\d\\d)){3})$";
			if (Pattern.matches(patternIPv4N, ip)) {
				result = "network";
			} else {
				if (Pattern.matches(patternIPv4S, ip)) {
					result = "single";
				} else {
					result = "false";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 
	 * @param ip
	 * @return String
	 */
	public static String ipv6Check(String ip) {
		String result = "false";

		try {
			String patternIPv6N = "^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\b)\\.){3}(\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\b)\\.){3}(\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\b)\\.){3}(\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))(?:\\/(\\d|[1-9]\\d|1[0-1]\\d|12[0-7])){1}$";
			String patternIPv6S = "^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\b)\\.){3}(\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\b)\\.){3}(\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\b)\\.){3}(\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))$";
			String patternNotNeed1 = "\\.";
			String patternNotSupportS = "^:{2}$";
			String patternNotSupportN = "^((\\:{2})(?:\\/(\\d|[1-9]\\d|1[0-1]\\d|12[0-7])){1})$";

			if (Pattern.matches(patternNotSupportN, ip)) {
				result = "network";
			} else {
				if (Pattern.matches(patternNotSupportS, ip)) {
					result = "single";
				} else {
					if (Pattern.matches(patternIPv6N, ip)) {
						if (Pattern.matches(patternNotNeed1, ip)) {
							result = "false";
						} else {
							result = "network";
						}
					} else {
						if (Pattern.matches(patternIPv6S, ip)) {
							if (Pattern.matches(patternNotNeed1, ip)) {
								result = "false";
							} else {
								result = "single";
							}
						} else {
							result = "false";
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Check port number format.
	 * 
	 * @param port
	 * @return true if collect format but return false if wrong.
	 */
	public static boolean isPort(String ports) {
		try {
			// except 5 port separate by comma, port number 0-65535.
			String pattern = "^((\\d|[1-9]\\d|[1-9]\\d\\d|[1-9]\\d\\d\\d|[1-5]\\d\\d\\d\\d|6[0-4]\\d\\d\\d|65[0-4]\\d\\d|655[0-2]\\d|6553[0-5])(?:\\,([1-9]|[1-9]\\d|[1-9]\\d\\d|[1-9]\\d\\d\\d|[1-5]\\d\\d\\d\\d|6[0-4]\\d\\d\\d|65[0-4]\\d\\d|655[0-2]\\d|6553[0-5])){0,4})$";
			return Pattern.matches(pattern, ports);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isPortExists(String ports) {
		try {
			if (ports != null && ports.length() > 0) {
				String[] items = ports.split(",");

				if (items.length > 1) {
					if (!items[0].equals("0")) {
						for (int i = 1; i < items.length - 1; i++) {
							if (items[0].equals(items[i])) {
								return false;
							}
						}
					} else {
						return false;
					}
				}

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String getNetmask(String prefix) {
		try {
			String result = "-1";
			if (prefix != null && !prefix.equals("")) {
				int pf = Integer.parseInt(prefix);

				if (pf >= 0 && pf <= 32) {
					StringBuilder builder = new StringBuilder();
					for (int i = 0; i < 32; i++) {
						if (i < pf) {
							builder.append("1");
						} else {
							builder.append("0");
						}
					}

					result = builder.toString();
				}
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
	}

	public static int ipFilter(String userIP, String permitIP, String prefix) {
		try {
			int match = 0;

			if (prefix.equals("0")) {
				prefix = "32";
			}

			String ipAddr = converIP2Bin(userIP);
			String ipMatch = converIP2Bin(permitIP);
			String netmask = getNetmask(prefix);

			// Error case:
			if (ipAddr.equals("-1") || ipMatch.equals("-1") || netmask.equals("-1")) {
				match = 0;
			} else {
				// If not error.
				String ipAddrXOR = bitXor(ipAddr, netmask);
				String ipMatchXOR = bitXor(ipMatch, netmask);
				if (ipAddrXOR.equals(ipMatchXOR)) {
					match = 1;
				}
			}

			return match;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static String converIP2Bin(String ip) {
		try {
			String[] ipCheck = ip.split("\\.");
			if (ipCheck.length < 4) {
				return "-1";
			}

			String[] ips = new String[4];
			for (int i = 0; i <= 3; i++) {
				ips[i] = Integer.toBinaryString(Integer.parseInt(ipCheck[i]));
				ips[i] = "00000000".substring(0, 8 - ips[i].length()) + ips[i];
			}

			String result = ips[0] + ips[1] + ips[2] + ips[3];

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
	}

	public static String bitXor(String obj1, String obj2) {
		try {
			String result = "";
			for (int i = 0; i < obj1.length(); i++) {
				int xor = Integer.parseInt(obj1.substring(i, i + 1)) & Integer.parseInt(obj2.substring(i, i + 1));
				result += xor + "";
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * @param url
	 * @param second
	 * @return
	 */
	public static boolean isURL(String url, int second) {
		try {
			boolean check = false;

			URL endpoint = new URL(url);

			if (url.indexOf("https") != -1) {
				disableSslVerification();
				
				HttpsURLConnection conn = (HttpsURLConnection) endpoint.openConnection();
				conn.setConnectTimeout(second * 1000);
				conn.setReadTimeout(second * 1000);
				check = (conn.getResponseCode() == HttpsURLConnection.HTTP_OK);
			} else {
				HttpURLConnection conn = (HttpURLConnection) endpoint.openConnection();
				conn.setConnectTimeout(second * 1000);
				conn.setReadTimeout(second * 1000);
				check = (conn.getResponseCode() == HttpURLConnection.HTTP_OK);
			}

			return check;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void disableSslVerification() {
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
	}
}
