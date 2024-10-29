
package com.daily.codework.lakeSide_hotel.service;


import com.daily.codework.lakeSide_hotel.exception.ResourceNotFoundException;
import com.daily.codework.lakeSide_hotel.exception.UserAlreadyExistsException;
import com.daily.codework.lakeSide_hotel.model.Role;
import com.daily.codework.lakeSide_hotel.model.User;
import com.daily.codework.lakeSide_hotel.repository.RoleRepository;
import com.daily.codework.lakeSide_hotel.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Rajesh R
 */

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    
    
    @Autowired
	public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,RoleRepository roleRepository) {
		this.userRepository=userRepository;
		this.passwordEncoder=passwordEncoder;
		this.roleRepository=roleRepository;
	}
    
    


    
    @Override
    public User registerUser(User user) {
        // Check if the user already exists by email
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
        }
        
        // Encrypt the user's password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        
        // Find the ROLE_USER, throw exception if not found
        Role userRole = roleRepository.findByName("ROLE_USER")
                         .orElseThrow(() -> new ResourceNotFoundException("Role 'ROLE_USER' not found"));

        // Set the role to the user
        user.setRoles(Collections.singletonList(userRole));

        // Save and return the registered user
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteUser(String email) {
        User theUser = getUser(email);
        if (theUser != null){
            userRepository.deleteByEmail(email);
        }

    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
