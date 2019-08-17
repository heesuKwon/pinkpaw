package com.pinkpaw.board.organicboard.model.service;

import java.sql.Connection;
import java.util.Map;

import com.pinkpaw.board.organicboard.model.dao.GraphDAO;

import static com.pinkpaw.common.JDBCTemplate.*;

public class GraphService {

	public Map<String, Integer> getAllOrganic() {
		Connection conn = getConnection();
		Map<String, Integer> organicMap = new GraphDAO().getAllOrganic(conn);
		close(conn);
		return organicMap;
	}

	public Map<String, Integer> getSelectOrganic(String name1, String name2) {
		Connection conn = getConnection();
		Map<String, Integer> organicMapSelect = new GraphDAO().getSelectOrganic(conn, name1, name2);
		close(conn);
		return organicMapSelect;
	}

}
