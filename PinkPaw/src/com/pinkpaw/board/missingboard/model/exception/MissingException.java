package com.pinkpaw.board.missingboard.model.exception;


public class MissingException extends RuntimeException {

	public MissingException() {
		super();
	}

	public MissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MissingException(String message, Throwable cause) {
		super(message, cause);
	}

	public MissingException(String message) {
		super(message);
	}

	public MissingException(Throwable cause) {
		super(cause);
	}

	
}
