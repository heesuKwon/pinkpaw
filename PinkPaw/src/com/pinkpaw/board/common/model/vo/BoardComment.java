package com.pinkpaw.board.common.model.vo;

import java.sql.Date;

public class BoardComment {
	private int BoardCommentNo;
	private int BoardCommentLevel;
	private String BoardCommentWriter;
	private String BoardCommentContent;
	private int BoardRef;
	private int BoardCommentRef;
	private Date BoardCommentDate;
	
	public BoardComment() {
		super();
	}

	public BoardComment(int boardCommentNo, int boardCommentLevel, String boardCommentWriter,
			String boardCommentContent, int boardRef, int boardCommentRef, Date boardCommentDate) {
		super();
		BoardCommentNo = boardCommentNo;
		BoardCommentLevel = boardCommentLevel;
		BoardCommentWriter = boardCommentWriter;
		BoardCommentContent = boardCommentContent;
		BoardRef = boardRef;
		BoardCommentRef = boardCommentRef;
		BoardCommentDate = boardCommentDate;
	}

	public int getBoardCommentNo() {
		return BoardCommentNo;
	}

	public void setBoardCommentNo(int boardCommentNo) {
		BoardCommentNo = boardCommentNo;
	}

	public int getBoardCommentLevel() {
		return BoardCommentLevel;
	}

	public void setBoardCommentLevel(int boardCommentLevel) {
		BoardCommentLevel = boardCommentLevel;
	}

	public String getBoardCommentWriter() {
		return BoardCommentWriter;
	}

	public void setBoardCommentWriter(String boardCommentWriter) {
		BoardCommentWriter = boardCommentWriter;
	}

	public String getBoardCommentContent() {
		return BoardCommentContent;
	}

	public void setBoardCommentContent(String boardCommentContent) {
		BoardCommentContent = boardCommentContent;
	}

	public int getBoardRef() {
		return BoardRef;
	}

	public void setBoardRef(int boardRef) {
		BoardRef = boardRef;
	}

	public int getBoardCommentRef() {
		return BoardCommentRef;
	}

	public void setBoardCommentRef(int boardCommentRef) {
		BoardCommentRef = boardCommentRef;
	}

	public Date getBoardCommentDate() {
		return BoardCommentDate;
	}

	public void setBoardCommentDate(Date boardCommentDate) {
		BoardCommentDate = boardCommentDate;
	}

	@Override
	public String toString() {
		return "BoardComment [BoardCommentNo=" + BoardCommentNo + ", BoardCommentLevel=" + BoardCommentLevel
				+ ", BoardCommentWriter=" + BoardCommentWriter + ", BoardCommentContent=" + BoardCommentContent
				+ ", BoardRef=" + BoardRef + ", BoardCommentRef=" + BoardCommentRef + ", BoardCommentDate="
				+ BoardCommentDate + "]";
	}

	
	
}
