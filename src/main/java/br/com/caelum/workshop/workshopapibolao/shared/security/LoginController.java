package br.com.caelum.workshop.workshopapibolao.shared.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	private AuthenticationManager authManager;

	@PostMapping(value = "/api/public/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> autentica(@RequestBody @Valid LoginDTO loginDTO) {
		
		Authentication authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getLogin(),
				loginDTO.getPassword());
		
		try {
			Authentication authentication = authManager.authenticate(authenticationToken);	
			SystemUserDetails details = (SystemUserDetails) authentication.getPrincipal();
			return ResponseEntity.ok(new LoginResponseDto(details));
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}


	}
}
