package com.pinkpaw.board.missingboard.model.service;

import static com.pinkpaw.common.JDBCTemplate.*;


import java.sql.Connection;
import java.util.List;


import com.pinkpaw.board.missingboard.model.dao.MissingDAO;
import com.pinkpaw.board.missingboard.model.vo.MissingBoard;
import com.pinkpaw.board.common.model.vo.*;


public class MissingService {
	
	public int selectBoardCount() {
		Connection conn = getConnection();
		int totalBoardCount = new MissingDAO().selectBoardCount(conn);
		close(conn);
		return totalBoardCount;
	}

	public List<MissingBoard> selectBoardList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<MissingBoard> list= new MissingDAO().selectBoardList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public int insertBoard(MissingBoard b) {
		Connection conn = getConnection();
		System.out.println("Service: "+b);
		int result = new MissingDAO().insertBoard(conn, b);
		if(result>0){
			//마지막에 추가한 시퀀스번호 가져오기
			result = new MissingDAO().selectLastSeq(conn);
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public MissingBoard selectOne(int boardNo) {
		Connection conn = getConnection();
		MissingBoard board = new MissingDAO().selectOne(conn, boardNo);
		close(conn);
		return board;
	}

	public int increaseReadCount(int boardNo) {
		Connection conn = getConnection();
		int result = new MissingDAO().increaseReadCount(conn, boardNo);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		return result;
	}

	public MissingBoard selectOne(int boardNo, boolean hasRead) {
		Connection conn = getConnection();
		//1.조회수 증가
		int result = 0;
		if(!hasRead) {
			result = new MissingDAO().increaseReadCount(conn, boardNo);			
		}
		
		//2.게시글 조회
		MissingBoard board = new MissingDAO().selectOne(conn, boardNo);
		
		//트랜잭션 처리
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		return board;
	}
	
	
	public int deleteBoard(int board_no) {
		Connection conn = getConnection();
		int result = new MissingDAO().deleteBoard(conn, board_no);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
		return result;
	}
	
	public int updateBoard(MissingBoard b) {
		Connection conn = getConnection();
		int result = new MissingDAO().updateBoard(conn, b);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int insertBoardComment(BoardComment boardComment) {
		Connection conn = getConnection();
		int result = new MissingDAO().insertBoardComment(conn, boardComment);
		
		//트랜잭션 
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public List<BoardComment> selectCommentList(int board_no) {
		Connection conn = getConnection();
		List<BoardComment> list= new MissingDAO().selectCommentList(conn, board_no);
		close(conn);
		return list;
	}
	
	public int deleteBoardComment(int boardCommentNo) {
		Connection conn = getConnection();
		int result = new MissingDAO().deleteBoardComment(conn, boardCommentNo);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
		return result;
	}

	public int updateReport(MissingBoard b) {
		Connection conn = getConnection();
		
		int result = new MissingDAO().updateReport(conn, b);
		
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}


}
