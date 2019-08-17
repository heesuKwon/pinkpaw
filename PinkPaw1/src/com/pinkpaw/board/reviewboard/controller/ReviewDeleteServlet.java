package com.pinkpaw.board.reviewboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.board.reviewboard.model.service.ReviewService;


/**
 * Servlet implementation class ReviewDeleteServlet
 */
@WebServlet("/board/review/reviewDelete")
public class ReviewDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터 핸들링
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		String reviewRenamedImg = request.getParameter("reviewRenamedImg");
		//2.업무로직
		int result = new ReviewService().deleteReview(reviewNo);

		//파일삭제
		if(result > 0 && !"".equals(reviewRenamedImg)) {
			//절대주소 가져오기
			String saveDirectory = getServletContext().getRealPath("/upload/board/review");
			System.out.println("reviewRenamedImg="+reviewRenamedImg);
			String[] renamedImgList = reviewRenamedImg.split("§");
			for(int i=0;i<renamedImgList.length;i++) {				
				File delFile = new File(saveDirectory+File.separator+renamedImgList[i]);
				//					File delFile = new File(saveDirectory+"/"+renamedFileName);//"/"로 적어도 요즘은 톰캣이 알아서 처리해줌
				System.out.println("delFile: "+delFile);
				//1.실제로 삭제시키기
				boolean bool = delFile.delete();//delete:실제 파일 삭제 메소드, 성공여부에 따라 bool값 리턴
				System.out.println("삭제여부: "+bool);
			}

		}
		//3.view단 처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/review/reviewList";

		if(result>0) {
			msg = "게시글 삭제에 성공하였습니다.";
		} else{
			msg = "게시글 삭제에 실패하였습니다.";
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
