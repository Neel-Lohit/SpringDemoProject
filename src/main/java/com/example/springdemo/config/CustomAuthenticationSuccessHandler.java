package com.example.springdemo.config;

import com.example.springdemo.entity.Investor;
import com.example.springdemo.entity.User;
import com.example.springdemo.service.InvestorService;
import com.example.springdemo.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	protected final Log logger = LogFactory.getLog(this.getClass());

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private UserService userService;

	@Autowired
	private InvestorService investorService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		logger.info("\n\nIn customAuthenticationSuccessHandler\n\n");



		String userName = authentication.getName();
		
		logger.info("userName=" + userName);


		Map<String, String> roleTargetUrlMap = new HashMap<>();
		roleTargetUrlMap.put("ROLE_USER", "/userHome");
		roleTargetUrlMap.put("ROLE_INVESTOR", "/investorHome");
		roleTargetUrlMap.put("ROLE_ADMIN", "/");

		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {

			String authorityName = grantedAuthority.getAuthority();
			if(roleTargetUrlMap.containsKey(authorityName)) {

				if (authorityName.equals("ROLE_USER"))
				{
					User theUser = userService.findByUserName(userName);
					// now place in the session
					HttpSession session = request.getSession();
					session.setAttribute("user", theUser);
				}

				else if (authorityName.equals("ROLE_INVESTOR")){
					Investor theInvestor = investorService.findByUserName(userName);
					// now place in the session
					HttpSession session = request.getSession();
					session.setAttribute("investor", theInvestor);
				}

				String targetUrl = roleTargetUrlMap.get(authorityName);
				if (response.isCommitted()) {
					logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
					return;
				}

				redirectStrategy.sendRedirect(request, response, targetUrl);

			}
		}





		
		// forward to home page
	}

}
