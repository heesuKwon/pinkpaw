package com.pinkpaw.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoggerFilter
 */
//WebFilter 대신 우선 순위를 정해주기 위해서 web.xml에 정의를 해주었다.(위에 쓸수록 먼저 실행)
//@WebFilter("/*")
public class LoggerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoggerFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//서블릿 처리 전
		HttpServletRequest httpReq = (HttpServletRequest)request;
		String uri = httpReq.getRequestURI();
		//http://localhost:9090/mvc/member/memberView?memberId=abcde
		
		System.out.println("==============================================");
		System.out.println(uri);// /mvc/member/memberView
		System.out.println("----------------------------------------------");
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		//서블릿 처리 후
		System.out.println("______________________________________________\n");

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
