package com.daily.codework.lakeSide_hotel.controller;

import java.security.interfaces.RSAKey;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daily.codework.lakeSide_hotel.exception.RoleAlreadyExistException;
import com.daily.codework.lakeSide_hotel.model.Role;
import com.daily.codework.lakeSide_hotel.model.User;
import com.daily.codework.lakeSide_hotel.service.IRoleService;

import lombok.RequiredArgsConstructor;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
	
	  private final IRoleService roleService;
	  
	  public RoleController(IRoleService roleService) {
		  this.roleService=roleService;
	  }

	    @GetMapping("/all-roles")
	    public ResponseEntity<List<Role>> getAllRoles(){
	        return new ResponseEntity<>(roleService.getRoles(),HttpStatus.FOUND);
	    }

	   // @PostMapping("/create-new-role",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    @PostMapping(value = "/create-new-role", 
        consumes = MediaType.APPLICATION_JSON_VALUE, 
        produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> createRole(@RequestBody Role theRole){
	        try{
	            roleService.createRole(theRole);
	            return ResponseEntity.ok("New role created successfully!");
	        }catch(RoleAlreadyExistException re){
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(re.getMessage());

	        }
	    }
	    @DeleteMapping("/delete/{roleId}")
	    public void deleteRole(@PathVariable("roleId") Long roleId){
	        roleService.deleteRole(roleId);
	    }
	    @PostMapping("/remove-all-users-from-role/{roleId}")
	    public Role removeAllUsersFromRole(@PathVariable("roleId") Long roleId){
	        return roleService.removeAllUsersFromRole(roleId);
	    }

	    @PostMapping("/remove-user-from-role")
	    public User removeUserFromRole(
	            @RequestParam("userId") Long userId,
	            @RequestParam("roleId") Long roleId){
	        return roleService.removeUserFromRole(userId, roleId);
	    }
	    @PostMapping("/assign-user-to-role")
	    public User assignUserToRole(
	            @RequestParam("userId") Long userId,
	            @RequestParam("roleId") Long roleId){
	        return roleService.assignRoleToUser(userId, roleId);
	    }
	}
