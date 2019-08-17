package com.pinkpaw.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.pinkpaw.member.model.vo.Member;


/**
 * 로그인한 사용자가 조회하고자 하는 사용자와 동일한지 검사하는 필터
 * 
 * 부정된 요청인 경우
 * "잘못된 경로로 접근하셨습니다." 사용자 메세지 전달
 */
@WebFilter(servletNames = { "MemberViewServlet" })
public class MemberAuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MemberAuthFilter() {
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
		
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpSession session = httpReq.getSession();
		//1. 로그인한 사용자
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		//2. 요청 한 사용자
		String memberId = request.getParameter("memberId");
				
		//로그인한 사용자와 정보를 요청하는 사용자 정보가 같은가 확인
		//관리자의 경우, 로그인한 사용자와 정보를 요청하는 정보가 같지 않아도 열람 가능
		if(memberLoggedIn == null || memberId == null ||
			!(memberId.equals(memberLoggedIn.getMemberId()) || "admin".equals(memberLoggedIn.getMemberId()))) {
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			request.setAttribute("loc", "/");
			
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			.forward(request, response);
	
			return;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
