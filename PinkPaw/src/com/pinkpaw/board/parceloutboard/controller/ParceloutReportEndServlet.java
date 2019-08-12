package com.pinkpaw.board.parceloutboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.parceloutboard.model.service.ParceloutService;
import com.pinkpaw.board.parceloutboard.model.vo.ParceloutBoard;

/**
 * Servlet implementation class ParceloutReportEndServlet
 */
@WebServlet("/board/parceloutBoard/parceloutReport")
public class ParceloutReportEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParceloutReportEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 전송값 꺼내서 변수에 기록하기.
				String reportTitle = request.getParameter("reportTitle");
				String reportWriter = request.getParameter("reportWriter");
				int parceloutNo = Integer.parseInt(request.getParameter("parceloutNo"));
				String reportContent = request.getParameter("reportContent");
				reportContent = reportContent.replaceAll("<", "&lt;")
						   .replaceAll(">", "&gt;");
				
				ParceloutBoard p = new ParceloutBoard();
//				String report = "제목:"+reportTitle+"작성자: "+reportWriter+"신고내용: "+reportContent;
//				p = new ParceloutService().selectOne(parceloutNo);
				
				
				//기존에 신고사유가 존재할 경우
//				if(p.getParceloutReportReason().length() > 0)
//				report = p.getParceloutReportReason() + report;
				
				p.setParceloutNo(parceloutNo);
				p.setParceloutReportReason(reportContent);
				int result = new ParceloutService().updateReport(p);
				
				//2.서비스로직호출
				//로그인 체크해야되여
//				int result = memberService.loginCheck(reportWriter);
				
				//3. 현재패스워드를 맞게 입력했으면, 비밀번호를 업데이트함. 
				//그렇지 않으면, 다시 팝업창 url을 호출함.
				String msg = "";
				String loc = "";
				String view = "/WEB-INF/views/common/msg.jsp";
				
					if(result>0){
						msg = "신고 작성 성공";
						String script = "self.close()";
//						//팝업창을 닫기위한 코드 추가
						request.setAttribute("script",script);
					}
//						
//				}
				else {
					msg = "신고 실패.";
					loc = "/board/community/parcelout/parceloutView?parceloutNo="+parceloutNo;
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
