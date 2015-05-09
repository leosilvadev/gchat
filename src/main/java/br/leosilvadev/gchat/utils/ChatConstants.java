package br.leosilvadev.gchat.utils;

import redis.clients.jedis.Jedis;

public class ChatConstants {

	public static final String SYSTEM_MAIL 		= "noreply@gchat.com";
	public static final String SYSTEM_MESSAGE 	= "System Message";

	public static void main(String[] args) {
		String chave = "ultimas_paginas_visitadas";
		String[] paginasVisitadas = { "/inicio", "/contato", "/sobre-mim", "/todos-os-posts", "/armazenando-dados-no-redis" };
		Jedis jedis = new Jedis("localhost");
		Long resultado = jedis.lpush(chave, paginasVisitadas);
		System.out.println(String.format("A lista %s contém %d elementos", chave, resultado));
	}

}
