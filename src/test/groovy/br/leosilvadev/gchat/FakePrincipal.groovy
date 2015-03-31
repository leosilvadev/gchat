package br.leosilvadev.gchat

import java.security.Principal

class FakePrincipal implements Principal {
	
	private String name
	
	FakePrincipal(name){
		this.name = name;
	}
	
	String getName(){
		return name;
	}

}
