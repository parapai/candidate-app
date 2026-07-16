package com.vai.test.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ToolsUtils {

	private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public static long generateUuidToLong() {
	    long id = UUID.randomUUID().getMostSignificantBits();
	    return Math.abs(id); 
	}
	
	/**
     * Mengubah ZonedDateTime menjadi String dengan format yyyy-MM-dd HH:mm:ss
     * Mengembalikan null dengan aman jika objek tanggal bernilai null
     */
    public static String formatToString(ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) {
            return null;
        }
        return zonedDateTime.format(DEFAULT_FORMATTER);
    }
    
    /**
     * Overload method jika Anda ingin menggunakan format custom sewaktu-waktu
     */
    public static String formatToString(ZonedDateTime zonedDateTime, String pattern) {
        if (zonedDateTime == null || pattern == null) {
            return null;
        }
        return zonedDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
