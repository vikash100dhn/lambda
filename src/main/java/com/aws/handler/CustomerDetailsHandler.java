package com.aws.handler;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws.data.DialogAction;
import com.aws.data.Message;
import com.aws.data.Request;
import com.aws.data.ResponseParam;
import com.aws.data.SessionAttributes;
import com.aws.manager.CustomerDetailsManager;
import com.aws.manager.ICustomerDetailsManager;


public class CustomerDetailsHandler implements RequestHandler<Request ,ResponseParam> {
	
	ICustomerDetailsManager customerDetailsManager = new CustomerDetailsManager();

	public ResponseParam handleRequest(Request input ,Context context) {

		System.out.println("input.getCurrentIntent().getName(): "+ input.getCurrentIntent().getName());
		System.out.println("input.getCurrentIntent().getSlots().getCustomerId(): "+input.getCurrentIntent().getSlots().getCustomerId());
		System.out.println("input.getCurrentIntent().getSlots().getInputNumber()"+input.getCurrentIntent().getSlots().getInputNumber());
		System.out.println("previous intent:"+input.getSessionAttributes().getPreviousIntent());
		//this will be content
		
		SessionAttributes sessionAttributes = new SessionAttributes();
		
		String output  = customerDetailsManager.handleRequest(input);
		
		if(input.getCurrentIntent().getName().equals("suitabletime"))
		{

			String date = input.getCurrentIntent().getSlots().getDate();
			String time = input.getCurrentIntent().getSlots().getTime();
			sessionAttributes.setDateFlag("2");
			output ="That’s an invalid day/time. We work between 9AM to 5PM on weekdays";
			if(validateDate(date).equals("valid"))
			{
				if(validateTime(time).equals("valid")) {
					sessionAttributes.setDateFlag("1");
					output="Thanks for the confirmation";
				}
			}
			else if(validateDate(date).equals("past")) {
				sessionAttributes.setDateFlag("0");
				output ="Please confirm a future date and time.";
			}
		}
		if(input.getCurrentIntent().getName().equals("getCustomerId"))
		{
			sessionAttributes.setDateFlag("1");
		}
		ResponseParam responseParam = new ResponseParam() ;
		DialogAction dialogAction = new DialogAction() ;
		Message message = new Message() ;
		message.setContentType("PlainText");
		
		System.out.println("Output before setting the content:"+output);
		message.setContent(output);
		dialogAction.setMessage(message);
		dialogAction.setFulfillmentState("Fulfilled");
		dialogAction.setType("Close");
		responseParam.setDialogAction(dialogAction);
		
		sessionAttributes.setPreviousIntent(input.getCurrentIntent().getName());
		
		
		responseParam.setSessionAttributes(sessionAttributes);
		return responseParam ;
	}
	public static String validateDate(String date)
	{
		String result ="";
		String arr[] = date.split("-");
		LocalDate localDate = LocalDate.of(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
		java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		System.out.println(dayOfWeek);
		LocalDate today = LocalDate.now();
		if(localDate.isBefore(today))
		{
			System.out.println("Past Date");
			result = "past";
		}
		else
		{
			System.out.println("Future date");
			if(dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY))
			{
				result = "nonworking";
			}
			else {
				return "valid";
			}
		}
		return result;
	}
	public static String validateTime(String time)
	{
		String timearr[] = time.split(":");
		int inputTime = Integer.parseInt(timearr[0]);
		if(inputTime > 9 && inputTime < 17)
		{
			//Valid time
			System.out.println("valid time");
			return "valid";
		}
		else {
			//Invalid time
			System.out.println("invalid time");
			return "nonworking";
		}
	}

}
