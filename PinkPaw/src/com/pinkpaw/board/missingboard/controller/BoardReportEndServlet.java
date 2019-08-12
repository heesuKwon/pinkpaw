package com.pinkpaw.board.missingboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.missingboard.model.service.MissingService;
import com.pinkpaw.board.missingboard.model.vo.MissingBoard;
import com.pinkpaw.member.model.service.MemberService;
import com.pinkpaw.member.model.vo.Member;


/**
 * Servlet implementation class MemberUpdatePasswordEndServlet
 */
@WebServlet(urlPatterns="/board/missing/reportMissingEnd")
public class BoardReportEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MissingService memberService = new MissingService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReportEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 전송값 꺼내서 변수에 기록하기.
		//String javax.servlet.ServletRequest.getParameter(String arg0)
		String reportTitle = request.getParameter("reportTitle");
		//로그인유저
		String reportWriter = request.getParameter("reportWriter");
		int missingNo = Integer.parseInt(request.getParameter("missingNo"));
		String missingContent = request.getParameter("reportContent");
		missingContent = missingContent.replaceAll("<", "&lt;")
				   .replaceAll(">", "&gt;");
		
		MissingBoard b = new MissingBoard();
		String report = "제목:"+reportTitle+"작성자: "+reportWriter+"신고내용: "+missingContent;
		b = new MissingService().selectOne(missingNo);
		
		
		//기존에 신고사유가 존재할 경우
		if(b.getMissingReportReason().length() > 0)
		report = b.getMissingReportReason() + report;
		
		
		
		
		b.setMissingReportReason(report);
		int result = new MissingService().updateReport(b);
		
		//2.서비스로직호출
		//로그인 체크해야되여
//		int result = memberService.loginCheck(reportWriter);
		
		//3. 현재패스워드를 맞게 입력했으면, 비밀번호를 업데이트함. 
		//그렇지 않으면, 다시 팝업창 url을 호출함.
		String msg = "";
		String loc = "";
		String view = "/WEB-INF/views/common/msg.jsp";
//		if(result == 0){
//			//현재 member객체에 갱신할 비밀번호를 업데이트
//			member.setPassword(password_new);
//			result = memberService.updatePassword(member);
			if(result>0){
				msg = "신고 작성 성공";
//				String script = "self.close()";
////				//팝업창을 닫기위한 코드 추가
//				request.setAttribute("script",script);
				loc = "/board/missing/missingReport?missingNo="+missingNo+"&close=true";
			}
//				
//		}
		else {
			msg = "신고 실패.";
			loc = "/member/board/missingReport?missingNo="+missingNo;
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
