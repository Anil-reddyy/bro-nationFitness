package com.bronationfitness.util;

import java.time.LocalDate;

public class DateUtils {
	 public static LocalDate addMonths(LocalDate date, int months) {
	        if (date == null) return null;
	        return date.plusMonths(months);
	    }

}
