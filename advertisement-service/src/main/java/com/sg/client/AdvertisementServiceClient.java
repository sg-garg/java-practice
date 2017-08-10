package com.sg.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.sg.utils.AdvertisementServiceUtil;

public class AdvertisementServiceClient {
	private final static Logger logger = Logger.getLogger(AdvertisementServiceClient.class);
	private Client client;
	public AdvertisementServiceClient(){
		this.client = ClientBuilder.newClient();
	}
	
	/*//This function will found out the second highest number in the array.
	
	public NumberResponse findSecondHighestNumber(List<Integer> inputArray){
		logger.debug("Start findSecondHighestNumber: inputArray="+inputArray);				
		NumberRequest requestData = new NumberRequest();
		requestData.setInputArray(inputArray);
		System.out.println(AdvertisementServiceUtil.getProperties("app.url"));
		WebTarget target= client.target(AdvertisementServiceUtil.getProperties("app.url")+"/AdvertisementService/webapi/");
		
		//Here 2 means 2nd highest number to find.
		Response response = target.path("findAllAccounts").request(MediaType.APPLICATION_JSON)
		.post(Entity.entity(requestData, MediaType.APPLICATION_JSON));
		
		if(response.getStatus() != 200){
			logger.error(response.getStatus() + ": there was an error on the server.");
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}
		logger.debug("End findSecondHighestNumber");
		System.out.println(response);
		return response.readEntity(NumberResponse.class);
	}*/
	
	public void getAllAccount(){
				
		System.out.println(AdvertisementServiceUtil.getProperties("app.url"));
		WebTarget target= client.target(AdvertisementServiceUtil.getProperties("app.url")+"/AdvertisementService/webapi/");
		
		//Here 2 means 2nd highest number to find.
		Response response = target.path("findAllAccounts").request(MediaType.APPLICATION_JSON).get();
		
		if(response.getStatus() != 200){
			logger.error(response.getStatus() + ": there was an error on the server.");
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}
		logger.debug("End findSecondHighestNumber");
		logger.debug(response);
	}
	public static void main(String[] args) {
		new AdvertisementServiceClient().getAllAccount();
	}
}
