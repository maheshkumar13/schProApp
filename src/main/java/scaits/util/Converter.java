package scaits.util;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author Administrator
 *
 */
public class Converter {
	
	/**
	 * 
	 */
	private static final String DEFAULT_DATE_PATTERN = "MM/dd/yyyy HH:mm:ss";
	private static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getDefault();
	
	public Converter() {
	}
	
	public static int getInt(String value, int defaultValue){
		int returnValue = defaultValue;
		try {
			returnValue = Integer.parseInt(value);
		} catch (Exception e) {}
		return returnValue;
	}
	public static int getInt(String value){
		return getInt(value,0);
	}
	public static float getFloat(String value,float defaultValue)
	{
		float returnValue=defaultValue;
		try{
		returnValue=Float.parseFloat(value);
		}catch(Exception e){}
		return returnValue;
	}
	public static float getFloat(String value){
		return getFloat(value,0.00f);
		
	}
	public static int getRoundValue(float f){
		return Math.round(f);
	}
	public static double getDouble(String value){
		return Double.parseDouble(value);
	}
	public static String getStringValue(float f) {
		return ""+f;
	}

	public static String getStringValue(Timestamp inTime) {
		return getStringValue(inTime,DEFAULT_DATE_PATTERN,DEFAULT_TIME_ZONE);
	}
	public static String getStringValue(java.util.Date inTime) {
		return getStringValue(inTime,DEFAULT_DATE_PATTERN,DEFAULT_TIME_ZONE);
	}
	public static String getStringValue(java.sql.Date inTime) {
		return getStringValue(inTime,DEFAULT_DATE_PATTERN,DEFAULT_TIME_ZONE);
	}
	public static String getStringValue(Calendar inTime) {
		return getStringValue(inTime,DEFAULT_DATE_PATTERN,DEFAULT_TIME_ZONE);
	}
	public static String getStringValue(Timestamp inTime, TimeZone zone) {
		return getStringValue(inTime,DEFAULT_DATE_PATTERN,zone);
	}
	public static String getStringValue(java.util.Date inTime, TimeZone zone) {
		return getStringValue(inTime,DEFAULT_DATE_PATTERN,zone);
	}
	public static String getStringValue(Calendar inTime, TimeZone zone) {
		return getStringValue(inTime,DEFAULT_DATE_PATTERN,zone);
	}
	public static String getStringValue(Timestamp inTime, String outPattern) {
		return getStringValue(inTime,outPattern,DEFAULT_TIME_ZONE);
	}
	public static String getStringValue(java.util.Date inTime, String outPattern) {
		return getStringValue(inTime,outPattern,DEFAULT_TIME_ZONE);
	}
	public static String getStringValue(Calendar inTime, String outPattern) {
		return getStringValue(inTime,outPattern,DEFAULT_TIME_ZONE);
	}
	public static Timestamp getTimestamp(String date){
		return getTimestamp(date,DEFAULT_DATE_PATTERN,DEFAULT_TIME_ZONE);
	}
	public static Timestamp getTimestamp(String date,String inPattern){
		return getTimestamp(date,inPattern,DEFAULT_TIME_ZONE);
	}
	public static Timestamp getTimestamp(String date,TimeZone zone){
		return getTimestamp(date,DEFAULT_DATE_PATTERN,zone);
	}
	public static String getStringValue(Timestamp inTime, String outPattern, TimeZone zone) {
		if(inTime==null) return "";
		SimpleDateFormat formatter = new SimpleDateFormat(outPattern);
		formatter.setTimeZone(zone);
		return formatter.format(inTime);
	}
	public static String getStringValue(java.util.Date inTime, String outPattern, TimeZone zone) {
		SimpleDateFormat formatter = new SimpleDateFormat(outPattern);
		formatter.setTimeZone(zone);
		return formatter.format(inTime);
	}
	public static String getStringValue(Calendar inTime, String outPattern, TimeZone zone) {
		SimpleDateFormat formatter = new SimpleDateFormat(outPattern);
		formatter.setTimeZone(zone);
		return formatter.format(inTime.getTime());
	}
	public static Timestamp getTimestamp(String date, String inPattern, TimeZone zone){
		Timestamp timestamp = null;
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat(inPattern);
			formatter.setTimeZone(zone);
			if(date!=null) timestamp = new Timestamp(formatter.parse(date).getTime());
		}catch (Exception ex){/*ex.printStackTrace();*/}
		return timestamp;
	}
	public static java.sql.Date getSQLDateValue(String str,java.sql.Date defaultValue){
		java.sql.Date val = defaultValue;
		try {
			//str should be in the format of yyyy-mm-dd
			val = java.sql.Date.valueOf(str);
		}catch(Exception err){
			val = defaultValue;	
		}
		return val;
	}
	public static TimeZone getDefaultTimeZone() {
		return TimeZone.getDefault();
	}
	public static TimeZone getTimeZone(String zone) {
		return TimeZone.getTimeZone(zone);
	}
	
	public static String getTextValue(int inValue){
		return ""+inValue;
	}
	public static String getTextValue(float inValue){
		return ""+inValue;
	}
	public static String getTextValue(String inValue){
		if(inValue==null) return "";
		else return inValue;
	}
	public static String getTextValue(Timestamp inValue){
		return getStringValue(inValue,"MM/dd/yyyy");
	}
	
	public static String getDisplayValue(String inValue){
		return getDisplayValue(inValue,"&nbsp;");
	}
	public static String getDisplayValue(String inValue,String defaultValue){
		if(inValue==null) return defaultValue;
		else return inValue;
	}
	public static String getDisplayValue(int inValue){
		return ""+inValue;
	}
	public static String getDisplayValue(float inValue){
		return ""+inValue;
	}
	public static String getDisplayValue(Timestamp inValue){
		return getDisplayValue(inValue,"&nbsp;");
	}
	public static String getDisplayValue(Timestamp inValue,String defaultValue){
		if(inValue==null) return defaultValue;
		return getStringValue(inValue,"MM/dd/yyyy");
	}
	public static String getString(String inValue){
		return inValue;
	}
	
	public static Integer getInteger(String inValue){
		if(inValue==null) return new Integer(0);
		else return new Integer(inValue);
	}
	public static Integer getInteger(int inValue){
		return new Integer(inValue);
	}
}
