package com.estatemarket.realestate.repo;

import com.estatemarket.realestate.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
