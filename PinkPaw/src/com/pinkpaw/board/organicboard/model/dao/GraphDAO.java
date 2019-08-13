package com.pinkpaw.board.organicboard.model.dao;

import static com.pinkpaw.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.pinkpaw.board.freeboard.model.dao.FreeBoardDAO;

public class GraphDAO {

	private Properties prop;

	public GraphDAO() {

		prop = new Properties();

		String fileName = FreeBoardDAO.class.getResource("/sql/board/organicgraph-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Integer> getAllOrganic(Connection conn) {
		Map<String, Integer> organicMap = new HashMap<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getAllOrganic");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				organicMap.put(rset.getString("processstate"),rset.getInt("cnt"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return organicMap;
	}

	public Map<String, Integer> getSelectOrganic(Connection conn,String name1, String name2) {
		Map<String, Integer> organicMapSelect = new HashMap<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getSelectOrganic");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name1);
			pstmt.setString(2, name2);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				organicMapSelect.put(rset.getString("processstate"),rset.getInt("cnt"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return organicMapSelect;
	}

}
