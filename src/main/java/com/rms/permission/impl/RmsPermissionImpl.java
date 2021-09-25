package com.rms.permission.impl;

import java.util.ArrayList;
import java.util.List;

import com.rms.model.Rate;
import com.rms.utils.CommonValues;

public abstract class RmsPermissionImpl {
	
	protected List<String> validateRate(Rate rate)
			throws Exception {
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
		return errors;
	}

}
