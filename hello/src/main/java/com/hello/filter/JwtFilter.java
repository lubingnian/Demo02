package com.hello.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

/*
 * 这个类声明了一个JWT过滤器类，从Http请求中提取JWT的信息，
 * 并使用了”secretkey”这个密匙对JWT进行验证
 */
public class JwtFilter extends
//GenericFilterBean 
		OncePerRequestFilter {

//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//			throws IOException, ServletException {
//		final HttpServletRequest request = (HttpServletRequest) req;
//		final HttpServletResponse response = (HttpServletResponse) res;
//
//		final String authHeader = request.getHeader("authorization");
//		
//		System.out.println(":" + authHeader);
//		
//		// If the Http request is OPTIONS then just return the status code 200
//		// which is HttpServletResponse.SC_OK in this code
//		if ("OPTIONS".equals(request.getMethod())) {
//			response.setStatus(HttpServletResponse.SC_OK);
//			chain.doFilter(req, res);
//		}
//		// Except OPTIONS, other request should be checked by JWT
//		else {
//
//			// Check the authorization, check if the token is started by "Bearer "
//			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//				throw new ServletException("Missing or invalid Authorization header");
//			}
//
//			// Then get the JWT token from authorization
//			final String token = authHeader.substring(7);
//
//			try {
//				// Use JWT parser to check if the signature is valid with the Key "secretkey"
//				final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
//
//				// Add the claim to request header
//				request.setAttribute("claims", claims);
//			} catch (final SignatureException e) {
//				throw new ServletException("Invalid token");
//			}
//
//			chain.doFilter(req, res);
//		}
//	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("authorization");
		// If the Http request is OPTIONS then just return the status code 200
		// which is HttpServletResponse.SC_OK in this code
		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			filterChain.doFilter(request, response);
		}
		// Except OPTIONS, other request should be checked by JWT
		else {
			// Check the authorization, check if the token is started by "Bearer "
			if (authHeader == null || authHeader.startsWith("Bearer ")) {
				throw new ServletException("Missing or invalid Authorization header");
			}

			// Then get the JWT token from authorization
			final String token = authHeader.substring(7);

			try {
				// Use JWT parser to check if the signature is valid with the Key "secretkey"
				final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();

				// Add the claim to request header
				request.setAttribute("claims", claims);
			} catch (final SignatureException e) {
				throw new ServletException("Invalid token");
			}
			filterChain.doFilter(request, response);
		}

	}
}
