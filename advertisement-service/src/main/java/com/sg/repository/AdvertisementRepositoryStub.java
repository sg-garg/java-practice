package com.sg.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sg.exception.AdvertisementServiceException;
import com.sg.model.Account;
import com.sg.model.Advertisement;
import com.sg.utils.AdvertisementServiceUtil;
import com.sg.utils.DataFiles;

public class AdvertisementRepositoryStub implements AdvertisementRepository {

	@Override
	public List<Advertisement> getAllAids(Account account) {
		Map<Account,List<Advertisement>> map = AdvertisementServiceUtil.deserilizeAsMap(DataFiles.ADVERTISEMENT.getFile());
		if(map != null){
			return map.get(account);
		}
		return null;		
	}
	
	@Override
	public List<Advertisement> getAllAidsByCategory(Account account, String categoryName) {
		List<Advertisement> allAids = getAllAids(account);
		
		if(allAids == null){
			allAids = new ArrayList<Advertisement>();
		}
		
		List<Advertisement> resultArray = new ArrayList<Advertisement>();
		for (int i = 0; i < allAids.size(); i++) {
			if(categoryName !=null && categoryName.equals(allAids.get(i).getCategory())){
				resultArray.add(allAids.get(i));
			}
		}
		return resultArray;		
	}
	
	@Override
	public List<Advertisement> getAllAidsByDateRange(Account account, Date startDate, Date endDate) throws AdvertisementServiceException {
		List<Advertisement> allAids = getAllAids(account);
				
		if(allAids == null){
			allAids = new ArrayList<Advertisement>();
		}
		
		List<Advertisement> resultArray = new ArrayList<Advertisement>();
		for (int i = 0; i < allAids.size(); i++) {
			Date adStartDate = AdvertisementServiceUtil.toDate(allAids.get(i).getStartDate());
			Date adEndDate = AdvertisementServiceUtil.toDate(allAids.get(i).getEndDate());
			System.out.println(adStartDate +" "+adEndDate +"--"+startDate +" "+endDate);
			
			if(startDate !=null && endDate!= null){
				if(adStartDate.after(startDate) && endDate.after(adEndDate)){
					resultArray.add(allAids.get(i));
				}
			}else if(( startDate !=null && adStartDate.after(startDate)) 
					|| (endDate!= null && endDate.after(adEndDate))){
				resultArray.add(allAids.get(i));
			}
		}
		return resultArray;		
	}
	@Override
	public Boolean saveAid(Advertisement advertisement, Account account) {
		Boolean result=true;
		List<Advertisement> allAids = getAllAids(account);
		if(allAids == null){
			allAids = new ArrayList<Advertisement>();
		}
		System.out.println(allAids);
		for (int i = 0; i < allAids.size(); i++) {
			if(allAids.get(i).getId().equals(advertisement.getId())){
				result = false;
				break;
			}
		}
		if(result){
			allAids.add(advertisement);
			AdvertisementServiceUtil.replaceValueByKeySerilizedMap(DataFiles.ADVERTISEMENT.getFile(), account, allAids);
		}
		return result;
			    
	}
	
	@Override
	public Boolean updateAid(Advertisement advertisement, Account account) {
		Boolean result=false;
		List<Advertisement> allAids = getAllAids(account);
		if(allAids != null){
			for (int i = 0; i < allAids.size(); i++) {
				if(allAids.get(i).getId().equals(advertisement.getId())){
					allAids.set(i, advertisement);
					result = true;
					break;
				}
			}
		}
		if(result){
			AdvertisementServiceUtil.replaceValueByKeySerilizedMap(DataFiles.ADVERTISEMENT.getFile(), account, allAids);
		}
		return result;
	}
	
	
	
	@Override
	public Boolean removeAid(Advertisement advertisement, Account account) {
		Boolean result=false;
		List<Advertisement> allAids = getAllAids(account);
		if(allAids != null){
			for (int i = 0; i < allAids.size(); i++) {
				if(allAids.get(i).getId().equals(advertisement.getId())){
					allAids.remove(i);
					result = true;
					break;
				}
			}
		}
		if(result){
			AdvertisementServiceUtil.replaceValueByKeySerilizedMap(DataFiles.ADVERTISEMENT.getFile(), account, allAids);
		}
		return result;
	}
	
	
	
	/*public static void main(String[] args) {
		AdvertisementDao advertisementService = new AdvertisementDaoImpl();
		Advertisement advertisement = new Advertisement(1L, "First Ad", "test url", new Date(), new Date(), "housing");
			
		Client client = new Client(101L, "First Client");
		Account account = new Account("sgarg", "sgarg@123", client);
		advertisementService.saveAid(advertisement,account);
		List<Advertisement> advertisements = advertisementService.getAllAids(account);
		System.out.println(advertisements.size());
		for (Advertisement advertisement2 : advertisements) {
			System.out.println(advertisement2.getMessage());
		}		
	}*/

}
