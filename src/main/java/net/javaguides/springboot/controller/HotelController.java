package net.javaguides.springboot.controller;

import java.util.List;
import java.util.Optional;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Season;
import net.javaguides.springboot.model.Service;
import net.javaguides.springboot.payload.request.BookRequest;
import net.javaguides.springboot.payload.request.HotelRequest;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.javaguides.springboot.model.Hotel;
import net.javaguides.springboot.repository.HotelRepository;

import javax.swing.text.html.Option;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private SeasonRepository seasonRepository;

	@PostMapping("/add/employee")
	public ResponseEntity<?> addEmployee(@Valid @RequestBody HotelRequest hotelRequest) {
		Optional<Hotel> hotelOptional = hotelRepository.findById(hotelRequest.getHotelId());
		Hotel hotel = hotelOptional.get();

		List<Employee> employees = hotel.getEmployees();
		Employee employee = new Employee(hotelRequest.getEmployeeName(), hotelRequest.getEmployeePosition(),
				hotelRequest.getEmployeeHours(), hotelRequest.getEmployeeSalary(), hotel);
		employees.add(employee);
		employeeRepository.save(employee);
		hotelRepository.save(hotel);

		return ResponseEntity.ok().body("You have added an employee!");
	}

	@PostMapping("/add/season")
	public ResponseEntity<?> addSeason(@Valid @RequestBody HotelRequest hotelRequest) {
		Optional<Hotel> hotelOptional = hotelRepository.findById(hotelRequest.getHotelId());
		Hotel hotel = hotelOptional.get();

		List<Season> seasons = hotel.getSeasons();
		Optional<Season> seasonOptional = seasonRepository.findById(hotelRequest.getSeasonId());
		Season season = seasonOptional.get();

		System.out.println(season.getName());

		seasons.add(season);
		hotelRepository.save(hotel);

		return ResponseEntity.ok().body("You have added a season!");
	}

	@PostMapping("/update/employee")
	public ResponseEntity<?> updateEmployee(@Valid @RequestBody HotelRequest hotelRequest) {
		Optional<Employee> employeeOptional = employeeRepository.findById(hotelRequest.getEmployeeId());
		Employee employee = employeeOptional.get();

		employee.setPosition(hotelRequest.getEmployeePosition());
		employee.setHours(hotelRequest.getEmployeeHours());
		employee.setSalary(hotelRequest.getEmployeeSalary());

		employeeRepository.save(employee);
		return ResponseEntity.ok().body("You have updated an employee information!");
	}

	@PostMapping("/delete/season")
	public ResponseEntity<?> deleteSeason(@Valid @RequestBody HotelRequest hotelRequest) {
		Optional<Season> seasonOptional = seasonRepository.findById(hotelRequest.getSeasonId());
		Season season = seasonOptional.get();

		season.getHotels().clear();

		List<Hotel> hotels = hotelRepository.findAll();
		for (Hotel hotel: hotels) {
			hotel.getSeasons().remove(season);
		}

		seasonRepository.delete(season);
		return ResponseEntity.ok().body("You have deleted the season!");
	}

	@GetMapping("/all/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/all/hotels")
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}
}
