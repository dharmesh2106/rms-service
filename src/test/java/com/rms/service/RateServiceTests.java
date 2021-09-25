package com.rms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.rms.entity.RateEntity;
import com.rms.model.Rate;
import com.rms.repository.RateRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RateServiceTests {
	
	@MockBean
	private RateRepository rateRepository;
	
	@Autowired  
	private RateService rateService; 
	
	@BeforeEach
    public void init() {
       
		RateEntity rateEntity = new RateEntity();
		rateEntity.setRateId(1L);
		rateEntity.setRateDescription("Rate of item.");
		Optional<RateEntity> rates = Optional.of(rateEntity) ;
		when(rateRepository.findById(1L)).thenReturn(rates);

    }

	@Test
	void findById() throws Exception {
		
		Rate rate = rateService.findById(1L);
		assertNotNull(rate);
		assertEquals(1L, rate.getRateId());
	}
	
	@Test
	void createRate(){
		RateEntity rateEntity = new RateEntity();
		rateEntity.setRateId(1L);
		rateEntity.setRateDescription("Rate of item.");
		
		Rate rate = new Rate();
		rate.setRateId(1L);
		rate.setRateDescription("Rate of item.");
		
		when(rateRepository.save(rateEntity)).thenReturn(rateEntity);
		Rate createdRate = rateService.createRate(rate);
		assertNotNull(createdRate);
		assertEquals(1L, createdRate.getRateId());
	}
	
	
	@Test
	void updateRate() throws Exception{
		RateEntity rateEntity = new RateEntity();
		rateEntity.setRateId(1L);
		rateEntity.setRateDescription("Rate of item.");
		
		Rate rate = new Rate();
		rate.setRateId(1L);
		rate.setRateDescription("Rate of item.");
		
		when(rateRepository.save(rateEntity)).thenReturn(rateEntity);
		Rate createdRate = rateService.updateRate(rate);
		assertNotNull(createdRate);
		assertEquals(1L, createdRate.getRateId());
	}
	
	@Test
	void deleteRate() throws Exception{
		
		doNothing().when(rateRepository).deleteById(1L);
		rateService.deleteRate(1L);
	}
	

}
