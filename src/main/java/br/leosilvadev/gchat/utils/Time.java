package br.leosilvadev.gchat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

	private static final SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
	
	public static String now(){
		return formatter.format(new Date());
	}
	
}
