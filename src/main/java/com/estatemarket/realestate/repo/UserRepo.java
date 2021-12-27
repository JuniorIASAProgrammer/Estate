package com.estatemarket.realestate.repo;

import com.estatemarket.realestate.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Optional<User> findOneById(long id);

    Optional<User> findOneByEmail(String email);
}
