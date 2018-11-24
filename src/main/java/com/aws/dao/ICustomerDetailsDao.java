package com.aws.dao;

public interface ICustomerDetailsDao {

	public int saveConnectConversation(String intentName, String inputTranscript);

	String getCustomerName(String name);

}
