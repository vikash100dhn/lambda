package com.aws.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.aws.data.IntentInfo;
import com.aws.data.WebSocketInput;
import com.aws.handler.StreamLambdaHandler;
import com.sun.jersey.core.util.Base64;

public class ServiceUtils {

	
	public static StringBuffer getOutputJson(String address, String method, String request) throws IOException {
		//StreamLambdaHandler.logger.log("getOutputJson address: " + address);
		URL url = new URL(address);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(method);
		connection.setDoOutput(true);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStream outputStream = null;
		if (method.equals("POST")) {
			outputStream = connection.getOutputStream();
			outputStream.write(request.getBytes());
			outputStream.flush();
		}
	//	StreamLambdaHandler.logger.log("outputStream: " + outputStream);
		if (connection.getResponseCode() != 200 && connection.getResponseCode() != 201) {
		//	StreamLambdaHandler.logger.log(ServiceCallConstants.ERR_LOGGER + connection.getResponseCode());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		String inputLine;
		StringBuffer outputJson = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			outputJson.append(inputLine);
		}
		connection.disconnect();
		if (method.equals("POST")) {
			if (outputStream != null)
				outputStream.close();
		}
		br.close();
	//	StreamLambdaHandler.logger.log("outputJson: " + outputJson);
		return outputJson;
	}
	

}
