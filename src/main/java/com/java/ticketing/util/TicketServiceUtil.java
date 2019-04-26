package com.java.ticketing.util;

import java.util.regex.Pattern;

import com.java.ticketing.enums.TicketServiceConstants;

public class TicketServiceUtil {

	public static String getResponseCode(boolean result) {

		return result ? TicketServiceConstants.SUCCESS : TicketServiceConstants.FAILED;
	}

	public static boolean isEmailValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}
}
