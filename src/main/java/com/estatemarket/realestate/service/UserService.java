package com.estatemarket.realestate.service;

import com.estatemarket.realestate.repo.model.User;
import com.estatemarket.realestate.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class UserService {

    private final UserRepo userRepo;

    public List<User> fetchAll(){
        return userRepo.findAll();
    }

    public User fetchById(long id) throws IllegalArgumentException{
        final Optional<User> maybeUser = userRepo.findById(id);
        if (maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
        else return maybeUser.get();
    }

    public long create(String name, String surname, String email, String password, String phone){
        final User user = new User(name, surname, email, password, phone);
        final User savedUser = userRepo.save(user);
        return savedUser.getId();
    }

    public void update(long id, String name, String surname, String email, String password, String phone) throws IllegalArgumentException {
        final Optional<User> maybeUser = userRepo.findById(id);
        if (maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");

        final User user = maybeUser.get();
        if (name != null && !name.isBlank()) user.setName(name);
        if (surname != null && !surname.isBlank()) user.setSurname(surname);
        if (email != null && !email.isBlank()) user.setEmail(email);
        if (password != null && !password.isBlank()) user.setPassword(password);
        if (phone != null && !phone.isBlank()) user.setPhone(phone);
        userRepo.save(user);
    }

    public void delete(long id){
        userRepo.deleteById(id);
    }
}
