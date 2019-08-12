package com.pinkpaw.board.freeboard.model.exception;

public class FreeBoardException extends RuntimeException {

	public FreeBoardException() {
		super();
	}

	public FreeBoardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FreeBoardException(String message, Throwable cause) {
		super(message, cause);
	}

	public FreeBoardException(String message) {
		super(message);
	}

	public FreeBoardException(Throwable cause) {
		super(cause);
	}

	
}
