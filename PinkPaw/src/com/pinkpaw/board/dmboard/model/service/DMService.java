package com.pinkpaw.board.dmboard.model.service;

import static com.pinkpaw.common.JDBCTemplate.close;
import static com.pinkpaw.common.JDBCTemplate.commit;
import static com.pinkpaw.common.JDBCTemplate.getConnection;
import static com.pinkpaw.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.pinkpaw.board.dmboard.model.dao.DMDAO;
import com.pinkpaw.board.dmboard.model.vo.DM;

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

	public DM selectOne(int dmNo) {
		Connection conn = getConnection();
		DM dm = new DM();
		dm = new DMDAO().selectOne(conn, dmNo);
		close(conn);
		return dm;
	}


}
