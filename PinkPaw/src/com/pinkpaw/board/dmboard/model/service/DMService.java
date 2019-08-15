package com.pinkpaw.board.dmboard.model.service;

import static com.pinkpaw.common.JDBCTemplate.close;
import static com.pinkpaw.common.JDBCTemplate.commit;
import static com.pinkpaw.common.JDBCTemplate.getConnection;
import static com.pinkpaw.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.pinkpaw.board.dmboard.model.dao.DMDAO;
import com.pinkpaw.board.dmboard.model.vo.DM;
import com.pinkpaw.board.freeboard.model.dao.FreeBoardDAO;

public class DMService {
	
	public List<DM> selectReceiveList(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<DM> list= new DMDAO().selectReceiveList(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public int ReceiveTotalContents(String memberId) {
		Connection conn = getConnection();
		int totalBoardCount = new DMDAO().ReceiveTotalContents(conn, memberId);
		close(conn);
		return totalBoardCount;
	}

	public DM selectOne(int dmNo, int read) {
		Connection conn = getConnection();
		DM dm = new DM();
		if(read == 0) {
		 new DMDAO().increaseRead(conn, dmNo);
				commit(conn);

		}
		
		dm = new DMDAO().selectOne(conn, dmNo);
		close(conn);
		return dm;
	}

	public int deleteReceiver(int dmNo, String receiver) {
		Connection conn = getConnection();
		int result = 0;
		System.out.println("Service"+receiver+dmNo);
		
		result = new DMDAO().deleteReceiver(conn, dmNo, receiver);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);

		
		
		return result;
	}

	public int deleteSender(int dmNo) {
		Connection conn = getConnection();
		int result = 0;
		
		result = new DMDAO().deleteSender(conn, dmNo);
		if(result>0){
			commit(conn);
		}
		else 
			rollback(conn);

		
		
		return result;
	}

	public List<DM> selectSentList(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<DM> list= new DMDAO().selectSentList(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int SendTotalContents(String memberId) {
		Connection conn = getConnection();
		int totalBoardCount = new DMDAO().SendTotalContents(conn, memberId);
		close(conn);
		return totalBoardCount;
	}

	public int updateDmBoardReport(DM d) {
Connection conn = getConnection();
		
		int result = new DMDAO().updateDmBoardReport(conn, d);
		
		if(result > 0)
			commit(conn);
		else 
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/*
	 * public void increaseRead(int dmNo) { Connection conn = getConnection(); new
	 * DMDAO().increaseRead(conn, dmNo); close(conn);
	 * 
	 * }
	 */


}
