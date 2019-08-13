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

}
