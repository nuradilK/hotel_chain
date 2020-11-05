package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.javaguides.springboot.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	
}
