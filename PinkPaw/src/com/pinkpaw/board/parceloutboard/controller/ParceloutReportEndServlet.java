package com.pinkpaw.board.parceloutboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.parceloutboard.model.service.ParceloutService;
import com.pinkpaw.board.parceloutboard.model.vo.ParceloutBoard;

@WebServlet("/board/parceloutBoard/parceloutReport")
public class ParceloutReportEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String origin;
    private static String newReport;
    private static int result;

    public ParceloutReportEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//1. 전송값 꺼내서 변수에 기록하기.
		int parceloutNo = Integer.parseInt(request.getParameter("parceloutNo"));
		String reportWriter = request.getParameter("reportWriter");
		String parceloutReportContent = request.getParameter("parceloutReportContent");
		String otherReason = request.getParameter("parceloutOtherReason");
		otherReason = otherReason.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
				
		ParceloutBoard parceloutBoard = new ParceloutService().selectOne(parceloutNo);
		
		// 신고 사례가 없는 경우
		if(parceloutBoard.getParceloutReportReason() == null) {
			newReport = parceloutReportContent + otherReason;
			parceloutBoard.setParceloutReportReason(newReport);
			System.out.println("newReport@servlet"+newReport);
			result = new ParceloutService().updateReport(parceloutBoard);
		}
		// 기존에 신고로 신고 사유가 존재하는 경우
		else if(parceloutBoard.getParceloutReportReason().length() > 0) {
			origin = parceloutBoard.getParceloutReportReason();
			newReport = ", " + parceloutReportContent + otherReason;
			
			ParceloutBoard p = new ParceloutBoard();
			p = new ParceloutService().selectOne(parceloutNo);
			p.setParceloutReportReason(origin + newReport);
			
			result = new ParceloutService().updateReport(p);
		}
				
		//2.서비스로직호출
		//로그인 체크해야되여
//		int result = memberService.loginCheck(reportWriter);
				
		//3. 현재패스워드를 맞게 입력했으면, 비밀번호를 업데이트함. 
		//그렇지 않으면, 다시 팝업창 url을 호출함.
		String msg = "";
		String loc = "";
		String view = "/WEB-INF/views/common/msg.jsp";
				
			if(result > 0){
				msg = "신고 작성 성공";
				loc = "/board/parcelout/parceloutReport?parceloutNo="+parceloutNo+"&close=true";
			}
//		}
			else {
				msg = "신고 실패.";
				loc = "/board/parcelout/parceloutView?parceloutNo="+parceloutNo;
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
