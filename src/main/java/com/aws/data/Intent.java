package com.aws.data;

public class Intent {
	
	private String intentName;
	private String inputTranscript;
	private String identifier;
	
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
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
