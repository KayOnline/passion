package org.kay.framework.persistence.exception;

/**
 * The Class JPAException.
 * Note: RuntimeException(unchecked exception)
 *
 * @author Kay
 * @version
 */
public class JPAException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JPAException() {
		super();
	}

	public JPAException(String detaiMessage) {
		super(detaiMessage);
	}

	public JPAException(String detaiMessage, Throwable cause) {
		super(detaiMessage, cause);
	}

	public JPAException(Throwable cause) {
		super(cause);
	}

}
