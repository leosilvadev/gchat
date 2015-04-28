package br.leosilvadev.gchat.utils;

import java.util.UUID;

public class RandomString {
	
	public static String randomEmail(){
		String user = generate(6);
		String domain = generate(6);
		return user + "@" + domain + ".com";
	}
	
	private static String generate(int size){
		String value = UUID.randomUUID().toString();
		return value.substring(0, size);
	}
	
}
