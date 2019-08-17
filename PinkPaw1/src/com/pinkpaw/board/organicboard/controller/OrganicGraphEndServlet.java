package com.pinkpaw.board.organicboard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pinkpaw.board.organicboard.model.service.GraphService;

/**
 * Servlet implementation class OrganicGraphEndServlet
 */
@WebServlet("/board/organic/graph/OrganicGraphEnd")
public class OrganicGraphEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrganicGraphEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
		
		Map<String, Integer> organicMapSelect = new HashMap<>();
		
		String name1 = request.getParameter("date1");
		String name2 = request.getParameter("date2");
		System.out.println(name1+"555555555555555555555");
		System.out.println(name2+"55dfasfdads555555555");
		
		organicMapSelect = new GraphService().getSelectOrganic(name1, name2);

		for(String key : organicMapSelect.keySet()) {
			System.out.println(key+" : "+organicMapSelect.get(key));
		}
		
		new Gson().toJson(organicMapSelect, response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
