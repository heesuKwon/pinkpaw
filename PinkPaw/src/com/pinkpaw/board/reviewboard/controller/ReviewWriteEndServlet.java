package com.pinkpaw.board.reviewboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.pinkpaw.board.reviewboard.model.service.ReviewService;
import com.pinkpaw.board.reviewboard.model.vo.ReviewBoard;
import com.pinkpaw.common.util.MvcRenamePolicy;

/**
 * Servlet implementation class ReviewWriteEndServlet
 */
@WebServlet("/board/review/reviewWriteEnd")
public class ReviewWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.파일 업로드 체크: enctype="multipart/form-data"

		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "게시판파일등록오류!");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			.forward(request, response);
			return;
		}
		/******** 파일업로드 로직 시작 ********/
		//com.oreilly.servlet.MultipartRequest.MultipartRequest(HttpServletRequest arg0, String arg1, int arg2, String arg3, FileRenamePolicy arg4) throws IOException
		//new MultipartRequest(HttpServletRequest, saveDirectory, maxPostSize, encoding, FileRenamePolicy(중복파일 naming 정책))

		//a.saveDirectory: 실제 파일 저장 경로(절대경로)
		//application 호출 : getServletContext()
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload" + File.separator + "board/review";
		System.out.println("saveDirectory="+saveDirectory);

		//b.maxPostSize: 파일 최대 용량 (10MB = 1KB * 1KB * 10)
		int maxPostSize = 1024 * 1024 * 10;

		//c.encoding: UTF-8
		String encoding = "UTF-8";

		//d.FileRenamePolicy객체: 서버 저장용 파일명 생성 객체
		FileRenamePolicy mvcRenamePolicy = new MvcRenamePolicy();

		MultipartRequest mReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, mvcRenamePolicy);

		/******** 파일업로드 로직 끝 ********/

		//1.파라미터 핸들링: MultipartRequest객체 생성 후에는
		//더이상 HttpServletRequest객체로부터 사용자 입력값을 가져올 수 없다.
		//request.getParameter로 값을 가지고 오면 다 null로 가져옴.

		String reviewTitle = mReq.getParameter("reviewTitle");
		String reviewWriter = mReq.getParameter("reviewWriter");
		String reviewKind = mReq.getParameter("reviewKind");
		
		//파일을 리스트로 관리
		List<String> originalImgList = new ArrayList<>();
		List<String> renamedImgList = new ArrayList<>();
		
		//첨부파일
		
		if(mReq.getOriginalFileName("upFile")!=null && mReq.getFilesystemName("upFile")!=null) {
			originalImgList.add(mReq.getOriginalFileName("upFile"));
			renamedImgList.add(mReq.getFilesystemName("upFile"));
		}
		if(mReq.getOriginalFileName("upFile1")!=null && mReq.getFilesystemName("upFile1")!=null) {
			originalImgList.add(mReq.getOriginalFileName("upFile1"));
			renamedImgList.add(mReq.getFilesystemName("upFile1"));
		}
		if(mReq.getOriginalFileName("upFile2")!=null && mReq.getFilesystemName("upFile2")!=null) {
			originalImgList.add(mReq.getOriginalFileName("upFile2"));
			renamedImgList.add(mReq.getFilesystemName("upFile2"));
		}
		
		
		//XSS Cross-site Scripting:
		//사용자 작성 내용이 필터링 없이 db에 저장될 경우
		//개인정보 탈취 및 보안상 여러 위험을 야기할 수 있다.
		String reviewContent = mReq.getParameter("reviewContent");
		reviewContent = reviewContent.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
		
		String originalImg = "";
		String renamedImg = "";
		for(int i=0;i<originalImgList.size();i++) {
			if(i==originalImgList.size()-1) {
				originalImg += originalImgList.get(i);
				renamedImg += renamedImgList.get(i);
			}				
			else {
				originalImg += originalImgList.get(i)+"§";
				renamedImg += renamedImgList.get(i)+"§";
			}
		}
		
		System.out.println("originalImg="+originalImg);
		System.out.println("renamedImg="+renamedImg);

		ReviewBoard rb = new ReviewBoard();
		rb.setReviewTitle(reviewTitle);
		rb.setReviewWriter(reviewWriter);
		rb.setReviewKind(reviewKind);
		rb.setReviewContent(reviewContent);
		rb.setReviewOriginalImg(originalImg);
		rb.setReviewRenamedImg(renamedImg);

		//2.업무로직
		int result = new ReviewService().insertReview(rb);
		System.out.println("result@ReviewWriteServlet="+result);

		String msg = "";
		String loc = "/board/review/reviewList";

		if(result>0) {
			msg = "게시글 등록 성공!";
			loc = "/board/review/reviewView?boardNo="+result;
		} else {
			msg = "게시글 등록 실패!";
		}

		//3.view단 처리
		String view = "/WEB-INF/views/common/msg.jsp";
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
