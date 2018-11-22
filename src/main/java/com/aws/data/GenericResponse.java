package com.aws.data;

public class GenericResponse<T> {
	private String responseCode;
	private String message;
	private T response;

	public GenericResponse() {
		// constructor cretaed
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "CPGenericResponse [responseCode=" + responseCode + ", message=" + message + ", response=" + response
				+ "]";
	}

}
