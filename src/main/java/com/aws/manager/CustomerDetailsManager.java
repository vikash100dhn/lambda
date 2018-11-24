package com.aws.manager;

import com.aws.dao.CustomerDetailsDao;
import com.aws.dao.ICustomerDetailsDao;
import com.aws.data.CurrentIntent;
import com.aws.data.Intent;
import com.aws.data.IntentRequest;
import com.aws.data.Request;
import com.aws.data.SessionAttributes;
import com.aws.data.Slots;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;

public class CustomerDetailsManager implements ICustomerDetailsManager
{
	ICustomerDetailsDao customerDetailsDao = new CustomerDetailsDao();

	private String username;

	public CustomerDetailsManager() {}

	public String handleRequest(Request input)
	{
		String response = "";

		String intent = input.getCurrentIntent().getName();
		String previousIntent = input.getSessionAttributes().getPreviousIntent();
		String identifier = input.getSessionAttributes().getIdentifier();
		String speech = input.getInputTranscript();

		//saveIntentDetails(input);

		System.out.println("Intent received in Manager: " + intent + ", previousIntent::" + previousIntent + " Identifier::" + identifier);
		switch (intent)
		{
		case "UserName": 
			System.out.println("Inside case of usename");
			return handleGetCustomerIdIntent(input);

		case "FavoritePlayer": 

			response = "";
			return response;
		case "FavoriteTeam": 
			response = "";
		case "Score":
			response ="";
		}
		response = handleDefaultIntent(input);
		return response;
	}

	private String handleDefaultIntent(Request input) {
		
	return "";
	}

	private String handleGetCustomerIdIntent(Request input)
	{
		String username= "";
		//fetch user details\\
		System.out.println("checking username");
		String userName = customerDetailsDao.getCustomerName(input.getCurrentIntent().getSlots().getName());
		System.out.println("Username received is:"+userName);
		return userName;
		
		//return "Hello " + username;
	}
}
