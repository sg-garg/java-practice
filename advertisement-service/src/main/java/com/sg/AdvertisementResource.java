package com.sg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.sg.exception.AdvertisementServiceException;
import com.sg.model.Account;
import com.sg.model.AccountTest;
import com.sg.model.Advertisement;
import com.sg.model.AdvertisementRequest;
import com.sg.model.AdvertisementResponse;
import com.sg.model.NumberResponse;
import com.sg.repository.AccountRepository;
import com.sg.repository.AccountRepositoryStub;
import com.sg.repository.AdvertisementRepository;
import com.sg.repository.AdvertisementRepositoryStub;
import com.sg.utils.AdvertisementServiceUtil;
@Path("")
public class AdvertisementResource {
	private final static Logger logger = Logger.getLogger(AdvertisementResource.class);
	
	
	// To get all ads associated with a account.
	// example: http://localhost:8081/AdvertisementService/webapi/findAllAds
	// like requestData = {"username": "sgarg"}
		
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("findAllAds")
	public Response getAllAdvertisements(AdvertisementRequest request) throws AdvertisementServiceException{
		logger.debug("Start getAllAdvertisements");
		if(request == null){
			logger.error("INVALID_INPUT: Input params are missing");
			throw new AdvertisementServiceException("INVALID_INPUT","Input params are missing");
			
		}
		Account account = validateAccount(request.getUsername());
		
		AdvertisementRepository advertisementService = new AdvertisementRepositoryStub();
		List<Advertisement> reponseEntity = advertisementService.getAllAids(account);
		if(reponseEntity == null){
			reponseEntity = new ArrayList<Advertisement>();
		}
		logger.debug("End getAllAdvertisements");
		return Response.ok(new GenericEntity<List<Advertisement>>(reponseEntity){}).build();
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("searchAdsByCategory")
	public Response getAllAdsByCategory(AdvertisementRequest request) throws AdvertisementServiceException{
		logger.debug("Start getAllAdsByCategory");
		if(request == null){
			logger.error("INVALID_INPUT: Input params are missing");
			throw new AdvertisementServiceException("INVALID_INPUT","Input params are missing");
			
		}
		Account account = validateAccount(request.getUsername());
		
		if(request.getAdvertisement() == null || request.getAdvertisement().getCategory() == null){
			logger.error("INVALID_INPUT: Input params are missing");
			throw new AdvertisementServiceException("INVALID_INPUT","Category name is missing to search all ads");
		}
		
		AdvertisementRepository advertisementService = new AdvertisementRepositoryStub();
		List<Advertisement> reponseEntity = advertisementService.getAllAidsByCategory(account, request.getAdvertisement().getCategory());
		
		logger.debug("End getAllAdsByCategory");
		return Response.ok(new GenericEntity<List<Advertisement>>(reponseEntity){}).build();
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("searchAdsByDateRange")
	public Response getAllAdsByDateRange(AdvertisementRequest request) throws AdvertisementServiceException{
		logger.debug("Start getAllAdsByDateRange");
		if(request == null){
			logger.error("INVALID_INPUT: Input params are missing");
			throw new AdvertisementServiceException("INVALID_INPUT","Input params are missing");
			
		}
		Account account = validateAccount(request.getUsername());
		
		if(request.getAdvertisement() == null 
				|| (request.getAdvertisement().getStartDate() == null && request.getAdvertisement().getEndDate() == null)){
			throw new AdvertisementServiceException("INVALID_INPUT","StartDate and/ or endDate params are missing");
		}
		
		Date startDate = AdvertisementServiceUtil.toDate(request.getAdvertisement().getStartDate());
		Date endDate = AdvertisementServiceUtil.toDate(request.getAdvertisement().getEndDate());
		
		AdvertisementRepository advertisementService = new AdvertisementRepositoryStub();
		List<Advertisement> reponseEntity = advertisementService.getAllAidsByDateRange(account, startDate, endDate);
		
		logger.debug("End getAllAdsByDateRange");
		return Response.ok(new GenericEntity<List<Advertisement>>(reponseEntity){}).build();
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("createAds")
	public Response createAds(AdvertisementRequest request) throws AdvertisementServiceException{
		logger.debug("Start saveAds");
		
		System.out.println(request.getUsername());
		System.out.println(request.getAdvertisement());
		if(request == null || request.getAdvertisement() == null){
			logger.error("INVALID_INPUT: Input params are missing");
			throw new AdvertisementServiceException("INVALID_INPUT","Input params are missing");
		}
		
		Account account = validateAccount(request.getUsername());
		validateAds(request.getAdvertisement());
		AdvertisementRepository advertisementService = new AdvertisementRepositoryStub();
		
		Boolean result = advertisementService.saveAid(request.getAdvertisement(), account);
		AdvertisementResponse response = new AdvertisementResponse();
		if(result){
			response.setMessage("Record Inserted Successfully");
		}else{
			response.setMessage("Record Already Exists");
		}
		logger.debug("End saveAds");
		return Response.ok(response).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("updateAds")
	public Response updateAds(AdvertisementRequest request) throws AdvertisementServiceException{
		logger.debug("Start updateAds");
		
		System.out.println(request.getUsername());
		System.out.println(request.getAdvertisement());
		if(request == null || request.getAdvertisement() == null){
			logger.error("INVALID_INPUT: Input params are missing");
			throw new AdvertisementServiceException("INVALID_INPUT","Input params are missing");
		}
		
		Account account = validateAccount(request.getUsername());
		validateAds(request.getAdvertisement());
		
		AdvertisementRepository advertisementService = new AdvertisementRepositoryStub();
		Boolean result = advertisementService.updateAid(request.getAdvertisement(), account);
		
		AdvertisementResponse response = new AdvertisementResponse();
		if(result){
			response.setMessage("Record Updated Successfully");
		}else{
			response.setMessage("No record found for update.");
		}
		logger.debug("End updateAds");
		return Response.ok(response).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("deleteAds")
	public Response deleteAds(AdvertisementRequest request) throws AdvertisementServiceException{
		logger.debug("Start deleteAds");
		
		if(request == null || request.getAdvertisement() == null){
			logger.error("INVALID_INPUT: Input params are missing");
			throw new AdvertisementServiceException("INVALID_INPUT","Input params are missing");
		}
		
		Account account = validateAccount(request.getUsername());
		
		if(request.getAdvertisement() == null || request.getAdvertisement().getId() == null){
			logger.error("INVALID_INPUT: Input params are missing");
			throw new AdvertisementServiceException("INVALID_INPUT","Ads ID is required to delete.");
		}
		
		
		AdvertisementRepository advertisementService = new AdvertisementRepositoryStub();
		Boolean result = advertisementService.removeAid(request.getAdvertisement(), account);
		
		AdvertisementResponse response = new AdvertisementResponse();
		if(result){
			response.setMessage("Record Deleted Successfully");
		}else{
			response.setMessage("No record found for delete.");
		}
		logger.debug("End deleteAds");
		return Response.ok(response).build();
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("findAllAccounts")
	public Response getAllAccount() throws AdvertisementServiceException{
		logger.debug("Start getAllAccount");
				
		//validate the account 
		AccountRepository accountService = new AccountRepositoryStub();
		List<Account> allAccounts = accountService.getAllAccounts();
		if(allAccounts == null){
			logger.error("INVALID_USER_ACCESS: No account found.");
			throw new AdvertisementServiceException("INVALID_USER_ACCESS", "No account found.");
		}
		
		GenericEntity<List<Account>> list = new GenericEntity<List<Account>>(allAccounts) { };
		System.out.println(list);
		System.out.println(allAccounts.get(0));
		logger.debug("End getAllAccount");
		return Response.ok(list).build();
	}
	
	private Account validateAccount(String username)
			throws AdvertisementServiceException {
		// If account is null then throw an error.
		if (username == null) {
			logger.error("INVALID_INPUT_DATA: account information is not provided to fetch the ads.");
			throw new AdvertisementServiceException("INVALID_INPUT_DATA",
					"account information is not provided to fetch the ads.");
		}

		// validate the account
		AccountRepository accountService = new AccountRepositoryStub();
		Account account = accountService.getAccountByUsername(username);
		if (account == null) {
			logger.error("INVALID_USER_ACCESS: Invalide access of resources.");
			throw new AdvertisementServiceException("INVALID_USER_ACCESS",
					"Invalide access of resources.");
		}
		return account;

	}
	
	private void validateAds(Advertisement advertisement) throws AdvertisementServiceException{
		List<String> messages = new ArrayList<String>();
		
		if(advertisement.getId() == null){
			messages.add("ID");
		}
		
		if(advertisement.getStartDate() == null){
			messages.add("startDate");
		}
		
		if(advertisement.getEndDate() == null){
			messages.add("endDate");
		}
		if(advertisement.getMessage() == null){
			messages.add("message");
		}
		if(advertisement.getCategory() == null){
			messages.add("category");
		}
		
		
		if(messages.size() > 0){
			throw new AdvertisementServiceException("MISSING_REQUIRED_FIELD", Arrays.toString(messages.toArray()));
		}
		
		Date startDate = AdvertisementServiceUtil.toDate(advertisement.getStartDate());
		Date endDate = AdvertisementServiceUtil.toDate(advertisement.getEndDate());
		
		if(startDate.after(endDate)){
			throw new AdvertisementServiceException("INVALID_DATE_RANGE", "StartDate should be less then EndDate.");
		}
	
	}	
	
		
	
	

}
