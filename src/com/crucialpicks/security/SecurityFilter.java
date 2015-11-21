package com.crucialpicks.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crucialpicks.core.SessionInfo;

/**
 * Servlet Filter. Every server request goes through here. Check if the user has
 * a Tomcat Session as a result of successful login. If not, redirect to the
 * login page.
 * 
 * @author Steven
 */
@WebFilter(urlPatterns = { "/*" })
public class SecurityFilter implements Filter {
	private static Boolean DISABLE_FILTER = false;

	@SuppressWarnings("unused")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (DISABLE_FILTER) {
			chain.doFilter(request, response);
			return;
		}

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;

		String servletPath = servletRequest.getServletPath();

		//System.out.println("servletPath: " + servletPath);
		// Allow resources, etc to pass through.
		if (servletPath.startsWith("/js") || servletPath.startsWith("/lib") || servletPath.startsWith("/img") || servletPath.startsWith("/css")
				|| servletPath.startsWith("/login.jsp") || servletPath.startsWith("/signUp.jsp") || servletPath.startsWith("/servlets/AttemptSignUpServlet")
				|| servletPath.startsWith("/servlets/AttemptLoginServlet") || servletPath.startsWith("/servlets/LogoutServlet")) {
			chain.doFilter(request, response);
			return;
		}

		HttpSession tomcatSession = servletRequest.getSession();
		SessionInfo sessionInfo = SessionInfo.getSessionInfoFromSession(tomcatSession);
		if (sessionInfo == null) {
			//TODO make this logging
			System.out.println("Redirecting...");

			String redirectUrl = "/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
			rd.forward(request, response);
			return;
		}

		chain.doFilter(request, response);
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}