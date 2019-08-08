package com.pinkpaw.board.reviewboard.model.dao;

import static com.pinkpaw.common.JDBCTemplate.*;

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

import com.pinkpaw.board.reviewboard.model.vo.ReviewBoard;

public class ReviewDAO {
	private Properties prop;
	
	public ReviewDAO() {
		prop = new Properties();
		
		String fileName = ReviewDAO.class
									.getResource("/sql/board/review-query.properties")
									.getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	public List<ReviewBoard> selectReviewBoardList(Connection conn, int cPage, int numPerPage) {
		List<ReviewBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectReviewBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int start = (cPage-1)*numPerPage+1;
			int end = cPage*numPerPage;
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ReviewBoard rb = new ReviewBoard();
				rb.setReviewNo(rset.getInt("review_no"));
				rb.setReviewTitle(rset.getString("review_title"));
				rb.setReviewWriter(rset.getString("review_writer"));
				rb.setReviewKind(rset.getString("review_kind"));
				rb.setReviewContent(rset.getString("review_content"));
				rb.setReviewOriginalImg(rset.getString("review_original_img"));
				rb.setReviewRenamedImg(rset.getString("review_renamed_img"));
				rb.setReviewEnrollDate(rset.getDate("review_enrolldate"));
				rb.setReviewCount(rset.getInt("review_count"));
				rb.setReviewLike(rset.getInt("review_like"));
				rb.setReviewReportCount(rset.getInt("review_112_count"));
				rb.setReviewReportReason(rset.getString("review_112_reason"));
				
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


	public ReviewBoard selectOne(Connection conn, int boardNo) {
		ReviewBoard rb = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				rb = new ReviewBoard();
				rb.setReviewNo(rset.getInt("review_no"));
				rb.setReviewTitle(rset.getString("review_title"));
				rb.setReviewWriter(rset.getString("review_writer"));
				rb.setReviewKind(rset.getString("review_kind"));
				rb.setReviewContent(rset.getString("review_content"));
				rb.setReviewOriginalImg(rset.getString("review_original_img"));
				rb.setReviewRenamedImg(rset.getString("review_renamed_img"));
				rb.setReviewEnrollDate(rset.getDate("review_enrolldate"));
				rb.setReviewCount(rset.getInt("review_count"));
				rb.setReviewLike(rset.getInt("review_like"));
				rb.setReviewReportCount(rset.getInt("review_112_count"));
				rb.setReviewReportReason(rset.getString("review_112_reason"));				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return rb;
	}


	public int insertReview(Connection conn, ReviewBoard rb) {
		int result=0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertReview");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, rb.getReviewTitle());
			pstmt.setString(2, rb.getReviewWriter());
			pstmt.setString(3, rb.getReviewKind());
			pstmt.setString(4, rb.getReviewContent());
			pstmt.setString(5, rb.getReviewOriginalImg());
			pstmt.setString(6, rb.getReviewRenamedImg());

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
		int reviewNo = 0;
		String sql = prop.getProperty("selectLastSeq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				reviewNo = rset.getInt("currval");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}		
		return reviewNo;
	}

}
