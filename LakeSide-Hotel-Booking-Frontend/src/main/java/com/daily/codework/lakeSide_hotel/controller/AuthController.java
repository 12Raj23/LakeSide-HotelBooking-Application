
package com.daily.codework.lakeSide_hotel.controller;

import com.daily.codework.lakeSide_hotel.exception.UserAlreadyExistsException;
import com.daily.codework.lakeSide_hotel.model.User;
import com.daily.codework.lakeSide_hotel.request.LoginRequest;
import com.daily.codework.lakeSide_hotel.response.JwtResponse;
import com.daily.codework.lakeSide_hotel.security.jwt.JwtUtils;
import com.daily.codework.lakeSide_hotel.security.user.HotelUserDetails;
import com.daily.codework.lakeSide_hotel.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Author: Rajesh R
 */
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    
    public AuthController(IUserService userService,AuthenticationManager authenticationManager,JwtUtils jwtUtils) {
    	this.userService=userService;
    	this.authenticationManager=authenticationManager;
    	this.jwtUtils=jwtUtils;
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        try{
            userService.registerUser(user);
            return ResponseEntity.ok("Registration successful!");

        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }



    
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        try {
            // Perform authentication
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String jwt = jwtUtils.generateJwtTokenForUser(authentication);

            // Retrieve user details
            HotelUserDetails userDetails = (HotelUserDetails) authentication.getPrincipal();

            // Extract roles from the user's authorities
            List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

            // Build and return the JWT response
            return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                userDetails.getEmail(),
                jwt,
                roles));

        } catch (BadCredentialsException e) {
            // Handle invalid email/password
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();  // Add logging here for detailed error information
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during authentication.");
        }
    }


}
