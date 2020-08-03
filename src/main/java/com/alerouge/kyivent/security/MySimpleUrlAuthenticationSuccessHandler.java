package com.alerouge.kyivent.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alerouge.kyivent.model.session.UserLoggedSession;
import com.alerouge.kyivent.model.user.UserEntity;
import com.alerouge.kyivent.service.user.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component("myAuthenticationSuccessHandler")
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private IUserService userService;

	@Autowired
	private UserLoggedSession userLoggedSession;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	 

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.setMaxInactiveInterval(30 * 60);

            String username;
            if (authentication.getPrincipal() instanceof UserEntity) {
            	username = ((UserEntity)authentication.getPrincipal()).getEmail();
            }
            else {
            	username = authentication.getName();
            }
            UserEntity userLogged = userService.findUserByEmail(username);
            
            // setto utente in sessione
            userLoggedSession.init(userLogged);
            
            
            // dispatch home dopo il login
            String targetUrl = determineTargetUrl(authentication);
            
            redirectStrategy.sendRedirect(request, response, targetUrl);
            
            clearAuthenticationAttributes(request);
        }
    }
    
    protected String determineTargetUrl(final Authentication authentication) {
   	 
	    Map<String, String> roleTargetUrlMap = new HashMap<>();
//	    roleTargetUrlMap.put("ROLE_ADMIN", "/adminPage");
	 // questa è l'unica cosa che sono riuscito a fare. Purtroppo non ha nessun riferimento per andare in una pagina piuttosto che nelle altre.
	    roleTargetUrlMap.put("ROLE_USER", "/firstPage");
	 
	    final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	    for (final GrantedAuthority grantedAuthority : authorities) {
	        String authorityName = grantedAuthority.getAuthority();
	        if(roleTargetUrlMap.containsKey(authorityName)) {
	            return roleTargetUrlMap.get(authorityName);
	        }
	    }
	 
	    throw new IllegalStateException();
	}

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    if (session == null) {
	        return;
	    }
	    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}