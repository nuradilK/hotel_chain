package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.RoomType;
import net.javaguides.springboot.repository.RoomTypeRepository;

@RestController
@RequestMapping("/api/roomtypes")
public class RoomTypeController {
	
	@Autowired
	private RoomTypeRepository roomTypeRepository;

	@GetMapping
	public List<RoomType> getAllRooms() {
		return roomTypeRepository.findAll();
	}
}
