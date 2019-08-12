package com.pinkpaw.board.freeboard.model.service;

import java.sql.Connection;
import java.util.List;

import com.pinkpaw.board.common.model.vo.BoardComment;
import com.pinkpaw.board.freeboard.model.dao.FreeBoardDAO;
import com.pinkpaw.board.freeboard.model.vo.FreeBoard;
import static com.pinkpaw.common.JDBCTemplate.*;

public class FreeBoardService {

	public int selectFreeBoardCount() {
		Connection conn = getConnection();

		int totalFreeBoardCount = new FreeBoardDAO().selectFreeBoardCount(conn);

		close(conn);

		return totalFreeBoardCount;
	}

	// 자유게시판 게시글 리스트 가져오는 부분
	public List<FreeBoard> selectFreeBoardList(int cPage, int numPerPage) {
		Connection conn = getConnection();

		List<FreeBoard> list = new FreeBoardDAO().selectFreeBoardList(conn, cPage, numPerPage);

		close(conn);

		return list;
	}

	// 게시글 조회수 관련 부분
	public FreeBoard selectOneFreeBoard(int freeNo, boolean hasRead) {
		
		Connection conn = getConnection();
		
		int result = 0;
		
		if(!hasRead) {
			result = new FreeBoardDAO().freeBoardIncreaseReadCount(conn, freeNo);			
		}
		
		// 게시글 조회
		FreeBoard board = new FreeBoardDAO().selectOneFreeBoard(conn, freeNo);
		
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		
		return board;
	}

	// 게시글 등록 부분
	public int insertFreeBoard(FreeBoard f) {
		Connection conn = getConnection();

		int result = new FreeBoardDAO().insertFreeBoard(conn, f);

		if(result > 0){
			//마지막에 추가한 시퀀스번호 가져오기
			result = new FreeBoardDAO().selectLastSeq(conn);
			commit(conn);
		}
		else 
			rollback(conn);

		close(conn);

		System.out.println("result@FreeBoardService : "+result);
		return result;

	}

	// 게시글 상세보기 부분, 신고하기 부분
	public FreeBoard selectOneFreeBoard(int freeNo) {
		Connection conn = getConnection();

		FreeBoard freeBoard = new FreeBoardDAO().selectOneFreeBoard(conn, freeNo);

		close(conn);

		return freeBoard;
	}

	// 게시글 수정 부분
	public int updateFreeBoard(FreeBoard f) {
		Connection conn = getConnection();

		int result = new FreeBoardDAO().updateFreeBoard(conn, f);

		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);

		close(conn);

		return result;
	}

	// 게시글 삭제 부분
	public int deleteFreeBoard(int freeNo) {
		Connection conn = getConnection();

		int result = new FreeBoardDAO().deleteFreeBoard(conn, freeNo);

		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);

		return result;
	}
	
	//------------------------자유게시판 검색 부분------------------------------------------------
	public List<FreeBoard> selectBoardByMemberId(String freeSearchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		
		List<FreeBoard> list = new FreeBoardDAO().selectBoardByMemberId(conn, freeSearchKeyword, cPage, numPerPage);
		
		close(conn);
		
		return list;
	}

	public List<FreeBoard> selectBoardByfreeTitle(String freeSearchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		
		List<FreeBoard> list = new FreeBoardDAO().selectBoardByfreeTitle(conn, freeSearchKeyword, cPage, numPerPage);
		
		close(conn);
		
		return list;
	}


	// 신고하기 부분
	public int updateFreeBoardReport(FreeBoard f) {
		Connection conn = getConnection();
		
		int result = new FreeBoardDAO().updateFreeBoardReport(conn, f);
		
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public List<FreeBoard> selectFreeBoardListAllAll(int cPage, int numPerPage, String keyword) {
		Connection conn = getConnection();
		List<FreeBoard> list = new FreeBoardDAO().selectFreeBoardListAllAll(conn, cPage, numPerPage, keyword);
		close(conn);
		return list;
	}

	public List<FreeBoard> selectFreeBoardListAllnotAll(int cPage, int numPerPage, String key, String keyword) {
		Connection conn = getConnection();
		List<FreeBoard> list = new FreeBoardDAO().selectFreeBoardListAllnotAll(conn, cPage, numPerPage, key, keyword);
		close(conn);
		return list;
	}

	

}
