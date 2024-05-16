package com.recycla.score.web.controller;


import com.recycla.score.domain.service.AuthenticationService;
import com.recycla.score.web.dto.dto.AuthenticationRequest;
import com.recycla.score.web.dto.dto.AuthenticationResponse;
import com.recycla.score.web.dto.dto.RegisterRequest;
import com.recycla.score.web.dto.dto.RegistrationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  // TODO : exception handler pour les erreur auth car Spring security les cast en 403
  @PostMapping("/register")
  public RegistrationToken register(@RequestBody RegisterRequest request) {
      String token = authenticationService.register(request);
      return new RegistrationToken(token);
  }

  @PostMapping("/authenticate")
  public AuthenticationResponse authenticate(
      @RequestBody AuthenticationRequest request
  ) {
      try {
          return authenticationService.authenticate(request);
      }
      catch (Exception e){
          log.error(e.getMessage());
          return null;
      }
  }


}
