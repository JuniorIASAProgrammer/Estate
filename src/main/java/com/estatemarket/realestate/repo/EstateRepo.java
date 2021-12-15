package com.estatemarket.realestate.repo;

import com.estatemarket.realestate.repo.model.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateRepo extends JpaRepository<Estate, Long> {
}
