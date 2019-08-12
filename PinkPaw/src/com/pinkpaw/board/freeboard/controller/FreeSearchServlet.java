package com.pinkpaw.board.freeboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pinkpaw.board.freeboard.model.service.FreeBoardService;
import com.pinkpaw.board.freeboard.model.vo.FreeBoard;

/**
 * Servlet implementation class FreeSearchServlet
 */
@WebServlet("/board/community/free/freeSearch")
public class FreeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		String key = request.getParameter("key");
		String keyword = request.getParameter("keyword");
		
		int cPage = 1;
		final int numPerPage = 10;
		
		List<FreeBoard> list = null;
		
		if("".equals(key)) {
			list = new FreeBoardService().selectFreeBoardListAllAll(cPage, numPerPage, keyword);
		} else {
			list = new FreeBoardService().selectFreeBoardListAllnotAll(cPage, numPerPage, key, keyword);
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
