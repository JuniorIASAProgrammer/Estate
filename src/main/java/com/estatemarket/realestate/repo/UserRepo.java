package com.estatemarket.realestate.repo;

import com.estatemarket.realestate.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);

    User findOwnerById(long ownerId);

    User findRealtorById(long realtorId);
}
