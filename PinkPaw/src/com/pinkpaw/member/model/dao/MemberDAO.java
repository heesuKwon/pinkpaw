package com.pinkpaw.member.model.dao;


import static com.pinkpaw.common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.pinkpaw.admin.model.vo.ReportBoard;
import com.pinkpaw.member.model.vo.Member;
import com.pinkpaw.member.model.vo.MyBoard;
import com.pinkpaw.member.model.vo.MyComment;

public class MemberDAO {
	
private Properties prop = new Properties();
	
	public MemberDAO() {
		String fileName 
			= MemberDAO.class		
					   .getResource("/sql/member/member-query.properties")
					   .getPath();
		try {
			prop.load(new FileReader(fileName));
			System.out.println("[[prop loading 완료:"+fileName+"]]");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public int insertMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertMember"); 
		
		try {
			
			Date enrolldate = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			
			String today = df.format(enrolldate);
			
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, today);
			pstmt.setInt(8, member.getReportCount());
			pstmt.setString(9, "member");
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public Member selectOne(Connection conn, String memberId) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOne");
		
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				m = new Member();
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setPassword(rset.getString("member_PASSWORD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setPhone(rset.getString("member_PHONE"));
				m.setEmail(rset.getString("member_EMAIL"));
				m.setAddress(rset.getString("member_ADDRESS"));
				m.setEnrolldate(rset.getString("member_enrolldate"));
				m.setReportCount(rset.getInt("member_112_count"));
				m.setGrade(rset.getString("member_grade"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return m;
	}


	public int loginCheck(Connection conn, Member member) {
		int result = -1;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("login_check");
		try {
			//1.PrepareStatement준비(미완성쿼리 완성)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMemberId());
			
			//2.실행 및 ResultSet 리턴받기
			rset = pstmt.executeQuery();
			
			//3.ResultSet -> result
			if(rset.next()) {
				result = rset.getInt("login_check");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		System.out.println("패스워드확인**********"+member.getPassword());
		System.out.println("결과값확인 ************"+result);
		
		return result;
	}


	public int updateMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateMember"); 

		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getMemberId());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int updatePassword(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updatePassword"); 

		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMemberId());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMember"); 

		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, memberId);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public List<MyBoard> selectMyBoard(Connection conn, int cPage, int numPerPage, String memberId) {
		List<MyBoard> list = new ArrayList<>();
		
		CallableStatement cstmt = null;
		
		ResultSet rset1 = null;
		
		Statement stmt2 = null;
		ResultSet rset2 = null;
		
		String sql2 = prop.getProperty("searchMyBoard");

		try {
			cstmt = conn.prepareCall("{call my_text(?)}");
			cstmt.setString(1, memberId);
			cstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(cstmt);
		}
		
		
		try {
			stmt2 = conn.createStatement();
			rset2 = stmt2.executeQuery(sql2);
			
			while(rset2.next()){
				MyBoard m = new MyBoard();
				m.setMyTable(rset2.getString("MY_TABLE"));
				m.setMyNo(rset2.getInt("MY_NO"));
				m.setMyTitle(rset2.getString("MY_TITLE"));
				m.setMyDate(rset2.getDate("MY_DATE"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt2);
			close(rset2);
			
		}
		
		return list;
	}
	
	public List<MyComment> selectMyComment(Connection conn, int cPage, int numPerPage, String memberId) {
		List<MyComment> list = new ArrayList<>();
		
		CallableStatement cstmt = null;
		
		ResultSet rset1 = null;
		
		Statement stmt2 = null;
		ResultSet rset2 = null;
		
		String sql2 = prop.getProperty("searchMyComment");

		try {
			cstmt = conn.prepareCall("{call my_comments(?)}");
			cstmt.setString(1, memberId);
			cstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(cstmt);
		}
		
		
		try {
			stmt2 = conn.createStatement();
			rset2 = stmt2.executeQuery(sql2);
			
			while(rset2.next()){
				MyComment m = new MyComment();
				m.setMyCoTable(rset2.getString("CO_TABLE"));
				m.setMyCoREF(rset2.getInt("REF_NO"));
				m.setMyCoContent(rset2.getString("CO_CONTENT"));
				m.setMyCoDate(rset2.getDate("CO_DATE"));
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt2);
			close(rset2);
			
		}
		
		return list;
	}

}
