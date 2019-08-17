package com.pinkpaw.animal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.animal.model.dao.AnimalDAO;
import com.pinkpaw.animal.model.vo.ProtectedAnimal;

/**
 * Servlet implementation class AnimalNoticeSrch
 */
@WebServlet("/animal/animalNoticeSrch")
public class AnimalNoticeSrch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalNoticeSrch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage = 1;
		final int numPerPage = 9;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(Exception e) {}
		String anikind = request.getParameter("anikind");
		String sido = request.getParameter("sido");
		String sigungu = request.getParameter("sigungu");
		
		System.out.println("anikind = "+anikind);
		System.out.println("sido = "+sido);
		System.out.println("sigungu = "+sigungu);
		
		List<ProtectedAnimal> list = new AnimalDAO().selectAnimalSrch(cPage, anikind, sido, sigungu);
		
		int totalContents = AnimalDAO.AllPA_count;
		
		int totalPage = (int)Math.ceil((double)totalContents/numPerPage);
		
		final int pageBarSize = 5;
		String pageBar = "<div class='w3-center w3-padding-32'><div class='w3-bar'>";
		
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageStart+pageBarSize-1;
		
		int pageNo = pageStart;
		if(pageNo == 1) {
			pageBar += "<span class='w3-bar-item w3-button w3-hover-black'>&lt;&lt;</span>";
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/animal/animalNoticeSrch?cPage="+(pageNo-1)+"&anikind="+anikind+"&sido="+sido+"&sigungu="+sigungu+"' class='w3-bar-item w3-button w3-hover-black'>&lt;&lt;</a>";
		}
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo == cPage) {
				pageBar += "<span class='w3-bar-item w3-black w3-button'>"+pageNo+"</span>";
			}
			else {
				pageBar += "<a href='"+request.getContextPath()+"/animal/animalNoticeSrch?cPage="+pageNo+"&anikind="+anikind+"&sido="+sido+"&sigungu="+sigungu+"' class='w3-bar-item w3-button w3-hover-black'>"+pageNo+"</a>";				
			}
			pageNo++;
		}
		if(pageNo > totalPage) {
			pageBar += "<span class='w3-bar-item w3-button w3-hover-black'>&gt;&gt;</span>";
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/animal/animalNoticeSrch?cPage="+pageNo+"&anikind="+anikind+"&sido="+sido+"&sigungu="+sigungu+"' class='w3-bar-item w3-button w3-hover-black'>&gt;&gt;</a>";
		}
		pageBar += "</div></div>";
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		
		request.getRequestDispatcher("/WEB-INF/views/board/organic/announcement/announcementList.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
