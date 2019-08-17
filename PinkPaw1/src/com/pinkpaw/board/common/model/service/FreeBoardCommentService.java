package com.pinkpaw.board.common.model.service;

import static com.pinkpaw.common.JDBCTemplate.close;
import static com.pinkpaw.common.JDBCTemplate.commit;
import static com.pinkpaw.common.JDBCTemplate.getConnection;
import static com.pinkpaw.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.pinkpaw.board.common.model.dao.FreeBoardCommentDAO;
import com.pinkpaw.board.common.model.vo.BoardComment;

public class FreeBoardCommentService {

	// 댓글 등록 부분
	public int insertFreeBoardComment(BoardComment boardComment) {
		Connection conn = getConnection();

		int result = new FreeBoardCommentDAO().insertFreeBoardComment(conn, boardComment);

		if(result > 0)
			commit(conn);
		else 
			rollback(conn);

		close(conn);

		return result;
	}

	// 댓글 읽어오는 부분
	public List<BoardComment> selectFreeCommentList(int freeNo) {
		Connection conn = getConnection();

		List<BoardComment> list= new FreeBoardCommentDAO().selectFreeCommentList(conn, freeNo);

		close(conn);

		return list;
	}

	// 댓글 삭제 부분
	public int deleteFreeBoardComment(int freeBoardCommentNo) {
		Connection conn = getConnection();

		int result = new FreeBoardCommentDAO().deleteFreeBoardComment(conn, freeBoardCommentNo);

		if(result > 0)
			commit(conn);
		else 
			rollback(conn);

		close(conn);

		return result;
	}

}
