package com.sunbase.assignment.repo;


import com.sunbase.assignment.dto.UserDto;
import com.sunbase.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    static User save(UserDto user) {
        return null;
    }

    User findByEmail(String email);

}
