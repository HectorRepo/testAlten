package com.ejemplo.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ejemplo.controller.PricesController;
import com.ejemplo.entity.InditextPrice;
import com.ejemplo.exceptions.PricesRepoException;
import com.ejemplo.repository.PricesRepo;

/**
 *  Spring Service to get data from PricesRepository
 */
@Service
@Component
public class PricesService {

	Logger logger = LoggerFactory.getLogger(PricesService.class);
	
	@Autowired  
	PricesRepo pricesRepository;  
	
	/**
	 * @param applyDate
	 * @param productId
	 * @param brandID
	 * @return a list of prices to apply having a date a brand and a productID
	 */
	public List<InditextPrice> getPricesToApply(Date applyDate, String productId, Integer brandID) throws PricesRepoException {
		logger.info("Searching price to apply for product {} and date {} for brand ZARA",productId, applyDate);
		List<InditextPrice> list = pricesRepository.findPricesToApply(applyDate,productId,brandID);	
		return list;
	}
}
