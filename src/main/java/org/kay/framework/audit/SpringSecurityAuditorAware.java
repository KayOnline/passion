package org.kay.framework.audit;

import org.kay.entity.SysUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("springSecurityAuditorAware")
public class SpringSecurityAuditorAware implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		
		SysUser sysUser = null;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		Object obj = authentication.getPrincipal();
		if (obj instanceof SysUser) {
			sysUser = (SysUser) obj;
		}
		
		return sysUser.getUserCode();
	}

}
