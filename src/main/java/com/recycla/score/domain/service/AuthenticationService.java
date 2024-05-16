package com.recycla.score.domain.service;


import com.recycla.score.configuration.JwtService;
import com.recycla.score.domain.entity.user.Role;
import com.recycla.score.domain.entity.user.User;
import com.recycla.score.domain.repository.UserRepository;
import com.recycla.score.web.dto.dto.AuthenticationRequest;
import com.recycla.score.web.dto.dto.AuthenticationResponse;
import com.recycla.score.web.dto.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) {
        Optional<User> userExists = userRepository.findByEmail(request.getEmail());


        if (userExists.isPresent()) {
            log.warn("email already used");
            throw new IllegalStateException("Un compte existe déjà avec cet email");
        }

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        log.info("user saved in db");
        return user.getEmail();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("authentification initiated");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Utilisateur introuvable"));

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
