package com.pinkpaw.member.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.member.model.service.MemberService;
import com.pinkpaw.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdatePasswordEndServlet
 */
@WebServlet(name="MemberUpdatePasswordEndServlet",urlPatterns="/member/updatePasswordEnd")
public class MemberUpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePasswordEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 전송값 꺼내서 변수에 기록하기.
		//String javax.servlet.ServletRequest.getParameter(String arg0)
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String password_new = request.getParameter("password_new");
		
//		System.out.println(memberId);
//		System.out.println(password);
//		System.out.println(password_new);
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setPassword(password);
		
		//2.서비스로직호출
		int result = new MemberService().loginCheck(member);
		
		//3. 현재패스워드를 맞게 입력했으면, 비밀번호를 업데이트함. 
		//그렇지 않으면, 다시 팝업창 url을 호출함.
		String msg = "";
		String loc = "";
		String view = "/WEB-INF/views/common/msg.jsp";
		if(result == MemberService.LOGIN_OK){
			//현재 member객체에 갱신할 비밀번호를 업데이트
			member.setPassword(password_new);
			result = new MemberService().updatePassword(member);
			if(result>0){
				msg = "패스워드 변경 성공";
				String script = "self.close()";
				//팝업창을 닫기위한 코드 추가
				request.setAttribute("script",script);
			}
				
		}
		else {
			msg = "패스워드를 잘못 입력하셨습니다.";
			loc = "/member/updatePassword?memberId="+memberId;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
