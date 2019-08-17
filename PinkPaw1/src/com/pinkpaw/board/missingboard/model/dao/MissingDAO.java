package com.pinkpaw.board.missingboard.model.dao;
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

import com.pinkpaw.board.common.model.vo.BoardComment;
import com.pinkpaw.board.missingboard.model.vo.MissingBoard;

public class MissingDAO {
private Properties prop = new Properties();
	
	public MissingDAO() {
		try {
			//클래스객체 위치찾기 : 절대경로를 반환한다. 
			// "/" : 루트디렉토리를 절대경로로 URL객체로 반환한다.
			// "./" : 현재디렉토리를 절대경로로 URL객체로 반환한다.
			// "./query.properties : 현재경로의 query.properties파일의 경로를 URL객체로 반환함.
			String fileName = MissingDAO.class.getResource("/sql/board/missingBoard-query.properties").getPath();
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

	public List<MissingBoard> selectBoardList(Connection conn, int cPage, int numPerPage) {
		List<MissingBoard> list = new ArrayList<>();
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
				MissingBoard b = new MissingBoard();
				//컬럼명은 대소문자 구분이 없다.
				b.setMissingNo(rset.getInt("missing_no"));
				b.setMissingTitle(rset.getString("missing_title"));
				b.setMissingWriter(rset.getString("missing_writer"));
				b.setMissingHpPlace(rset.getString("missing_hp_place"));
				b.setMissingHpDate(rset.getString("missing_hp_date"));
				b.setMissingMoney(rset.getInt("missing_money"));
				b.setMissingPhone(rset.getString("missing_phone"));
				b.setMissingKind(rset.getString("missing_kind"));
				b.setMissingContent(rset.getString("missing_content"));
				//sql바뀌면 바꿔야됌
				b.setMissingOriginalImg(rset.getString("missing_original_img"));
				b.setMissingRenamedImg(rset.getString("missing_renamed_img"));
				b.setMissingEnrollDate(rset.getDate("missing_enrolldate"));
				b.setMissingCount(rset.getInt("missing_count"));
				//b.setMissingLike(rset.getInt("missing_like"));
				b.setMissingReportCount(rset.getInt("missing_112_count"));
				b.setMissingReportReason(rset.getString("missing_112_reason"));
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
	
	public int insertBoard(Connection conn, MissingBoard b) {
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println("DAO:  "+b);
		String query = prop.getProperty("insertBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, b.getMissingTitle());
			pstmt.setString(2, b.getMissingWriter());
			pstmt.setString(3, b.getMissingHpPlace());
			pstmt.setString(4, b.getMissingHpDate());
			pstmt.setInt(5, b.getMissingMoney());
			pstmt.setString(6, b.getMissingPhone());
			pstmt.setString(7, b.getMissingKind());
			pstmt.setString(8, b.getMissingContent());
			pstmt.setString(9, b.getMissingOriginalImg());
			pstmt.setString(10, b.getMissingRenamedImg());
			
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
	
	public MissingBoard selectOne(Connection conn, int board_no) {
		MissingBoard board = null;
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
				board = new MissingBoard();
				//컬럼명은 대소문자 구분이 없다.
				board.setMissingNo(rset.getInt("missing_no"));
				board.setMissingTitle(rset.getString("missing_title"));
				board.setMissingWriter(rset.getString("missing_writer"));
				board.setMissingHpPlace(rset.getString("missing_hp_place"));
				board.setMissingHpDate(rset.getString("missing_hp_date"));
				board.setMissingMoney(rset.getInt("missing_money"));
				board.setMissingPhone(rset.getString("missing_phone"));
				board.setMissingKind(rset.getString("missing_kind"));
				board.setMissingContent(rset.getString("missing_content"));
				//sql바뀌면 바꿔야됌
				board.setMissingOriginalImg(rset.getString("missing_original_img"));
				board.setMissingRenamedImg(rset.getString("missing_renamed_img"));
				board.setMissingEnrollDate(rset.getDate("missing_enrolldate"));
				board.setMissingCount(rset.getInt("missing_count"));
				//board.setMissingLike(rset.getInt("missing_like"));
				board.setMissingReportCount(rset.getInt("missing_112_count"));
				board.setMissingReportReason(rset.getString("missing_112_reason"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return board;
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
		System.out.println(boardNo);
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
	
	public int updateBoard(Connection conn, MissingBoard b) {
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println("@DAOMissingBOARD"+b);
		String query = prop.getProperty("updateBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, b.getMissingTitle());
			pstmt.setString(2, b.getMissingHpPlace());
			pstmt.setString(3, b.getMissingHpDate());
			pstmt.setInt(4, b.getMissingMoney());
			pstmt.setString(5, b.getMissingPhone());
			pstmt.setString(6, b.getMissingKind());
			pstmt.setString(7, b.getMissingContent());
			pstmt.setString(8, b.getMissingOriginalImg());
			pstmt.setString(9, b.getMissingRenamedImg());
			pstmt.setInt(10, b.getMissingNo());
			
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

	public int insertBoardComment(Connection conn, BoardComment boardComment) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoardComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardComment.getBoardCommentLevel());
			pstmt.setString(2, boardComment.getBoardCommentWriter());
			pstmt.setString(3, boardComment.getBoardCommentContent());
			pstmt.setInt(4, boardComment.getBoardRef());
			//java - int: null을 허용하지 않음. 0
			//oracle - number: null 
			pstmt.setString(5, boardComment.getBoardCommentRef()==0
								?null
								:boardComment.getBoardCommentRef()+"");
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public List<BoardComment> selectCommentList(Connection conn, int board_no) {
		List<BoardComment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectCommentList");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			
			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setInt(1, board_no);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				BoardComment bc = new BoardComment();
				//컬럼명은 대소문자 구분이 없다.
				bc.setBoardCommentNo(rset.getInt("m_comment_no"));
				bc.setBoardCommentLevel(rset.getInt("m_comment_level"));
				bc.setBoardCommentWriter(rset.getString("m_comment_writer"));
				bc.setBoardCommentContent(rset.getString("m_comment_content"));
				bc.setBoardRef(rset.getInt("m_comment_ref"));
				bc.setBoardCommentRef(rset.getInt("m_comment_ref_no"));//null인 참조댓글필드는 0값이 대입됨.
				bc.setBoardCommentDate(rset.getDate("m_comment_enrolldate"));
				list.add(bc);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	
	public int deleteBoardComment(Connection conn, int boardCommentNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteBoardComment"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, boardCommentNo);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateReport(Connection conn, MissingBoard b) {
		int result = 0;
		PreparedStatement pstmt = null;
		System.out.println("@DAOMissingBOARD"+b);
		String query = prop.getProperty("updateReport"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, b.getMissingReportReason());
			pstmt.setInt(2, b.getMissingNo());
			
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

	public int selectTotalContents(Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

}











