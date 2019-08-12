package com.pinkpaw.board.parceloutboard.model.dao;

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

import static com.pinkpaw.common.JDBCTemplate.*;

import com.pinkpaw.board.common.model.vo.BoardComment;
import com.pinkpaw.board.parceloutboard.model.vo.ParceloutBoard;

public class ParceloutDAO {

	private Properties prop = new Properties();
		
	public ParceloutDAO() {
		try {
			String fileName 
			= ParceloutDAO.class.getResource("/sql/board/parcelout/parcelout-query.properties").getPath();
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<ParceloutBoard> selectBoardList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardList");
		List<ParceloutBoard> list = new ArrayList<>();
		
		try {
			pstmt= conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ParceloutBoard p = new ParceloutBoard();
				p.setParceloutNo(rset.getInt("parcelout_no"));
				p.setParceloutTitle(rset.getString("parcelout_title"));
				p.setParceloutWriter(rset.getString("parcelout_writer"));
				p.setParceloutPlace(rset.getString("parcelout_place"));
				p.setParceloutMoney(rset.getInt("parcelout_money"));
				p.setParceloutKind(rset.getString("parcelout_kind"));
				p.setParceloutGender(rset.getString("parcelout_gender"));
				p.setParceloutContent(rset.getString("parcelout_content"));
				p.setParceloutOriginalImg(rset.getString("parcelout_original_Img"));
				p.setParceloutRenamedImg(rset.getString("parcelout_renamed_Img"));
				p.setParceloutEnrolldate(rset.getDate("parcelout_enrolldate"));
				p.setParceloutCount(rset.getInt("parcelout_count"));
				p.setParceloutReportCount(rset.getInt("parcelout_112_count"));
				p.setParceloutReportReason(rset.getString("parcelout_112_reason"));
				list.add(p);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("list@dao="+list);
		return list;
	}
	public int insertParcelout(Connection conn, ParceloutBoard p) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertParcelout");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getParceloutTitle());
			pstmt.setString(2, p.getParceloutWriter());
			pstmt.setString(3, p.getParceloutPlace());
			pstmt.setInt(4, p.getParceloutMoney());
			pstmt.setString(5, p.getParceloutKind());
			pstmt.setString(6, p.getParceloutGender());
			pstmt.setString(7, p.getParceloutContent());
			pstmt.setString(8, p.getParceloutOriginalImg());
			pstmt.setString(9, p.getParceloutRenamedImg());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		System.out.println("result@dao="+result);
		return result;
	}
	public int selectLastSeq(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int parceloutNo = 0;
		String sql = prop.getProperty("selectLastSeq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				parceloutNo = rset.getInt("currval");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return parceloutNo;
	}
	public ParceloutBoard selectOne(Connection conn, int parceloutNo) {
		ParceloutBoard p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, parceloutNo);
			
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				p = new ParceloutBoard();
				p.setParceloutNo(rset.getInt("parcelout_no"));
				p.setParceloutTitle(rset.getString("parcelout_title"));
				p.setParceloutWriter(rset.getString("parcelout_writer"));
				p.setParceloutPlace(rset.getString("parcelout_place"));
				p.setParceloutMoney(rset.getInt("parcelout_money"));
				p.setParceloutKind(rset.getString("parcelout_kind"));
				p.setParceloutGender(rset.getString("parcelout_gender"));
				p.setParceloutContent(rset.getString("parcelout_content"));
				p.setParceloutOriginalImg(rset.getString("parcelout_original_Img"));
				p.setParceloutRenamedImg(rset.getString("parcelout_renamed_Img"));
				p.setParceloutEnrolldate(rset.getDate("parcelout_enrolldate"));
				p.setParceloutCount(rset.getInt("parcelout_count"));
				p.setParceloutReportCount(rset.getInt("parcelout_112_count"));
				p.setParceloutReportReason("parcelout_112_reason");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("p@dao="+p);
		return p;
	}
	public int updateParcelout(Connection conn, ParceloutBoard p) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateParcelout");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getParceloutTitle());
			pstmt.setString(2, p.getParceloutPlace());
			pstmt.setInt(3, p.getParceloutMoney());
			pstmt.setString(4, p.getParceloutKind());
			pstmt.setString(5, p.getParceloutGender());
			pstmt.setString(6, p.getParceloutContent());
			pstmt.setString(7, p.getParceloutOriginalImg());
			pstmt.setString(8, p.getParceloutRenamedImg());
			pstmt.setInt(9, p.getParceloutNo());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("result@dao="+result);
		return result;
	}
	public int deleteParcelout(Connection conn, int parceloutNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteParcelout"); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, parceloutNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	public int insertParceloutComment(Connection conn, BoardComment boardComment) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("insertBoardComment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardComment.getBoardCommentLevel());
			pstmt.setString(2,boardComment.getBoardCommentWriter());
			pstmt.setString(3, boardComment.getBoardCommentContent());
			pstmt.setInt(4, boardComment.getBoardRef());
			pstmt.setString(5, boardComment.getBoardCommentRef()==0
								?null:boardComment.getBoardCommentRef()+"");
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("result@dao="+result);
		return result;
	}
	public List<BoardComment> selectCommentList(Connection conn, int parceloutNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCommentList");
		List<BoardComment> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, parceloutNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BoardComment bc = new BoardComment();
				bc.setBoardCommentNo(rset.getInt("p_comment_no"));
				bc.setBoardCommentLevel(rset.getInt("p_comment_level"));
				bc.setBoardCommentWriter(rset.getString("p_comment_writer"));
				bc.setBoardCommentContent(rset.getString("p_comment_content"));
				bc.setBoardRef(rset.getInt("p_comment_ref_no"));
				bc.setBoardCommentRef(rset.getInt("p_comment_ref"));
				bc.setBoardCommentDate(rset.getDate("p_comment_enrolldate"));
				
				list.add(bc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("commentlist@dao="+list);
		return list;
	}
	public int deleteParceloutComment(Connection conn, int boardCommentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteParceloutComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardCommentNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("result@dao="+result);
		return result;
	}
	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalMember = 0;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				totalMember = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return totalMember;
	}
	public List<ParceloutBoard> selectBoardList(Connection conn, int cPage, int numPerPage) {
		List<ParceloutBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectBoardList");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			
			//시작 rownum과 마지막 rownum 구하는 공식
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				ParceloutBoard p = new ParceloutBoard();
				p.setParceloutNo(rset.getInt("parcelout_no"));
				p.setParceloutTitle(rset.getString("parcelout_title"));
				p.setParceloutWriter(rset.getString("parcelout_writer"));
				p.setParceloutPlace(rset.getString("parcelout_place"));
				p.setParceloutMoney(rset.getInt("parcelout_money"));
				p.setParceloutKind(rset.getString("parcelout_kind"));
				p.setParceloutGender(rset.getString("parcelout_gender"));
				p.setParceloutContent(rset.getString("parcelout_content"));
				p.setParceloutOriginalImg(rset.getString("parcelout_original_Img"));
				p.setParceloutRenamedImg(rset.getString("parcelout_renamed_Img"));
				p.setParceloutEnrolldate(rset.getDate("parcelout_enrolldate"));
				p.setParceloutCount(rset.getInt("parcelout_count"));
				p.setParceloutReportCount(rset.getInt("parcelout_112_count"));
				p.setParceloutReportReason("parcelout_112_reason");
			
				list.add(p);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public int updateReport(Connection conn, ParceloutBoard p) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReport");
		int result = 0;
		
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, p.getParceloutReportReason());
				pstmt.setInt(2, p.getParceloutNo());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
		System.out.println("result@dao="+result);
		return result;
	}

}
