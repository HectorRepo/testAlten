package com.ejemplo.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.entity.InditextPrice;
import com.ejemplo.exceptions.PricesRepoException;
import com.ejemplo.service.PricesService;

/**
 * Rest Controller to get Product data as price to be applied using 
 * certain criteria as product names, product brand ...
 */
@RestController
public class PricesController {

	Logger logger = LoggerFactory.getLogger(PricesController.class);

	@Autowired
	PricesService pricesService;

	/**
	 * Returns a list of prices to apply given a current date, a productId and
	 * product brand
	 * 
	 * @param applyDate
	 * @param productId
	 * @param brandID
	 * @return a list containing the prices to apply for a certain product and brand
	 */
	@RequestMapping(value = "/listPrice", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InditextPrice>> getPricesToApply(
			@RequestParam("applyDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH.mm.ss") Date applyDate,
			@RequestParam("productId") String productId, @RequestParam("brandID") Integer brandID) {

		try {
			logger.info("Request to Prices front controller recieved correctly...");
			logger.info("Sending request to service...");
			List<InditextPrice> prices = pricesService.getPricesToApply(applyDate, productId, brandID);
			logger.info("Process completed successfully");
			return ResponseEntity.ok().body(prices);
		} catch (PricesRepoException ex) {
			logger.info("Process completed UNSUCCESSFULLY");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
