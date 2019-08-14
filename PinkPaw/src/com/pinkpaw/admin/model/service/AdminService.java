package com.pinkpaw.admin.model.service;

import static com.pinkpaw.common.JDBCTemplate.close;

import static com.pinkpaw.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.pinkpaw.admin.model.dao.AdminDAO;
import com.pinkpaw.admin.model.vo.ReportBoard;
import com.pinkpaw.member.model.vo.Member;

public class AdminService {

	public List<Member> selectMemberList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list 
			= new AdminDAO().selectMemberList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public List<ReportBoard> selectReportList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<ReportBoard> list 
			= new AdminDAO().selectReportList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	
	public List<Member> selectMemberByMemberId(String searchKeyword) {
		Connection conn = getConnection();
		List<Member> list 
			= new AdminDAO().selectMemberByMemberId(conn, searchKeyword);
		close(conn);
		return list;
	}
	
	public List<Member> selectMemberByMemberName(String searchKeyword) {
		Connection conn = getConnection();
		List<Member> list= new AdminDAO().selectMemberByMemberName(conn,searchKeyword);
		close(conn);
		return list;
	}

	public List<Member> selectMemberByGender(String searchKeyword) {
		Connection conn = getConnection();
		List<Member> list= new AdminDAO().selectMemberByGender(conn,searchKeyword);
		close(conn);
		return list;
	}

	public int selecTotalContents() {
		Connection conn = getConnection();
		int totalContents = new AdminDAO().selecTotalContents(conn);
		close(conn);
		return totalContents;
	}
	
	public int selectMemberCountByMemberId(String searchKeyword) {
		Connection conn = getConnection();
		int totalMember = new AdminDAO().selectMemberCountByMemberId(conn, searchKeyword);
		close(conn);
		return totalMember;
	}

	public int selectMemberCountByMemberName(String searchKeyword) {
		Connection conn = getConnection();
		int totalMember = new AdminDAO().selectMemberCountByMemberName(conn, searchKeyword);
		close(conn);
		return totalMember;
	}

	public int selectMemberCountByGender(String searchKeyword) {
		Connection conn = getConnection();
		int totalMember = new AdminDAO().selectMemberCountByGender(conn, searchKeyword);
		close(conn);
		return totalMember;
	}

	public List<Member> selectMemberByMemberId(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list= new AdminDAO().selectMemberByMemberId(conn,searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Member> selectMemberByMemberName(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list= new AdminDAO().selectMemberByMemberName(conn,searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Member> selectMemberByGender(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list= new AdminDAO().selectMemberByGender(conn,searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	
		
}
