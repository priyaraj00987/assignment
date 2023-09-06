package com.sunbase.assignment.repo;
import com.sunbase.assignment.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long> {

    com.sunbase.assignment.entity.Role findByName(String name);
}
