package com.daily.codework.lakeSide_hotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daily.codework.lakeSide_hotel.model.Role;
import com.daily.codework.lakeSide_hotel.model.User;

public interface RoleRepository extends JpaRepository<Role,Long> {

	 Optional<Role> findByName(String role);


	    boolean existsByName(String role);
	}
