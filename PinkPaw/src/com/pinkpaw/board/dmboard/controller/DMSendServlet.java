package com.pinkpaw.board.dmboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pinkpaw.board.dmboard.model.service.DMService;
import com.pinkpaw.board.dmboard.model.vo.DM;

/**
 * Servlet implementation class DMListServlet
 */
@WebServlet("/board/dm/dmSendList")
public class DMSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DMSendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1.파라미터 인코딩
				final int numPerPage = 10;
				
				String memberId = request.getParameter("memberId");
				int cPage = 1;
				try {
					cPage = Integer.parseInt(request.getParameter("cPage"));
				} catch(NumberFormatException e) {
					
				}
				//2.업무로직
				//2.1컨텐츠 영역
				List<DM> list = new DMService().selectSentList(memberId,cPage, numPerPage);
				System.out.println("list@reviewListServlet="+list);
				
				//2.2페이지바 영역
				int totalContents = new DMService().SendTotalContents(memberId);
				System.out.println("totalContents@dmListServlet="+totalContents);
				
				int totalPage = (int)Math.ceil((double)totalContents/numPerPage);
				System.out.println("totalPage@boardServlet="+totalPage);
				
				final int pageBarSize = 5;
				String pageBar = "";
				
				int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
				int pageEnd = pageStart+pageBarSize-1;
				System.out.println(pageStart+"~"+pageEnd);
				
				//페이지 증감변수
				int pageNo = pageStart;
				//a.[이전]
				if(pageNo == 1) {
					pageBar += "<span>[이전]</span>";
				}
				else {
					pageBar += "<a href='"+request.getContextPath()+"/board/dm/dmList?cPage="+(pageNo-1)+"'>[이전]</a>";
				}
				//b.page
				while(pageNo <= pageEnd && pageNo <= totalPage) {
					//현재페이지인 경우. 링크필요없음
					if(pageNo == cPage) {
						pageBar += "<span class='cPage'>"+pageNo+"</span>";
					}
					else {
						pageBar += "<a href='"+request.getContextPath()+"/board/dm/dmList?cPage="+pageNo+"'>"+pageNo+"</a>";				
					}
					pageNo++;
				}
				//c.[다음]
				if(pageNo > totalPage) {
					pageBar += "<span>[다음]</span>";
				}
				//while문을 빠져나올 때 이미 pageNo가 증가되어있는 상태기 때문에 +1을 하지않고 pageNo로 이동시킨다.
				else {
					pageBar += "<a href='"+request.getContextPath()+"/board/dm/dmList?cPage="+pageNo+"'>[다음]</a>";
				}
				System.out.println("pageBar="+pageBar);
				
				//3.view단 처리 
				request.setAttribute("list", list);
				request.setAttribute("pageBar", pageBar);
				request.setAttribute("cPage", cPage);
				request.setAttribute("numPerPage", numPerPage);
		
		request.getRequestDispatcher("/WEB-INF/views/board/dm/receiveDMList.jsp")
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
