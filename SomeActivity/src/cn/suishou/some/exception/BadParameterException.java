package cn.suishou.some.exception;

public class BadParameterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BadParameterException(String message) {
		super(message);
	}
	
	public BadParameterException(Throwable e) {
		super(e);
	}
	
	public BadParameterException(String message, Throwable cause) {
		super(message, cause);
	}
}
