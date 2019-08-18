package com.pinkpaw.board.dmboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.dmboard.model.service.DMService;
import com.pinkpaw.board.dmboard.model.vo.DM;
import com.pinkpaw.board.freeboard.model.service.FreeBoardService;
import com.pinkpaw.board.freeboard.model.vo.FreeBoard;

/**
 * Servlet implementation class DMReportEndServlet
 */
@WebServlet("/board/dm/dmReportEnd")
public class DMReportEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String origin;
    private static String newReport;
    private static int result;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DMReportEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1. 파라미터 핸들링
		int dmNo = Integer.parseInt(request.getParameter("dmNo"));
		String ReportWriter = request.getParameter("reportWriter");
		String ReportContent = request.getParameter("dmReportContent");
		ReportContent = ReportContent.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		int read = 1;
		DM dm = new DMService().selectOne(dmNo, read);

		// 신고 사례가 없는 경우
		if(dm.getDmReportReason() == null) {
			newReport = ReportContent;
			dm.setDmReportReason(newReport);
			
			result = new DMService().updateDmBoardReport(dm);
		}
		// 기존에 신고로 신고 사유가 존재하는 경우
		else if(dm.getDmReportReason().length() > 0) {
			origin = dm.getDmReportReason();
			newReport = ", " + ReportContent;
			
			DM d = new DM();
			d = new DMService().selectOne(dmNo, read);
			d.setDmReportReason(origin + newReport);
			
			result = new DMService().updateDmBoardReport(d);
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
				loc = "/board/dm/dmReport?dmNo="+dmNo+"&close=true";
			}
//		}
		else {
			msg = "신고 실패.";
			loc = "/board/dm/dmReport?dmNo="+dmNo;
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
