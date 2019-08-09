package com.pinkpaw.board.shelter.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.shelter.model.service.ShelterService;
import com.pinkpaw.board.shelter.model.vo.Shelter;

/**
 * Servlet implementation class ShelterListByCityServlet
 */
@WebServlet("/shelter/shelterListByCity")
public class ShelterListByCityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShelterListByCityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		
		String keyword = sido + " " + gugun;
		List<Shelter> list = null;
		if("시/도 선택 ".equals(keyword)) {
			list = new ShelterService().selectShelterList();
		}
		else {
			list = new ShelterService().selectShelterListByCity(keyword);
		}
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/board/shelter/shelterfind/shelterList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
