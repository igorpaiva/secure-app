package com.springsecured.controller;

import com.springsecured.dto.ResponseDTO;
import com.springsecured.dto.CurrentUserDTO;
import com.springsecured.session.InMemorySessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    public InMemorySessionRegistry inMemorySessionRegistry;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody CurrentUserDTO currentUserDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(currentUserDTO.getUsername(), currentUserDTO.getPassword())
        );
        final String sessionId = inMemorySessionRegistry.registerSession(currentUserDTO.getUsername());
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSessionId(sessionId);

        return ResponseEntity.ok(responseDTO);
    }

}
