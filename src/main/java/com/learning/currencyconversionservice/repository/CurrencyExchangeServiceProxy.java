package com.learning.currencyconversionservice.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.currencyconversionservice.entities.CurrencyConverterBean;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8082")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeServiceProxy {

	@GetMapping(value= "/currency-exchange/from/{from}/to/{to}")
	public CurrencyConverterBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to);

}
