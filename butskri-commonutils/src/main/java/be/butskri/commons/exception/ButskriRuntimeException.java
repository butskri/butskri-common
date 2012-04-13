package be.butskri.commons.exception;

public class ButskriRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -3894132245538628969L;

	private String errorKey;
	private Object[] parameters;

	public ButskriRuntimeException(Exception cause, String errorKey,
			Object... parameters) {
		super(errorKey, cause);
		this.errorKey = errorKey;
		this.parameters = parameters;
	}

	public ButskriRuntimeException(String errorKey, Object... parameters) {
		super(errorKey);
		this.errorKey = errorKey;
		this.parameters = parameters;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public Object[] getParameters() {
		return parameters;
	}

}
