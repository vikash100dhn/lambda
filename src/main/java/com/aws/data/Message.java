package com.aws.data;

public class Message {
	String contentType ;
	String content ;
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Message [contentType=" + contentType + ", content=" + content + "]";
	}
	
	
}
