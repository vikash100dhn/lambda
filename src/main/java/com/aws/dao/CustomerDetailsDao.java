package com.aws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.aws.data.Customer;
import com.aws.handler.DWLabConnManager;
import com.aws.handler.Database;

public class CustomerDetailsDao implements ICustomerDetailsDao {

	@Override
	public int saveConnectConversation(String intentName, String inputTranscript) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCustomerName(int id) {
		// TODO Auto-generated method stub
		return null;
	}/*

	@Override
	public String getCustomerName(int id) {

		Customer customer = null;

		try {

			Connection conn = DWLabConnManager.createConnection();
			System.out.println(conn);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tblconnectcustomer where id="+id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				customer = Customer.of(
						rs.getInt("id"),
						rs.getString("customer_name"));
			}
			else {
				return "customer not found";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		return customer.getCustomerName();
	}


	public static void main(String args[]) {
		CustomerDetailsDao dao = new CustomerDetailsDao();
		String intent = dao.getCustomerName(1234);
		System.out.println(intent);
		System.out.println(""+dao.saveConnectConversation("getCustomerId", "My customer id is 1234"));
	}
	@Override
	public int saveConnectConversation(String intentName, String inputTranscript) {
		int result =0;
		try {

			Connection conn = DWLabConnManager.createConnection();
			PreparedStatement ps = conn.prepareStatement("insert into tblconnectconversation values (?, ?, ?, ?)");
			ps.setInt(1, getNextValue());
			ps.setString(2, intentName);
			ps.setString(3, inputTranscript);
			ps.setString(4, Calendar.getInstance().getTime().toString());
			result = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}

		return result;
	}

	public int getNextValue() {
		int result =0;

		try  {
			Connection conn = DWLabConnManager.createConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT max(id) FROM tblconnectconversation");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return result+1;

	}


*/}
