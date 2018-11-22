package com.aws.apigateway;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aws.util.ServiceCallConstants;

@Path("/api")
public class ServiceApi {
	
	@GET
	@Path("/welcome")
	@Produces(MediaType.APPLICATION_JSON)
	public Response welcome() {
		Map<String, String> test = new HashMap<>();
		test.put("value", "Service up and Running");
		return Response.status(200).entity(test)
				.header(ServiceCallConstants.RESP_ACCESS_CONTROL_ALLOW_ORIGIN_KEY,
						ServiceCallConstants.RESP_ACCESS_CONTROL_ALLOW_ORIGIN_VALUE)
				.header(ServiceCallConstants.RESP_ACCESS_CONTROL_ALLOW_METHOD_KEY,
						ServiceCallConstants.RESP_ACCESS_CONTROL_ALLOW_METHOD_VALUE)
				//.allow(ServiceCallConstants.RESP_ALLOW_OPTIONS)
				.build();
	}
	
	@GET
	@Path("/getConnectIdentifier")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConnectIdentifier() {

		String url = "http://localhost:8080/customerprofilerservices/api/getconnectconversationslist";
		String respString = getResponseFromURL(url, "GET");
		System.out.println("Response from getConnectIdentifier"+respString);

		return Response.status(200).entity(respString)
				.header(ServiceCallConstants.RESP_ACCESS_CONTROL_ALLOW_ORIGIN_KEY,
						ServiceCallConstants.RESP_ACCESS_CONTROL_ALLOW_ORIGIN_VALUE)
				.header(ServiceCallConstants.RESP_ACCESS_CONTROL_ALLOW_METHOD_KEY,
						ServiceCallConstants.RESP_ACCESS_CONTROL_ALLOW_METHOD_VALUE)
				//.allow(ServiceCallConstants.RESP_ALLOW_OPTIONS)
				.build();
	}
	private static String getResponseFromURL(String urlString, String urlMethod) {
		
		StringBuilder outputJson = new StringBuilder();
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(urlMethod);
			conn.setRequestProperty(ServiceCallConstants.ACCEPT, ServiceCallConstants.APPLICATION_JSON);
			conn.setRequestProperty(ServiceCallConstants.CONTENT_TYPE, ServiceCallConstants.APPLICATION_JSON);

			if (conn.getResponseCode() != 200) {
		
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;

			while ((inputLine = br.readLine()) != null) {
				outputJson.append(inputLine);
			}
			conn.disconnect();
			br.close();
		} catch (Exception e) {
			
		}
		
		return outputJson.toString();
	}
}
