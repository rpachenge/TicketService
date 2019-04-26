package com.java.ticketing.exceptions;

public class TicketServiceRuntimeException extends RuntimeException {

	private String code;
	private String description;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TicketServiceRuntimeException() {
		super();
	}

	public TicketServiceRuntimeException(String message, String code) {

		super(message);
		this.description = message;
		this.code = code;
	}

	public TicketServiceRuntimeException(String message, String code, Throwable cause) {
		super(message, cause);
		this.description = message;
		this.code = code;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TicketService [code=");
		builder.append(code);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
