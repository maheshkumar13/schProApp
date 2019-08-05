package scaits.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import scaits.util.Constants.FormatDates;


public class DU {
	private static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getDefault();

	public static Timestamp now() {
		Calendar zoneCalendar = Calendar.getInstance(DEFAULT_TIME_ZONE);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, zoneCalendar.get(Calendar.DATE));
		calendar.set(Calendar.MONTH, zoneCalendar.get(Calendar.MONTH));
		calendar.set(Calendar.YEAR, zoneCalendar.get(Calendar.YEAR));
		calendar.set(Calendar.HOUR, zoneCalendar.get(Calendar.HOUR));
		calendar.set(Calendar.HOUR_OF_DAY, zoneCalendar.get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.AM_PM, zoneCalendar.get(Calendar.AM_PM));
		calendar.set(Calendar.MINUTE, zoneCalendar.get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, zoneCalendar.get(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, zoneCalendar.get(Calendar.MILLISECOND));
		return new Timestamp(calendar.getTime().getTime());
	}

	public static Timestamp addWorkingDays(Timestamp date, int days) {
		return addWorkingDays(date, days, DEFAULT_TIME_ZONE);
	}

	public static Timestamp addWorkingDays(Timestamp date, int days, TimeZone zone) {
		if (days == 0) {
			return date;
		}
		Calendar oldCal = new GregorianCalendar(zone);
		oldCal.setTime(date);
		int oldWeekDay = oldCal.get(Calendar.DAY_OF_WEEK);
		Timestamp newDay = addDays(date, days, zone);
		Calendar newCal = new GregorianCalendar(zone);
		newCal.setTime(newDay);
		int newWeekDay = newCal.get(Calendar.DAY_OF_WEEK);
		if (newWeekDay <= oldWeekDay) {
			return addDays(newDay, 2 * (days / 7), zone);
		} else if (newWeekDay == 7) {
			return addDays(newDay, 1 + (2 * (days / 7 - 1)), zone);
		}
		return newDay;
	}

	public static Timestamp addDaysAndHours(Timestamp date, int days, int hours) {
		return addDaysAndHours(date, days, hours, DEFAULT_TIME_ZONE);
	}

	public static Timestamp addDaysAndHours(Timestamp date, int days, int hours, TimeZone zone) {
		Calendar newDate = new GregorianCalendar(zone);
		newDate.setTime(date);
		newDate.add(Calendar.DATE, days);
		newDate.add(Calendar.HOUR, hours);
		return new Timestamp(newDate.getTime().getTime());
	}

	public static Timestamp addDays(Timestamp date, int days) {
		return addDays(date, days, DEFAULT_TIME_ZONE);
	}

	public static Timestamp addDays(Timestamp date, int days, TimeZone zone) {
		Calendar tomorrow = new GregorianCalendar(zone);
		tomorrow.setTime(date);
		tomorrow.add(Calendar.DATE, days);
		return new Timestamp(tomorrow.getTime().getTime());
	}

	private static Timestamp getWeekDay(int weekday) {
		return getWeekDay(weekday, DEFAULT_TIME_ZONE);
	}

	private static Timestamp getWeekDay(int weekday, TimeZone zone) {
		Calendar today = new GregorianCalendar(zone);
		int diffDays = weekday - today.get(Calendar.DAY_OF_WEEK);
		today.add(Calendar.DATE, diffDays);
		Timestamp newTime = new Timestamp(today.getTime().getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return Converter.getTimestamp(formatter.format(newTime), "MM/dd/yyyy", zone);
	}

	public static Timestamp getWeekDay(Timestamp day, int weekday) {
		return getWeekDay(day, weekday, DEFAULT_TIME_ZONE);
	}

	public static Timestamp getWeekDay(Timestamp day, int weekday, TimeZone zone) {
		Calendar today = new GregorianCalendar(zone);
		today.setTime(day);
		int diffDays = weekday - today.get(Calendar.DAY_OF_WEEK);
		today.add(Calendar.DATE, diffDays);
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		formatter.setTimeZone(zone);
		Timestamp newTime = new Timestamp(today.getTime().getTime());
		return Converter.getTimestamp(formatter.format(newTime));
	}

	public static int getDifferenceDays(Timestamp day1, Timestamp day2) {
		if (day1 == null || day2 == null) {
			return 0;
		}
		int diff = 0;
		diff = (int) ((day1.getTime() - day2.getTime()) / (1000 * 60 * 60 * 24));
		return diff;
	}

	public static int getDifferenceSeconds(Date day1, Date day2) {
		if (day1 == null || day2 == null) {
			return 0;
		}
		int diff = 0;
		diff = (int) ((day1.getTime() - day2.getTime()) / (1000));
		return diff;
	}

	public static boolean isWeekDay(Timestamp day, int weekDay) {
		return isWeekDay(day, weekDay, DEFAULT_TIME_ZONE);
	}

	public static boolean isWeekDay(Timestamp day, int weekDay, TimeZone zone) {
		Calendar today = new GregorianCalendar(zone);
		today.setTime(day);
		return today.get(Calendar.DAY_OF_WEEK) == weekDay;
	}

	public static Timestamp getCWRFromDate() {
		if (isWeekDay(now(), 1)) {
			return addDays(getWeekDay(2), -7);
		}
		return getWeekDay(2);
	}

	public static Timestamp getCWREndDate() {
		if (isWeekDay(now(), 1)) {
			return getWeekDay(2);
		}
		return addDays(getWeekDay(2), 7);
	}

	public static Timestamp getLWRFromDate() {
		if (isWeekDay(now(), 1)) {
			return addDays(getWeekDay(2), -14);
		}
		return addDays(getWeekDay(2), -7);
	}

	public static Timestamp getLWREndDate() {
		if (isWeekDay(now(), 1)) {
			return addDays(getWeekDay(2), -7);
		}
		return getWeekDay(2);
	}

	public static Timestamp getPastLWRFromDate() {
		if (isWeekDay(now(), 1)) {
			return addDays(getWeekDay(2), -21);
		}
		return addDays(getWeekDay(2), -14);
	}

	public static Timestamp getPastLWREndDate() {
		if (isWeekDay(now(), 1)) {
			return addDays(getWeekDay(2), -14);
		}
		return addDays(getWeekDay(2), -7);
	}

	public static Timestamp getCurrentMonth() {
		Calendar zoneCalendar = Calendar.getInstance(DEFAULT_TIME_ZONE);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, zoneCalendar.get(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, zoneCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.YEAR, zoneCalendar.get(Calendar.YEAR));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTime().getTime());
	}

