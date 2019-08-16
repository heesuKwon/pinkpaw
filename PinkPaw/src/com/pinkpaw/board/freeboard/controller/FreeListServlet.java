package com.pinkpaw.board.freeboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.freeboard.model.service.FreeBoardService;
import com.pinkpaw.board.freeboard.model.vo.FreeBoard;

@WebServlet("/board/community/free/freeList")
public class FreeListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public FreeListServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터 핸들링
		final int numPerPage = 10; //한페이지당 수
		int cPage = 1; //요청페이지
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e){
		
		}
		//System.out.println("cPage="+cPage); 
		
		//2.업무 로직
		List<FreeBoard> list = new FreeBoardService().selectFreeBoardList(cPage, numPerPage);
        System.out.println("list@freeListServlet : " + list);
		
		// 전체 게시글 수 구하기
		int totalFreeBoardCount = new FreeBoardService().selectFreeBoardCount();
		// 전체 페이지 수 구하기
		int totalPage = (int)Math.ceil((double)totalFreeBoardCount/numPerPage);
		System.out.println("totalFreeBoardCount="+totalFreeBoardCount+", totalPage="+totalPage);
		
		// 페이지 바 구성
		String pageBar = "";	
		int pageBarSize = 5;
		
		int pageStart = ((cPage - 1)/pageBarSize) * pageBarSize +1;
		// 종료페이지 번호 세팅
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;
		System.out.println("pageStart["+pageStart+"] ~ pageEnd["+pageEnd+"]");
		
		//[이전] section
		if(pageNo == 1 ){
			pageBar += "<span class='w3-bar-item w3-button w3-hover-black'>&lt;&lt;</span>"; 
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/board/community/free/freeList?cPage="+(pageNo-1)+"' class='w3-bar-item w3-button w3-hover-black'>&lt;&lt;</a>";
		}
			
		// pageNo section
		while(!(pageNo>pageEnd || pageNo > totalPage)){
			
			if(cPage == pageNo ){
				pageBar += "<span class='w3-bar-item w3-black w3-button'>"+pageNo+"</span>";
			} 
			else {
				pageBar += "<a href='"+request.getContextPath()+"/board/community/free/freeList?cPage="+pageNo+"' class='w3-bar-item w3-button w3-hover-black'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		
		//[다음] section
		if(pageNo > totalPage){
			pageBar += "<span class='w3-bar-item w3-button w3-hover-black'>&gt;&gt;</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/board/community/free/freeList?cPage="+pageNo+"' class='w3-bar-item w3-button w3-hover-black'>&gt;&gt;</a>";
		}
		
		
		//4.view단 처리
		System.out.println("list@FreeListServlet 나오니?: "+list);
		request.setAttribute("list",list);
		request.setAttribute("pageBar",pageBar);
        request.setAttribute("cPage", cPage);
        request.setAttribute("numPerPage", numPerPage);
        
        request.getRequestDispatcher("/WEB-INF/views/board/community/free/freeList.jsp")
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
