package com.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateTimeUtil {

	/**
	 * 
	 * @param inputDate
	 *            (String)
	 * @return Date (Locale : TH)
	 */
	public static Date parseInputCalendar(String inputDate) {
		Date result = null;
		try {
			if (inputDate != null && !"".equals(inputDate.trim())) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"));
				result = format.parse(inputDate);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static Date parseInputCalendar(String inputDate, Locale locale) {
		Date result = null;
		try {		
			if (inputDate != null && !"".equals(inputDate.trim())) {				
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", locale);
				result = format.parse(inputDate);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static Date parseInputCalendar(String inputDate, int hour, int minute, int second) {
		Date result = null;
		try {
			if (inputDate != null && !"".equals(inputDate.trim())) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"));
				Date date = format.parse(inputDate);

				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.set(Calendar.HOUR_OF_DAY, hour);
				cal.set(Calendar.MINUTE, minute);
				cal.set(Calendar.SECOND, second);
				cal.set(Calendar.MILLISECOND, 0);

				result = cal.getTime();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param inputDate
	 *            (Date)
	 * @return String (Locale : TH)
	 */
	public static String parseOutputCalendar(Date inputDate) {
		String result = "";
		try {
			if (inputDate != null) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"));
				result = df.format(inputDate);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public static String parseOutputCalendarEn(Date inputDate) {
		String result = "";
		try {
			if (inputDate != null) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "US"));
				result = df.format(inputDate);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @param inputDate
	 *            (Date)
	 * @param pattern
	 *            (String 'dd/MM/yyyy','d MMM yy',...)
	 * @param local
	 *            (Locale)
	 * @return String
	 */
	public static String parseOutputCalendar(Date inputDate, String pattern, Locale local) {
		String result = "";
		try {
			if (inputDate != null) {
				Locale locale = new Locale("th", "TH");
				if (local != null) {
					locale = local;
				}
				SimpleDateFormat df = new SimpleDateFormat(pattern, locale);
				result = df.format(inputDate);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static Calendar getDate2Calendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static XMLGregorianCalendar getDate2XMLGregorianCalendar(Date date) throws DatatypeConfigurationException {
		GregorianCalendar cGregory = new GregorianCalendar();
		cGregory.setTime(date);
		XMLGregorianCalendar cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cGregory);
		return cal;
	}

	/**
	 * 
	 * @return Date (Locale : EN)
	 */
	public static Date getSystemDate() {
		Calendar cal = Calendar.getInstance(new Locale("en", "US"));
		return cal.getTime();
	}

	public static Calendar getSystemCalendar() {
		return Calendar.getInstance(new Locale("en", "US"));
	}

	public static XMLGregorianCalendar getSystemXMLGregorianCalendar() throws DatatypeConfigurationException {
		GregorianCalendar cGregory = new GregorianCalendar();
		cGregory.setTime(getSystemDate());
		XMLGregorianCalendar cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cGregory);
		return cal;
	}

	public static String getMonthName(int month) {
		String[] data = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
		return data[month];
	}

	public static int getDaysInMonth(int month, int year) {
		Calendar cal = Calendar.getInstance(); // or pick another time zone if
												// necessary
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1); // 1st day of month
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		Date startDate = cal.getTime();

		int nextMonth = (month == Calendar.DECEMBER) ? Calendar.JANUARY : month + 1;
		cal.set(Calendar.MONTH, nextMonth);
		if (month == Calendar.DECEMBER) {
			cal.set(Calendar.YEAR, year + 1);
		}
		Date endDate = cal.getTime();

		// get the number of days by measuring the time between the first of
		// this
		// month, and the first of next month
		return (int) ((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return 1 (for Sunday) through 7 (for Saturday)
	 */
	public static int dayOfWeek(Integer year, Integer month, Integer day) {
		if (year != null && month != null && day != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, day);
			return calendar.get(Calendar.DAY_OF_WEEK);
		} else {
			return 0;
		}
	}

	/**
	 * 
	 * @param date
	 * @return 1 (for Sunday) through 7 (for Saturday)
	 */
	public static int dayOfWeek(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar.get(Calendar.DAY_OF_WEEK);
		} else {
			return 0;
		}
	}

	/**
	 * 
	 * @param date
	 * @return 0 (for Monday) through 6 (for Sunday)
	 */
	public static int dayOfWeekZero(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			switch (calendar.get(Calendar.DAY_OF_WEEK)) {
			case 1:
				return 6;
			case 2:
				return 0;
			case 3:
				return 1;
			case 4:
				return 2;
			case 5:
				return 3;
			case 6:
				return 4;
			case 7:
				return 5;
			default:
				return 0;
			}

		} else {
			return 0;
		}
	}

	/**
	 * 
	 * @param date
	 * @return 0 through 23
	 */
	public static int hourOfDate(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar.get(Calendar.HOUR_OF_DAY);
		} else {
			return 0;
		}
	}

	public static int dayBetweenDate(Date dateStart, Date dateEnd) {
		if (dateStart != null && dateEnd != null) {
			long day = Math.round((dateEnd.getTime() - dateStart.getTime()) / 86400000D);
			return Integer.parseInt(String.valueOf(day)) + 1;
		} else {
			return 0;
		}
	}

	public static int dayOfMonth(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.DAY_OF_MONTH);
		} else {
			return 0;
		}
	}

	public static int monthOfDate(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.MONTH);
		} else {
			return 0;
		}
	}

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	public static String getCurrentDDMMYYYYhh24miss_CEExcludeColonSlash() {
		String tsStr = getCurrentTimestamp().toString();
		tsStr = tsStr.substring(0, 4) + tsStr.substring(5, 7) + tsStr.substring(8, 10) + tsStr.substring(11, 19).replaceAll(":", "");
		return tsStr;
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return long
	 */
	public static long dateUTC2Time(int year, int month, int day, int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.set(year, (month - 1), day, hour, minute, second);
		return calendar.getTimeInMillis();
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return long
	 */
	public static long dateUTC2Time(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.set(year, (month - 1), day, 0, 0, 0);
		return calendar.getTimeInMillis();
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return Calendar
	 */
	public static Calendar calendarDate(int year, int month, int day, int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, (month - 1), day, hour, minute, second);
		return calendar;
	}

	/**
	 * 
	 * @param date
	 * @param local
	 * @param totalYear
	 * @return date
	 */
	public static Date dateNextYear(String date, Locale local, int totalYear) {
		try {
			if (local == null) {
				local = new Locale("th", "TH");
			}
			Calendar cal = Calendar.getInstance();
			Date today = DateTimeUtil.parseInputCalendar(date, local);
			cal.setTime(today);
			cal.add(Calendar.YEAR, totalYear); // to get previous year add -1
			return cal.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date parseInputCalendarEn(String inputDate) {
		Date result = null;
		try {		
			SimpleDateFormat ftus = new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "US"));
			 SimpleDateFormat ftth = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"));			
			 int yearnow = (Integer.parseInt(ftus.format(DateTimeUtil.getSystemDate()).substring(6, 10))+400);
			 int yearIn = Integer.parseInt(inputDate.substring(6, 10));
			 
			if (inputDate != null && !"".equals(inputDate.trim())) {
				 if(yearIn>yearnow){					 
					 result = ftth.parse(inputDate);
				 }else{
					 result =ftus.parse(inputDate);
				 }
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}


}
