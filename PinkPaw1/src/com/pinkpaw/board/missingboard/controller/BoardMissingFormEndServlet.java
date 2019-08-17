package com.pinkpaw.board.missingboard.controller;

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
import com.pinkpaw.board.missingboard.model.service.MissingService;
import com.pinkpaw.board.missingboard.model.vo.MissingBoard;
import com.pinkpaw.common.util.MvcRenamePolicy;

/**
 * Servlet implementation class BoardMissingFormEndServlet
 */
@WebServlet("/board/missingFormEnd")
public class BoardMissingFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardMissingFormEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//0.파일업로드 체크: enctype="multipart/form-data"
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "게시판파일등록오류!");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
				   .forward(request, response);
			return;
		}
		
		/*********** 파일업로드 로직시작 ***********/
		//new MultipartRequest(HttpServletRequest, saveDirectory, maxPostSize, encoding, FileRenamePolicy(중복파일naming정책))
		//a.saveDirectory: 실제파일저장경로(절대경로)
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload" + File.separator + "board/missing";
		System.out.println("saveDirectory="+saveDirectory);
		
		//b.maxPostSize:파일최대용량(10mb) - 1kb * 1kb * 10
		int maxPostSize = 1024 * 1024 * 10;
		
		//c.encoding: UTF-8
		String encoding = "UTF-8";
		
		//d.FileRenamePolicy객체: 서버저장용 파일명 생성객체
		MvcRenamePolicy mvcRenamePolicy
			= new MvcRenamePolicy();
		
		MultipartRequest mReq 
			= new MultipartRequest(request, 
								   saveDirectory, 
								   maxPostSize, 
								   encoding, 
								   mvcRenamePolicy);
		
		/*********** 파일업로드 로직끝 ***********/
		
		
		//1.파라미터 핸들링: MultipartRequest객체 생성후에는
		//더이상 HttpServletRequest객체로부터 사용자입력값을 가져올수 없다.
		//글제목
		String missingTitle = mReq.getParameter("boardTitle");
		System.out.println(missingTitle);
		//등록인
		String missingWriter = mReq.getParameter("boardWriter");
		//분류
		String kind = mReq.getParameter("kind");
		//기타분류
		String others = mReq.getParameter("others");
		
		
		if(kind.equals("animal")) {
			kind = others;
		}
		
		//장소
//		String missingPlace = mReq.getParameter("missingPlace");
		//시
		String sido = mReq.getParameter("sido");
		//구군
		String gugun  = mReq.getParameter("gugun");
		//상세
		String detailPlace = mReq.getParameter("detailPlace");
		String missingPlace = sido+" "+ gugun+" "+detailPlace;
		System.out.println("잃어버린장소: "+missingPlace);
		//잃어버린날짜 문자열타입
		String missingDate = mReq.getParameter("lostDate");
		System.out.println("잃어버린 날짜: "+missingDate);
		//사례금
		int money = Integer.parseInt(mReq.getParameter("reward"));
			int reward = 0;
		try {
			
			reward = Integer.parseInt(mReq.getParameter("rewardText"));
		} catch(NumberFormatException e) {
			
		}
		//협의 -1, 없음 0 , 있음 1
		if(money == 1) {
			money = reward;
			
		}
		//연락처
		String phone = mReq.getParameter("phone");
		
		String missingContent = mReq.getParameter("boardContent");
		
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
				
				System.out.println("originalImg="+originalImg);
				System.out.println("renamedImg="+renamedImg);
		
		
		

		//Secure-Coding
		//XSS Cross-site Scripting:
		//사용자 작성 내용이 필터링 없이 db에 저장될 경우
		//개인정보 탈취 및 보안상 여러 위험을 야기할 수 있다.
		String boardContent = mReq.getParameter("boardContent");
		boardContent = boardContent.replaceAll("<", "&lt;")
								   .replaceAll(">", "&gt;");
	

		
	

		
		MissingBoard b = new MissingBoard();
		b.setMissingTitle(missingTitle);
		b.setMissingWriter(missingWriter);
		b.setMissingHpPlace(missingPlace);
		b.setMissingHpDate(missingDate);
		b.setMissingMoney(money);
		b.setMissingPhone(phone);
		b.setMissingKind(kind);
		b.setMissingContent(missingContent);
		b.setMissingOriginalImg(originalImg);
		b.setMissingRenamedImg(renamedImg);
		System.out.println("servlet@"+b);
		
		//2.업무로직
		int result = new MissingService().insertBoard(b);
		
		String msg = "";
		String loc = "/";

		if(result>0) {
			System.out.println("게시글 등록성공");
			msg = "게시글 등록성공!";
			loc = "/board/missingList";
		}
		else {
			msg = "게시글 등록실패!";
		}
		
		//3.view단 처리
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			   .forward(request, response);
		
//		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
//		.forward(request, response);
//		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
