package com.recycla.score.service;

import com.recycla.score.configuration.JwtService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JwtServiceTest {
    @Test
    public void testExtractUsername() {
        JwtService jwtService = new JwtService();
        String jwt = "eyJ";
        String result = jwtService.extractUsername(jwt);
        assertEquals("  ", result);
    }
}
