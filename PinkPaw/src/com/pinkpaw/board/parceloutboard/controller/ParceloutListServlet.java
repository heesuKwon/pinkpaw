package com.pinkpaw.board.parceloutboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.parceloutboard.model.service.ParceloutService;
import com.pinkpaw.board.parceloutboard.model.vo.ParceloutBoard;

/**
 * Servlet implementation class ParceloutListServlet
 */
@WebServlet("/board/parcelout/parceloutList")
public class ParceloutListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParceloutListServlet() {
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
		
	List<ParceloutBoard> list = new ParceloutService().selectBoardList(cPage, numPerPage);
	System.out.println("list@servlet="+list);	
	
	int totalBoardCount = new ParceloutService().selectBoardCount();
	//(공식2)전체페이지수 구하기
	int totalPage = (int)Math.ceil((double)totalBoardCount/numPerPage);
	System.out.println("totalBoardCount="+totalBoardCount+", totalPage="+totalPage);
	
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
	
	//[이전] section
	if(pageNo == 1) {
		pageBar += "<span class='w3-bar-item w3-button w3-hover-black'>&lt;&lt;</span>";
	}
	else {
		pageBar += "<a href='"+request.getContextPath()+"/board/parcelout/parceloutList?cPage="+(pageNo-1)+"' class='w3-bar-item w3-button w3-hover-black'>&lt;&lt;</a>";
	}
	//b.page
	while(pageNo <= pageEnd && pageNo <= totalPage) {
		//현재페이지인 경우. 링크필요없음
		if(pageNo == cPage) {
			pageBar += "<span class='w3-bar-item w3-black w3-button'>"+pageNo+"</span>";
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/board/parcelout/parceloutList?cPage="+pageNo+"' class='w3-bar-item w3-button w3-hover-black'>"+pageNo+"</a>";				
		}
		pageNo++;
	}
	//c.[다음]
	if(pageNo > totalPage) {
		pageBar += "<span class='w3-bar-item w3-button w3-hover-black'>&gt;&gt;</span>";
	}
	//while문을 빠져나올 때 이미 pageNo가 증가되어있는 상태기 때문에 +1을 하지않고 pageNo로 이동시킨다.
	else {
		pageBar += "<a href='"+request.getContextPath()+"/board/parcelout/parceloutList?cPage="+pageNo+"' class='w3-bar-item w3-button w3-hover-black'>&gt;&gt;</a>";
	}
	System.out.println("pageBar="+pageBar);
	
	//3.view단 처리 
	request.setAttribute("list", list);
	request.setAttribute("pageBar", pageBar);
	request.setAttribute("cPage", cPage);
	request.setAttribute("numPerPage", numPerPage);

	
	request.getRequestDispatcher("/WEB-INF/views/board/community/parcelout/parceloutList.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
