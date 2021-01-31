package com.cognizant.ormlearn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Attempt;
import com.cognizant.ormlearn.model.AttemptOption;
import com.cognizant.ormlearn.model.AttemptQuestion;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.AttemptService;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	private static CountryService countryService;
	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static SkillService skillService;
	private static AttemptService attemptService;

	@Autowired
	static StockRepository stockRepository;

	public static void main(String[] args) throws CountryNotFoundException, ParseException {
		LOGGER.info("Inside main");

		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

		// country
		countryService = context.getBean(CountryService.class);
		testGetAllCountries();
		getAllCountriesTest();
		testAddCountry();
		updateCountryTest();
		deleteCountryTest();
		testFindCountryByCharacters();
		testFindCountryStartingByCharacter();

		// stock
		stockRepository = context.getBean(StockRepository.class);
		testGetFbSep2019();
		testFindByCodeAndCloseGreterThan();
		testfindTop3OrderByVolumeDesc();
		testFindTop3ByCodeOrderByClose();

		//Employee
		testGetEmployee();
		testAddEmployee();
		testUpdateEmployee();
		testGetDepartment();
		testAddSkillToEmployee();
		testGetAllPermanentEmployees();
		testGetAverageSalary();
		testGetAllEmployeesNative();
		
		//Quiz
		testGetAttemptDetail();
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

	private static void testFindCountryByCharacters() throws CountryNotFoundException {
		LOGGER.info("Start");
		List<Country> countries = countryService.findCountryByCharacters("ou");
		LOGGER.debug("Country:{}", countries);
		LOGGER.info("End");
	}

	private static void testFindCountryStartingByCharacter() throws CountryNotFoundException {
		LOGGER.info("Start");
		List<Country> countries = countryService.findCountryStartingByCharacter("Z");
		LOGGER.debug("Country:{}", countries);
		LOGGER.info("End");
	}

	private static void testGetFbSep2019() {
		LOGGER.info("Start");
		List<Stock> stocks = stockRepository.findByCodeAndDateLike("FB", "2019-9%");
		LOGGER.debug("Stock:{}", stocks);
		LOGGER.info("End");
	}

	private static void testFindByCodeAndCloseGreterThan() {
		LOGGER.info("Start");
		List<Stock> stocks = stockRepository.findByCodeAndCloseGreaterThan("GOOGL", BigDecimal.valueOf(1250));
		LOGGER.debug("Stock:{}", stocks);
		LOGGER.info("End");
	}

	private static void testfindTop3OrderByVolumeDesc() {
		LOGGER.info("Start");
		List<Stock> stocks = stockRepository.findTop3ByOrderByVolumeDesc();
		LOGGER.debug("Stock:{}", stocks);
		LOGGER.info("End");
	}

	private static void testFindTop3ByCodeOrderByClose() {
		LOGGER.info("Start");
		List<Stock> stocks = stockRepository.findTop3ByCodeOrderByClose("NFLX");
		LOGGER.debug("Stock:{}", stocks);
		LOGGER.info("End");
	}
	
	private static void testGetEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Department:{}", employee.getDepartment());
		LOGGER.debug("Skills:{}", employee.getSkillList());
		LOGGER.info("End");
	}

	private static void testAddEmployee() throws ParseException {
		LOGGER.info("Start");
		Employee employee = new Employee();
		employee.setName("Alice");
		employee.setSalary(100000);
		employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1998-09-23"));
		employee.setDepartment(departmentService.get(1));
		employee.setPermanent(false);
		employeeService.save(employee);
		Employee savedEmployee = employeeService.get(2);
		LOGGER.debug("Saved Employee:{}", savedEmployee);
		LOGGER.info("End");

	}

	private static void testUpdateEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(1);
		Department department = departmentService.get(3);
		employee.setDepartment(department);
		employeeService.save(employee);
		Employee savedEmployee = employeeService.get(1);
		LOGGER.debug("Saved Employee:{}", savedEmployee);
		LOGGER.info("End");
	}

	private static void testGetDepartment() {
		LOGGER.info("Start");
		Department department = departmentService.get(2);
		Set<Employee> employeeList = department.getEmployeeList();
		LOGGER.debug("Saved Employee:{}", employeeList);
		LOGGER.info("End");
	}

	private static void testAddSkillToEmployee() {
		LOGGER.info("Start");
		Employee employee = employeeService.get(4);
		Skill skill = skillService.get(7);
		Set<Skill> employeeSkills = employee.getSkillList();
		employeeSkills.add(skill);
		employeeService.save(employee);
		Employee savedEmployee = employeeService.get(4);
		LOGGER.debug("Saved Employee:{}", savedEmployee);
		LOGGER.debug("Skills:{}", savedEmployee.getSkillList());
		LOGGER.info("End");
	}

	public static void testGetAllPermanentEmployees() {
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllPermanentEmployees();
		LOGGER.debug("Permanent Employees:{}", employees);
		employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
		LOGGER.info("End");
	}

	public static void testGetAverageSalary() {
		LOGGER.info("Start");
		LOGGER.debug("Average Salary:{}", employeeService.getAverageSalary(2));
		LOGGER.info("End");
	}

	public static void testGetAllEmployeesNative() {
		LOGGER.info("Start");
		List<Employee> employees = employeeService.getAllEmployeesNative();
		LOGGER.debug("Employees:{}", employees);
		LOGGER.info("End");
	}

	public static void testGetAttemptDetail() {
		LOGGER.info("Start");
		Attempt attempt = attemptService.getAttempt(1, 1);
		LOGGER.debug("Attempt:{}", attempt);
		for (AttemptQuestion attemptQuestion : attempt.getAttemptQuestions()) {
			System.out.println(attemptQuestion.getQuestion().getText());
			int no = 1;
			for (AttemptOption attemptOption : attemptQuestion.getAttemptOptions()) {
				System.out.println(no + ") " + attemptOption.getOption().getText() + " "
						+ attemptOption.getOption().getScore() + " " + attemptOption.isSelected());
				no++;
			}
		}
		LOGGER.info("End");

	}

}
