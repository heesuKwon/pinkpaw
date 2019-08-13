package com.pinkpaw.board.freeboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.freeboard.model.vo.FreeBoard;

@WebServlet("/board/community/free/freeBoardReport")
public class FreeBoardReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FreeBoardReportServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("여기 freeBoardReport까지는 전송된다.");		
		request.getRequestDispatcher("/WEB-INF/views/board/community/free/freeReport.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
