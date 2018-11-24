package com.aws.handler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aws.data.DialogAction;
import com.aws.data.Message;
import com.aws.data.Request;
import com.aws.data.ResponseParam;
import com.aws.data.SessionAttributes;
import com.aws.data.User;
import com.aws.manager.CustomerDetailsManager;
import com.aws.manager.ICustomerDetailsManager;
import com.aws.util.HibernateUtil;


public class CustomerDetailsHandler implements RequestHandler<Request ,ResponseParam> {
	
	ICustomerDetailsManager customerDetailsManager = new CustomerDetailsManager();

	public ResponseParam handleRequest(Request input ,Context context) {

		System.out.println("input.getCurrentIntent().getName(): "+ input.getCurrentIntent().getName());
		System.out.println("input.getCurrentIntent().getSlots().getCustomerId(): "+input.getCurrentIntent().getSlots().getName());
		System.out.println("previous intent:"+input.getSessionAttributes().getPreviousIntent());
		//this will be content
		
		SessionAttributes sessionAttributes = new SessionAttributes();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            
            User employee = new User();
            employee.setUserId(2);
            employee.setUsername("abc");
            session.save(employee);
            session.getTransaction().commit();
        }

    	
		String output  = customerDetailsManager.handleRequest(input);
		
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
}
