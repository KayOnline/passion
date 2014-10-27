package org.kay.framework.dataset.exception;

public class DataSetException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataSetException() {
		super();
	}

	public DataSetException(String detaiMessage) {
		super(detaiMessage);
	}

	public DataSetException(String detaiMessage, Throwable cause) {
		super(detaiMessage, cause);
	}

	public DataSetException(Throwable cause) {
		super(cause);
	}

}
