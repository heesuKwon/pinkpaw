package com.pinkpaw.board.common.model.service;

import static com.pinkpaw.common.JDBCTemplate.close;
import static com.pinkpaw.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.pinkpaw.board.common.model.dao.ReviewCommentDAO;
import com.pinkpaw.board.common.model.vo.BoardComment;

public class ReviewCommentService {

	public List<BoardComment> selectBoardCommentList(int boardNo) {
		Connection conn = getConnection();
		List<BoardComment> list = new ReviewCommentDAO().selectBoardCommentList(conn, boardNo);
		close(conn);
		
		return list;
	}

}
