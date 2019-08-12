package com.pinkpaw.board.missingboard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFileDownloadServlet
 */
@WebServlet("/board/boardFileDownload")
public class BoardFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFileDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터핸들링
		String oName = request.getParameter("oName");
		String rName = request.getParameter("rName");
		System.out.printf("oName=%s, rName=%s\n", oName, rName);
		
		//2.입출력스트림생성
		String saveDirectory 
			= getServletContext().getRealPath("/upload/board");
		File downFile 
			= new File(saveDirectory+File.separator+rName);
		//입력스트림
		BufferedInputStream bis 
		= new BufferedInputStream(new FileInputStream(downFile));
		//출력스트림
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos 
			= new BufferedOutputStream(sos);
		
		//브라우져에 따른 파일명 선처리
		String resFileName = "";
		System.out.println(request.getHeader("user-agent"));
		boolean isMSIE 
			= request.getHeader("user-agent").indexOf("MSIE") != -1 
			|| request.getHeader("user-agent").indexOf("Trident") != -1;
		
		if(isMSIE) {
			//인코딩처리
			resFileName = URLEncoder.encode(oName, "utf-8");
			System.out.println("resFileName="+resFileName);
			//" " -> + : 이를 다시 명시적으로 + -> %20으로 치환
			resFileName = resFileName.replaceAll("\\+", "%20");
			System.out.println("resFileName="+resFileName);
		}
		else {
			resFileName	
			= new String(oName.getBytes("UTF-8"), "ISO-8859-1");			
		}
		
		
		
		
		
		
		//3.헤더정보작성
		//이진데이터 전송용 MIME타입
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", 
						   "attachment;filename="+resFileName);
		
		//4.파일쓰기
		int read = -1;
		while((read=bis.read()) != -1) {
			bos.write(read);
		}
		
		//자원반납
		bos.close();
		bis.close();
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
