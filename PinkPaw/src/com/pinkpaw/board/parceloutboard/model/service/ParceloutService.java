package com.pinkpaw.board.parceloutboard.model.service;

import java.sql.Connection;
import java.util.List;
import com.pinkpaw.board.common.model.vo.BoardComment;
import com.pinkpaw.board.parceloutboard.model.dao.ParceloutDAO;
import com.pinkpaw.board.parceloutboard.model.vo.ParceloutBoard;

import static com.pinkpaw.common.JDBCTemplate.*;

public class ParceloutService {

	public List<ParceloutBoard> selectBoardList() {
		Connection conn = getConnection();
		List<ParceloutBoard> list = new ParceloutDAO().selectBoardList(conn);		
		close(conn);
		System.out.println("list@service="+list);
		return list;
	}

	public int insertParcelout(ParceloutBoard p) {
		Connection conn = getConnection();
		int result = new ParceloutDAO().insertParcelout(conn, p);
		if(result > 0){
			result = new ParceloutDAO().selectLastSeq(conn);
			commit(conn);
		}else {
			rollback(conn);
		}
	
		close(conn);
		System.out.println("result@service="+result);
		return result;
	}

	public ParceloutBoard selectOne(int parceloutNo) {
		Connection conn = getConnection();
		ParceloutBoard p = new ParceloutDAO().selectOne(conn, parceloutNo);
		close(conn);
		System.out.println("p@service="+p);
		return p;
	}

	public int updateParcelout(ParceloutBoard p) {
		Connection conn = getConnection();
		int result = new ParceloutDAO().updateParcelout(conn, p);
		if(result > 0){
			commit(conn);
		}else {
			rollback(conn);
		}
	
		close(conn);
		System.out.println("result@service="+result);
		return result;
	}

	public int deleteParcelout(int parceloutNo) {
		Connection conn = getConnection();
		int result = new ParceloutDAO().deleteParcelout(conn, parceloutNo);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertParceloutComment(BoardComment boardComment) {
		Connection conn = getConnection();
		int result = new ParceloutDAO().insertParceloutComment(conn, boardComment);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		System.out.println("result@service="+result);
		return result;
	}

	public List<BoardComment> selectCommentList(int parceloutNo){
		Connection conn = getConnection();
		List<BoardComment> list = new ParceloutDAO().selectCommentList(conn, parceloutNo);		
		close(conn);
		System.out.println("commentlist@service="+list);
		return list;
	}

	public int deleteParceloutComment(int boardCommentNo) {
		Connection conn = getConnection();
		int result = new ParceloutDAO().deleteParceloutComment(conn, boardCommentNo);		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		System.out.println("result@service="+result);
		return result;
	}

	public int selectBoardCount() {
		Connection conn =  getConnection();
		int totalBoardCount = new ParceloutDAO().selectBoardCount(conn);
		close(conn);
		return totalBoardCount;
	}

	public List<ParceloutBoard> selectBoardList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<ParceloutBoard> list= new ParceloutDAO().selectBoardList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int updateReport(ParceloutBoard p) {
		Connection conn = getConnection();
		int result = new ParceloutDAO().updateReport(conn, p);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		System.out.println("result@service="+result);
		return result;
	}

	public ParceloutBoard selectOne(int parceloutNo, boolean hasRead) {
		Connection conn = getConnection();
		//1.조회수 증가
		int result = 0;
		if(!hasRead) {
			result = new ParceloutDAO().increaseReadCount(conn, parceloutNo);			
		}
		
		//2.게시글 조회
		ParceloutBoard board = new ParceloutDAO().selectOne(conn, parceloutNo);
		
		//트랜잭션 처리
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		return board;
	}
	
	
	
	
	
}
