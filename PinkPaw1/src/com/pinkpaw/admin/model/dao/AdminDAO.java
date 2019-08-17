package com.pinkpaw.admin.model.dao;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.pinkpaw.admin.model.vo.ReportBoard;
import com.pinkpaw.member.model.vo.Member;

public class AdminDAO {

	private Properties prop;
	
	public AdminDAO() {
		prop = new Properties();
		
		String fileName 
			= AdminDAO.class
				   	  .getResource("/sql/admin/admin-query.properties")
				   	  .getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
		
	public List<Member> selectMemberList(Connection conn, int cPage, int numPerPage) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMemberList");
		
		try {
			//미완성쿼리객체생성
			pstmt = conn.prepareStatement(sql);
			//(페이징공식1)
			//cPage=1,numPerPage=10 => 1,10
			//cPage=2,numPerPage=10 => 11,20
			//cPage=3,numPerPage=10 => 21,30
			int start = (cPage-1)*numPerPage+1;
			int end = cPage * numPerPage;
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			
			//쿼리실행
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setPassword(rset.getString("member_PASSWORD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setPhone(rset.getString("member_PHONE"));
				m.setEmail(rset.getString("member_EMAIL"));
				m.setAddress(rset.getString("member_ADDRESS"));
				m.setEnrolldate(rset.getString("member_enrolldate"));
				m.setReportCount(rset.getInt("member_112_count"));
				m.setGrade(rset.getString("member_grade"));
				
				list.add(m);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public List<Member> selectMemberByMemberId(Connection conn, String searchKeyword) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMemberByMemberId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setPassword(rset.getString("member_PASSWORD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setPhone(rset.getString("member_PHONE"));
				m.setEmail(rset.getString("member_EMAIL"));
				m.setAddress(rset.getString("member_ADDRESS"));
				m.setEnrolldate(rset.getString("member_enrolldate"));
				m.setReportCount(rset.getInt("member_112_count"));
				m.setGrade(rset.getString("member_grade"));
				
				list.add(m);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<Member> selectMemberByMemberName(Connection conn, String searchKeyword) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectMemberByMemberName");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Member m = new Member();
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setPassword(rset.getString("member_PASSWORD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setPhone(rset.getString("member_PHONE"));
				m.setEmail(rset.getString("member_EMAIL"));
				m.setAddress(rset.getString("member_ADDRESS"));
				m.setEnrolldate(rset.getString("member_enrolldate"));
				m.setReportCount(rset.getInt("member_112_count"));
				m.setGrade(rset.getString("member_grade"));
				
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
		
	public List<ReportBoard> selectReportList(Connection conn, int cPage, int numPerPage) {
		List<ReportBoard> list = new ArrayList<>();
		
		CallableStatement cstmt = null;
		
		String sql1 = prop.getProperty("startProcedure");
		ResultSet rset1 = null;
		
		Statement stmt2 = null;
		ResultSet rset2 = null;
		
		String sql2 = prop.getProperty("selectReportList");

		try {
			cstmt = conn.prepareCall("{call search_test}");
			rset1 = cstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(cstmt);
			close(rset1);
		}
		
		
		try {
			stmt2 = conn.createStatement();
			rset2 = stmt2.executeQuery(sql2);
			
			while(rset2.next()){
				ReportBoard r = new ReportBoard();
				r.setReportTableName(rset2.getString("DE_TABLE"));
				r.setReportNo(rset2.getInt("DE_NO"));
				r.setReportTitle(rset2.getString("DE_TITLE"));
				r.setReportReason(rset2.getString("DE_REASON"));
				
				list.add(r);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt2);
			close(rset2);
			
		}
		
		return list;
	}
	
	

	public List<Member> selectMemberByGender(Connection conn, String searchKeyword) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectMemberByGender");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Member m = new Member();
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setPassword(rset.getString("member_PASSWORD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setPhone(rset.getString("member_PHONE"));
				m.setEmail(rset.getString("member_EMAIL"));
				m.setAddress(rset.getString("member_ADDRESS"));
				m.setEnrolldate(rset.getString("member_enrolldate"));
				m.setReportCount(rset.getInt("member_112_count"));
				m.setGrade(rset.getString("member_grade"));
				
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selecTotalContents(Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalContents");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContents = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}

	public int selectMemberCountByMemberId(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalMember = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountByMemberId");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				totalMember = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return totalMember;
	}

	public int selectMemberCountByMemberName(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalMember = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountByMemberName");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				totalMember = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return totalMember;
	}

	public int selectMemberCountByGender(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalMember = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountByGender");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				totalMember = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return totalMember;

	}

	public List<Member> selectMemberByMemberId(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectPagedMemberByMemberId");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Member m = new Member();
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setPassword(rset.getString("member_PASSWORD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setPhone(rset.getString("member_PHONE"));
				m.setEmail(rset.getString("member_EMAIL"));
				m.setAddress(rset.getString("member_ADDRESS"));
				m.setEnrolldate(rset.getString("member_enrolldate"));
				m.setReportCount(rset.getInt("member_112_count"));
				m.setGrade(rset.getString("member_grade"));
				
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public List<Member> selectMemberByMemberName(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectPagedMemberByMemberName");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Member m = new Member();
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setPassword(rset.getString("member_PASSWORD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setPhone(rset.getString("member_PHONE"));
				m.setEmail(rset.getString("member_EMAIL"));
				m.setAddress(rset.getString("member_ADDRESS"));
				m.setEnrolldate(rset.getString("member_enrolldate"));
				m.setReportCount(rset.getInt("member_112_count"));
				m.setGrade(rset.getString("member_grade"));
				
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public List<Member> selectMemberByGender(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectPagedMemberByGender");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Member m = new Member();
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setPassword(rset.getString("member_PASSWORD"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setPhone(rset.getString("member_PHONE"));
				m.setEmail(rset.getString("member_EMAIL"));
				m.setAddress(rset.getString("member_ADDRESS"));
				m.setEnrolldate(rset.getString("member_enrolldate"));
				m.setReportCount(rset.getInt("member_112_count"));
				m.setGrade(rset.getString("member_grade"));
				
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	

}













