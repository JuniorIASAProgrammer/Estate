package com.estatemarket.realestate.exceptions.api;

import com.estatemarket.realestate.exceptions.api.dto.UserDto;
import com.estatemarket.realestate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<com.estatemarket.realestate.repo.model.User>> index(){
        final List<com.estatemarket.realestate.repo.model.User> user = userService.fetchAll();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.estatemarket.realestate.repo.model.User> show(@PathVariable long id) {
        try {
            final com.estatemarket.realestate.repo.model.User user = userService.fetchById(id);
            return ResponseEntity.ok(user);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDto user) {
        final String name = user.getName();
        final String surname = user.getSurname();
        final String email = user.getEmail();
        final String password = user.getPassword();
        final String phone = user.getPhone();
        final String role = user.getRole();
        final long id = userService.create(name, surname, email, password, phone);
        final String location = String.format("/users/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody UserDto user) {
        final String name = user.getName();
        final String surname = user.getSurname();
        final String email = user.getEmail();
        final String password = user.getPassword();
        final String phone = user.getPhone();

        try{
            userService.update(id, name, surname, email, password, phone);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
