package com.aws.manager;

import com.aws.dao.CustomerDetailsDao;
import com.aws.dao.ICustomerDetailsDao;
import com.aws.data.CPResponse;
import com.aws.data.CurrentIntent;
import com.aws.data.Customer;
import com.aws.data.CustomerRequest;
import com.aws.data.Intent;
import com.aws.data.IntentRequest;
import com.aws.data.Request;
import com.aws.data.SessionAttributes;
import com.aws.data.Slots;
import com.aws.util.ServiceUtils;
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

		saveIntentDetails(input);

		System.out.println("Intent received in Manager: " + intent + ", previousIntent::" + previousIntent + " Identifier::" + identifier);
		List<String> options = new ArrayList();
		switch (intent)
		{

		case "getCustomerId": 
			options = Arrays.asList(new String[] { "getCustomerId", "numberInput", "Default", "Error" });
			
			return handleGetCustomerIdIntent(input);


		case "putonhold": 
			options = Arrays.asList(new String[] { "confirmIntent", "NegativeResponse", "putonhold", "Default", "Error" });

			return "My apologies";


		case "confirmIntent": 
			if (previousIntent.equals("getCustomerId"))
			{

				options = Arrays.asList(new String[] { "confirmIntent", "NegativeResponse", "putonhold", "Default", "Error" });
				
				//TODO: Return blank response
				response = "Thanks for the confirmation!";
			}
			if (previousIntent.equals("NegativeResponse"))
			{
				options = Arrays.asList(new String[] { "getCustomerId", "numberInput", "Default", "Error" });
				
				response = "We typically provide working capital in the form of cash advances. Our approval process is simple and we can provide you funds typically within 7 to 10 days. ";
			}

			if (previousIntent.equals("confirmIntent")) {
				options = Arrays.asList(new String[] { "confirmIntent", "Default", "Error" });
				
				response = "That's good";
			}

			/*if (previousIntent.equals("isInterested")) {
				options = Arrays.asList(new String[] { "confirmIntent", "NegativeResponse", "Default", "Error" });
				

				response = "";
			}*/

			if (previousIntent.equals("putonhold"))
			{
				options = Arrays.asList(new String[] { "negativeResponse", "confirmIntent", "Default", "Error" });
				

				response = "We typically provide working capital in the form of cash advances. Our approval process is simple and we can provide you funds typically within 7 to 10 days. ";
			}
			//this is erroneous
			if (previousIntent.equals("batchAndSettle"))
			{
				options = Arrays.asList(new String[] { "confirmIntent", "busy", "Default", "Error" });
				

				response = "Thanks!";
			}

			if (previousIntent.equals("beforeInterested"))
			{
				options = Arrays.asList(new String[] { "NegativeResponse", "ConfirmIntent", "Default", "Error" });
				

				response = "Before I send you some information I want to ask you a few questions, to make sure you qualify";
			}

			if (previousIntent.equals("lease"))
			{
				options = Arrays.asList(new String[] { "confirmIntent", "NegativeResponse", "Default", "Error" });
				
				response = "Thanks!";
			}

			if (previousIntent.equals("YearLeftLease"))
			{
				options = Arrays.asList(new String[] { "confirmIntent", "NegativeResponse", "Default", "Error" });
				

				response = "Sorry! We consider traders that do not have any outstanding taxes.";
			}

			if (previousIntent.equals("numberInput")) {
				options = Arrays.asList(new String[] { "negativeResponse", "confirmIntent", "Default", "Error" });
				
				//TODO: Return blank response
				response = "Thanks for the confirmation!";
			}
			return response;


		case "NegativeResponse": 
			if (previousIntent.equals("getCustomerId"))
			{
				options = Arrays.asList(new String[] { "confirmIntent", "NegativeResponse", "putonhold", "Default", "Error" });
				

				response = "My apologies";
			}

			if (previousIntent.equals("LeaseConfirmIntent"))
			{
				options = Arrays.asList(new String[] { "isLeased", "NegativeResponse", "busy", "Default", "Error" });
				

				response = "Thanks!";
			}

			if (previousIntent.equals("lease"))
			{
				options = Arrays.asList(new String[] { "confirmIntent", "NegativeResponse", "Default", "Error" });
				

				response = "Thanks!";
			}
			if (previousIntent.equals("YearLeftLease")) {
				options = Arrays.asList(new String[] { "confirmIntent", "NegativeResponse", "Default", "Error" });
				

				response = "Ok, great. It sounds like you are a strong candidate for the program. I will send you a brochure explaining our program in greater detail and our application for your review.  The application has a checklist which can be used as a guideline of what we need in order to submit your application";
			}

			if (previousIntent.equals("confirmIntent"))
			{
				response = "Thanks!";

				options = Arrays.asList(new String[] { "ConfirmIntent", "NegativeResponse", "Default", "Error" });
				
			}


			if (previousIntent.equals("isInterested")) {
				options = Arrays.asList(new String[] { "confirm", "NegativeResponse", "Default", "Error" });
				

				response = "Thanks!";
			}
			return response;

		case "numberInput": 
			if ((null == previousIntent) || (previousIntent.equals("")))
			{
				options = Arrays.asList(new String[] { "getCustomerId", "numberInput", "Default", "Error" });
				

				response = handleGetCustomerIdIntent(input);


			}
			else if (previousIntent.equals("confirmIntent")) {
				System.out.println("response for case:numberInput and confirmIntent:" + input.getCurrentIntent().getSlots().getInputNumber());

				options = Arrays.asList(new String[] { "earning", "numberInput", "Default", "Error" });
				

				response = "You Said "+input.getCurrentIntent().getSlots().getInputNumber() ;

			}
			else if (previousIntent.equals("earning"))
			{
				options = Arrays.asList(new String[] { "batchAndSettle", "numberInput", "Default", "Error" });
				

				response = "You Said "+ input.getCurrentIntent().getSlots().getInputNumber();
			}
			return response;


		case "suitabletime": 
			String date = input.getCurrentIntent().getSlots().getDate();
			String time = input.getCurrentIntent().getSlots().getTime();


			String dateToday = new SimpleDateFormat("yyyy-mm-dd").format(new Date());


			System.out.println("date:" + input.getCurrentIntent().getSlots().getDate() + " Time:" + input.getCurrentIntent().getSlots().getTime());

			options = Arrays.asList(new String[] { "getCustomerId", "numberInput", "Default", "Error" });
			

			response = "Thanks for confirmation";

			response = "Please confirm the date.";

			response = "That’s an invalid date. Please confirm a future date and time . We work between 9AM to 5PM on weekdays";
			return "";

		case "earning": 
			options = Arrays.asList(new String[] { "earning", "numberInput", "Default", "Error" });
			

			response = "You Said "+ input.getCurrentIntent().getSlots().getEarningAmount();

			return response;

		case "batchAndSettle": 
			String inputAmount = input.getCurrentIntent().getSlots().getSettleAmount();

			if ((null == inputAmount) || (inputAmount.equals("")))
			{
				String utterance = input.getInputTranscript();
				if (utterance.equalsIgnoreCase("Once"))
				{
					response = "You said one";
				}
				if (utterance.equalsIgnoreCase("twice")) {
					response = "You said two";
				}
				if (utterance.equalsIgnoreCase("Thrice")) {
					response = "you said three";
				}
			}
			else {
				response = "You Said "+ input.getCurrentIntent().getSlots().getSettleAmount();
			}
			System.out.println("Current intent: Batch and settle" + inputAmount);
			options = Arrays.asList(new String[] { "batchAndSettle", "numberInput", "Default", "Error" });
			

			return response;

		case "busy": 
			if (previousIntent.equals("putonhold"))
			{
				options = Arrays.asList(new String[] { "confirmIntent", "busy", "Default", "Error" });
				
				response = "Sure Nicholas";
			}

			if (previousIntent.equals("LeaseConfirmIntent"))
			{
				options = Arrays.asList(new String[] { "isleased", "NegativeResponse", "busy", "Default", "Error" });
				
			}
			return response;
		case "isLeased": 
			options = Arrays.asList(new String[] { "isLeased", "NegativeResponse", "busy", "Default", "Error" });
			
			response = ""; }

		response = handleDefaultIntent(input);


		return response;
	}

	private String handleDefaultIntent(Request input) {
		return "";
	}

	private static String handleGetCustomerIdIntent(Request input)
	{
		System.out.println("Inside handleGetcustomerIdIntent");
		String customerId = "";
		if (input.getCurrentIntent().getName().equals("numberInput"))
		{
			customerId = input.getCurrentIntent().getSlots().getInputNumber();
		}
		else if (input.getCurrentIntent().getName().equals("getCustomerId")) {
			customerId = input.getCurrentIntent().getSlots().getCustomerId();
		}

		String response = "";

		Gson gson = new Gson();
		CustomerRequest obj = new CustomerRequest();
		Customer customer = new Customer();
		customer.setId(customerId);
		obj.setRequest(customer);
		String json = gson.toJson(obj);
		System.out.println("json is:" + json);
		StringBuffer output = new StringBuffer();
		try {
			output = ServiceUtils.getOutputJson"POST", json);
		} catch (IOException e) {
			e.printStackTrace();
		return response;
	}


}
