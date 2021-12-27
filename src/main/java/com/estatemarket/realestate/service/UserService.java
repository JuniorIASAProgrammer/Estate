package com.estatemarket.realestate.service;

import com.estatemarket.realestate.api.dto.UserDto;
import com.estatemarket.realestate.api.enums.BanEnum;
import com.estatemarket.realestate.repo.model.User;
import com.estatemarket.realestate.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public final class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public User getById(long id) throws IllegalArgumentException{
        final Optional<User> maybeUser = userRepo.findOneById(id);
        if (maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
        else return maybeUser.get();
    }

    public User getByEmail(String email) throws IllegalArgumentException{
        final Optional<User> maybeUser = userRepo.findOneByEmail(email);
        if (maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
        else return maybeUser.get();
    }

    public void update(UserDto userDto) throws IllegalArgumentException {
        String name = userDto.getName();
        String surname = userDto.getSurname();
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        String phone = userDto.getPhone();
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        long id = userRepo.findByEmail(currentUserEmail).getId();
        final Optional<User> maybeUser = userRepo.findById(id);
        if (maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
        User user = maybeUser.get();
        if (name != null && !name.isBlank()) user.setName(name);
        if (surname != null && !surname.isBlank()) user.setSurname(surname);
        if (email != null && !email.isBlank()) user.setEmail(email);
        if (password != null && !password.isBlank()) user.setPassword(passwordEncoder.encode(password));
        if (phone != null && !phone.isBlank()) user.setPhone(phone);
        try {
            userRepo.save(user);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Email already used");
        }
    }

    public void blockById(long id) {
        Optional<User> maybeUser = userRepo.findById(id);
        if (maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
        User user = maybeUser.get();
        user.setBan(BanEnum.YES);
        userRepo.save(user);
    }

    public void unblockById(long id) {
        Optional<User> maybeUser = userRepo.findById(id);
        if (maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
        User user = maybeUser.get();
        user.setBan(BanEnum.NO);
        userRepo.save(user);
    }

    public void delete(){
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        long id = userRepo.findByEmail(currentUserEmail).getId();
        userRepo.deleteById(id);
    }
}
