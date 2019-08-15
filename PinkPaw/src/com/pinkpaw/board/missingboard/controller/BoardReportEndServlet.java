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


@WebServlet("/board/missing/reportMissingEnd")
public class BoardReportEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String origin;
    private static String newReport;
    private static int result;
    
    public BoardReportEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//1. 전송값 꺼내서 변수에 기록하기.
		//String javax.servlet.ServletRequest.getParameter(String arg0)
		int missingNo = Integer.parseInt(request.getParameter("missingNo"));
		String reportWriter = request.getParameter("reportWriter");
		String missingContent = request.getParameter("missingReportContent");
		missingContent = missingContent.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		
		MissingBoard missingBoard = new MissingService().selectOne(missingNo);
		
		//신고 사례가 없는 경우
		if(missingBoard.getMissingReportReason() == null) {
			newReport = missingContent;
			missingBoard.setMissingReportReason(newReport);
			
			result = new MissingService().updateReport(missingBoard);
		}
		// 기존에 신고로 신고 사유가 존재하는 경우
		else if(missingBoard.getMissingReportReason().length() > 0) {
			origin = missingBoard.getMissingReportReason();
			newReport = ", " + missingContent;
			
			MissingBoard m = new MissingBoard();
			m = new MissingService().selectOne(missingNo);
			m.setMissingReportReason(origin + newReport);
			
			result = new MissingService().updateReport(m);
		}
		
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
			if(result > 0){
				msg = "신고 작성 성공";
				loc = "/board/missing/missingReport?missingNo="+missingNo+"&close=true";
			}
//		}
		else {
			msg = "신고 실패.";
			loc = "/member/board/missingReport?missingNo="+missingNo;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
