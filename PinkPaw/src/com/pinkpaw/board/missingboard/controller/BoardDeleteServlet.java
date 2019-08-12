package com.pinkpaw.board.missingboard.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.missingboard.model.service.MissingService;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet("/board/missingDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.전송값 꺼내서 변수에 기록하기.
		//String javax.servlet.ServletRequest.getParameter(String arg0)
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String renamedFileName
			= request.getParameter("renamedFileName");
		
		
		//2.서비스로직호출
		int result = new MissingService().deleteBoard(boardNo);
		
		//파일삭제
		if(result > 0 && !"".equals(renamedFileName)) {
			String saveDirectory
				= getServletContext().getRealPath("/upload/board");
			File delFile 
				= new File(saveDirectory+File.separator+renamedFileName);
			System.out.println("delFile="+delFile);
			//1.정말 삭제시키기
			//boolean bool = delFile.delete();
			//System.out.println("삭제여부: "+bool);
			
			//2.삭제폴더로 이동시키기
			String delDirectory
				= getServletContext().getRealPath("/deleteFiles/board");
			File renamedFile
				= new File(delDirectory+File.separator+renamedFileName);
			boolean bool = delFile.renameTo(renamedFile);
			System.out.println("삭제(파일이동)여부: "+bool);
		}
		
		
		
		//3. 받은 결과에 따라 뷰페이지 내보내기
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/missingList";

		if(result>0)
			msg = "게시글 삭제 성공!";
			
		else 
			msg = "게시글 삭제 실패!";	
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
