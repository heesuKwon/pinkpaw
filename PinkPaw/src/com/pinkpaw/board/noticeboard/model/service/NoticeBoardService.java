package com.pinkpaw.board.noticeboard.model.service;

import static com.pinkpaw.common.JDBCTemplate.*;



import java.sql.Connection;
import java.util.List;

import com.pinkpaw.board.noticeboard.model.dao.NoticeBoardDAO;
import com.pinkpaw.board.noticeboard.model.vo.NoticeBoard;

public class NoticeBoardService {
	
	public int selectBoardCount() {
		Connection conn = getConnection();
		int totalBoardCount = new NoticeBoardDAO().selectBoardCount(conn);
		close(conn);
		return totalBoardCount;
	}

	public List<NoticeBoard> selectBoardList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<NoticeBoard> list= new NoticeBoardDAO().selectBoardList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public int insertBoard(NoticeBoard b) {
		Connection conn = getConnection();
		int result = new NoticeBoardDAO().insertBoard(conn, b);
		if(result>0){
			//마지막에 추가한 시퀀스번호 가져오기
			result = new NoticeBoardDAO().selectLastSeq(conn);
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public NoticeBoard SelectOne(int boardNo) {
		Connection conn = getConnection();
		NoticeBoard board = new NoticeBoardDAO().selectOne(conn, boardNo);
		close(conn);
		return board;
	}

	public int IncreaseReadCount(int boardNo) {
		Connection conn = getConnection();
		int result = new NoticeBoardDAO().increaseReadCount(conn, boardNo);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		return result;
	}

	public NoticeBoard SelectOne(int boardNo, boolean hasRead) {
		Connection conn = getConnection();
		//1.조회수 증가
		int result = 0;
		if(!hasRead) {
			result = new NoticeBoardDAO().increaseReadCount(conn, boardNo);			
		}
		
		//2.게시글 조회
		NoticeBoard board = new NoticeBoardDAO().selectOne(conn, boardNo);
		
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
		int result = new NoticeBoardDAO().deleteBoard(conn, board_no);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
		return result;
	}
	
	public int updateBoard(NoticeBoard b) {
		Connection conn = getConnection();
		int result = new NoticeBoardDAO().updateBoard(conn, b);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
//	public int insertBoardComment(BoardComment boardComment) {
//		Connection conn = getConnection();
//		int result = new BoardDAO().insertBoardComment(conn, boardComment);
//		
//		//트랜잭션
//		if(result > 0) {
//			commit(conn);
//		}
//		else {
//			rollback(conn);
//		}
//		
//		close(conn);
//		
//		return result;
//	}
//
//	public List<BoardComment> boardComment(int boardNo) {
//		
//		Connection conn = getConnection();
//		List<BoardComment> list= new BoardDAO().boardComment(conn, boardNo);
//		close(conn);
////		System.out.println("서비스단 확인*****************************"+list);
//		return list;
//		
//		
//		
//	}
//	
//	public int deleteBoardComment(int boardCommentNo) {
//		Connection conn = getConnection();
//		int result = new BoardDAO().deleteBoardComment(conn, boardCommentNo);
//		if(result>0)
//			commit(conn);
//		else 
//			rollback(conn);
//		close(conn);
//		
//		return result;
//	}
	
	

}
