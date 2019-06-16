package io.sisa.api.v1.controller;

import io.sisa.core.model.domain.AppUser;
import io.sisa.core.security.JwtTokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author isaozturk
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {


	private final UserDetailsService userDetailsService;

	private final JwtTokenHelper jwtTokenHelper;

	public UserController(@Qualifier UserDetailsService userDetailsService, JwtTokenHelper jwtTokenHelper) {
		this.userDetailsService = userDetailsService;
		this.jwtTokenHelper = jwtTokenHelper;
	}


	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AppUser user) {
		try {

			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
			if (userDetails.getPassword().equals(user.getPassword())) {
				String jwt = jwtTokenHelper.generateToken(userDetails);
				return new ResponseEntity<>(jwt, HttpStatus.OK);
			}

		} catch (UsernameNotFoundException e) {
			log.error(e.getMessage(), e);
		}


		return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);

	}

}
