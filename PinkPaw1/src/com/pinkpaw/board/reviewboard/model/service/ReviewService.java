package com.pinkpaw.board.reviewboard.model.service;

import java.sql.Connection;
import java.util.List;

import com.pinkpaw.board.freeboard.model.dao.FreeBoardDAO;
import com.pinkpaw.board.reviewboard.model.dao.ReviewDAO;
import com.pinkpaw.board.reviewboard.model.vo.ReviewBoard;

import static com.pinkpaw.common.JDBCTemplate.*;

public class ReviewService {

	public List<ReviewBoard> selectReviewBoardList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<ReviewBoard> list = new ReviewDAO().selectReviewBoardList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContents() {
		Connection conn = getConnection();
		int totalContents = new ReviewDAO().selectTotalContents(conn);
		close(conn);
		return totalContents;
	}

	public ReviewBoard selectOne(int boardNo) {
		Connection conn = getConnection();
		ReviewBoard board = new ReviewDAO().selectOne(conn, boardNo);
		close(conn);
		return board;		
	}

	public int insertReview(ReviewBoard rb) {
		Connection conn = getConnection();
		int result = new ReviewDAO().insertReview(conn, rb);
		
		if(result>0){
			//마지막에 추가한 시퀀스번호 가져오기
			result = new ReviewDAO().selectLastSeq(conn);
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		return result;
	}

	public int deleteReview(int reviewNo) {
		Connection conn = getConnection();
		
		int result = new ReviewDAO().deleteReview(conn, reviewNo);
		
		//트랜잭션 처리
		if(result>0) commit(conn);
		else rollback(conn);

		close(conn);

		return result;
	}

	public int updateReview(ReviewBoard rb) {
		Connection conn = getConnection();

		int result = new ReviewDAO().updateReview(conn, rb);

		//트랜잭션 처리
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}
	
	public List<ReviewBoard> selectReviewBoardListByKind(int cPage, int numPerPage, String kind) {
		Connection conn = getConnection();
		List<ReviewBoard> list = new ReviewDAO().selectReviewBoardListByKind(conn, cPage, numPerPage, kind);
		close(conn);
		return list;
	}

	public List<ReviewBoard> selectReviewBoardListAllAll(int cPage, int numPerPage, String keyword) {
		Connection conn = getConnection();
		List<ReviewBoard> list = new ReviewDAO().selectReviewBoardListAllAll(conn, cPage, numPerPage, keyword);
		close(conn);
		return list;
	}

	public List<ReviewBoard> selectReviewBoardListAllnotAll(int cPage, int numPerPage, String key, String keyword) {
		Connection conn = getConnection();
		List<ReviewBoard> list = new ReviewDAO().selectReviewBoardListAllnotAll(conn, cPage, numPerPage, key, keyword);
		close(conn);
		return list;
	}

	public List<ReviewBoard> selectReviewBoardListnotAllAll(int cPage, int numPerPage, String kind, String keyword) {
		Connection conn = getConnection();
		List<ReviewBoard> list = new ReviewDAO().selectReviewBoardListnotAllAll(conn, cPage, numPerPage, kind, keyword);
		close(conn);
		return list;
	}

	public List<ReviewBoard> selectReviewBoardListnotAllnotAll(int cPage, int numPerPage, String kind, String key,
			String keyword) {
		Connection conn = getConnection();
		List<ReviewBoard> list = new ReviewDAO().selectReviewBoardListnotAllnotAll(conn, cPage, numPerPage, kind, key, keyword);
		close(conn);
		return list;
	}

	//신고하기 부분
	public int updateReviewBoardReport(ReviewBoard r) {
		Connection conn = getConnection();
		
		int result = new ReviewDAO().updateReviewBoardReport(conn, r);
		
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	

}
