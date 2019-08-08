package com.pinkpaw.board.reviewboard.model.service;

import java.sql.Connection;
import java.util.List;

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

}
