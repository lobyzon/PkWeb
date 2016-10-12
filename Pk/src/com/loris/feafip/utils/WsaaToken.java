package com.loris.feafip.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class WsaaToken {
	private String token;
	private String sign;
	private String generationTime;
	private String expirationTime;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getGenerationTime() {
		return generationTime;
	}
	public void setGenerationTime(String generationTime) {
		this.generationTime = generationTime;
	}
	public String getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}
	
	public static void main(String[] args) {
		String dateTA = "2016-08-21T11:26:22.648-03:00";
		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
		DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		DateTime expirationTime = dtf.parseDateTime(dateTA);
		DateTime actualDate = new DateTime();
		System.out.println(actualDate.compareTo(expirationTime) >= 0);
		System.out.println(new DateTime());
	}
}