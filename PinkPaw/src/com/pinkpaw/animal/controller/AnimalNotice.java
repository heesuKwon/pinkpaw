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
import com.pinkpaw.board.reviewboard.model.service.ReviewService;

/**
 * Servlet implementation class AnimalNotice
 */
@WebServlet("/animal/animalNotice")
public class AnimalNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalNotice() {
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
		
		List<ProtectedAnimal> list = new AnimalDAO().selectAnimalDefault(cPage);
		
		int totalContents = AnimalDAO.AllPA_count;
		
		int totalPage = (int)Math.ceil((double)totalContents/numPerPage);
		
		final int pageBarSize = 5;
		String pageBar = "";
		
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageStart+pageBarSize-1;
		
		int pageNo = pageStart;
		if(pageNo == 1) {
			pageBar += "<span>[이전]</span>";
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/animal/animalNotice?cPage="+(pageNo-1)+"'>[이전]</a>";
		}
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo == cPage) {
				pageBar += "<span class='cPage'>"+pageNo+"</span>";
			}
			else {
				pageBar += "<a href='"+request.getContextPath()+"/animal/animalNotice?cPage="+pageNo+"'>"+pageNo+"</a>";				
			}
			pageNo++;
		}
		if(pageNo > totalPage) {
			pageBar += "<span>[다음]</span>";
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/animal/animalNotice?cPage="+pageNo+"'>[다음]</a>";
		}
		
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
