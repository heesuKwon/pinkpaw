package com.pinkpaw.member.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.member.model.service.MemberService;
import com.pinkpaw.member.model.vo.Member;


/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet(urlPatterns="/member/memberEnrollEnd",
			name="MemberEnrollServlet")
public class MemberEnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩
		
		request.setCharacterEncoding("UTF-8");
		
		// 2. 파라미터 핸들링
		
		Date enrolldate = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		
		String today = df.format(enrolldate);
		
				
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String memberName = request.getParameter("memberName");
		String email = request.getParameter("email");
		
		
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		int reportCount = 0;
		String grade = "member";
		
		Member member = new Member(memberId, password, memberName, email, address, phone, today, reportCount, grade);
		
		System.out.println(member);
		
		
		
		// 3. 업무로직
		
		int result = new MemberService().insertMember(member);
		
		
		// 4. view단처리
		
		String loc = "/";
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		
		if(result>0)
			msg = "성공적으로 회원등록했습니다.";
		else 
			msg = "회원등록에 실패했습니다.";	
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.setAttribute("memberId", memberId);
		
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
