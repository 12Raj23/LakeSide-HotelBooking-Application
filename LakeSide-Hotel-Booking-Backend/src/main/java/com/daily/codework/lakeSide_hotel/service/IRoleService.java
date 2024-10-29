package com.daily.codework.lakeSide_hotel.service;

import java.util.List;

import com.daily.codework.lakeSide_hotel.model.Role;
import com.daily.codework.lakeSide_hotel.model.User;

public interface IRoleService {
	List<Role>getRoles();
	Role createRole(Role theRole);
	void deleteRole(Long id);
	Role findByName(String name);
	
	User removeUserFromRole(Long userId,Long roleId);
	User assignRoleToUser(Long userId,Long roleId);
	Role removeAllUsersFromRole(Long userId,Long roleId);
	Role removeAllUsersFromRole(Long roleId);

}
