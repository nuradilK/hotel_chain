package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

