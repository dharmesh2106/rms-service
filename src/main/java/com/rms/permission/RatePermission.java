package com.rms.permission;

import com.rms.model.Rate;

public interface RatePermission {
	
	
	public Rate findById(Long rateId) throws Exception;
	public Rate createRate(Rate rate) throws Exception;
	
	public Rate updateRate(Rate rate) throws Exception;
	
	public void deleteRate(Long rateId) throws Exception ;

}
