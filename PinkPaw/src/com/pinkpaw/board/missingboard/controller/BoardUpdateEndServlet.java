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
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.pinkpaw.board.missingboard.model.service.MissingService;
import com.pinkpaw.board.missingboard.model.vo.MissingBoard;
import com.pinkpaw.common.util.MvcRenamePolicy;

/**
 * Servlet implementation class BoardFormEndServlet
 */
@WebServlet("/board/missingUpdateEnd")
public class BoardUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/******* 파일업로드 로직 시작 ********/
		//enctype="multipart/form-data" 로 전송되었는지 확인. 
		//아래 두패키지에서 제공함.
		//org.apache.commons.fileupload.servlet.ServletFileUpload
		//org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "게시판작성오류![form:enctype 관리자에게 문의하세요.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
				   .forward(request, response);
			return;
		}
		
		
		//a.현재 웹 컨테이너에서 구동중인 웹 어플리케이션의 루트 절대경로 알아내기
		//ServletContext javax.servlet.GenericServlet.getServletContext()
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload"+File.separator+"board/missing";
		System.out.println("saveDirectory => "+saveDirectory);
		
		//b.파일최대용량 : 업로드할 파일 10MB까지 제한.
		//1KB*1KB*10
		int maxPostSize = 1024 * 1024 * 10;
		
		//c.FileRenamePolicy객체 생성
		FileRenamePolicy policy
			= new MvcRenamePolicy();

		//d. MultipartRequest 객체 생성함 : 자동 파일 업로드됨
		//업로드 파일최대크기를 초과하면 IOException이 발생된다. 반드시 Exception처리해야 한다.(try~catch)
		/* 
		 com.oreilly.servlet.MultipartRequest.MultipartRequest(HttpServletRequest request, 
															   String saveDirectory, 
															   int maxPostSize, 
															   String encoding, 
															   FileRenamePolicy 중복파일네이밍정책객체) throws IOException
															
		 */
		MultipartRequest mReq = new MultipartRequest(request, 
														 saveDirectory, 
														 maxPostSize, 
														 "UTF-8", 
														 policy);
		/******* 파일업로드 로직 끝 ********/
		
		//1.파라미터 핸들링: MultipartRequest객체 생성후에는
		//더이상 HttpServletRequest객체로부터 사용자입력값을 가져올수 없다.
		//글제목
		int boardNo = Integer.parseInt(mReq.getParameter("boardNo"));
		
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
		System.out.println("핸드폰"+phone);
		
		String boardContent = mReq.getParameter("boardContent");
		boardContent = boardContent.replaceAll("<", "&lt;")
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

//if(mReq.getOriginalFileName("upFile2")!=null && mReq.getFilesystemName("upFile2")!=null) {
//	originalImgList.add(mReq.getOriginalFileName("upFile2"));
//	renamedImgList.add(mReq.getFilesystemName("upFile2"));
//}
//if(mReq.getOriginalFileName("upFile3")!=null && mReq.getFilesystemName("upFile3")!=null) {
//	originalImgList.add(mReq.getOriginalFileName("upFile3"));
//	renamedImgList.add(mReq.getFilesystemName("upFile3"));
//}

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


		

		//Secure-Coding
		//XSS Cross-site Scripting:
		//사용자 작성 내용이 필터링 없이 db에 저장될 경우
		//개인정보 탈취 및 보안상 여러 위험을 야기할 수 있다.
		

		
		
		MissingBoard b = new MissingBoard();
		b.setMissingNo(boardNo);
		b.setMissingTitle(missingTitle);
		b.setMissingWriter(missingWriter);
		b.setMissingHpPlace(missingPlace);
		b.setMissingHpDate(missingDate);
		b.setMissingMoney(money);
		b.setMissingPhone(phone);
		b.setMissingKind(kind);
		b.setMissingContent(boardContent);
		b.setMissingOriginalImg(originalImg);
		b.setMissingRenamedImg(renamedImg);
		System.out.println("servlet@"+b);
		
		
		
		
		
		
		
		//2. 비지니스로직 호출
		int result = new MissingService().updateBoard(b);
		
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
		//3. 처리결과에 따른 view단에 처리위임.
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		//javascript/html에서 사용할 url은 contextPath를 포함한다.
		String loc = "/board/missingList";

		if(result>0){
			msg = "게시판 등록 성공!";
			loc = "/board/missingView?missingNo="+boardNo;
		}
		else {
			msg = "게시판 등록 실패!";				
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
