package com.learning.currencyconversionservice.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.currencyconversionservice.entities.CurrencyConverterBean;
import com.learning.currencyconversionservice.repository.CurrencyConversionRepo;

@Service
public class CurrencyConversionService {

	@Autowired
	private CurrencyConversionRepo conversionRepo;

	public CurrencyConverterBean findByFromAndToAndQuantity(String from, String to, BigDecimal quantity) {
		return conversionRepo.findByFromAndToAndQuantity(from, to, quantity);
	}

	public CurrencyConverterBean saveConverterValue(CurrencyConverterBean converterValue) {
		String randomHotelId= UUID.randomUUID().toString();
		converterValue.setId(randomHotelId);
		return conversionRepo.save(converterValue);
	}
}
