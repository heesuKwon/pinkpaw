package com.pinkpaw.board.parceloutboard.controller;

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
import com.pinkpaw.board.parceloutboard.model.service.ParceloutService;
import com.pinkpaw.board.parceloutboard.model.vo.ParceloutBoard;
import com.pinkpaw.common.util.MvcRenamePolicy;

/**
 * Servlet implementation class ParceloutUpdateEndServlet
 */
@WebServlet("/board/parceloutboard/parceloutUpdateEnd")
public class ParceloutUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParceloutUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//0. 파일 업로드 체크 : enctype="multipart/form-data"
				if(!ServletFileUpload.isMultipartContent(request)) {
					request.setAttribute("msg", "게시판 파일 등록 오류!");
					request.setAttribute("loc", "/");
					request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
					return;
				}
				/*********** 파일업로드 로직시작 ***********/
				//new MultipartRequest(HttpServletRequest, saveDirectory, maxPostSize, encoding, FileRenamePolicy(중복파일naming정책))
				//a.saveDirectory: 실제파일저장경로(절대경로)
				String root = getServletContext().getRealPath("/");
				String saveDirectory = root + "upload" + File.separator	 + "board/parcelout";
				System.out.println("saveDirectory="+saveDirectory);
				
				
				//b.maxPostSize:파일최대용량(10mb) - 1kb * 1kb * 10
				int maxPostSize = 1024 *1024 * 10;
				
				//c.encoding = UTF-8
				String encoding = "UTF-8";
				
				//d.FileRenamePolicy객체 : 서버저장용 파일명 생성객체
				FileRenamePolicy policy
					= new MvcRenamePolicy();
				
				MultipartRequest mReq
					= new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
				
				/******* 파일업로드 로직 끝 ********/
				
				int parceloutNo = Integer.parseInt(mReq.getParameter("parceloutNo"));
				String parceloutTitle = mReq.getParameter("parceloutTitle");
				String sido = mReq.getParameter("sido");
				String gugun = mReq.getParameter("gugun");
				String parceloutPlace = sido +" "+ gugun;
				int parceloutMoney = Integer.parseInt(mReq.getParameter("parceloutMoney"));
				String parceloutKind = mReq.getParameter("parceloutKind");
				String parceloutGender = mReq.getParameter("parceloutGender");
				String parceloutContent = mReq.getParameter("parceloutContent");
				String others = mReq.getParameter("others");
				
				if(parceloutGender.equals("수")) {
					parceloutGender = "m";
				}else if(parceloutGender.equals("암")) {
					parceloutGender = "f";
				}else {
					parceloutGender = "s";
				}
				
				
				if(parceloutKind.equals("animal")) {
					parceloutKind = others;
				}
				String parceloutRenamedImg = mReq.getFilesystemName("upFile");
				String parceloutOriginalImg = mReq.getOriginalFileName("upFile");
				
				System.out.println("parceloutNo@servlet="+parceloutNo);
				System.out.println("parceloutTitle@servlet="+parceloutTitle);
				System.out.println("parceloutPlace@servlet="+parceloutPlace);
				System.out.println("parceloutkind@servlet="+parceloutKind);
				System.out.println("parceloutGender@servlet="+parceloutGender);
				System.out.println("parceloutContent@servlet="+parceloutContent);
				
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

				//				if(mReq.getOriginalFileName("upFile2")!=null && mReq.getFilesystemName("upFile2")!=null) {
				//					originalImgList.add(mReq.getOriginalFileName("upFile2"));
				//					renamedImgList.add(mReq.getFilesystemName("upFile2"));
				//				}
				//				if(mReq.getOriginalFileName("upFile3")!=null && mReq.getFilesystemName("upFile3")!=null) {
				//					originalImgList.add(mReq.getOriginalFileName("upFile3"));
				//					renamedImgList.add(mReq.getFilesystemName("upFile3"));
				//				}

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

				
				
				//기존 첨부파일에 대한 정보
//				String oldOName = mReq.getParameter("oldOName");
//				String oldRName = mReq.getParameter("oldRName");
				
				//기존에 첨부한 파일이 있다면, 후처리작업
//				if(!"".equals(oldOName)) {
//					System.out.println("******* 기존 첨부파일이 있는 경우, 후처리 ********");
//					
//					//업로드된 파일에 대한 객체생성
//					File f = mReq.getFile("upFile");
//					
//					//1.전송된 파일이 있는 경우
//					if(f!=null && f.length()>0) {
//						//기존파일이 있다면, 삭제
//						File delFile 
//							= new File(saveDirectory+"/"+mReq.getParameter("oldRName"));
//						boolean bool = delFile.delete();
//						System.out.println(bool?"파일 삭제 성공":"파일 삭제 실패");
//						
//					}
					//2.기존첨부파일을 삭제하려는 경우
//					else if(mReq.getParameter("delFile") != null){
//						//기존파일이 삭제
//						//기존파일이 있다면, 삭제
//						File delFile 
//							= new File(saveDirectory+"/"+mReq.getParameter("oldRName"));
//						boolean bool = delFile.delete();
//						System.out.println(bool?"파일 삭제 성공":"파일 삭제 실패");
						
//					}
					//3.첨부한 파일이 없는 경우
//					else {
//						//기존파일명을 다시 대입
//						parceloutOriginalImg = oldOName;
//						parceloutRenamedImg = oldRName;
//						}
//					}
		
				ParceloutBoard p = new ParceloutBoard();
				p.setParceloutNo(parceloutNo);
				p.setParceloutTitle(parceloutTitle);
				p.setParceloutPlace(parceloutPlace);
				p.setParceloutMoney(parceloutMoney);
				p.setParceloutKind(parceloutKind);
				p.setParceloutGender(parceloutGender);
				p.setParceloutContent(parceloutContent);
				p.setParceloutOriginalImg(originalImg);
				p.setParceloutRenamedImg(renamedImg);
				
				int result = new ParceloutService().updateParcelout(p);
				
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
				String loc = "/board/parcelout/parceloutList";
				
				if(result>0) {
					msg = "게시글 수정성공!";
					loc = "/board/parcelout/parceloutView?parceloutNo="+parceloutNo;
				}
				else {
					msg = "게시글 수정실패!";
				}
				
				//3.view단 처리
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
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
