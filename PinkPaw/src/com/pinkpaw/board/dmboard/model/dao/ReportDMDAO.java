package com.pinkpaw.board.dmboard.model.dao;

import static com.pinkpaw.common.JDBCTemplate.*;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.pinkpaw.board.dmboard.model.vo.DM;
import com.pinkpaw.board.dmboard.model.vo.reportDM;

public class ReportDMDAO {
private Properties prop = new Properties();
	
	public ReportDMDAO() {
		try {
			//클래스객체 위치찾기 : 절대경로를 반환한다. 
			// "/" : 루트디렉토리를 절대경로로 URL객체로 반환한다.
			// "./" : 현재디렉토리를 절대경로로 URL객체로 반환한다.
			// "./query.properties : 현재경로의 query.properties파일의 경로를 URL객체로 반환함.
			String fileName = ReportDMDAO.class.getResource("/sql/board/ReportDM-query.properties").getPath();
			prop.load(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public int selectReportDMCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalMember = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectDMReportCount");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			
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

	public List<reportDM> selectReportDMList(Connection conn, int cPage, int numPerPage) {
		List<reportDM> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectReportDMList");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			
			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				reportDM d = new reportDM();
				//컬럼명은 대소문자 구분이 없다.
				d.setDmNo(rset.getInt("dm_no"));
				d.setDmTitle(rset.getString("dm_title"));
				d.setDmSend(rset.getString("dm_send"));
				d.setDmRecive(rset.getString("dm_receive"));
				d.setDmContent(rset.getString("dm_content"));
				d.setDmDate(rset.getDate("dm_date"));
				d.setDmReportReason(rset.getString("dm_112_reason"));
				list.add(d);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
		
	public DM selectOne(Connection conn, int dm_no) {
		DM d = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOne");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, dm_no);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				d = new DM();
						
				d.setDmNo(rset.getInt("dm_no"));
				d.setDmTitle(rset.getString("dm_title"));
				d.setDmSend(rset.getString("dm_send"));
				d.setDmRecive(rset.getString("dm_receive"));
				d.setDmContent(rset.getString("dm_content"));
				d.setDmDate(rset.getDate("dm_date"));
				d.setDmReportReason(rset.getString("dm_112_reason"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return d;
	}

	public int selectLastSeq(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int DMNo = 0;
		String sql = prop.getProperty("selectLastSeq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				DMNo = rset.getInt("currval");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return DMNo;
	}
	
	
	/********************************************************/

	public int deleteReportDM(Connection conn, int DmNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteReportDM"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, DmNo);
			
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

	
	}
	












