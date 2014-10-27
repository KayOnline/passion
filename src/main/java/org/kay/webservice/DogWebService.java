package org.kay.webservice;

import org.springframework.stereotype.Component;

@Component("dogWebService")
public class DogWebService {
	public String shout(String name) {
		return "Dog name is :" + name;
	}
}
