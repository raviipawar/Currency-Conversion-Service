package com.learning.currencyconversionservice.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.currencyconversionservice.entities.CurrencyConverterBean;

public interface CurrencyConversionRepo extends JpaRepository<CurrencyConverterBean, String> {

	CurrencyConverterBean findByFromAndToAndQuantity(String from, String to, BigDecimal quantity);
	

}
