package com.pinkpaw.board.freeboard.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.pinkpaw.board.common.model.vo.BoardComment;
import com.pinkpaw.board.freeboard.model.vo.FreeBoard;
import static com.pinkpaw.common.JDBCTemplate.*;

public class FreeBoardDAO {

	private Properties prop;

	public FreeBoardDAO() {

		prop = new Properties();
		String fileName = FreeBoardDAO.class.getResource("/sql/board/freeboard-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public int selectFreeBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalFreeContent = 0;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectFreeBoardCount");

		try{
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();

			while(rset.next()){
				totalFreeContent = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}

		return totalFreeContent;
	}

	// 자유게시판 게시글 리스트 가져오는 부분
	public List<FreeBoard> selectFreeBoardList(Connection conn, int cPage, int numPerPage) {
		
		List<FreeBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectFreeBoardList");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);

			rset = pstmt.executeQuery();

			while(rset.next()) {
				FreeBoard freeBoard = new FreeBoard();

				freeBoard.setFreeNo(rset.getInt("free_no"));
				freeBoard.setFreeTitle(rset.getString("free_title"));
				freeBoard.setFreeWriter(rset.getString("free_writer"));
				freeBoard.setFreeContent(rset.getString("free_content"));
				freeBoard.setFreeOriginalImg(rset.getString("free_original_img"));
				freeBoard.setFreeRenamedImg(rset.getString("free_renamed_img"));
				freeBoard.setFreeEnrolldate(rset.getDate("free_enrolldate"));
				freeBoard.setFreeCount(rset.getInt("free_count"));
				freeBoard.setFreeReportCount(rset.getInt("free_112_count"));
				freeBoard.setFreeReportReason(rset.getString("free_112_reason"));

				list.add(freeBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	// 게시글 조회수 관련 부분
	public int freeBoardIncreaseReadCount(Connection conn, int freeNo) {

		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("freeBoardIncreaseReadCount"); 

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, freeNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 게시글 등록 부분
	public int insertFreeBoard(Connection conn, FreeBoard f) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("insertFreeBoard"); 

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, f.getFreeTitle());
			// 나중에 로그인 유저가 생기면 바꾸기
			pstmt.setString(2, f.getFreeWriter());
			pstmt.setString(3, f.getFreeContent());
			pstmt.setString(4, f.getFreeOriginalImg());
			pstmt.setString(5, f.getFreeRenamedImg());
			//pstmt.setString(3, f.getfree);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("result@FreeBoardDAO : "+result);
		return result;
	}

	//마지막에 추가한 시퀀스번호 가져오기
	public int selectLastSeq(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int freeNo = 0;
		
		String sql = prop.getProperty("selectLastSeq");

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				freeNo = rset.getInt("currval");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("freeNo@FreeBoardDAO : "+freeNo);
		return freeNo;
	}

	// 게시글 상세보기 부분
	public FreeBoard selectOneFreeBoard(Connection conn, int freeNo) {
		
		FreeBoard freeBoard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectOneFreeBoard");

		try{
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, freeNo);

			rset = pstmt.executeQuery();

			if(rset.next()){
				freeBoard = new FreeBoard();

				freeBoard.setFreeNo(rset.getInt("free_no"));
				freeBoard.setFreeTitle(rset.getString("free_title"));
				freeBoard.setFreeWriter(rset.getString("free_writer"));
				freeBoard.setFreeContent(rset.getString("free_content"));
				freeBoard.setFreeOriginalImg(rset.getString("free_original_img"));
				freeBoard.setFreeRenamedImg(rset.getString("free_renamed_img"));
				freeBoard.setFreeEnrolldate(rset.getDate("free_enrolldate"));
				freeBoard.setFreeCount(rset.getInt("free_count"));
				freeBoard.setFreeReportCount(rset.getInt("free_112_count"));
				freeBoard.setFreeReportReason(rset.getString("free_112_reason"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return freeBoard;
	}

	// 게시글 수정 부분
	public int updateFreeBoard(Connection conn, FreeBoard f) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateFreeBoard"); 

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, f.getFreeTitle());
			pstmt.setString(2, f.getFreeContent());
			pstmt.setString(3, f.getFreeOriginalImg());
			pstmt.setString(4, f.getFreeRenamedImg());
			pstmt.setInt(5, f.getFreeNo());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 게시글 삭제 부분
	public int deleteFreeBoard(Connection conn, int freeNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteFreeBoard"); 

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, freeNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	

	//------------------------자유게시판 검색 부분------------------------------------------------
	public List<FreeBoard> selectBoardByMemberId(Connection conn, String freeSearchKeyword, int cPage, int numPerPage) {
		
		List<FreeBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBoardByMemberId");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+freeSearchKeyword+"%");

			rset = pstmt.executeQuery();

			while(rset.next()) {
				FreeBoard freeBoard = new FreeBoard();
				
				freeBoard.setFreeNo(rset.getInt("free_no"));
				freeBoard.setFreeTitle(rset.getString("free_title"));
				freeBoard.setFreeWriter(rset.getString("free_writer"));
				freeBoard.setFreeContent(rset.getString("free_content"));
				freeBoard.setFreeOriginalImg(rset.getString("free_original_img"));
				freeBoard.setFreeRenamedImg(rset.getString("free_renamed_img"));
				freeBoard.setFreeEnrolldate(rset.getDate("free_enrolldate"));
				freeBoard.setFreeCount(rset.getInt("free_count"));
				freeBoard.setFreeReportCount(rset.getInt("free_112_count"));
				freeBoard.setFreeReportReason(rset.getString("free_112_reason"));

				list.add(freeBoard);
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}


	public List<FreeBoard> selectBoardByfreeTitle(Connection conn, String freeSearchKeyword, int cPage, int numPerPage) {
		
		List<FreeBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBoardByfreeTitle");

		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+freeSearchKeyword+"%");

			rset = pstmt.executeQuery();

			while(rset.next()) {
				FreeBoard freeBoard = new FreeBoard();
				
				freeBoard.setFreeNo(rset.getInt("free_no"));
				freeBoard.setFreeTitle(rset.getString("free_title"));
				freeBoard.setFreeWriter(rset.getString("free_writer"));
				freeBoard.setFreeContent(rset.getString("free_content"));
				freeBoard.setFreeOriginalImg(rset.getString("free_original_img"));
				freeBoard.setFreeRenamedImg(rset.getString("free_renamed_img"));
				freeBoard.setFreeEnrolldate(rset.getDate("free_enrolldate"));
				freeBoard.setFreeCount(rset.getInt("free_count"));
				freeBoard.setFreeReportCount(rset.getInt("free_112_count"));
				freeBoard.setFreeReportReason(rset.getString("free_112_reason"));

				list.add(freeBoard);
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}


	// 신고하기 부분
	public FreeBoard selectOneFreeBoardReport(Connection conn, int freeReportNo) {
		
		FreeBoard freeBoard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 같은 내용이라서 기존 sql문 사용
		String query = prop.getProperty("selectOneFreeBoard");

		try{
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, freeReportNo);

			rset = pstmt.executeQuery();

			if(rset.next()){
				freeBoard = new FreeBoard();

				freeBoard.setFreeNo(rset.getInt("free_no"));
				freeBoard.setFreeTitle(rset.getString("free_title"));
				freeBoard.setFreeWriter(rset.getString("free_writer"));
				freeBoard.setFreeContent(rset.getString("free_content"));
				freeBoard.setFreeOriginalImg(rset.getString("free_original_img"));
				freeBoard.setFreeRenamedImg(rset.getString("free_renamed_img"));
				freeBoard.setFreeEnrolldate(rset.getDate("free_enrolldate"));
				freeBoard.setFreeCount(rset.getInt("free_count"));
				freeBoard.setFreeReportCount(rset.getInt("free_112_count"));
				freeBoard.setFreeReportReason(rset.getString("free_112_reason"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return freeBoard;
	}

	// 신고하기 부분
	public int updateFreeBoardReport(Connection conn, FreeBoard f) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("updateFreeBoardReport"); 
		
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, f.getFreeReportReason());
			pstmt.setInt(2, f.getFreeNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public List<FreeBoard> selectFreeBoardListAllAll(Connection conn, int cPage, int numPerPage, String keyword) {
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectFreeBoardListAllAll");
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
				FreeBoard freeBoard = new FreeBoard();
				
				freeBoard.setFreeNo(rset.getInt("free_no"));
				freeBoard.setFreeTitle(rset.getString("free_title"));
				freeBoard.setFreeWriter(rset.getString("free_writer"));
				freeBoard.setFreeContent(rset.getString("free_content"));
				freeBoard.setFreeOriginalImg(rset.getString("free_original_img"));
				freeBoard.setFreeRenamedImg(rset.getString("free_renamed_img"));
				freeBoard.setFreeEnrolldate(rset.getDate("free_enrolldate"));
				freeBoard.setFreeCount(rset.getInt("free_count"));
				freeBoard.setFreeReportCount(rset.getInt("free_112_count"));
				freeBoard.setFreeReportReason(rset.getString("free_112_reason"));

				list.add(freeBoard);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}


	public List<FreeBoard> selectFreeBoardListAllnotAll(Connection conn, int cPage, int numPerPage, String key, String keyword) {
		List<FreeBoard> list = new ArrayList<FreeBoard>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "";
		switch (key) {
		case "free_title": sql = prop.getProperty("selectFreeBoardListAllnotAllTitle"); break;
		case "free_writer": sql = prop.getProperty("selectFreeBoardListAllnotAllWriter"); break;
		case "free_content": sql = prop.getProperty("selectFreeBoardListAllnotAllContent"); break;
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
				FreeBoard freeBoard = new FreeBoard();
				
				freeBoard.setFreeNo(rset.getInt("free_no"));
				freeBoard.setFreeTitle(rset.getString("free_title"));
				freeBoard.setFreeWriter(rset.getString("free_writer"));
				freeBoard.setFreeContent(rset.getString("free_content"));
				freeBoard.setFreeOriginalImg(rset.getString("free_original_img"));
				freeBoard.setFreeRenamedImg(rset.getString("free_renamed_img"));
				freeBoard.setFreeEnrolldate(rset.getDate("free_enrolldate"));
				freeBoard.setFreeCount(rset.getInt("free_count"));
				freeBoard.setFreeReportCount(rset.getInt("free_112_count"));
				freeBoard.setFreeReportReason(rset.getString("free_112_reason"));

				list.add(freeBoard);
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
