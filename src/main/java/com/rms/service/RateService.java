package com.rms.service;

import com.rms.model.Rate;

public interface RateService {

	public Rate findById(Long rateId) throws Exception;

	public Rate createRate(Rate rate);

	public Rate updateRate(Rate rate) throws Exception;

	public void deleteRate(Long rateId) throws Exception;

}
