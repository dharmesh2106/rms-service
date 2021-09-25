package com.rms.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


public class ErrorMessage {
	private int status;
	private Integer code;
	private List<String> messages;

	/**
	 * Default Constructor
	 */
	public ErrorMessage() {
		this.messages = new ArrayList<String>();
	}

	/**
	 * Constructor to set all properties
	 *
	 * @param status   int representing an HTTP Status
	 * @param code     Integer representing an error code
	 * @param messages List of Strings representing the messages
	 */
	public ErrorMessage(final int status, final Integer code, final List<String> messages) {
		this.setStatus(status);
		this.setCode(code);
		this.setMessages(messages);
	}

	/**
	 * It allows a message to be added to the list of messages.
	 *
	 * @param message String representing a single message that needs to be added to the list
	 */
	public void addMessage(final String message) {
		if (this.messages == null) {
			this.messages = new ArrayList<String>();
		}
		this.messages.add(StringUtils.trimToNull(message));
	}

	/**
	 * Contains the same HTTP Status code returned by the server
	 *
	 * @return int representing an HTTP Status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Contains the same HTTP Status code returned by the server
	 *
	 * @param status int representing an HTTP Status
	 */
	public void setStatus(final int status) {
		this.status = status;
	}

	/**
	 * An application specific error code
	 *
	 * @return Integer representing an error code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * An application specific error code
	 *
	 * @param code Integer representing an error code
	 */
	public void setCode(final Integer code) {
		this.code = code;
	}

	/**
	 * List of Strings representing the messages needed to report the errors
	 *
	 * @return List of Strings representing the messages
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * List of Strings representing the messages needed to report the errors
	 *
	 * @param messages List of Strings representing the messages
	 */
	public void setMessages(final List<String> messages) {
		if (messages != null && !messages.isEmpty()) {
			messages.replaceAll(StringUtils::trimToNull);
			messages.removeIf(Objects::isNull);
		}
		this.messages = messages;
	}

	/**
	 * @see Object
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(15, 35).append(getStatus()).append(getCode()).append(getMessages()).toHashCode();
	}

	/**
	 * @see Object
	 */
	@Override
	public boolean equals(Object o) {
		boolean returnValue;

		if (this == o) {
			returnValue = true;
		} else if (o == null || getClass() != o.getClass()) {
			returnValue = false;
		} else {
			ErrorMessage errorMessage = (ErrorMessage) o;
			returnValue = new EqualsBuilder().append(getStatus(), errorMessage.getStatus()).append(getCode(),
					errorMessage.getCode()).append(getMessages(), errorMessage.getMessages()).isEquals();
		}
		return returnValue;
	}

	/**
	 * @see Object
	 */
	@Override
	public String toString() {
		return "ErrorMessage{status=" + this.status + ", code=" + this.code + ", messages=" + this.messages + "}";
	}
}
