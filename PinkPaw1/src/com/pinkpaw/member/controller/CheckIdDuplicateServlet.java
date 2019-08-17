package com.pinkpaw.member.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.member.model.service.MemberService;
import com.pinkpaw.member.model.vo.Member;

/**
 * Servlet implementation class CheckIdDuplicateServlet
 */
@WebServlet("/member/checkIdDuplicate")
public class CheckIdDuplicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIdDuplicateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.인코딩처리
		request.setCharacterEncoding("utf-8");

		//1.파라미터핸들링
		String memberId = request.getParameter("memberId");
		
		System.out.println("서블릿 체크"+memberId);
		
		//2.업무로직
		Member m = new MemberService().selectOne(memberId);
		
		boolean isUsable = m==null?true:false;
		
		//3. view단 처리
		request.setAttribute("isUsable", isUsable);
		request.setAttribute("memberId", memberId);
		request.getRequestDispatcher("/WEB-INF/views/member/checkIdDuplicate.jsp")
			   .forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
