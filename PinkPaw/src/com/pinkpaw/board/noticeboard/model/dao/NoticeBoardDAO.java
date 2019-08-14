package com.pinkpaw.board.noticeboard.model.dao;

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

import com.pinkpaw.board.noticeboard.model.vo.NoticeBoard;

public class NoticeBoardDAO {
private Properties prop = new Properties();
	
	public NoticeBoardDAO() {
		try {
			//클래스객체 위치찾기 : 절대경로를 반환한다. 
			// "/" : 루트디렉토리를 절대경로로 URL객체로 반환한다.
			// "./" : 현재디렉토리를 절대경로로 URL객체로 반환한다.
			// "./query.properties : 현재경로의 query.properties파일의 경로를 URL객체로 반환함.
			String fileName = NoticeBoardDAO.class.getResource("/sql/board/adminboard-query.properties").getPath();
			prop.load(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalMember = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectBoardCount");
		
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

	public List<NoticeBoard> selectBoardList(Connection conn, int cPage, int numPerPage) {
		List<NoticeBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectBoardList");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			
			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				NoticeBoard b = new NoticeBoard();
				//컬럼명은 대소문자 구분이 없다.
				b.setNoticeNo(rset.getInt("notice_no"));
				b.setNoticeTitle(rset.getString("notice_title"));
				b.setNoticeWriter(rset.getString("notice_writer"));
				b.setNoticeContent(rset.getString("notice_content"));
				b.setNoticeEnrollDate(rset.getDate("notice_enrolldate"));
				b.setNoticeCount(rset.getInt("notice_count"));
				list.add(b);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	
	public int insertBoard(Connection conn, NoticeBoard b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, b.getNoticeTitle());
			pstmt.setString(2, b.getNoticeWriter());
			pstmt.setString(3, b.getNoticeContent());
			
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
	
	public NoticeBoard selectOne(Connection conn, int board_no) {
		NoticeBoard b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOne");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, board_no);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				b = new NoticeBoard();
				b.setNoticeNo(rset.getInt("notice_no"));
				b.setNoticeTitle(rset.getString("notice_title"));
				b.setNoticeWriter(rset.getString("notice_writer"));
				b.setNoticeContent(rset.getString("notice_content"));
				b.setNoticeEnrollDate(rset.getDate("notice_enrolldate"));
				b.setNoticeCount(rset.getInt("notice_count"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return b;
	}

	public int selectLastSeq(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int boardNo = 0;
		String sql = prop.getProperty("selectLastSeq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				boardNo = rset.getInt("currval");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return boardNo;
	}

	public int increaseReadCount(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("increaseReadCount"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
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
	
	public int deleteBoard(Connection conn, int board_no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, board_no);
			
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
	
	public int updateBoard(Connection conn, NoticeBoard b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, b.getNoticeTitle());
			pstmt.setString(2, b.getNoticeContent());
			pstmt.setInt(3, b.getNoticeNo());
			
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

//	public int insertBoardComment(Connection conn, BoardComment boardComment) {
//		
//		int result = 0;
//		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("insertBoardComment");
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, boardComment.getBoardCommentLevel());
//			pstmt.setString(2, boardComment.getBoardCommentWriter());
//			pstmt.setString(3, boardComment.getBoardCommentContent());
//			pstmt.setInt(4, boardComment.getBoardRef());
//			
//			//java - int : null을 허용하지 않음
//			//oracle - number : null가능
//			//**오라클쪽 db에는 null을 넣어야하는데 자바에서는 null을 넣을수가 없어서 다른방법을 써야함 (fk때문에)
//			pstmt.setString(5, boardComment.getBoardCommentRef()==0?null:boardComment.getBoardCommentRef()+"");
//			//원래는 setInt인데 setString으로 씀
//			
//			result = pstmt.executeUpdate();
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		
//		return result;
//	}
//
//	public List<BoardComment> boardComment(Connection conn, int boardNo) {
//		List<BoardComment> list = new ArrayList<>();
//		PreparedStatement pstmt = null;
//		String sql = prop.getProperty("boardCommnet");
//		ResultSet rset = null;
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setInt(1, boardNo);
//			rset = pstmt.executeQuery();
//			
//			while(rset.next()){
//				BoardComment comment = null;
//				comment = new BoardComment();
//				comment.setBoardCommentContent(rset.getString("B"));
//				comment.setBoardCommentNo(rset.getInt("board_Comment_No"));
//				comment.setBoardCommentLevel(rset.getInt("board_comment_level"));
//				comment.setBoardCommentWriter(rset.getString("board_comment_writer"));
//				comment.setBoardCommentContent(rset.getString("board_comment_content"));
//				comment.setBoardCommentRef(rset.getInt("board_comment_ref"));
//				comment.setBoardCommentDate(rset.getDate("board_comment_date"));
//				list.add(comment);
//			}
//			
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} 
//		
////		System.out.println("DAO서비스 확인 11111111111111111"+list);
//		
//		return list;
//	}
//	
//	public int deleteBoardComment(Connection conn, int boardCommentNo) {
//		int result = 0;
//		PreparedStatement pstmt = null;
//		String query = prop.getProperty("deleteBoardComment"); 
//		
//		try {
//			//미완성쿼리문을 가지고 객체생성.
//			pstmt = conn.prepareStatement(query);
//			//쿼리문미완성
//			pstmt.setInt(1, boardCommentNo);
//			
//			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
//			//DML은 executeUpdate()
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
	
}











