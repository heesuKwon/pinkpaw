package com.pinkpaw.admin.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.admin.model.service.AdminService;
import com.pinkpaw.member.model.vo.Member;

/**
 * Paging 
 * 1. contents
 * 	- cPage: 현재페이지
 *  - numPerPage: 한 페이지당 표시할  게시물수
 * 
 * 2. pageBar
 *  - totalContents: 총게시물수
 *  - totalPage: 총페이지수. 
 *  	109건게시물을 한페이지당 10건씩 표시할때, 11페이지가 필요하다.
 *  - pageBarSize: 페이지바의 표시될 페이지개수
 *  - pageNo: pageStart ~ pageEnd사이에서 사용될 증감변수
 * 
 * 
 */
@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.파라미터핸들링
		int numPerPage = 10;
		try {
			numPerPage 
				= Integer.parseInt(request.getParameter("numPerPage"));
		}catch(NumberFormatException e) {
			
		}
		
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			
		}
		//2.업무로직
		//2.1 컨텐츠영역
		List<Member> list 
			= new AdminService().selectMemberList(cPage, numPerPage);
		System.out.println("list@adminServlet="+list);
		
		//2.2 페이지바영역
		//(페이지바 구하기 공식2)
		//전체페이지수 구하기: 올림(전체컨텐츠수/10)
		int totalContents
			 = new AdminService().selecTotalContents();
		//System.out.println("totalContents="+totalContents);//115
		
		//115/10.0 =>  Math.ceil(11.231412)  => 12
		int totalPage = (int)Math.ceil((double)totalContents/numPerPage);
		//System.out.println("totalPage="+totalPage);
		
		//pageBar html코드작성
		final int pageBarSize = 5;
		String pageBar = "";
		//(페이지바공식3): 시작페이지번호 세팅
		//cPage=1,pageBarSize=5 => pageStart=1
		//cPage=2,pageBarSize=5 => pageStart=1
		//cPage=5,pageBarSize=5 => pageStart=1
		//cPage=6,pageBarSize=5 => pageStart=6
		//cPage=12,pageBarSize=5 => pageStart=11
		
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageStart+pageBarSize-1;
		//System.out.println(pageStart+"~"+pageEnd);
		int pageNo = pageStart;
		
		//a.[이전]
		if(pageNo == 1) {
			pageBar += "<span>[이전]</span>";
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/memberList?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>[이전]</a>";
		}
		
		//b.page
		while(pageNo<=pageEnd && pageNo <= totalPage) {
			//현재페이지인 경우. 링크필요없음
			if(pageNo == cPage) {
				pageBar += "<span class='cPage'>"+pageNo+"</span>";
			}
			else {
				pageBar += "<a href='"+request.getContextPath()+"/admin/memberList?cPage="+pageNo+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
			}
			
			pageNo++;
		}
		
		//c.[다음]
		if(pageNo > totalPage) {
			pageBar += "<span>[다음]</span>";
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/memberList?cPage="+pageNo+"&numPerPage="+numPerPage+"'>[다음]</a>";
		}
		
		
		System.out.println("pageBar="+pageBar);
		
		
		//3.view단 처리
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);		
		request.setAttribute("cPage", cPage);		
		request.setAttribute("numPerPage", numPerPage);		
		request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp")
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
