package com.rms.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.entity.RateEntity;
import com.rms.exceptions.NotFoundException;
import com.rms.model.Rate;
import com.rms.repository.RateRepository;
import com.rms.service.RateService;
import com.rms.utils.CommonValues;

@Service
public class RateServiceImpl implements RateService{
	
	@Autowired
	private RateRepository rateRepository;
	
	public Rate findById(Long rateId) throws Exception {
		Optional<RateEntity> rateEntity = rateRepository.findById(rateId);
		if(!rateEntity.isPresent()) {
			return null;
		}
		Rate rate = new Rate();
		BeanUtils.copyProperties(rateEntity.get(),rate);
		return rate;
	}
	
	public Rate createRate(Rate rate) {
		RateEntity rateEntity = new RateEntity();
		
	    // Coping model properties to entity
		BeanUtils.copyProperties(rate, rateEntity);
		RateEntity savedRateEntity = rateRepository.save(rateEntity);
		// Coping saved entity properties to model
		BeanUtils.copyProperties(savedRateEntity, rate);
		return rate;
	}
	
	public Rate updateRate(Rate rate) throws Exception  {
		RateEntity rateEntity = new RateEntity();
		Optional<RateEntity> savedRateEntity = rateRepository.findById(rate.getRateId());
		if(!savedRateEntity.isPresent()) {
			throw new NotFoundException(String.format(CommonValues.NOT_FOUND, "RateId"));
		}
	    // Coping model properties to entity
		BeanUtils.copyProperties(rate, rateEntity);
		rateEntity = rateRepository.save(rateEntity);
		// Coping saved entity properties to model
		BeanUtils.copyProperties(savedRateEntity, rate);
		return rate;
	}
	
	public void deleteRate(Long rateId) throws Exception {
		Optional<RateEntity> savedRateEntity = rateRepository.findById(rateId);
		if(!savedRateEntity.isPresent()) {
			throw new NotFoundException(String.format(CommonValues.NOT_FOUND, "RateId"));
		}
		rateRepository.deleteById(rateId);
	}

}
