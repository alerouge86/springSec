package com.alerouge.kyivent.utility;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Utility per operazioni con date
 */
public class UtiData {

	private static final String FORMATO_DATA_EUROPE = "dd/MM/yyyy";
	private static final String FORMATO_ORA = "HH:mm";	// formato ora (24 ore)

	private UtiData(){
		/* costruttore privato */
	}

    /**
     * Formatta data da LocalDate a dd/MM/yyyy
     */
	private static String formatFromLocalDate(LocalDate data){
		DateTimeFormatter f = DateTimeFormatter.ofPattern(FORMATO_DATA_EUROPE);
		return data.format(f);
	}

	/**
	 * Converte una java.time.LocalDateTime in java.util.Date
	 */
	private static java.util.Date formatLocalDateTimeToDate(LocalDateTime parData){
		return java.util.Date
			      .from(parData.atZone(ZoneId.systemDefault())
			      .toInstant());
	}

	/**
	 * Converte una java.util.Date in java.time.LocalDateTime 
	 */
	private static LocalDateTime formatDateToLocalDateTime(java.util.Date parData) {
	    return parData.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}

    /**
     * Formatta ora da LocalTime a HH:mm
     */
	private static String formatFromLocalTime(LocalTime ora){
		DateTimeFormatter f = DateTimeFormatter.ofPattern(FORMATO_ORA);
		return ora.format(f);
	}

    /**
     * Ritorna time stamp (con millisecondi)
     */
	public static long timeStamp() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
		return Long.parseLong(dateTime.format(f));
    }

    /**
     * Ritorna data attuale in formato java.util.date (data + ora)
     */
    public static java.util.Date todayNow() {
    	LocalDateTime localDateTime = LocalDateTime.now();
    	return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Ritorna data attuale in formato dd/MM/yyyy
     */
    public static String todayStr(){
    	return formatFromLocalDate(LocalDate.now());
    }
    
    /**
     * Ritorna ora attuale in formato HH:mm
     */
    public static String now() {
    	return formatFromLocalTime(LocalTime.now());
    }
    
    /**
     * Formatta data da dd/MM/yyyy a yyyyMMdd
     */
    public static int formatDateNumeric(String data){
    	if (!UtiStringa.strVoid(UtiStringa.trimNotNull(data))){
    		String[] app = data.split("/");
    		return Integer.parseInt(app[2] + app[1] + app[0]);
    	}
    	return 0;
    }

    /**
     * Formatta ora da hh:mm a hhmm
     */
    public static int formatTimeNumeric(String ora){
    	if (!UtiStringa.strVoid(UtiStringa.trimNotNull(ora))){
    		return Integer.parseInt(ora.replace(":", ""));
    	}
    	return 0;
    }

	/**
	 * Ricava la data sql (yyyy-MM-dd) da una java.util.date
	 */
	public static String getSqlDateFromUtilDate(java.util.Date parData){
		LocalDateTime localDateTime = formatDateToLocalDateTime(parData);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return localDateTime.format(formatter);
	}

	/**
	 * Ricava la data string (dd/MM/yyyy) da una java.util.date
	 */
	public static String getDateStrFromUtilDate(java.util.Date parData){
		LocalDateTime localDateTime = formatDateToLocalDateTime(parData);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DATA_EUROPE);
		return localDateTime.format(formatter);
	}

	/**
	 * Ricava l'ora in formato (hh:mm) da una java.util.date
	 */
	public static String getTimeFromUtilDate(java.util.Date parData){
		LocalDateTime localDateTime = formatDateToLocalDateTime(parData);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_ORA);
		return localDateTime.format(formatter);
	}

    /**
     * Data data e ora in formato stringa, ritorna datetime (java.util.Date) 
     */
	public static java.util.Date convertInDateTime(String dataSql, String ora){
		// ricavo LocalDateTime
		int day = Integer.parseInt(dataSql.split("-")[2]);
		int month = Integer.parseInt(dataSql.split("-")[1]);
		int year = Integer.parseInt(dataSql.split("-")[0]);
		int hour = Integer.parseInt(ora.substring(0,2));
		int minute = Integer.parseInt(ora.substring(3,5));
		LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute);
		return formatLocalDateTimeToDate(localDateTime);
	}

	/**
	 * Ricava il giorno della settimana
	 */
	public static String getDayOfWeek(java.util.Date dataOra) {
		LocalDateTime localDateTime = formatDateToLocalDateTime(dataOra);
		DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
		return dayOfWeek.toString();
	}
	
}
