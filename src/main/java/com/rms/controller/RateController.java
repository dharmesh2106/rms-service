package com.rms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rms.model.Surcharge;
import com.rms.model.Rate;
import com.rms.permission.RatePermission;

@RestController
@RequestMapping("/rms/v1")
public class RateController {
	
	private static final Logger logger = LoggerFactory.getLogger(RateController.class);
	
	private static final String SURCHARGE_URI = "https://surcharge.free.beeceptor.com/surcharge";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RatePermission ratePermission;
	
	@GetMapping("/rates/{rateId}")
	public Rate findRateById(@PathVariable("rateId") Long rateId) throws Exception {
		logger.info("Called RateController.findRateById with {}", rateId);
		Rate rate = ratePermission.findById(rateId);
		if(rate != null) {
			Surcharge surcharge = restTemplate.getForObject(SURCHARGE_URI, Surcharge.class);
			rate.setSurcharge(surcharge);
		}
		return rate;
	}
	
	@PostMapping("/saverate")
	public Rate saveRate(@RequestBody Rate rate) throws Exception {
		logger.info("Creating rate {}", rate.getRateDescription());
		Rate savedRate = ratePermission.createRate(rate);
		if(savedRate != null) {
			Surcharge surcharge = restTemplate.getForObject(SURCHARGE_URI, Surcharge.class);
			savedRate.setSurcharge(surcharge);
		}
		return savedRate;
	}
	
	@PutMapping("/updaterate")
	public Rate updateRate(@RequestBody Rate rate) throws Exception {
		logger.info("Updating rate {}", rate.getRateId());
		Rate updatedRate = ratePermission.updateRate(rate);
		if(updatedRate != null) {
			Surcharge surcharge = restTemplate.getForObject(SURCHARGE_URI, Surcharge.class);
			updatedRate.setSurcharge(surcharge);
		}
		return updatedRate;
	}
	
	@DeleteMapping("/rates/{rateId}")
	public void deleteById(@PathVariable("rateId") Long rateId) throws Exception {
		logger.info("Deleting rate {}", rateId);
		ratePermission.deleteRate(rateId);
		logger.info("rateId {} deleted successfully.", rateId);
	}

}
