package com.pinkpaw.board.reviewboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class ReviewUpdateEndServlet
 */
@WebServlet("/board/review/reviewUpdateEnd")
public class ReviewUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.파일 업로드 체크: entype="multipart/form-data"
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "게시판파일수정오류!");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			.forward(request, response);
			return;
		}

		/*******파일업로드 로직 시작*******/
		//2번째 경우를 위한 파일 업로드 기본틀 잡기
		//a.saveDirectory: 실제 파일 저장 경로(절대경로)
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload" + File.separator + "board/review";
		System.out.println("saveDirectory="+saveDirectory);

		//b.maxPostSize: 파일 최대 용량(10MB = 1KB * 1KB *10)
		int maxPostSize = 1024 * 1024 * 10;

		//c.encoding: UTF-8
		String encoding = "UTF-8";

		//d.FileRenamePolicy객체: 서버 저장용 파일명 생성 객체
		FileRenamePolicy mvcRenamePolicy = new MvcRenamePolicy();

		MultipartRequest mReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, mvcRenamePolicy);

		/*******파일업로드 로직 끝*******/

		//1.파라미터 핸들링
		int reviewNo = Integer.parseInt(mReq.getParameter("reviewNo"));
		String reviewTitle = mReq.getParameter("reviewTitle");
		String reviewWriter = mReq.getParameter("reviewWriter");
		String reviewKind = mReq.getParameter("reviewKind");

		String reviewContent = mReq.getParameter("reviewContent");
		reviewContent = reviewContent.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
		/*
				2. 첨부파일이 있는 경우
				 1) 파일 관련 수정을 하지 않는 경우:  upFile=null
				 2) 새로운 파일을 첨부한 경우: upFile있음. 기존파일은 삭제
				 3) 기존 파일을 삭제만 하는 경우(delFile): upFile=null.기존파일 삭제
		 */
		String delFile = mReq.getParameter("delFile");
		System.out.println("delFile="+delFile);
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
		

		String oldOName = mReq.getParameter("oldOName");
		String oldRName = mReq.getParameter("oldRName");

		/*
		 * //기존에 첨부한 파일이 있다면, 후처리 작업 if(!"".equals(oldOName)) {
		 * System.out.println("******* 기존 첨부파일이 있는 경우, 후처리 *******");
		 * 
		 * //업로드된 파일에 대한 객체 생성 File f = mReq.getFile("upFile");
		 * 
		 * //2)새로운 파일을 첨부한 경우: upFile 있음. 기존파일은 삭제 if(f!=null &&f.length()>0) { //이름 //
		 * b.setOriginalFileName(originalFileName);//새로운파일 //
		 * b.setRenamedFileName(renamedFileName);//새로운파일 //실제파일: 기존파일 삭제필요 // File
		 * delOldFile = new File(saveDirectory+File.separator+oldRenamedFileName); File
		 * delOldFile = new File(saveDirectory+File.separator+oldRName);
		 * System.out.println("delOldFile="+delOldFile); boolean bool =
		 * delOldFile.delete(); System.out.println("삭제여부: "+bool); } //upFile=null :
		 * 1),3) else{ //3)기존 파일을 삭제만 하는 경우(delFile) : // if("on".equals(delFile)) {
		 * if(delFile != null) { //이름 // b.setOriginalFileName(originalFileName);//null
		 * // b.setRenamedFileName(renamedFileName);//null //실제파일: 기존파일 삭제필요 // File
		 * delOldFile = new File(saveDirectory+File.separator+oldRenamedFileName); File
		 * delOldFile = new File(saveDirectory+File.separator+oldRName);
		 * System.out.println("delOldFile="+delOldFile); boolean bool =
		 * delOldFile.delete(); System.out.println("삭제여부: "+bool); } //1)파일 관련 수정을 하지 않는
		 * 경우 else { //이름 // b.setOriginalFileName(oldOriginalFileName);//이전파일 //
		 * b.setRenamedFileName(oldRenamedFileName);//이전파일
		 * 
		 * originalFileName = oldOName; //이전파일 renamedFileName = oldRName; //이전파일
		 * //실제파일: 그대로 } } }
		 */

		//Board 객체 생성
		ReviewBoard rb = new ReviewBoard();
		rb.setReviewNo(reviewNo);
		rb.setReviewTitle(reviewTitle);
		rb.setReviewWriter(reviewWriter);
		rb.setReviewKind(reviewKind);
		rb.setReviewContent(reviewContent);
		rb.setReviewOriginalImg(originalImg);
		rb.setReviewRenamedImg(renamedImg);

		//2.업무로직
		int result = new ReviewService().updateReview(rb);

		String msg = "";
		String loc = "/board/review/reviewList";

		if(result>0) {
			msg = "게시글 등록 성공!";
			loc = "/board/review/reviewView?reviewNo="+reviewNo;
		}
		else {
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
