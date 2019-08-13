package com.pinkpaw.board.volunteer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.common.model.exception.BoardException;
import com.pinkpaw.board.volunteer.model.service.VolunteerService;
import com.pinkpaw.board.volunteer.model.vo.VolunteerBoard;

/**
 * Servlet implementation class volunteerUpdateServlet
 */
@WebServlet("/board/volunteer/volunteerUpdate")
public class VolunteerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VolunteerUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터 핸들링
		int volunteerNo;
		try{
			volunteerNo = Integer.parseInt(request.getParameter("volunteerNo"));
		} catch(Exception e){
			throw new BoardException("유효하지 않은 게시글 요청입니다.");
		}

		//2.업무로직
		VolunteerBoard vb = new VolunteerService().selectOne(volunteerNo);

		//3.view단 처리
		request.setAttribute("volunteerBoard", vb);
		request.getRequestDispatcher("/WEB-INF/views/board/shelter/volunteer/volunteerUpdate.jsp")
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
