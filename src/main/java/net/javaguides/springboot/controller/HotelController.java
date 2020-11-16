package net.javaguides.springboot.controller;

import java.util.List;
import java.util.Optional;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.payload.request.BookRequest;
import net.javaguides.springboot.payload.request.HotelRequest;
import net.javaguides.springboot.repository.EmployeeRepository;
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

	@PostMapping("/add/employee")
	public ResponseEntity<?> book(@Valid @RequestBody HotelRequest hotelRequest) {
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

	@GetMapping("/all/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/all/hotels")
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}
}
