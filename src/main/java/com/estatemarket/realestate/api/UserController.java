package com.estatemarket.realestate.api;

import com.estatemarket.realestate.repo.model.User;
import com.estatemarket.realestate.api.dto.UserDto;
import com.estatemarket.realestate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByEmail/email={email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        try {
            final User user = userService.getByEmail(email);
            return ResponseEntity.ok(user);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<String> update(@RequestBody UserDto user) {
        try{
            userService.update(user);
            return new ResponseEntity<>("User info updated", HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/block/userId={id}")
    public ResponseEntity<String> block(@PathVariable long id) {
        try {
            userService.blockById(id);
            return new ResponseEntity<>("User blocked", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @PatchMapping("/unblock/userId={id}")
    public ResponseEntity<String> unblock(@PathVariable long id) {
        try {
            userService.unblockById(id);
            return new ResponseEntity<>("User unblocked", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(){
        userService.delete();
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }
}
