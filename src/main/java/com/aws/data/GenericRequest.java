package com.aws.data;

public class GenericRequest<T> {

	private T request;

	public T getRequest() {
		return request;
	}

	public void setRequest(T request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "GenericRequest [request=" + request + "]";
	}

}
