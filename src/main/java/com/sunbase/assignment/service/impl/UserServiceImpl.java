package com.sunbase.assignment.service.impl;

import com.sunbase.assignment.dto.UserDto;
import com.sunbase.assignment.entity.Role;
import com.sunbase.assignment.entity.User;
import com.sunbase.assignment.repo.RoleRepository;
import com.sunbase.assignment.repo.UserRepository;
import com.sunbase.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.AbstractSingleValueEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        UserRepository.save((UserDto) user);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }


    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    @Override
    public User updateUser(User user, Long userId) {
        UserDto userDto = new UserDto();
        UserDto depDB =  userRepository.findById(userId).get();

        if (Objects.nonNull(userDto.getFirstName()) && !"".equalsIgnoreCase(userDto.getLastName())) {
            depDB.setFirstName(userDto.getFirstName());
        }

        if (Objects.nonNull(userDto.getAddress()) && !"".equalsIgnoreCase(userDto.getAddress())) {
            depDB.setFirstName(userDto.getAddress());
        }

        if (Objects.nonNull(userDto.getCity()) && !"".equalsIgnoreCase(userDto.getCity())) {
            depDB.setFirstName(userDto.getCity());
        }
        if (Objects.nonNull(userDto.getState()) && !"".equalsIgnoreCase(userDto.getState())) {
            depDB.setFirstName(userDto.getState());
        }

        return UserRepository.save(depDB);
    }



    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser(long id) {

    }
}

