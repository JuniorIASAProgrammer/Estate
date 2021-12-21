package com.estatemarket.realestate.repo;

import com.estatemarket.realestate.repo.model.Estate;
import com.estatemarket.realestate.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstateRepo extends JpaRepository<Estate, Long> {
    List<Estate> findAllByOwner(User owner);
}
