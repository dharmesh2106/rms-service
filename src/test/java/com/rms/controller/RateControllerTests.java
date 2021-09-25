package com.rms.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rms.model.Rate;
import com.rms.permission.RatePermission;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RateController.class)
//@SpringBootTest
class RateControllerTests {
    
	@MockBean
    RatePermission ratePermission;
    
	@Autowired
    private MockMvc mockMvc;

	@Test
	public void findRateById() throws Exception {
		Calendar calendar = Calendar.getInstance();
		Rate rate = new Rate();
		rate.setRateId(1L);
		rate.setRateDescription("Rate of item.");
		rate.setRateEffectiveDate(calendar.getTime());
		calendar.add(Calendar.MONTH, 1);
		rate.setRateExpirationDate(calendar.getTime());
		rate.setAmount(10);
		
		Mockito.when(ratePermission.findById(1L)).thenReturn(rate);
		
		mockMvc.perform(get("/rms/v1/rates/{rateId}",1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("rateId", Matchers.is(1)));
	}
	
	@Test
	public void saveRate() throws Exception {
		Calendar calendar = Calendar.getInstance();
		Rate rate = new Rate();
		rate.setRateId(1L);
		rate.setRateDescription("Rate of item.");
		rate.setRateEffectiveDate(calendar.getTime());
		calendar.add(Calendar.MONTH, 1);
		rate.setRateExpirationDate(calendar.getTime());
		rate.setAmount(10);
		Mockito.when(ratePermission.createRate(rate)).thenReturn(rate);
		
		mockMvc.perform( MockMvcRequestBuilders
			      .post("/rms/v1/saverate")
			      .content(asJsonString(rate))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.rateId").exists());
	}
	
	@Test
	public void updaterate() throws Exception {
		Calendar calendar = Calendar.getInstance();
		Rate rate = new Rate();
		rate.setRateId(1L);
		rate.setRateDescription("Rate of item.");
		rate.setRateEffectiveDate(calendar.getTime());
		calendar.add(Calendar.MONTH, 1);
		rate.setRateExpirationDate(calendar.getTime());
		rate.setAmount(10);
		Mockito.when(ratePermission.updateRate(rate)).thenReturn(rate);
		
		mockMvc.perform( MockMvcRequestBuilders
			      .put("/rms/v1/updaterate")
			      .content(asJsonString(rate))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.rateId").exists());
	}
	
	@Test
	public void deleteById() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders
	            .delete("/rms/v1/rates/{rateId}", "1")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
