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
		
		//파일을 리스트로 관리
		List<String> originalImgList = new ArrayList<>();
		List<String> renamedImgList = new ArrayList<>();
		
		//이전 파일 내역을 불러옴
		if(!"".equals(mReq.getParameter("oldOName"))&&!"".equals(mReq.getParameter("oldRName"))){
			String oldOName = mReq.getParameter("oldOName");
			String oldRName = mReq.getParameter("oldRName");
			
			System.out.println("oldOName="+oldOName);
			System.out.println("oldRName="+oldRName);
			
			String[] oldONames = oldOName.split("§");
			String[] oldRNames = oldRName.split("§");
			
			for(int i=0;i<oldONames.length;i++) {
				originalImgList.add(oldONames[i]);
				renamedImgList.add(oldRNames[i]);
			}
		}
		System.out.println(originalImgList.size());
		System.out.println(renamedImgList.size());
		
		//삭제될 파일을 String배열로 받아옴
		String[] delOriginalImg = mReq.getParameterValues("delOriginalImg");
		String[] delRenamedImg = mReq.getParameterValues("delRenamedImg");
		if(delOriginalImg != null && delRenamedImg != null) {
			for(int i=0;i<delOriginalImg.length;i++) {
				System.out.println("delOriginalImg="+delOriginalImg[i]);
				System.out.println("delRenamedImg="+delRenamedImg[i]);
				//리스트에 파일이 있으면 삭제
				if(originalImgList.contains(delOriginalImg[i])) {
					originalImgList.remove(delOriginalImg[i]);
				}
				if(renamedImgList.contains(delRenamedImg[i])) {
					renamedImgList.remove(delRenamedImg[i]);
				}
			}
		}
		System.out.println("삭제할 파일 삭제 후 : "+originalImgList.size());
		System.out.println("삭제할 파일 삭제 후 : "+renamedImgList.size());
		
		
		//첨부파일
		for(int i=1;i<=3;i++) {
			String original=mReq.getOriginalFileName("upFile"+i);
			String renamed=mReq.getFilesystemName("upFile"+i);
			if(original!=null && renamed!=null) {
				originalImgList.add(original);
				renamedImgList.add(renamed);
			}
		}
		
//		if(mReq.getOriginalFileName("upFile2")!=null && mReq.getFilesystemName("upFile2")!=null) {
//			originalImgList.add(mReq.getOriginalFileName("upFile2"));
//			renamedImgList.add(mReq.getFilesystemName("upFile2"));
//		}
//		if(mReq.getOriginalFileName("upFile3")!=null && mReq.getFilesystemName("upFile3")!=null) {
//			originalImgList.add(mReq.getOriginalFileName("upFile3"));
//			renamedImgList.add(mReq.getFilesystemName("upFile3"));
//		}
		
		System.out.println("추가된 파일 추가 후 : "+originalImgList.size());
		System.out.println("추가된 파일 추가 후 : "+renamedImgList.size());
		
		
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
		
	
		//기존에 첨부한 파일이 있다면, 후처리 작업 if(!"".equals(oldOName)) {
		System.out.println("******* 기존 첨부파일이 있는 경우, 후처리 *******");

		//업로드된 파일에 대한 객체 생성 File f = mReq.getFile("upFile");

		/*
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
		
		//update 성공시
		if(result>0) {
			//삭제된 파일이 있으면
			if(delOriginalImg != null && delRenamedImg != null) {
				//삭제된 파일 메모리에서 삭제
				for(int i=0;i<delRenamedImg.length;i++) {
					File delOldFile = new File(saveDirectory+File.separator+delRenamedImg[i]);
					System.out.println("delOldFile="+delOldFile);
					boolean bool = delOldFile.delete();
					System.out.println("삭제여부: "+bool);		
				}
			}
		}

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
