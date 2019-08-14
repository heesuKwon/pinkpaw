package com.pinkpaw.member.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet(name="MemberUpdateServlet", urlPatterns="/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.전송값에 한글이 있을 경우 인코딩처리해야함.
		//void javax.servlet.ServletRequest.setCharacterEncoding(String arg0) throws UnsupportedEncodingException
		request.setCharacterEncoding("UTF-8");//대소문자 상관없음. 요청한 view단의 charset값과 동일해야 한다.
		
		//2.전송값 꺼내서 변수에 기록하기.
		//String javax.servlet.ServletRequest.getParameter(String arg0)
		String memberId = request.getParameter("memberId");
		String memberName = request.getParameter("memberName");
		
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		Date enrolldate = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		
		String today = df.format(enrolldate);
		int reportCount = 0;
		
		String grade = "member";
		
		//체크박스같은 경우 선택된 복수의 값이 배열로 전달된다.
		//파라미터로 전달한 문자열배열이 null이면, NullPointerException유발.
		
		Member member = new Member(memberId, null, memberName, email, address, phone, today, reportCount, grade);

		System.out.println("입력한 회원정보 : "+member);
		
		//3.서비스로직호출
		int result = new MemberService().updateMember(member);
		
		//4. 받은 결과에 따라 뷰페이지 내보내기
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		//javascript/html에서 사용할 url은 contextPath를 포함한다.
		String loc = "/";

		if(result>0){
			msg = "성공적으로 회원정보를 수정했습니다.";
			loc = "/member/memberView?memberId="+memberId;
			
			//갱신된 회원정보를 session에 반영한다.
			Member memberLoggedIn
				= (Member)request.getSession()
						 		 .getAttribute("memberLoggedIn");
			if(!"admin".equals(memberLoggedIn.getMemberId())) {
				request.getSession()
					   .setAttribute("memberLoggedIn", 
							   		 new MemberService().selectOne(memberId));
			}
		}
		else 
			msg = "회원정보수정에 실패했습니다.";	
		
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
