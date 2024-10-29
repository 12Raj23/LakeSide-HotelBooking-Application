package com.daily.codework.lakeSide_hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daily.codework.lakeSide_hotel.model.Room;
import com.daily.codework.lakeSide_hotel.model.User;

/**
 * Author: Rajesh R
 */
public interface UserRepository extends JpaRepository<User, Long>{

	  boolean existsByEmail(String email);

	    void deleteByEmail(String email);

	   Optional<User> findByEmail(String email);

}
