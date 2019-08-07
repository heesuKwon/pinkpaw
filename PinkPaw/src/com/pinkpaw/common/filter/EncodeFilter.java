package com.pinkpaw.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodeFilter implements Filter{

	private FilterConfig filterConfig;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		System.out.println("EncodeFilter.init() 호출됨!!");
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String encodeType = filterConfig.getInitParameter("encodeType");
		//request객체 인코딩처리: 사용자가 요청하는 모든 요청에 대해 인코딩
//		request.setCharacterEncoding("utf-8");
		//web.xml에서 utf-8 지정해주기 (관리가 더 쉬움)
		request.setCharacterEncoding(encodeType);
		System.out.println("[[Encoding: "+encodeType+" 처리됨!!]]");
		
		//필터 체인 호출: 다음 필터가 있다면 다음 필터로 전달, 다음 필터가 없다면 서블릿 호출
		chain.doFilter(request, response);
		
		//서블릿 처리 후 작성할 내용
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
