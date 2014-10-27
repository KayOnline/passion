package org.kay.framework.security;

import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import org.kay.entity.SysUser;
import org.kay.framework.config.ApplicationConfig;
import org.kay.framework.persistence.util.BeanLocator;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;
import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;

public class ExtAuthenticationProvider extends DaoAuthenticationProvider {

	private static final Logger logger = Logger.getLogger(ExtAuthenticationProvider.class);

	private CaptchaFilter captchaFilter;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new BadCredentialsException("No Username and/or Password Provided.");
		} else if (StringUtils.isEmpty(captchaFilter.getUserCaptchaResponse())) {
			throw new BadCredentialsException("Captcha Response is Empty");
		} else if (isCaptchaPassed()) {

			// WebAuthenticationDetails
			Object details = authentication.getDetails();
			WebAuthenticationDetails wad = (WebAuthenticationDetails) details;
			logger.info(wad.toString());

			Authentication successAuthentication = super.authenticate(authentication);

			// SysUser inherit from UserDetails
			SysUser sysUser = (SysUser) successAuthentication.getPrincipal();
			logger.info(sysUser.getPassword());

			return successAuthentication;
		} else {
			// return error to user
			logger.info("Invalid Captcha.");
			throw new BadCredentialsException("Invalid Captcha.");
		}
	}

	public CaptchaFilter getCaptchaFilter() {
		return captchaFilter;
	}

	public void setCaptchaFilter(CaptchaFilter captchaFilter) {
		this.captchaFilter = captchaFilter;
	}
	
	private boolean isCaptchaPassed() {
		boolean isCaptchaPassed = false;
		ApplicationConfig appConfig = BeanLocator.getBean("applicationConfig", ApplicationConfig.class);
		if (appConfig.isRunInDebugMode()) {
			// Send HTTP request to validate user's Captcha
			HttpServletRequest request = captchaFilter.getRequest();
			String userCaptchaResponse = captchaFilter.getUserCaptchaResponse();
			captchaFilter.setUserCaptchaResponse(null);
			isCaptchaPassed = SimpleImageCaptchaServlet.validateResponse(request, userCaptchaResponse);
		}
		return isCaptchaPassed;
	}

}
