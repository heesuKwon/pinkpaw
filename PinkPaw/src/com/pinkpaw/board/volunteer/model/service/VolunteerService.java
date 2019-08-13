package com.pinkpaw.board.volunteer.model.service;

import static com.pinkpaw.common.JDBCTemplate.close;
import static com.pinkpaw.common.JDBCTemplate.commit;
import static com.pinkpaw.common.JDBCTemplate.getConnection;
import static com.pinkpaw.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.pinkpaw.board.volunteer.model.dao.VolunteerDAO;
import com.pinkpaw.board.volunteer.model.vo.VolunteerBoard;

public class VolunteerService {

	public List<VolunteerBoard> selectVolunteerBoardList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<VolunteerBoard> list = new VolunteerDAO().selectVolunteerBoardList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContents() {
		Connection conn = getConnection();
		int totalContents = new VolunteerDAO().selectTotalContents(conn);
		close(conn);
		return totalContents;
	}

	public VolunteerBoard selectOne(int boardNo) {
		Connection conn = getConnection();
		VolunteerBoard board = new VolunteerDAO().selectOne(conn, boardNo);
		close(conn);
		return board;		
	}

	public int insertVolunteer(VolunteerBoard rb) {
		Connection conn = getConnection();
		int result = new VolunteerDAO().insertVolunteer(conn, rb);
		
		if(result>0){
			//마지막에 추가한 시퀀스번호 가져오기
			result = new VolunteerDAO().selectLastSeq(conn);
			commit(conn);
		}
		else 
			rollback(conn);
		
		close(conn);
		return result;
	}

	public int deleteVolunteer(int VolunteerNo) {
		Connection conn = getConnection();
		
		int result = new VolunteerDAO().deleteVolunteer(conn, VolunteerNo);
		
		//트랜잭션 처리
		if(result>0) commit(conn);
		else rollback(conn);

		close(conn);

		return result;
	}

	public int updateVolunteer(VolunteerBoard rb) {
		Connection conn = getConnection();

		int result = new VolunteerDAO().updateVolunteer(conn, rb);

		//트랜잭션 처리
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}


	public List<VolunteerBoard> selectVolunteerBoardListAllAll(int cPage, int numPerPage, String keyword) {
		Connection conn = getConnection();
		List<VolunteerBoard> list = new VolunteerDAO().selectVolunteerBoardListAllAll(conn, cPage, numPerPage, keyword);
		close(conn);
		return list;
	}

	public List<VolunteerBoard> selectVolunteerBoardListAllnotAll(int cPage, int numPerPage, String key, String keyword) {
		Connection conn = getConnection();
		List<VolunteerBoard> list = new VolunteerDAO().selectVolunteerBoardListAllnotAll(conn, cPage, numPerPage, key, keyword);
		close(conn);
		return list;
	}

	public List<VolunteerBoard> selectVolunteerBoardListnotAllAll(int cPage, int numPerPage, String kind, String keyword) {
		Connection conn = getConnection();
		List<VolunteerBoard> list = new VolunteerDAO().selectVolunteerBoardListnotAllAll(conn, cPage, numPerPage, kind, keyword);
		close(conn);
		return list;
	}

	public List<VolunteerBoard> selectVolunteerBoardListnotAllnotAll(int cPage, int numPerPage, String kind, String key,
			String keyword) {
		Connection conn = getConnection();
		List<VolunteerBoard> list = new VolunteerDAO().selectVolunteerBoardListnotAllnotAll(conn, cPage, numPerPage, kind, key, keyword);
		close(conn);
		return list;
	}

}
