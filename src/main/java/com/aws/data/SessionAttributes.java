package com.aws.data;

public class SessionAttributes {

	private String previousIntent ;
	private String dateFlag;
	private String identifier;

	public String getPreviousIntent() {
		return previousIntent;
	}

	public void setPreviousIntent(String previousIntent) {
		this.previousIntent = previousIntent;
	}
	
	public String getDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

}
