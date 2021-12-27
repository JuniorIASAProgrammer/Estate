package com.estatemarket.realestate.service;

import com.estatemarket.realestate.api.dto.UserDto;
import com.estatemarket.realestate.repo.RoleRepo;
import com.estatemarket.realestate.repo.StatusRepo;
import com.estatemarket.realestate.repo.UserRepo;
import com.estatemarket.realestate.repo.model.Role;
import com.estatemarket.realestate.repo.model.Status;
import com.estatemarket.realestate.repo.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthorisationService implements UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final StatusRepo statusRepo;


    public long singup(UserDto newUser) {
        String name = newUser.getName();
        String surname = newUser.getSurname();
        String email = newUser.getEmail();
        String password = newUser.getPassword();
        String repeatPassword = newUser.getRepeatPassword();
        String phone = newUser.getPhone();
        Status status = null;
        Role role = roleRepo.findByName(newUser.getRole());

        Pattern p = Pattern.compile("^\\d{10}$");
        Matcher m = p.matcher(phone);
        if (!m.matches()){ throw new IllegalArgumentException("Wrong phone number"); }

        if (Objects.equals(role.getName(), "REALTOR")) {
            status = statusRepo.getByName("BRONZE");
        }
        if (Objects.equals(password, repeatPassword)) {
            User user = new User(name, surname, email, passwordEncoder.encode(password), phone, role, status);
            User savedUser = userRepo.save(user);
            log.info("New user {} was saved", savedUser.getEmail());
            return savedUser.getId();
        }
        else throw new IllegalArgumentException("Incorrect password. Please, try again");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}

