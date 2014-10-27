package org.kay.webservice;

import org.springframework.stereotype.Component;

@Component("catWebService")
public class CatWebService {

	public String say() {
		return "Congratulations to you!";
	}
	
	public void show(String str) {
		System.out.println("OK");
	}
	
	
}
