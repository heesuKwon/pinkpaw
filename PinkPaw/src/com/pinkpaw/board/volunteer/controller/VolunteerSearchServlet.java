package com.pinkpaw.board.volunteer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pinkpaw.board.volunteer.model.service.VolunteerService;
import com.pinkpaw.board.volunteer.model.vo.VolunteerBoard;

/**
 * Servlet implementation class volunteerSearchServlet
 */
@WebServlet("/board/volunteer/volunteerSearch")
public class VolunteerSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VolunteerSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		String kind = request.getParameter("kind");
		String key = request.getParameter("key");
		String keyword = request.getParameter("keyword");
		
		final int numPerPage = 10;
		int cPage = 1;
		
		List<VolunteerBoard> list = null;
		
		if("".equals(kind)) { // 글종류 선택x, 전체일때
			if("".equals(key)) {
				list = new VolunteerService().selectVolunteerBoardListAllAll(cPage, numPerPage, keyword);
			} else {
				list = new VolunteerService().selectVolunteerBoardListAllnotAll(cPage, numPerPage, key, keyword);
			}
		}else { // 글종류 선택o
			if("".equals(key)) {
				list = new VolunteerService().selectVolunteerBoardListnotAllAll(cPage, numPerPage, kind, keyword);
			} else {
				list = new VolunteerService().selectVolunteerBoardListnotAllnotAll(cPage, numPerPage, kind, key, keyword);
			}
		}
		
		new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
