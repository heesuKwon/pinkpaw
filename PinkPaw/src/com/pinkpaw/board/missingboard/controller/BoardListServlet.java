package com.pinkpaw.board.missingboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.missingboard.model.service.MissingService;
import com.pinkpaw.board.missingboard.model.vo.MissingBoard;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/missingList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터값 변수에 담기
				final int numPerPage = 6;//한페이지당 수
				int cPage = 1;//요청페이지
				try{
					cPage = Integer.parseInt(request.getParameter("cPage"));
				} catch(NumberFormatException e){
				
				}
				//System.out.println("cPage="+cPage); 
				
				//2.업무로직처리
				//2.1 현재페이지의 회원구하기
				List<MissingBoard> list = new MissingService().selectBoardList(cPage, numPerPage);
				System.out.println("list="+list);
				
				//2.2 전체게시글수, 전체페이지수 구하기
				int totalBoardCount = new MissingService().selectBoardCount();
				//(공식2)전체페이지수 구하기
				int totalPage = (int)Math.ceil((double)totalBoardCount/numPerPage);
				System.out.println("totalBoardCount="+totalBoardCount+", totalPage="+totalPage);
				
				//2.3 페이지바구성
				String pageBar = "";	
				int pageBarSize = 5;
				//(공식3)시작페이지 번호 세팅
				//cPage=5,pageBarSize=5 -> 1
				//cPage=6,pageBarSize=5 -> 6
				int pageStart = ((cPage - 1)/pageBarSize) * pageBarSize +1;
				//종료페이지 번호 세팅
				int pageEnd = pageStart+pageBarSize-1;
				int pageNo = pageStart;
				System.out.println("pageStart["+pageStart+"] ~ pageEnd["+pageEnd+"]");
				
				if(pageNo == 1) {
					pageBar += "<span class='w3-bar-item w3-button w3-hover-black'>&lt;&lt;</span>";
				}
				else {
					pageBar += "<a href='"+request.getContextPath()+"/board/missingList?cPage="+(pageNo-1)+"' class='w3-bar-item w3-button w3-hover-black'>&lt;&lt;</a>";
				}
				//b.page
				while(pageNo <= pageEnd && pageNo <= totalPage) {
					//현재페이지인 경우. 링크필요없음
					if(pageNo == cPage) {
						pageBar += "<span class='w3-bar-item w3-black w3-button'>"+pageNo+"</span>";
					}
					else {
						pageBar += "<a href='"+request.getContextPath()+"/board/missingList?cPage="+pageNo+"' class='w3-bar-item w3-button w3-hover-black'>"+pageNo+"</a>";				
					}
					pageNo++;
				}
				//c.[다음]
				if(pageNo > totalPage) {
					pageBar += "<span class='w3-bar-item w3-button w3-hover-black'>&gt;&gt;</span>";
				}
				//while문을 빠져나올 때 이미 pageNo가 증가되어있는 상태기 때문에 +1을 하지않고 pageNo로 이동시킨다.
				else {
					pageBar += "<a href='"+request.getContextPath()+"/board/missingList?cPage="+pageNo+"' class='w3-bar-item w3-button w3-hover-black'>&gt;&gt;</a>";
				}
				System.out.println("pageBar="+pageBar);
				
				//3.view단 처리 
				request.setAttribute("list", list);
				request.setAttribute("pageBar", pageBar);
				request.setAttribute("cPage", cPage);
				request.setAttribute("numPerPage", numPerPage);
				
				
				request.getRequestDispatcher("/WEB-INF/views/board/community/missing/missingList.jsp")
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
