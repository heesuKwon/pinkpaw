package com.pinkpaw.board.organicboard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.organicboard.model.service.GraphService;

/**
 * Servlet implementation class OrganicGraph
 */
@WebServlet("/board/organic/graph/OrganicGraph")
public class OrganicGraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrganicGraphServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Integer> organicMap = new HashMap<>();
		organicMap = new GraphService().getAllOrganic();
		
		for(String key : organicMap.keySet()) {
			System.out.println(key+" : "+organicMap.get(key));
		}
		
		
		request.setAttribute("organicMap", organicMap);
		request.getRequestDispatcher("/WEB-INF/views/board/organic/graph.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
