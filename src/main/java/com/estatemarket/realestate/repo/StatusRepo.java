package com.estatemarket.realestate.repo;

import com.estatemarket.realestate.repo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status, Long> {
    Status getByName(String bronze);
}
