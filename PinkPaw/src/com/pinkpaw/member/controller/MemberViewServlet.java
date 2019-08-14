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
 * Servlet implementation class MemberViewServlet
 */
@WebServlet(urlPatterns="/member/memberView", 
			name="MemberViewServlet")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.인코딩
		request.setCharacterEncoding("utf-8");
		
		//1.파라미터핸들링
		String memberId = request.getParameter("memberId");
		
		//2.업무로직
		Member member = new MemberService().selectOne(memberId);
		
		//3.view단 처리
		String view = "";
		String loc = "";
		String msg = "";
		if(member != null){
			view = "/WEB-INF/views/member/memberView.jsp";
			request.setAttribute("member", member);
		}
		else {
			view = "/WEB-INF/views/common/msg.jsp"; 
			msg = "해당회원이 없습니다.";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}
		
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
