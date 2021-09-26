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

import com.rms.model.Rate;
import com.rms.permission.RatePermission;

@RestController
@RequestMapping("/rms/v1")
public class RateController {
	
	private static final Logger logger = LoggerFactory.getLogger(RateController.class);
	
	@Autowired
	private RatePermission ratePermission;
	
	@GetMapping("/rates/{rateId}")
	public Rate findRateById(@PathVariable("rateId") Long rateId) throws Exception {
		logger.info("Called RateController.findRateById with {}", rateId);
		return ratePermission.findById(rateId);
	}
	
	@PostMapping("/saverate")
	public Rate saveRate(@RequestBody Rate rate) throws Exception {
		logger.info("Creating rate {}", rate.getRateDescription());
		return ratePermission.createRate(rate);
	}
	
	@PutMapping("/updaterate")
	public Rate updateRate(@RequestBody Rate rate) throws Exception {
		logger.info("Updating rate {}", rate.getRateId());
		return ratePermission.updateRate(rate);
	}
	
	@DeleteMapping("/rates/{rateId}")
	public void deleteById(@PathVariable("rateId") Long rateId) throws Exception {
		logger.info("Deleting rate {}", rateId);
		ratePermission.deleteRate(rateId);
		logger.info("rateId {} deleted successfully.", rateId);
	}

}
