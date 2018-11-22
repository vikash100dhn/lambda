package com.aws.data;

public class CustomerRequest {
	
	private Customer request;

    public Customer getRequest ()
    {
        return request;
    }

    public void setRequest (Customer request)
    {
        this.request = request;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [request = "+request+"]";
    }

}
