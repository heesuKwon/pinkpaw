package com.pinkpaw.board.freeboard.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.freeboard.model.service.FreeBoardService;

@WebServlet("/board/community/free/FreeBoardDelete")
public class FreeBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FreeBoardDeleteServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터 핸들링
		int freeNo = Integer.parseInt(request.getParameter("freeNo"));
		//String renamedFileName = request.getParameter("renamedFileName");


		//2. 업무 로직
		int result = new FreeBoardService().deleteFreeBoard(freeNo);

		//파일삭제
		/*
		 * if(result > 0 && !"".equals(renamedFileName)) { String saveDirectory =
		 * getServletContext().getRealPath("/upload/board"); File delFile = new
		 * File(saveDirectory+File.separator+renamedFileName);
		 * System.out.println("delFile="+delFile); //1.정말 삭제시키기 //boolean bool =
		 * delFile.delete(); //System.out.println("삭제여부: "+bool);
		 * 
		 * //2.삭제폴더로 이동시키기 String delDirectory =
		 * getServletContext().getRealPath("/deleteFiles/board"); File renamedFile = new
		 * File(delDirectory+File.separator+renamedFileName); boolean bool =
		 * delFile.renameTo(renamedFile); System.out.println("삭제(파일이동)여부: "+bool); }
		 */

		//3.view단 처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/community/free/freeList";

		if(result > 0)
			msg = "게시글 삭제 성공!";

		else 
			msg = "게시글 삭제 실패!";	

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
