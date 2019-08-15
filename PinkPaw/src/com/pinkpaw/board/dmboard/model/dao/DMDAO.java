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



public class DMDAO {
	private Properties prop = new Properties();

	public DMDAO() {
		try {
			//클래스객체 위치찾기 : 절대경로를 반환한다. 
			// "/" : 루트디렉토리를 절대경로로 URL객체로 반환한다.
			// "./" : 현재디렉토리를 절대경로로 URL객체로 반환한다.
			// "./query.properties : 현재경로의 query.properties파일의 경로를 URL객체로 반환함.
			String fileName = DMDAO.class.getResource("/sql/board/dm-query.properties").getPath();
			prop.load(new FileReader(fileName));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	//	public int selectBoardCount(Connection conn) {
	//		PreparedStatement pstmt = null;
	//		int totalMember = 0;
	//		ResultSet rset = null;
	//		String query = prop.getProperty("selectBoardCount");
	//		
	//		try{
	//			//미완성쿼리문을 가지고 객체생성. 
	//			pstmt = conn.prepareStatement(query);
	//			
	//			//쿼리문실행
	//			rset = pstmt.executeQuery();
	//			
	//			while(rset.next()){
	//				totalMember = rset.getInt("cnt");
	//			}
	//		}catch(Exception e){
	//			e.printStackTrace();
	//		}finally{
	//			close(rset);
	//			close(pstmt);
	//		}
	//		
	//		return totalMember;
	//	}

	public List<DM> selectReceiveList(Connection conn,String memberId, int cPage, int numPerPage) {
		List<DM> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectReceiveList");

		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);

			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setString(1, memberId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);

			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();

			while(rset.next()){
				DM d = new DM();
				d.setDmNo(rset.getInt("dm_no"));
				d.setDmSend(rset.getString("dm_send"));
				d.setDmRecive(rset.getString("dm_receive"));
				d.setDmTitle(rset.getString("dm_title"));
				d.setDmContent(rset.getString("dm_content"));
				d.setDmDate(rset.getDate("dm_date"));
				d.setDmRecvRead(rset.getInt("dm_recv_read"));
				d.setRecvDel(rset.getInt("recv_del"));
				d.setSentDel(rset.getInt("sent_del"));
				d.setDmReportCount(rset.getInt("dm_112_count"));
				d.setDmReportWriter(rset.getString("dm_112_writer"));
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

	public int ReceiveTotalContents(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int totalMember = 0;
		ResultSet rset = null;
		String query = prop.getProperty("ReceiveTotalContents");

		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
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

	public DM selectOne(Connection conn, int dmNo) {
		DM d = new DM();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectOne");

		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);

			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setInt(1, dmNo);

			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();

			if(rset.next()){
				d = new DM();
				d.setDmNo(rset.getInt("dm_no"));
				d.setDmSend(rset.getString("dm_send"));
				d.setDmRecive(rset.getString("dm_receive"));
				d.setDmTitle(rset.getString("dm_title"));
				d.setDmContent(rset.getString("dm_content"));
				d.setDmDate(rset.getDate("dm_date"));
				d.setDmRecvRead(rset.getInt("dm_recv_read"));
				d.setRecvDel(rset.getInt("recv_del"));
				d.setSentDel(rset.getInt("sent_del"));
				d.setDmReportCount(rset.getInt("dm_112_count"));
				d.setDmReportWriter(rset.getString("dm_112_writer"));
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

	public void increaseRead(Connection conn, int dmNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("increaseRead");

		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);

			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setInt(1, dmNo);

			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}



	}

	public int deleteReceiver(Connection conn, int dmNo, String receiver) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("deleteReceiver");
		System.out.println("DAO"+dmNo+receiver);

		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);

			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setInt(1, dmNo);
			pstmt.setString(2, receiver);

			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			result = pstmt.executeUpdate();


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return result;
	}



	public int deleteSender(Connection conn, int dmNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("deleteSender");

		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);

			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setInt(1, dmNo);

			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public List<DM> selectSentList(Connection conn, String memberId, int cPage, int numPerPage) {
		List<DM> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectSentList");

		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);

			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setString(1, memberId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);

			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();

			while(rset.next()){
				DM d = new DM();
				d.setDmNo(rset.getInt("dm_no"));
				d.setDmSend(rset.getString("dm_send"));
				d.setDmRecive(rset.getString("dm_receive"));
				d.setDmTitle(rset.getString("dm_title"));
				d.setDmContent(rset.getString("dm_content"));
				d.setDmDate(rset.getDate("dm_date"));
				d.setDmRecvRead(rset.getInt("dm_recv_read"));
				d.setRecvDel(rset.getInt("recv_del"));
				d.setSentDel(rset.getInt("sent_del"));
				d.setDmReportCount(rset.getInt("dm_112_count"));
				d.setDmReportWriter(rset.getString("dm_112_writer"));
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

	public int SendTotalContents(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int totalMember = 0;
		ResultSet rset = null;
		String query = prop.getProperty("SendTotalContents");

		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
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

	public int updateDmBoardReport(Connection conn, DM d) {
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println("DAO"+d.getDmContent());
		System.out.println("DAO"+d.getDmNo());
		
		String query = prop.getProperty("updateDmBoardReport"); 
		
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, d.getDmReportReason());
			pstmt.setInt(2, d.getDmNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
}
