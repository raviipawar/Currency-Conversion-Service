package com.learning.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.currencyconversionservice.entities.CurrencyConverterBean;
import com.learning.currencyconversionservice.entities.User;
import com.learning.currencyconversionservice.repository.CurrencyExchangeServiceProxy;
import com.learning.currencyconversionservice.repository.UserServiceProxy;
import com.learning.currencyconversionservice.service.CurrencyConversionService;

@RestController
public class CurrencyConversionController {

	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyConversionService currencyConversionService;

	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@Autowired
	private UserServiceProxy proxy1;

	@PostMapping("/add")
	public ResponseEntity<CurrencyConverterBean> createConverterData(
			@RequestBody CurrencyConverterBean converterValue) {
		CurrencyConverterBean converterValue1 = currencyConversionService.saveConverterValue(converterValue);
		return ResponseEntity.status(HttpStatus.CREATED).body(converterValue1);
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverterBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) throws NoSuchAlgorithmException, KeyManagementException {
		CurrencyConverterBean response = proxy.retrieveExchangeValue(from, to);
		return new CurrencyConverterBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()),
				Integer.parseInt(environment.getProperty("local.server.port")));
	}

	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		List<User> response = proxy1.getAllUsers();
		System.out.println(response.toString());
		return response;
	}
}
