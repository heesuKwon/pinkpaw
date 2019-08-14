package com.pinkpaw.board.dmboard.controller;

import java.io.IOException;



import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.dmboard.model.service.ReportDMService;
import com.pinkpaw.board.dmboard.model.vo.reportDM;
//import com.pinkpaw.board.model.service.NoticeBoardService;
//import com.pinkpaw.board.model.vo.Board;
//import com.pinkpaw.board.model.vo.NoticeBoard;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/dm/reportDMList")
public class ReportDMListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportDMListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터값 변수에 담기
		final int numPerPage = 5;//한페이지당 수
		int cPage = 1;//요청페이지
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e){
		
		}
		
		//2.업무로직처리
		//2.1 현재페이지의 신고쪽지구하기
		List<reportDM> list = new ReportDMService().selectReportDMList(cPage, numPerPage);
		System.out.println("list="+list);
		
		//2.2 전체게시글수, 전체페이지수 구하기
		int totalBoardCount = new ReportDMService().selectReportDMCount();
		//(공식2)전체페이지수 구하기
		int totalPage = (int)Math.ceil((double)totalBoardCount/numPerPage);
		System.out.println("totalBoardCount="+totalBoardCount+", totalPage="+totalPage);
		
		//2.3 페이지바구성
		String pageBar = "";	
		int pageBarSize = 5;
		//(공식3)시작페이지 번호 세팅
		int pageStart = ((cPage - 1)/pageBarSize) * pageBarSize +1;
		//종료페이지 번호 세팅
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;
		System.out.println("pageStart["+pageStart+"] ~ pageEnd["+pageEnd+"]");
		
		//[이전] section
		if(pageNo == 1 ){
			//pageBar += "<span>[이전]</span>"; 
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/board/dm/reportDMList?cPage="+(pageNo-1)+"'>[이전]</a> ";
		}
			
		// pageNo section
		// 보통 !(빠져나가는 조건식)으로 많이 쓴다.
		while(!(pageNo>pageEnd || pageNo > totalPage)){
			
			if(cPage == pageNo ){
				pageBar += "<span class='cPage'>"+pageNo+"</span> ";
			} 
			else {
				pageBar += "<a href='"+request.getContextPath()+"/board/dm/reportDMList?cPage="+pageNo+"'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		
		//[다음] section
		if(pageNo > totalPage){
			//pageBar += "<span>[다음]</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/board/dm/reportDMList?cPage="+pageNo+"'>[다음]</a>";
		}
		
		
		//4.뷰단 포워딩		
		request.setAttribute("list",list);
		request.setAttribute("pageBar",pageBar);
		request.getRequestDispatcher("/WEB-INF/views/board/dm/reportDMList.jsp")
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
