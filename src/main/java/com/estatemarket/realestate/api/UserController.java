package com.estatemarket.realestate.api;

import com.estatemarket.realestate.repo.model.User;
import com.estatemarket.realestate.api.dto.UserDto;
import com.estatemarket.realestate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("EstateMarketplace/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll(){
        final List<User> userList = userService.getAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/getById/id={id}")
    public ResponseEntity<User> getById(@PathVariable long id) {
        try {
            final User user = userService.getById(id);
            return ResponseEntity.ok(user);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/getByEmail/email={email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        try {
            final User user = userService.getByEmail(email);
            return ResponseEntity.ok(user);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.noContent().build();
        }
    }

    @PatchMapping("/update/id={id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody UserDto user) {
        try{
            userService.update(id, user);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/block/userId={id}")
    public ResponseEntity<Void> block(@PathVariable long id) {
        userService.blockById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/unblock/userId={id}")
    public ResponseEntity<Void> unblock(@PathVariable long id) {
        userService.unblockById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/userId={id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
