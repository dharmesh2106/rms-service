package com.rms.permission.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rms.model.Rate;
import com.rms.utils.CommonValues;

public abstract class RmsPermissionImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(RmsPermissionImpl.class);
	
	protected List<String> validateRate(Rate rate)
			throws Exception {
		
		logger.info("Start validating rate {}.", rate.getRateId());
		List<String> errors = new ArrayList<String>();
		if (rate.getRateEffectiveDate()  == null) {
			errors.add(String.format(CommonValues.FIELD_REQUIRED, CommonValues.RATE_EFFECTIVE_DATE));
		}
		if (rate.getRateExpirationDate()  == null) {
			errors.add(String.format(CommonValues.FIELD_REQUIRED, CommonValues.RATE_EXPIRATION_DATE));
		}
		if (rate.getAmount()  == null) {
			errors.add(String.format(CommonValues.FIELD_REQUIRED, CommonValues.RATE_AMOUNT));
		}
		if (rate.getRateEffectiveDate() != null && rate.getRateExpirationDate() != null && rate.getRateEffectiveDate().after(rate.getRateExpirationDate())  == true) {
			errors.add(CommonValues.EFFECTIVE_DATE_SHOULD_BEFOR_EXPIREDATE);
		}
		logger.info("Validating rate {} completed.", rate.getRateId());
		return errors;
	}

}
