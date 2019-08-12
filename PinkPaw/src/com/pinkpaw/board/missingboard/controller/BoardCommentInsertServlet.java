package com.pinkpaw.board.missingboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.common.model.vo.BoardComment;
import com.pinkpaw.board.missingboard.model.service.MissingService;


/**
 * Servlet implementation class BoardCommentInsertServlet
 */
@WebServlet("/board/missingCommentInsert")
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터 핸들링
		int boardRef 
			= Integer.parseInt(request.getParameter("boardRef"));
		String boardCommentWriter
			= request.getParameter("boardCommentWriter");
		String boardCommentContent
			= request.getParameter("boardCommentContent");
		int boardCommentLevel 
			= Integer.parseInt(request.getParameter("boardCommentLevel"));
		int boardCommentRef 
			= Integer.parseInt(request.getParameter("boardCommentRef"));
		
		BoardComment boardComment 
			= new BoardComment(0, boardCommentLevel, 
								  boardCommentWriter, 
								  boardCommentContent, 
								  boardRef, 
								  boardCommentRef, 
								  null);
		System.out.println("boardComment@servlet="+boardComment);
		
		//2.업무로직
		int result 
			= new MissingService().insertBoardComment(boardComment);
		
		
		//3.view단 처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/missingView?missingNo="+boardRef;
		
		if(result > 0) {
			msg = "댓글 등록 성공!";
		}
		else {
			msg = "댓글 등록 실패!";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher(view)
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
