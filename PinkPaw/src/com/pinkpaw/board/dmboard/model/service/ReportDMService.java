package com.pinkpaw.board.dmboard.model.service;

import static com.pinkpaw.common.JDBCTemplate.*;



import java.sql.Connection;
import java.util.List;

import com.pinkpaw.board.dmboard.model.dao.ReportDMDAO;
import com.pinkpaw.board.dmboard.model.vo.DM;
import com.pinkpaw.board.dmboard.model.vo.reportDM;
//import com.pinkpaw.board.model.dao.NoticeBoardDAO;
//import com.pinkpaw.board.model.vo.NoticeBoard;

public class ReportDMService {
	
	public int selectReportDMCount() {
		Connection conn = getConnection();
		int totalBoardCount = new ReportDMDAO().selectReportDMCount(conn);
		close(conn);
		return totalBoardCount;
	}
	// 신고메모 리스트
	public List<reportDM> selectReportDMList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<reportDM> list= new ReportDMDAO().selectReportDMList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	// 신고메모조회	
	public DM SelectOne(int DMNo) {
		Connection conn = getConnection();
		DM DM = new ReportDMDAO().selectOne(conn, DMNo);
		close(conn);
		return DM;
	}
	
	
	public int deleteReportDM(int DmNo) {
		Connection conn = getConnection();
		int result = new ReportDMDAO().deleteReportDM(conn, DmNo);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
		return result;
	}

}
