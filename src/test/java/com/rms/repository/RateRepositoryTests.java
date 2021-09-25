package com.rms.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.rms.entity.RateEntity;

@DataJpaTest
@Rollback(false)
@ExtendWith(MockitoExtension.class)
class RateRepositoryTests {
	
    @Autowired
    private RateRepository rateRepository;
	
	@BeforeEach
    public void init() {
       
		Calendar calendar = Calendar.getInstance(); 
		RateEntity rateEntity = new RateEntity();
		rateEntity.setRateId(1L);
		rateEntity.setRateDescription("Rate of item.");
		rateEntity.setRateEffectiveDate(calendar.getTime());
		calendar.add(Calendar.MONTH, 1);
		rateEntity.setRateExpirationDate(calendar.getTime());
		rateEntity.setAmount(10);
		rateRepository.save(rateEntity);

    }

	@Test
	@Order(value = 1)
	void testSaveRate(){
		Calendar calendar = Calendar.getInstance(); 
		RateEntity rateEntity = new RateEntity();
		rateEntity.setRateDescription("Rate of item.");
		rateEntity.setRateEffectiveDate(calendar.getTime());
		calendar.add(Calendar.MONTH, 1);
		rateEntity.setRateExpirationDate(calendar.getTime());
		rateEntity.setAmount(10);
		RateEntity savedRate = rateRepository.save(rateEntity);
		assertNotNull(savedRate);
		assertThat(savedRate.getRateId()).isGreaterThan(0);
	}
	
	@Test
	@Order(value = 2)
	void testFindByRateId(){
		Optional<RateEntity> foundRates = rateRepository.findById(1L);
		RateEntity foundRate = null;
		if(foundRates.isPresent()) {
			foundRate =foundRates.get();
		}
		assertNotNull(foundRate);
		assertEquals(1L, foundRate.getRateId());
	}
	
	@Test
	@Order(value = 3)
	void testDeleteById(){
		rateRepository.deleteById(1L);
	}
	
	

	

}
