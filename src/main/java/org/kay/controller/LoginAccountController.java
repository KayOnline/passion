package org.kay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("loginAccount")
public class LoginAccountController extends BaseController {

	@Override
	@RequestMapping("/")
	public String init() throws Exception {
		return "framework/right/loginAccount";
	}

}
