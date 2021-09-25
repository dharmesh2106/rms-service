package com.rms.permission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.rms.model.Rate;
import com.rms.service.RateService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RatePermissionTests {

	@MockBean
	private RateService rateService;

	@Autowired
	private RatePermission ratePermission;

	@BeforeEach
	public void init() throws Exception {
		Calendar calendar = Calendar.getInstance(); 
		Rate rate = new Rate();
		rate.setRateId(1L);
		rate.setRateDescription("Rate of item.");
		rate.setRateEffectiveDate(calendar.getTime());
		calendar.add(Calendar.MONTH, 1);
		rate.setRateExpirationDate(calendar.getTime());
		rate.setAmount(10);
		when(rateService.findById(Mockito.any(Long.class))).thenReturn(rate);
		when(rateService.createRate(Mockito.any(Rate.class))).thenReturn(rate);
		when(rateService.updateRate(Mockito.any(Rate.class))).thenReturn(rate);
		doNothing().when(rateService).deleteRate(Mockito.any(Long.class));
	}

	@Test
	void findById() throws Exception {

		Rate rate = ratePermission.findById(1L);
		assertNotNull(rate);
		assertEquals(1L, rate.getRateId());
	}

	@Test
	void createRate() throws Exception {
		Calendar calendar = Calendar.getInstance(); 
		Rate rate = new Rate();
		rate.setRateId(1L);
		rate.setRateDescription("Rate of item.");
		rate.setRateEffectiveDate(calendar.getTime());
		calendar.add(Calendar.MONTH, 1);
		rate.setRateExpirationDate(calendar.getTime());
		rate.setAmount(10);
		Rate createdRate = ratePermission.createRate(rate);
		assertNotNull(createdRate);
		assertEquals(1L, createdRate.getRateId());
	}

	@Test
	void updateRate() throws Exception {
		Calendar calendar = Calendar.getInstance();    
		Rate rate = new Rate();
		rate.setRateId(1L);
		rate.setRateDescription("Rate of item.");
		rate.setRateEffectiveDate(calendar.getTime());
		calendar.add(Calendar.MONTH, 1);
		rate.setRateExpirationDate(calendar.getTime());
		rate.setAmount(10);
		Rate createdRate = ratePermission.updateRate(rate);
		assertNotNull(createdRate);
		assertEquals(1L, createdRate.getRateId());
	}

	@Test
	void deleteRate() throws Exception {

		rateService.deleteRate(1L);
	}

}
