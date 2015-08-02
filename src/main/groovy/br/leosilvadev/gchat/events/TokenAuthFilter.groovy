package br.leosilvadev.gchat.events

import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor

import br.leosilvadev.gchat.auth.TokenValidator
import br.leosilvadev.gchat.security.TokenAuthentication

class TokenAuthFilter extends FilterSecurityInterceptor {

	private TokenValidator tokenValidator
	
	TokenAuthFilter(tokenValidator){
		this.tokenValidator = tokenValidator
	}
	
	@Override
	void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		def token = req.getHeader("X-Auth-Token")
		tokenValidator.validate(token, { user -> 
			SecurityContextHolder.getContext().setAuthentication(new TokenAuthentication(user, token, true))
			chain.doFilter(req, res)
			
		}, { ex -> 
			res.sendError(401, ex.getMessage())
		
		})
	}

	void init(FilterConfig filterConfig) {
	}

	void destroy() {
	}

}