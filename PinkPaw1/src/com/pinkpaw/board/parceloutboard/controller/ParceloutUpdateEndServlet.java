package com.pinkpaw.board.parceloutboard.controller;

import java.io.File;
import java.io.IOException;
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
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "게시판작성오류! 관리자에게 문의하세요.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
				   .forward(request, response);
			return;
		}
		
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
				String saveDirectory = root + "upload" + File.separator	 + "board\\parcelout";
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
				
				
				//기존 첨부파일에 대한 정보
				String oldOName = mReq.getParameter("oldOName");
				String oldRName = mReq.getParameter("oldRName");
				
				//기존에 첨부한 파일이 있다면, 후처리작업
				if(!"".equals(oldOName)) {
					System.out.println("******* 기존 첨부파일이 있는 경우, 후처리 ********");
					
					//업로드된 파일에 대한 객체생성
					File f = mReq.getFile("upFile");
					
					//1.전송된 파일이 있는 경우
					if(f!=null && f.length()>0) {
						//기존파일이 있다면, 삭제
						File delFile 
							= new File(saveDirectory+"/"+mReq.getParameter("oldRName"));
						boolean bool = delFile.delete();
						System.out.println(bool?"파일 삭제 성공":"파일 삭제 실패");
						
					}
					//2.기존첨부파일을 삭제하려는 경우
					else if(mReq.getParameter("delFile") != null){
						//기존파일이 삭제
						//기존파일이 있다면, 삭제
						File delFile 
							= new File(saveDirectory+"/"+mReq.getParameter("oldRName"));
						boolean bool = delFile.delete();
						System.out.println(bool?"파일 삭제 성공":"파일 삭제 실패");
						
					}
					//3.첨부한 파일이 없는 경우
					else {
						//기존파일명을 다시 대입
						parceloutOriginalImg = oldOName;
						parceloutRenamedImg = oldRName;
						}
					}
		
				ParceloutBoard p = new ParceloutBoard();
				p.setParceloutNo(parceloutNo);
				p.setParceloutTitle(parceloutTitle);
				p.setParceloutPlace(parceloutPlace);
				p.setParceloutMoney(parceloutMoney);
				p.setParceloutKind(parceloutKind);
				p.setParceloutGender(parceloutGender);
				p.setParceloutContent(parceloutContent);
				p.setParceloutOriginalImg(parceloutOriginalImg);
				p.setParceloutRenamedImg(parceloutRenamedImg);
				
				int result = new ParceloutService().updateParcelout(p);
						
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
