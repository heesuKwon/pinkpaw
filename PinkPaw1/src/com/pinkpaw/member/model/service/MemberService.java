package com.pinkpaw.member.model.service;

import static com.pinkpaw.common.JDBCTemplate.close;


import static com.pinkpaw.common.JDBCTemplate.commit;
import static com.pinkpaw.common.JDBCTemplate.getConnection;
import static com.pinkpaw.common.JDBCTemplate.rollback;
import static com.pinkpaw.common.JDBCTemplate.*;
import static com.pinkpaw.common.JDBCTemplate.getConnection;
import static com.pinkpaw.common.JDBCTemplate.close;
import static com.pinkpaw.common.JDBCTemplate.getConnection;
import static com.pinkpaw.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.pinkpaw.admin.model.dao.AdminDAO;
import com.pinkpaw.admin.model.vo.ReportBoard;
import com.pinkpaw.member.model.dao.MemberDAO;
import com.pinkpaw.member.model.vo.Member;
import com.pinkpaw.member.model.vo.MyBoard;
import com.pinkpaw.member.model.vo.MyComment;

public class MemberService {
	
	public static final int LOGIN_OK = 1;
	public static final int WRONG_PASSWORD = 0;
	public static final int ID_NOT_EXIST = -1;

	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = new MemberDAO().insertMember(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public Member selectOne(String memberId) {
		Connection conn = getConnection();
		Member m = new MemberDAO().selectOne(conn, memberId);
		close(conn);
		return m;
	}

	public int loginCheck(Member member) {
		Connection conn = getConnection();
		int result = new MemberDAO().loginCheck(conn, member);
		close(conn);
		return result;
	}

	public int updateMember(Member member) {
		Connection conn = getConnection();
		int result = new MemberDAO().updateMember(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int updatePassword(Member member) {
		Connection conn = getConnection();
		int result = new MemberDAO().updatePassword(conn, member);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteMember(String memberId) {
		Connection conn = getConnection();
		int result = new MemberDAO().deleteMember(conn, memberId);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}
	
	public List<MyBoard> selectMyBoard(int cPage, int numPerPage, String memberId) {
		Connection conn = getConnection();
		List<MyBoard> list 
			= new MemberDAO().selectMyBoard(conn, cPage, numPerPage, memberId);
		close(conn);
		
		
		return list;
	}
	
	public List<MyComment> selectMyComment(int cPage, int numPerPage, String memberId) {
		Connection conn = getConnection();
		List<MyComment> list 
			= new MemberDAO().selectMyComment(conn, cPage, numPerPage, memberId);
		close(conn);
		
		
		return list;
	}

}
