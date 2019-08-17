package com.pinkpaw.admin.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.admin.model.service.AdminService;
import com.pinkpaw.member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberFinderServlet
 */
@WebServlet("/admin/memberFinder")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberFinderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//1.파라미터핸들링
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		System.out.printf("searchType=%s, searchKeyword=%s\n",
							searchType, searchKeyword);
		final int numPerPage = 10;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			
		}
		//2.업무로직
		List<Member> list = null;
		switch(searchType) {
		case "memberId"	:list = new AdminService().selectMemberByMemberId(searchKeyword, cPage, numPerPage);break;
		case "memberName"	:list = new AdminService().selectMemberByMemberName(searchKeyword, cPage, numPerPage);break;
		case "gender"	:list = new AdminService().selectMemberByGender(searchKeyword, cPage, numPerPage);break;
		}
		
		//3.2 페이지바구성
		//전체회원수, 전체페이지수 구하기
		int totalMember = 0;
		switch (searchType) {
		case "memberId"	:totalMember = new AdminService().selectMemberCountByMemberId(searchKeyword);break;
		case "memberName" :totalMember = new AdminService().selectMemberCountByMemberName(searchKeyword);break;
		case "gender"	:totalMember = new AdminService().selectMemberCountByGender(searchKeyword);break;
		}
		//(공식2)totalPage구하기
		int totalPage = (int)Math.ceil((double)totalMember/numPerPage);
		System.out.println("totalMember="+totalMember+", totalPage="+totalPage);
		
		//페이지바 html코드
		String pageBar = "";	
		//페이지바 길이
		int pageBarSize = 5;
		//(공식3)시작페이지 번호 세팅
		//cPage=5,pageBarSize=5 -> 1
		//cPage=6,pageBarSize=5 -> 6
		int pageStart = ((cPage - 1)/pageBarSize) * pageBarSize +1;
		//종료페이지 번호 세팅
		int pageEnd = pageStart+pageBarSize-1;
		System.out.println("pageStart["+pageStart+"] ~ pageEnd["+pageEnd+"]");
		int pageNo = pageStart;
		
		//이전 section
		if(pageNo == 1 ){

		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/memberFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+(pageNo-pageBarSize)+"&numPerPage="+numPerPage+"'>[이전]</a> ";
		}
		// pageNo section
		// 보통 !(빠져나가는 조건식)으로 많이 쓴다.
//				while(pageNo<=pageEnd && pageNo<=totalPage){
		while(!(pageNo>pageEnd || pageNo > totalPage)){
			if(cPage == pageNo ){
				pageBar += "<span class='cPage'>"+pageNo+"</span> ";
			} 
			else {
				pageBar += "<a href='"+request.getContextPath()+"/admin/memberFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"&numPerPage="+numPerPage+"'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		
		//[다음] section
		if(pageNo > totalPage){
			
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/memberFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"&numPerPage="+numPerPage+"'>[다음]</a>";
		}
		//3.view단처리
		request.setAttribute("list", list);
		request.setAttribute("pageBar",pageBar);
		RequestDispatcher reqDispatcher 
			= request.getRequestDispatcher("/WEB-INF/views/admin/memberFinder.jsp");
		reqDispatcher.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
