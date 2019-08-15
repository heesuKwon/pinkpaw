
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
 * Servlet implementation class DMDeleteServlet
 */
@WebServlet("/board/dm/dmDelete")
public class DMDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DMDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int dmNo = Integer.parseInt(request.getParameter("dmNo"));
		System.out.println("Servelet"+dmNo);
		String receiver =(String)request.getParameter("dmReceive");
//		String sender = null;
//		try {
//			receiver = (String)request.getParameter("dmReceive");
//		} catch(NullPointerException e) {
//			e.printStackTrace();
//		}
		System.out.println("Severlet확인"+receiver);
		
		int result = 0;
		System.out.println("뷰넘버"+dmNo);
//		if(receiver != null ) {
			result = new DMService().deleteReceiver(dmNo, receiver);
			
//		}
			System.out.println("result: "+result);
			
		
//		
//		try {
//			 sender = (String)request.getParameter("dmSender");
//		} catch(NullPointerException e) {
//			e.printStackTrace();
//		}
//		
//		if(sender !=null ) {
//			result = new DMService().deleteSender(dmNo);
//			
//		}
//		
		
		
		//3.view단 처리
				String view = "/WEB-INF/views/common/msg.jsp";
				String msg = "";
				String loc = "/board/dm/DMView?dmNo="+dmNo+"&dmRead=1";
				String close = "";
				if(result > 0) {
					msg = "쪽지 삭제 성공!";
					close= "true";
				}
				else {
					msg = "쪽지 삭제 실패!";	
					loc = "/board/dm/DMView?dmNo="+dmNo+"&dmRead=1";
				
				}
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				request.setAttribute("close", close);
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
