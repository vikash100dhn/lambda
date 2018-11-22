package com.aws.data;

public class DialogAction {
	
	String type ;
	String fulfillmentState ;
	Message message ;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFulfillmentState() {
		return fulfillmentState;
	}
	public void setFulfillmentState(String fulfillmentState) {
		this.fulfillmentState = fulfillmentState;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "DialogAction [type=" + type + ", fulfilled=" + fulfillmentState + ", message=" + message + "]";
	}
	
	
}
