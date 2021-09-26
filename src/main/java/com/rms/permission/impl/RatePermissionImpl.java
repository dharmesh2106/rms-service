package com.rms.permission.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.exceptions.DataValidationException;
import com.rms.exceptions.NotFoundException;
import com.rms.model.Rate;
import com.rms.permission.RatePermission;
import com.rms.service.RateService;
import com.rms.utils.CommonValues;

@Service
public class RatePermissionImpl extends RmsPermissionImpl implements RatePermission {
	
	private static final Logger logger = LoggerFactory.getLogger(RatePermissionImpl.class);
	
	@Autowired  
	private RateService rateService; 
	
	
	public Rate findById(Long rateId) throws Exception {
		logger.info("Rate permission findRateById with {}", rateId);
		Rate foundRate = rateService.findById(rateId);
		if(foundRate == null) {
			logger.info("RateId {} not found.", rateId);
			throw new NotFoundException(String.format(CommonValues.NOT_FOUND, CommonValues.RATE_ID));
		}
		logger.info("RateId {} found.", rateId);
		return foundRate;
	}
	
	public Rate createRate(Rate rate) throws Exception {
		List<String> validationErrors = validateRate(rate);
		if (validationErrors.size() > 0) {
			logger.info("Rate {} validation faild.", rate.getRateDescription());
			throw new DataValidationException(validationErrors);
		}
		return rateService.createRate(rate);
	}
	
	public Rate updateRate(Rate rate) throws Exception  {
		List<String> validationErrors = validateRate(rate);
		if (validationErrors.size() > 0) {
			logger.info("Rate {} validation faild.", rate.getRateDescription());
			throw new DataValidationException(validationErrors);
		}
		return rateService.updateRate(rate);
	}
	
	public void deleteRate(Long rateId) throws Exception {
		logger.info("Rate permissoin rate {} deteting stated.", rateId);
		rateService.deleteRate(rateId);
	}

}
