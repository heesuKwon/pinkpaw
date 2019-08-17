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

public class FreeBoardCommentDAO {
	private Properties prop;

	public FreeBoardCommentDAO() {

		prop = new Properties();
		
		String fileName = FreeBoardCommentDAO.class.getResource("/sql/board/freeboard-comment-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 댓글 등록 부분
	public int insertFreeBoardComment(Connection conn, BoardComment boardComment) {

		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertFreeBoardComment");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardComment.getBoardCommentLevel());
			pstmt.setString(2, boardComment.getBoardCommentWriter());
			pstmt.setString(3, boardComment.getBoardCommentContent());
			pstmt.setInt(4, boardComment.getBoardRef());
			pstmt.setString(5, boardComment.getBoardCommentRef()==0?null:boardComment.getBoardCommentRef()+"");

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 댓글 읽어오는 부분
	public List<BoardComment> selectFreeCommentList(Connection conn, int freeNo) {

		List<BoardComment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectFreeCommentList");

		try{
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, freeNo);

			rset = pstmt.executeQuery();

			while(rset.next()){

				BoardComment bc = new BoardComment();

				bc.setBoardCommentNo(rset.getInt("f_comment_no"));
				bc.setBoardCommentLevel(rset.getInt("f_comment_level"));
				bc.setBoardCommentWriter(rset.getString("f_comment_writer"));
				bc.setBoardCommentContent(rset.getString("f_comment_content"));
				bc.setBoardRef(rset.getInt("f_comment_ref"));
				bc.setBoardCommentRef(rset.getInt("f_comment_ref_no"));//null인 참조댓글필드는 0값이 대입됨.
				bc.setBoardCommentDate(rset.getDate("f_comment_enrolldate"));

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

	// 댓글 삭제 부분
	public int deleteFreeBoardComment(Connection conn, int freeBoardCommentNo) {

		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteFreeBoardComment"); 

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, freeBoardCommentNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
