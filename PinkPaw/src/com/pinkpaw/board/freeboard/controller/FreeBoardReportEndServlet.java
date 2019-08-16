package com.pinkpaw.board.freeboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.freeboard.model.service.FreeBoardService;
import com.pinkpaw.board.freeboard.model.vo.FreeBoard;



@WebServlet("/board/community/free/freeBoardReportEnd")
public class FreeBoardReportEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String origin;
    private static String newReport;
    private static int result;
	
    public FreeBoardReportEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//1. 파라미터 핸들링
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		String freeReportWriter = request.getParameter("reportWriter");
		String freeReportContent = request.getParameter("freeReportContent");
		freeReportContent = freeReportContent.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		
		FreeBoard freeBoard = new FreeBoardService().selectOneFreeBoard(freeNo);

		// 신고 사례가 없는 경우
		if(freeBoard.getFreeReportReason() == null) {
			newReport = freeReportContent;
			freeBoard.setFreeReportReason(newReport);
			
			result = new FreeBoardService().updateFreeBoardReport(freeBoard);
		}
		// 기존에 신고로 신고 사유가 존재하는 경우
		else if(freeBoard.getFreeReportReason().length() > 0) {
			origin = freeBoard.getFreeReportReason();
			newReport = ", " + freeReportContent;
			
			FreeBoard f = new FreeBoard();
			f = new FreeBoardService().selectOneFreeBoard(freeNo);
			f.setFreeReportReason(origin + newReport);
			
			result = new FreeBoardService().updateFreeBoardReport(f);
		}
						
		//2.서비스로직호출
		//로그인 체크
//		int result = memberService.loginCheck(freeReportWriter);
		
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
				loc = "/board/community/free/freeBoardReport?freeNo="+freeNo+"&close=true";
			}
//		}
		else {
			msg = "신고 실패.";
			loc = "/board/community/free/freeBoardReport?freeReportNo="+freeNo;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
