package com.sg.repository;

import java.util.Date;
import java.util.List;

import com.sg.exception.AdvertisementServiceException;
import com.sg.model.Account;
import com.sg.model.Advertisement;

public interface AdvertisementRepository {
	Boolean saveAid(Advertisement advertisement, Account account);
	Boolean updateAid(Advertisement advertisement, Account account);
	List<Advertisement> getAllAids(Account account);
	Boolean removeAid(Advertisement advertisement, Account account);
	List<Advertisement> getAllAidsByCategory(Account account,
                                             String categoryName);
	List<Advertisement> getAllAidsByDateRange(Account account, Date startDate,
                                              Date endDate) throws AdvertisementServiceException;
	
}
