package org.kay.service;

import org.springframework.util.StringUtils;

public class HelloWorldServiceImpl implements HelloWorldService {

	@Override
	public String sayHello(String name) {
		String words = "Hello,World!";
		if (StringUtils.hasText(name)) {
			words += name;
		}
		System.out.println(words);
		return words;
	}
}
