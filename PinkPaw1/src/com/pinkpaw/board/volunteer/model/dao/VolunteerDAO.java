package com.pinkpaw.board.volunteer.model.dao;

import static com.pinkpaw.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.pinkpaw.board.volunteer.model.vo.VolunteerBoard;


public class VolunteerDAO {
	private Properties prop;
	
	public VolunteerDAO() {
		prop = new Properties();
		
		String fileName = VolunteerDAO.class
									.getResource("/sql/board/volunteer-query.properties")
									.getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	public List<VolunteerBoard> selectVolunteerBoardList(Connection conn, int cPage, int numPerPage) {
		List<VolunteerBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectVolunteerBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int start = (cPage-1)*numPerPage+1;
			int end = cPage*numPerPage;
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				VolunteerBoard rb = new VolunteerBoard();
				rb.setVolunteerNo(rset.getInt("s_volunteer_no"));
				rb.setVolunteerTitle(rset.getString("s_volunteer_title"));
				rb.setVolunteerWriter(rset.getString("s_volunteer_writer"));
				rb.setVolunteerContent(rset.getString("s_volunteer_content"));
				rb.setVolunteerOriginalImg(rset.getString("s_volunteer_original_img"));
				rb.setVolunteerRenamedImg(rset.getString("s_volunteer_renamed_img"));
				rb.setVolunteerEnrolldate(rset.getDate("s_volunteer_enrolldate"));
				rb.setVolunteerCount(rset.getInt("s_volunteer_count"));
				
				list.add(rb);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
		
	}


	public int selectTotalContents(Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalContents");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContents = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
			
		return totalContents;
	}


	public VolunteerBoard selectOne(Connection conn, int boardNo) {
		VolunteerBoard rb = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				rb = new VolunteerBoard();
				rb.setVolunteerNo(rset.getInt("s_volunteer_no"));
				rb.setVolunteerTitle(rset.getString("s_volunteer_title"));
				rb.setVolunteerWriter(rset.getString("s_volunteer_writer"));
				rb.setVolunteerContent(rset.getString("s_volunteer_content"));
				rb.setVolunteerOriginalImg(rset.getString("s_volunteer_original_img"));
				rb.setVolunteerRenamedImg(rset.getString("s_volunteer_renamed_img"));
				rb.setVolunteerEnrolldate(rset.getDate("s_volunteer_enrolldate"));
				rb.setVolunteerCount(rset.getInt("s_volunteer_count"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return rb;
	}


	public int insertVolunteer(Connection conn, VolunteerBoard rb) {
		int result=0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertVolunteer");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, rb.getVolunteerTitle());
			pstmt.setString(2, rb.getVolunteerWriter());
			pstmt.setString(3, rb.getVolunteerContent());
			pstmt.setString(4, rb.getVolunteerOriginalImg());
			pstmt.setString(5, rb.getVolunteerRenamedImg());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}


	public int selectLastSeq(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int VolunteerNo = 0;
		String sql = prop.getProperty("selectLastSeq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				VolunteerNo = rset.getInt("currval");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}		
		return VolunteerNo;
	}


	public int deleteVolunteer(Connection conn, int VolunteerNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteVolunteer");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, VolunteerNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int updateVolunteer(Connection conn, VolunteerBoard rb) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateVolunteer");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, rb.getVolunteerTitle());
			pstmt.setString(2, rb.getVolunteerContent());
			pstmt.setString(3, rb.getVolunteerOriginalImg());
			pstmt.setString(4, rb.getVolunteerRenamedImg());
			pstmt.setInt(5, rb.getVolunteerNo());
			
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}
	

	public List<VolunteerBoard> selectVolunteerBoardListAllAll(Connection conn, int cPage, int numPerPage, String keyword) {
		List<VolunteerBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectVolunteerBoardListAllAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int start = (cPage-1)*numPerPage+1;
			int end = cPage*numPerPage;
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setString(3, "%"+keyword+"%");
			pstmt.setInt(4, start);
			pstmt.setInt(5, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				VolunteerBoard rb = new VolunteerBoard();
				rb.setVolunteerNo(rset.getInt("s_volunteer_no"));
				rb.setVolunteerTitle(rset.getString("s_volunteer_title"));
				rb.setVolunteerWriter(rset.getString("s_volunteer_writer"));
				rb.setVolunteerContent(rset.getString("s_volunteer_content"));
				rb.setVolunteerOriginalImg(rset.getString("s_volunteer_original_img"));
				rb.setVolunteerRenamedImg(rset.getString("s_volunteer_renamed_img"));
				rb.setVolunteerEnrolldate(rset.getDate("s_volunteer_enrolldate"));
				rb.setVolunteerCount(rset.getInt("s_volunteer_count"));
				
				list.add(rb);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}


	public List<VolunteerBoard> selectVolunteerBoardListAllnotAll(Connection conn, int cPage, int numPerPage, String key,
			String keyword) {
		List<VolunteerBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "";
		switch (key) {
		case "volunteer_title": sql = prop.getProperty("selectVolunteerBoardListAllnotAllTitle"); break;
		case "volunteer_writer": sql = prop.getProperty("selectVolunteerBoardListAllnotAllWriter"); break;
		case "volunteer_content": sql = prop.getProperty("selectVolunteerBoardListAllnotAllContent"); break;
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int start = (cPage-1)*numPerPage+1;
			int end = cPage*numPerPage;
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				VolunteerBoard rb = new VolunteerBoard();
				rb.setVolunteerNo(rset.getInt("s_volunteer_no"));
				rb.setVolunteerTitle(rset.getString("s_volunteer_title"));
				rb.setVolunteerWriter(rset.getString("s_volunteer_writer"));
				rb.setVolunteerContent(rset.getString("s_volunteer_content"));
				rb.setVolunteerOriginalImg(rset.getString("s_volunteer_original_img"));
				rb.setVolunteerRenamedImg(rset.getString("s_volunteer_renamed_img"));
				rb.setVolunteerEnrolldate(rset.getDate("s_volunteer_enrolldate"));
				rb.setVolunteerCount(rset.getInt("s_volunteer_count"));
				
				list.add(rb);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}

}
