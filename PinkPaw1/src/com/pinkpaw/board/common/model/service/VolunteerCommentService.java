package com.pinkpaw.board.common.model.service;

import static com.pinkpaw.common.JDBCTemplate.close;
import static com.pinkpaw.common.JDBCTemplate.commit;
import static com.pinkpaw.common.JDBCTemplate.getConnection;
import static com.pinkpaw.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.pinkpaw.board.common.model.dao.VolunteerCommentDAO;
import com.pinkpaw.board.common.model.vo.BoardComment;

public class VolunteerCommentService {
	
	

	public List<BoardComment> selectBoardCommentList(int boardNo) {
		Connection conn = getConnection();
		List<BoardComment> list = new VolunteerCommentDAO().selectBoardCommentList(conn, boardNo);
		close(conn);
		
		return list;
	}

	public int deleteBoardComment(int boardCommentNo) {
		Connection conn = getConnection();
		int result = new VolunteerCommentDAO().deleteBoardComment(conn, boardCommentNo);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
		return result;
	}
	
	public int insertBoardComment(BoardComment boardComment) {
		Connection conn = getConnection();
		int result = new VolunteerCommentDAO().insertBoardComment(conn, boardComment);
		
		//트랜잭션 처리
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);//이 곳에서 자동으로 커밋되긴 함. 
		return result;
	}
	
}
