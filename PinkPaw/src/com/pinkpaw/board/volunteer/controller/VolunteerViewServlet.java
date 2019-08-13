package com.pinkpaw.board.volunteer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.common.model.service.VolunteerCommentService;
import com.pinkpaw.board.common.model.vo.BoardComment;
import com.pinkpaw.board.volunteer.model.service.VolunteerService;
import com.pinkpaw.board.volunteer.model.vo.VolunteerBoard;

/**
 * Servlet implementation class volunteerViewServlet
 */
@WebServlet("/board/volunteer/volunteerView")
public class VolunteerViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VolunteerViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터 핸들링
		int volunteerNo = Integer.parseInt(request.getParameter("volunteerNo"));
		System.out.println("volunteerBoardNo@volunteerViewServlet="+volunteerNo);
		
		//2.업무로직
		VolunteerBoard volunteerBoard = new VolunteerService().selectOne(volunteerNo);
		System.out.println("volunteerBoard@volunteerViewServlet="+volunteerBoard);
		
		List<BoardComment> boardCommentList = new VolunteerCommentService().selectBoardCommentList(volunteerNo);
		System.out.println("boardCommentList@volunteerViewServlet="+boardCommentList.toString());
		
		//3.view단 처리
		request.setAttribute("volunteerBoard", volunteerBoard);
		request.setAttribute("boardCommentList", boardCommentList);
		
		request.getRequestDispatcher("/WEB-INF/views/board/shelter/volunteer/volunteerView.jsp")
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
