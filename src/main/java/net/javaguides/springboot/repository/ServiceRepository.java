package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Book;
import net.javaguides.springboot.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

}
