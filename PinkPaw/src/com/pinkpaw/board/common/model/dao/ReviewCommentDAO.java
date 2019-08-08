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

public class ReviewCommentDAO {
	private Properties prop;

	public ReviewCommentDAO() {
		prop = new Properties();
		
		String fileName = ReviewCommentDAO.class
								.getResource("/sql/board/review-comment-query.properties")
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
				
				bc.setBoardCommentNo(rset.getInt("r_comment_no"));
				bc.setBoardCommentLevel(rset.getInt("r_comment_level"));
				bc.setBoardCommentWriter(rset.getString("r_comment_writer"));
				bc.setBoardCommentContent(rset.getString("r_comment_content"));
				bc.setBoardRef(rset.getInt("r_comment_ref"));
				bc.setBoardCommentRef(rset.getInt("r_comment_ref_no"));
				bc.setBoardCommentDate(rset.getDate("r_comment_enrolldate"));
				
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

}
