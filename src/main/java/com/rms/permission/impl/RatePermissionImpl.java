package com.rms.permission.impl;

import java.util.List;

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
	
	@Autowired  
	private RateService rateService; 
	
	
	public Rate findById(Long rateId) throws Exception {
		Rate foundRate = rateService.findById(rateId);
		if(foundRate == null) {
			throw new NotFoundException(String.format(CommonValues.NOT_FOUND, CommonValues.RATE_ID));
		}
		return foundRate;
	}
	
	public Rate createRate(Rate rate) throws Exception {
		List<String> validationErrors = validateRate(rate);
		if (validationErrors.size() > 0) {
			throw new DataValidationException(validationErrors);
		}
		return rateService.createRate(rate);
	}
	
	public Rate updateRate(Rate rate) throws Exception  {
		List<String> validationErrors = validateRate(rate);
		if (validationErrors.size() > 0) {
			throw new DataValidationException(validationErrors);
		}
		return rateService.updateRate(rate);
	}
	
	public void deleteRate(Long rateId) throws Exception {
		rateService.deleteRate(rateId);
	}

}
