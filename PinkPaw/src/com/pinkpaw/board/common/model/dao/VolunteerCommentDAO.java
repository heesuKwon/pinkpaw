package com.pinkpaw.board.common.model.dao;

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

public class VolunteerCommentDAO {
	private Properties prop;

	public VolunteerCommentDAO() {
		prop = new Properties();
		
		String fileName = ReviewCommentDAO.class
								.getResource("/sql/board/volunteer-comment-query.properties")
								.getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<BoardComment> selectBoardCommentList(Connection conn, int boardNo) {
		List<BoardComment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardCommentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BoardComment bc = new BoardComment();
				
				bc.setBoardCommentNo(rset.getInt("s_v_comment_no"));
				bc.setBoardCommentLevel(rset.getInt("s_v_comment_level"));
				bc.setBoardCommentWriter(rset.getString("s_v_comment_writer"));
				bc.setBoardCommentContent(rset.getString("s_v_comment_content"));
				bc.setBoardRef(rset.getInt("s_v_comment_ref"));
				bc.setBoardCommentRef(rset.getInt("s_v_comment_ref_no"));
				bc.setBoardCommentDate(rset.getDate("s_v_comment_enrolldate"));
				
				list.add(bc);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}

	public int deleteBoardComment(Connection conn, int boardCommentNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteBoardComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, boardCommentNo);
			
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
			//java와 oracle의 차이
			//java - int: null을 허용하지 않음.
			//oracle - number: null을 허용함.
			//하지만 oracle에 0으로 입력을 하면 fk값이기 때문에 참조값을 찾을 수 없어서 에러 발생.
			//String으로 적어도 자동으로 number타입으로 형변환되기 때문에 String으로 적어줌.
			pstmt.setString(5, boardComment.getBoardCommentRef()==0?null:boardComment.getBoardCommentRef()+"");
		
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
}
