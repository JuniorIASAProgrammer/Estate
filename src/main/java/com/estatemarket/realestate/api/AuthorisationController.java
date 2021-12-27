package com.estatemarket.realestate.api;

import com.estatemarket.realestate.api.dto.UserDto;
import com.estatemarket.realestate.service.AuthorisationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("EstateMarketplace")
public class AuthorisationController {
    private final AuthorisationService authorisationService;

    @PostMapping("/signup")
    public ResponseEntity<String> create(@RequestBody UserDto user) {
        try {
            long id = authorisationService.singup(user);
            String location = String.format("/user/%d", id);
            return ResponseEntity.created(URI.create(location)).build();
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
