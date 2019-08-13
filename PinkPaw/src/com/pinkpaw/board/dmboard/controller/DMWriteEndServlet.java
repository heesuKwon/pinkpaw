package com.pinkpaw.board.dmboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.dmboard.model.service.DMService;
import com.pinkpaw.board.dmboard.model.vo.DM;

/**
 * Servlet implementation class DMWriteEndServlet
 */
@WebServlet("/DMWriteEnd")
public class DMWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DMWriteEndServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//파라미터핸들링
		String dmSender = (String)request.getParameter("dmSender");
		String dmWriter = (String)request.getParameter("dmWriter");
		
		String dmTitle = (String)request.getParameter("dmTitle");
		String dmContent = (String)request.getParameter("dmContent");
		
		DM d = new DM();
		
//		d = new DMService().
		
		
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
