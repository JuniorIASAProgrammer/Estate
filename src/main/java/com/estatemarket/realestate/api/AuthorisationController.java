package com.estatemarket.realestate.api;

import com.estatemarket.realestate.api.dto.UserDto;
import com.estatemarket.realestate.service.AuthorisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("EstateMarketplace")
public class AuthorisationController {
    private final AuthorisationService authorisationService;

//    @GetMapping("/login")
//    public ResponseEntity<Object> find(@RequestBody UserDto user){
//        try {
//            authorisationService.login(user);
//            return ResponseEntity.ok().build();
//        }
//        catch (IllegalArgumentException e){
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PostMapping("/signup")
    public ResponseEntity<Void> create(@RequestBody UserDto user) {
        long id = authorisationService.singup(user);
        String location = String.format("/user/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }
}