	public static Timestamp getNextMonth() {
		Calendar zoneCalendar = Calendar.getInstance(DEFAULT_TIME_ZONE);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, zoneCalendar.get(Calendar.MONTH) + 1);
		calendar.set(Calendar.DAY_OF_MONTH, zoneCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.YEAR, zoneCalendar.get(Calendar.YEAR));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTime().getTime());
	}

	// This gives date in format which client wants
	public static String format(Date date) {
		return format(date, "dd/MM/yyyy-HH:mm:ss");
	}

	public static String formatStr(Date date) {
		return format(date, "dd-MMM-yyyy");
	}

	public static String format(Date date, String format) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	// This gives date in format which client wants
	public static Date parse(String dateString) {
		return parse(dateString, "dd/MM/yyyy-HH:mm:ss");
	}

	public static Date parse(String dateString, String format) {
		if (format != null && format.equals("dd-MM-yyyy") && dateString != null && !dateString.contains("-")) {
			format = FormatDates.DATEFORMAT;
		}
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static Date parse(long timeInSecs) {
		Date date = new Date();
		date.setTime(timeInSecs * 1000);
		return date;
	}

	public static Date addDays(Date date, int days, boolean tillLastMinOfDay) {
		Calendar tomorrow = new GregorianCalendar(DEFAULT_TIME_ZONE);
		tomorrow.setTime(date);
		tomorrow.add(Calendar.DATE, days);
		if (tillLastMinOfDay) {
			tomorrow.set(Calendar.AM_PM, 0);
			tomorrow.set(Calendar.HOUR_OF_DAY, 23);
			tomorrow.set(Calendar.MINUTE, 59);
			tomorrow.set(Calendar.SECOND, 59);
		}
		return tomorrow.getTime();
	}

	public static int getYear(Date date) {
		Calendar today = new GregorianCalendar(DEFAULT_TIME_ZONE);
		today.setTime(date);
		return today.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		Calendar today = new GregorianCalendar(DEFAULT_TIME_ZONE);
		today.setTime(date);
		return today.get(Calendar.MONTH);
	}

	public static int getDay(Date date) {
		Calendar today = new GregorianCalendar(DEFAULT_TIME_ZONE);
		today.setTime(date);
		return today.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDate(Date date) {
		Calendar today = new GregorianCalendar(DEFAULT_TIME_ZONE);
		today.setTime(date);
		return today.get(Calendar.DAY_OF_MONTH);
	}

	public static Date getStartDateOfMonth(Date date, boolean fromFirstMinOfDay) {
		Calendar cal = new GregorianCalendar(DEFAULT_TIME_ZONE);
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		if (fromFirstMinOfDay) {
			cal.set(Calendar.AM_PM, 0);
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
		}
		date = cal.getTime();
		return date;
	}

	public static Date getEndDateOfMonth(Date date, boolean tillLastMinOfDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		cal.add(Calendar.DATE, -1);
		if (tillLastMinOfDay) {
			cal.set(Calendar.AM_PM, 0);
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
		} else {
			cal.set(Calendar.AM_PM, 0);
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
		}
		date = cal.getTime();
		return date;
	}

	public static Date getEndOfDay(Date date) {
		Date todayEnd = addDays(date, 1, true);
		todayEnd = addDays(todayEnd, -1, true);
		return todayEnd;
	}

	public static Date addMonths(Date date, int months) {
		Calendar cal = new GregorianCalendar(DEFAULT_TIME_ZONE);
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + months);
		// cal.add(Calendar.DATE, -1);
		date = cal.getTime();
		return date;
	}

	public static String logCurrentDate() {
		return DU.format(new Date(), "d MMM HH:mm:ss.SSS");
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getWeekDayCount(DU.getStartDateOfMonth(DU.now(), true), DU.getEndDateOfMonth(DU.now(), true), Calendar.SUNDAY));
	}

	public static String dbDateFormate(String dateStrIn, String formate) {
		SimpleDateFormat sdfTarget = new SimpleDateFormat();
		String dateStrOut = "";
		try {
			sdfTarget.applyPattern("yyyy-MM-dd");
			dateStrOut = sdfTarget.format(DU.parse(dateStrIn, formate));
		} catch (Exception e) {
			dateStrOut = DU.format(new Date(), "yyyy-MM-dd");
			System.out.println("In Exception, startDate: " + dateStrOut);
		}
		return dateStrOut;
	}

	public static String hourAndMinFormat(int minute) {

		String result = "00";
		int hour = 0;
		if (60 < minute) {
			hour = minute / 60;
			minute = minute % 60;
		}
		result = (hour > 9 ? hour : "0" + hour) + ":" + (minute > 9 ? minute : "0" + minute);
		return result;
	}

	public static int getMonthDays(Timestamp now) {
		return getEndDateOfMonth(now, false).getDate();
	}

	public static int getWeekDayCount(Date startDate, Date endDate, int weekDay) {
		int cnt = 0;
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		while (start.before(end)) {
			if (start.get(Calendar.DAY_OF_WEEK) == weekDay) {
				cnt++;
			}
			start.add(Calendar.DATE, 1);
		}
		return cnt;
	}

	public static int getDifferenceDays(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return 0;
		}
		int diff = 0;
		diff = (int) ((startDate.getTime() - endDate.getTime()) / (1000 * 60 * 60 * 24));
		return diff;
	}

	public static int getDifferenceMonths(Date startDate, Date endDate) {
		LocalDate pdate = LocalDate.of(getYear(endDate), getMonth(endDate) + 1, getDay(endDate));
		LocalDate now = LocalDate.of(getYear(startDate), getMonth(startDate) + 1, getDay(startDate));

		Period diff = Period.between(pdate, now);

		return diff.getMonths() + 1;

	}

	public static int getDifferenceInMonths(Date startDate, Date endDate) {
		int endMonth = getMonth(endDate) + 1;
		int startMonth = getMonth(startDate) + 1;

		int diff = startMonth - endMonth + 1;

		return diff;

	}

	public static Date trimTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		// cal.set(Calendar.ZONE_OFFSET,0);
		return cal.getTime();
	}

	public static Date addDaysAndHours(Date date, int days, int hours) {
		return addDaysAndHours(date, days, hours, DEFAULT_TIME_ZONE);
	}

	public static Date addDaysAndHours(Date date, int days, int hours, TimeZone zone) {
		Calendar newDate = new GregorianCalendar(zone);
		newDate.setTime(date);
		newDate.add(Calendar.DATE, days);
		newDate.add(Calendar.HOUR, hours);
		return new Timestamp(newDate.getTime().getTime());
	}

	public static Date today() {
		Timestamp now = now();
		return now;
	}

	public static boolean isToday(Date otherDate) {
		Calendar otherCal = Calendar.getInstance();
		otherCal.setTime(otherDate);
		Calendar nowCal = Calendar.getInstance();

		if (otherCal.get(Calendar.DATE) == nowCal.get(Calendar.DATE) && otherCal.get(Calendar.MONTH) == nowCal.get(Calendar.MONTH)
				&& otherCal.get(Calendar.YEAR) == nowCal.get(Calendar.YEAR)) {
			return true;
		} else {
			return false;
		}
	}

	public static Date addMinutes(int minBefore) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minBefore);
		return now.getTime();
	}

	// add Minutes for recentAttemptTime
	public static Date addMinutes(Date date1, int minBefore) {
		Calendar now = Calendar.getInstance();
		now.setTime(date1);
		now.add(Calendar.MINUTE, minBefore);
		return now.getTime();
	}
	
	static String st1[] = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
	static int st2[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	public static int mac1(String n) {
		int digit = 0;
		try {
			if (Arrays.asList(st1).contains(n.toLowerCase())) {
				int a = Arrays.asList(st1).indexOf(n.toLowerCase());
				digit = st2[a];
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return digit;
	}

	public static boolean contains(final int[] array, final int key) {
		Arrays.sort(array);
		return Arrays.binarySearch(array, key) >= 0;
	}
	
	public static String mac(Character n) {
		String digit = "";
		try {
			int i = 0;
			for (int string : st2) {
				if (String.valueOf(n).equalsIgnoreCase(String.valueOf(string))) {
					digit = st1[i];
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return digit;
	}

	public static String DateToWords(String date) {

		String[] strList = date.split("-");
		String disStr = "";
		int i = 0;
		for (String string : strList) {
			char[] stringCha = string.toCharArray();
			String spaceStr = "";
			if (i > 0) {
				disStr += " / ";
			}
			for (char c : stringCha) {
				if (mac(c) != "" && mac(c).length() > 0) {
					disStr += spaceStr + mac(c);
					spaceStr = " ";
				}
			}
			i++;
		}
		return disStr.toUpperCase();
	}

}
