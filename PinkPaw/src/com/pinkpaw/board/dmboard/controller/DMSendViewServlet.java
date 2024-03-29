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
 * Servlet implementation class DMViewServlet
 */
@WebServlet("/board/dm/DMSendView")
public class DMSendViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DMSendViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//업무로직
		int dmNo = Integer.parseInt(request.getParameter("dmNo"));
		int read = 1;
		System.out.println("뷰넘버"+dmNo);
		
		DM dm = new DMService().selectOne(dmNo, read);
		
		System.out.println("dm"+dm);
		//System.out.println("읽었음?:"+dm.getDmRecvRead());
		
		
		
		
		
		request.setAttribute("dm", dm);
		request.getRequestDispatcher("/WEB-INF/views/board/dm/DMView.jsp")
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
