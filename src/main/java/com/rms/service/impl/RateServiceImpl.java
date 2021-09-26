package com.rms.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Transactional
public class RateServiceImpl implements RateService{
	
	private static final Logger logger = LoggerFactory.getLogger(RateServiceImpl.class);
	
	@Autowired
	private RateRepository rateRepository;
	
	public Rate findById(Long rateId) throws Exception {
		logger.info("Rate service findRateById with {}", rateId);
		Optional<RateEntity> rateEntity = rateRepository.findById(rateId);
		if(!rateEntity.isPresent()) {
			logger.info("RateId {} not found.", rateId);
			return null;
		}
		Rate rate = new Rate();
		BeanUtils.copyProperties(rateEntity.get(),rate);
		return rate;
	}
	
	public Rate createRate(Rate rate) {
		logger.info("Rate service start creating rate {}", rate.getRateDescription());
		RateEntity rateEntity = new RateEntity();
		
	    // Coping model properties to entity
		BeanUtils.copyProperties(rate, rateEntity);
		RateEntity savedRateEntity = rateRepository.save(rateEntity);
		// Coping saved entity properties to model
		BeanUtils.copyProperties(savedRateEntity, rate);
		logger.info("Rate service rate creation completed, rateId {}", rate.getRateId());
		return rate;
	}
	
	public Rate updateRate(Rate rate) throws Exception  {
		logger.info("Rate service rate updating started for rateId {}.", rate.getRateId());
		RateEntity rateEntity = new RateEntity();
		Optional<RateEntity> savedRateEntity = rateRepository.findById(rate.getRateId());
		if(!savedRateEntity.isPresent()) {
			logger.info("Rate service rate update not found rateId {}.", rate.getRateId());
			throw new NotFoundException(String.format(CommonValues.NOT_FOUND, CommonValues.RATE_ID));
		}
	    // Coping model properties to entity
		BeanUtils.copyProperties(rate, rateEntity);
		rateEntity = rateRepository.save(rateEntity);
		// Coping saved entity properties to model
		BeanUtils.copyProperties(savedRateEntity, rate);
		logger.info("Rate service rate updating completed for rateId {}.", rate.getRateId());
		return rate;
	}
	
	public void deleteRate(Long rateId) throws Exception {
		logger.info("Rate service rate delete started for rateId {}.", rateId);
		Optional<RateEntity> savedRateEntity = rateRepository.findById(rateId);
		if(!savedRateEntity.isPresent()) {
			logger.info("Rate service rate delete not found rateId {}.", rateId);
			throw new NotFoundException(String.format(CommonValues.NOT_FOUND, CommonValues.RATE_ID));
		}
		rateRepository.deleteById(rateId);
		logger.info("Rate service rate deleting completed for rateId {}.", rateId);
	}

}
