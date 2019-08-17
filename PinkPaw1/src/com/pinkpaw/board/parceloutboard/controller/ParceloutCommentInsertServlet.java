package com.pinkpaw.board.parceloutboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.common.model.vo.BoardComment;
import com.pinkpaw.board.parceloutboard.model.service.ParceloutService;


/**
 * Servlet implementation class ParceloutCommentInsertServlet
 */
@WebServlet("/board/parceloutboard/boardCommentInsert")
public class ParceloutCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParceloutCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		int boardRef
		=Integer.parseInt(request.getParameter("boardRef"));
		System.out.println("boardRef@servlet="+boardRef);
	String boardCommentWriter
		=request.getParameter("boardCommentWriter");
	String boardCommentContent
		=request.getParameter("boardCommentContent");
	int boardCommentLevel
		=Integer.parseInt(request.getParameter("boardCommentLevel"));
	int boardCommentRef
		=Integer.parseInt(request.getParameter("boardCommentRef"));
	
	BoardComment boardComment
		= new BoardComment(0, 
							boardCommentLevel,
							boardCommentWriter,
							boardCommentContent,
							boardRef,
							boardCommentRef,
							null);
	
	System.out.println("boardComment@servlet="+boardComment); 
	
	int result = new ParceloutService().insertParceloutComment(boardComment);
	
	String view = "/WEB-INF/views/common/msg.jsp";
	String msg = "";
	String loc = "/board/parcelout/parceloutView?parceloutNo="+boardRef;
	
	if(result > 0) {
		msg = "댓글 등록 성공!";
	}else {
		msg = "댓글 등록 실패!";
	}
	
	request.setAttribute("msg", msg);
	request.setAttribute("loc", loc);
	
	request.getRequestDispatcher(view).forward(request, response);

	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
