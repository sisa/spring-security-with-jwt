package io.sisa.api.controller;

import io.sisa.core.model.domain.AppUser;
import io.sisa.core.security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author isaozturk
 */

@RestController
@RequestMapping("/auth")
public class UserController {


	private final UserDetailsService userDetailsService;
	private final JwtTokenHelper jwtTokenHelper;

	@Autowired
	public UserController(UserDetailsService userDetailsService, JwtTokenHelper jwtTokenHelper) {
		this.userDetailsService = userDetailsService;
		this.jwtTokenHelper = jwtTokenHelper;
	}


	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AppUser user , Device device) {

		UserDetails userDetails=  userDetailsService.loadUserByUsername(user.getUsername());
		if (userDetails.getPassword().equals(user.getPassword())){
			String jwt= jwtTokenHelper.generateToken(userDetails, device);
			return new ResponseEntity<>(jwt, HttpStatus.OK);
		}

		return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);

	}

}
