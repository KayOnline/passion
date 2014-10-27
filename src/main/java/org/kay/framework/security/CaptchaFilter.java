package org.kay.framework.security;

import org.apache.log4j.Logger;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class CaptchaFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = Logger.getLogger(CaptchaFilter.class);

	private HttpServletRequest request;
	private String userCaptchaResponse;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

		if (httpServletRequest.getParameter("jcaptcha") != null) {
			this.request = httpServletRequest;
			this.userCaptchaResponse = httpServletRequest.getParameter("jcaptcha");
			LOGGER.info("userResponse: " + this.userCaptchaResponse);
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	public String getUserCaptchaResponse() {
		return userCaptchaResponse;
	}

	public void setUserCaptchaResponse(String userCaptchaResponse) {
		this.userCaptchaResponse = userCaptchaResponse;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
