package com.aws.data;

public class ResponseParam {
	
	DialogAction dialogAction ;
	SessionAttributes sessionAttributes;

	public DialogAction getDialogAction() {
		return dialogAction;
	}

	public void setDialogAction(DialogAction dialogAction) {
		this.dialogAction = dialogAction;
	}
	

	public SessionAttributes getSessionAttributes() {
		return sessionAttributes;
	}

	public void setSessionAttributes(SessionAttributes sessionAttributes) {
		this.sessionAttributes = sessionAttributes;
	}

	@Override
	public String toString() {
		return "ResponseParam [dialogAction=" + dialogAction + ", sessionAttributes=" + sessionAttributes + "]";
	}

	
}
