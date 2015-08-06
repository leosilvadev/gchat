package br.leosilvadev.gchat.events

import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor

import br.leosilvadev.gchat.auth.services.TokenService
import br.leosilvadev.gchat.security.TokenAuthentication

class TokenAuthFilter extends FilterSecurityInterceptor {

	private final String[] permittedUrls
	private TokenService tokenService
	
	TokenAuthFilter(tokenService, permittedUrls){
		this.tokenService = tokenService
		this.permittedUrls = permittedUrls
	}
	
	@Override
	void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		def path = req.getRequestURI()
		println needPermission(req)
		needPermission(req) ? validateAccess(req, res, chain) : chain.doFilter(req, res)
	}
	
	private boolean needPermission(ServletRequest req){
		def path = req.getRequestURI()
		!permittedUrls.find { it.equalsIgnoreCase path }
	}
	
	private void validateAccess(ServletRequest req, ServletResponse res, FilterChain chain){
		def token = req.getHeader("X-Auth-Token")
		tokenService.validate token, { user ->
			SecurityContextHolder.getContext().setAuthentication(new TokenAuthentication(user, token, true))
			chain.doFilter req, res
			tokenService.renew token
			
		}, { ex ->
			res.sendError 401, ex.message
			
		}
	}

	void init(FilterConfig filterConfig) {
	}

	void destroy() {
	}

}