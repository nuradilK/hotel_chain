package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Hotel;
import net.javaguides.springboot.repository.HotelRepository;

@RestController
@RequestMapping("/api")
public class HotelController {

	@Autowired
	private HotelRepository hotelRepository;

	@GetMapping("/hotels")
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}
}
