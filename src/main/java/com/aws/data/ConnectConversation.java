package com.aws.data;

public class ConnectConversation {
	
	private String id;
	private String intentName;
	private String inputTranscript;
	private String date;
	
	
	public ConnectConversation(String id, String intentName, String inputTranscript, String date) {
		super();
		this.id = id;
		this.intentName = intentName;
		this.inputTranscript = inputTranscript;
		this.date = date;
	}
	
	public static ConnectConversation of(String id, String intentName, String inputTranscript, String date) {
        return new ConnectConversation(id, intentName, inputTranscript, date);
    }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIntentName() {
		return intentName;
	}
	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}
	public String getInputTranscript() {
		return inputTranscript;
	}
	public void setInputTranscript(String inputTranscript) {
		this.inputTranscript = inputTranscript;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
