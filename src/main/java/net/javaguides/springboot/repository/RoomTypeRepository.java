package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.javaguides.springboot.model.RoomType;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

}
