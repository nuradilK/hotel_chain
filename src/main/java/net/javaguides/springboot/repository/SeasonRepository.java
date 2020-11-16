package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Season;
import org.hibernate.cfg.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository <Season, Long> {
}
