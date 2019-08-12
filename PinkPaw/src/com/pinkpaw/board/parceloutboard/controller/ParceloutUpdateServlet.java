package com.pinkpaw.board.parceloutboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.parceloutboard.model.service.ParceloutService;
import com.pinkpaw.board.parceloutboard.model.vo.ParceloutBoard;

/**
 * Servlet implementation class ParceloutUpdateServlet
 */
@WebServlet("/board/community/parcelout/parceloutUpdate")
public class ParceloutUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParceloutUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int parceloutNo = Integer.parseInt(request.getParameter("parceloutNo"));
		System.out.println("parceloutNo@servlet="+parceloutNo);
		ParceloutBoard p = new ParceloutService().selectOne(parceloutNo);
		
		System.out.println(p);
		request.setAttribute("p", p);
		
		String view = "/WEB-INF/views/board/community/parcelout/parceloutView.jsp";
		
		if(p == null) {
			request.setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
			request.setAttribute("loc", "/board/community/parcelout/parceloutList");
			view = "/WEB-INF/views/common/msg.jsp";
		}
		
		
		request.getRequestDispatcher("/WEB-INF/views/board/community/parcelout/parceloutUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
