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
 * Servlet implementation class ParceloutWriteEndServlet
 */
@WebServlet("/board/parceloutboard/parceloutWriteEnd")
public class ParceloutWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParceloutWriteEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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

		/*********** 파일업로드 로직끝 ***********/
		String parceloutTitle = mReq.getParameter("parceloutTitle");
		String parceloutWriter = mReq.getParameter("parceloutWriter");
		String sido = mReq.getParameter("sido");
		String gugun = mReq.getParameter("gugun");
		String parceloutPlace = sido +" "+ gugun;
		int parceloutMoney = Integer.parseInt(mReq.getParameter("parceloutMoney"));
		String parceloutKind = mReq.getParameter("parceloutKind");
		String parceloutGender = mReq.getParameter("parceloutGender");
		String others = mReq.getParameter("others");
		
		if(parceloutGender.equals("수")) {
			parceloutGender = "m";
		}else if(parceloutGender.equals("암")) {
			parceloutGender = "f";
		}else {
			parceloutGender = "s";
		}
		
		
		if(parceloutKind.equals("animal")) {
			parceloutKind = "기타_"+others;
		}
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
				String parceloutContent = mReq.getParameter("parceloutContent");
				parceloutContent = parceloutContent.replaceAll("<", "&lt;")
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
		
		
		System.out.println("parceloutTitle@servlet="+parceloutTitle);
		System.out.println("parceloutWriter@servlet="+parceloutWriter);
		System.out.println("parceloutPlace@servlet="+parceloutPlace);
		System.out.println("parceloutkind@servlet="+parceloutKind);
		System.out.println("parceloutGender@servlet="+parceloutGender);
		System.out.println("parceloutContent@servlet="+parceloutContent);
		
		
		ParceloutBoard p = new ParceloutBoard();
		p.setParceloutTitle(parceloutTitle);
		p.setParceloutWriter(parceloutWriter);
		p.setParceloutPlace(parceloutPlace);
		p.setParceloutMoney(parceloutMoney);
		p.setParceloutKind(parceloutKind);
		p.setParceloutGender(parceloutGender);
		p.setParceloutContent(parceloutContent);
		p.setParceloutOriginalImg(originalImg);
		p.setParceloutRenamedImg(renamedImg);
		
		int result = new ParceloutService().insertParcelout(p);
				
		String msg = "";
		String loc = "/board/parcelout/parceloutList";
		
		if(result>0) {
			msg = "게시글 등록성공!";
			loc = "/board/parcelout/parceloutView?parceloutNo="+result;
		}
		else {
			msg = "게시글 등록실패!";
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
