package com.cognizant.ormlearn;

import org.springframework.boot.SpringApplication;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	private static CountryService countryService;
	
	public static void main(String[] args) throws CountryNotFoundException  {
		LOGGER.info("Inside main");

		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

		countryService = context.getBean(CountryService.class);
		testGetAllCountries();
		getAllCountriesTest();
		testAddCountry();
		updateCountryTest();
		deleteCountryTest();
	}
	
	private static void testGetAllCountries() {

		LOGGER.info("Start");

		List<Country> countries = countryService.getAllCountries();

		LOGGER.debug("countries={}", countries);

		LOGGER.info("End");

	}
	
	private static void getAllCountriesTest() throws CountryNotFoundException {

		LOGGER.info("Start");

		Country country = countryService.findCountryByCode("IN");

		LOGGER.debug("Country:{}", country);

		LOGGER.info("End");

	}
	
	
	private static void testAddCountry() throws CountryNotFoundException {
		LOGGER.info("Start");

		Country testCountry = new Country();
		testCountry.setCode("AA");
		testCountry.setName("Test");
		countryService.addCountry(testCountry);
		Country country = countryService.findCountryByCode("AA");

		LOGGER.debug("Country:{}", country);

		LOGGER.info("End");
	}
	
	private static void updateCountryTest() throws CountryNotFoundException {

		LOGGER.info("Start");

		countryService.updateCountry("AA", "Trial");

		LOGGER.info("End");

	}
	
	private static void deleteCountryTest() throws CountryNotFoundException {
		LOGGER.info("Start");

		countryService.deleteCountry("AA");

		LOGGER.info("End");

	}

}
