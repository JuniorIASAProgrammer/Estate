package com.estatemarket.realestate.service;

import com.estatemarket.realestate.api.dto.UserDto;
import com.estatemarket.realestate.repo.RoleRepo;
import com.estatemarket.realestate.repo.UserRepo;
import com.estatemarket.realestate.repo.model.Role;
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
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthorisationService implements UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

//    public long login(UserDto someUser) {
//        String email = someUser.getEmail();
//        String password = someUser.getPassword();
//        final Optional<User> maybeUser = userRepo.findOneByEmail(email);
//        if (maybeUser.isEmpty()) throw new IllegalArgumentException("User not found");
//        else {
//            if (Objects.equals(maybeUser.get().getPassword(), password)) {
//                log.info("Successfully login");
//                return maybeUser.get().getId();
//            }
//            else throw new IllegalArgumentException("Incorrect password");
//        }
//    }

    public long singup(UserDto newUser) {
        String name = newUser.getName();
        String surname = newUser.getSurname();
        String email = newUser.getEmail();
        String password = newUser.getPassword();
        String repeatPassword = newUser.getPassword();
        String phone = newUser.getPhone();
        Role role = roleRepo.findByName(newUser.getRole());
        if (Objects.equals(password, repeatPassword)) {
            User user = new User(name, surname, email, password, phone, role);
            user.setPassword(passwordEncoder.encode((user.getPassword())));
            User savedUser = userRepo.save(user);
            log.info("New user {} was saved", savedUser.getEmail());
            return savedUser.getId();
        }
        else throw new IllegalArgumentException("Incorrect password. Please, try again");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findOneByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User with login not found"));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}

