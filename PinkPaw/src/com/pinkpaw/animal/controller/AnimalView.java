package com.pinkpaw.animal.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pinkpaw.animal.model.vo.ProtectedAnimal;

/**
 * Servlet implementation class AnimalView
 */
@WebServlet("/animal/animalView")
public class AnimalView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String age = request.getParameter("age");
		String careAddr = request.getParameter("careAddr");
		String careNm = request.getParameter("careNm");
		String careTel = request.getParameter("careTel");
		String chargeNm = request.getParameter("chargeNm");
		String colorCd = request.getParameter("colorCd");
		String desertionNo = request.getParameter("desertionNo");
		String filename = request.getParameter("filename");
		String happenDt = request.getParameter("happenDt");
		String happenPlace = request.getParameter("happenPlace");
		String kindCd = request.getParameter("kindCd");
		String neuterYn = request.getParameter("neuterYn");
		String noticeEdt = request.getParameter("noticeEdt");
		String noticeNo = request.getParameter("noticeNo");
		String noticeSdt = request.getParameter("noticeSdt");
		String officetel = request.getParameter("officetel");
		String orgNm = request.getParameter("orgNm");
		String popfile = request.getParameter("popfile");
		String processState = request.getParameter("processState");
		String sexCd = request.getParameter("sexCd");
		String specialMark = request.getParameter("specialMark");
		String weight = request.getParameter("weight");
		
		ProtectedAnimal p = new ProtectedAnimal(age, careAddr, careNm, careTel, chargeNm, colorCd, desertionNo, filename, happenDt, happenPlace, kindCd, neuterYn, noticeEdt, noticeNo, noticeSdt, officetel, orgNm, popfile, processState, sexCd, specialMark, weight);
		
		request.setAttribute("Animal", p);
		
		request.getRequestDispatcher("/WEB-INF/views/board/organic/announcement/announcementView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
