package com.aws.data;

public class Request {
	private CurrentIntent currentIntent ;
	private String inputTranscript ;
	private SessionAttributes sessionAttributes;
	
	public CurrentIntent getCurrentIntent() {
		return currentIntent;
	}

	public void setCurrentIntent(CurrentIntent currentIntent) {
		this.currentIntent = currentIntent;
	}

	
	public String getInputTranscript() {
		return inputTranscript;
	}

	public void setInputTranscript(String inputTranscript) {
		this.inputTranscript = inputTranscript;
	}

	public SessionAttributes getSessionAttributes() {
		return sessionAttributes;
	}

	public void setSessionAttributes(SessionAttributes sessionAttributes) {
		this.sessionAttributes = sessionAttributes;
	}

	@Override
	public String toString() {
		return "NewRequest [currentIntent=" + currentIntent + ", inputTranscript=" + inputTranscript + "]";
	}
	
	
}
