package com.pinkpaw.board.reviewboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.freeboard.model.service.FreeBoardService;
import com.pinkpaw.board.freeboard.model.vo.FreeBoard;
import com.pinkpaw.board.reviewboard.model.service.ReviewService;
import com.pinkpaw.board.reviewboard.model.vo.ReviewBoard;


@WebServlet("/board/review/reviewBoardReportEnd")
public class ReviewBoardReportEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String origin;
    private static String newReport;
    private static int result;
	
    public ReviewBoardReportEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//1. 파라미터 핸들링
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		String reportWriter = request.getParameter("reportWriter");
		String reviewReportContent = request.getParameter("reviewReportContent");
		String otherReason = request.getParameter("reviewOtherReason");
		otherReason = otherReason.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		
		ReviewBoard reviewBoard = new ReviewService().selectOne(reviewNo);

		// 신고 사례가 없는 경우
		if(reviewBoard.getReviewReportReason() == null) {
			newReport = reviewReportContent + otherReason;
			reviewBoard.setReviewReportReason(newReport);
			
			result = new ReviewService().updateReviewBoardReport(reviewBoard);
		}
		// 기존에 신고로 신고 사유가 존재하는 경우
		else if(reviewBoard.getReviewReportReason().length() > 0) {
			origin = reviewBoard.getReviewReportReason();
			newReport = ", " + reviewReportContent + otherReason;
			
			ReviewBoard r = new ReviewBoard();
			r = new  ReviewService().selectOne(reviewNo);
			r.setReviewReportReason(origin + newReport);
			
			result = new ReviewService().updateReviewBoardReport(r);
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
				loc = "/board/review/reviewBoardReport?reviewNo="+reviewNo+"&close=true";
			}
//		}
		else {
			msg = "신고 실패.";
			loc = "/board/review/reviewBoardReport?reviewNo="+reviewNo;
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
