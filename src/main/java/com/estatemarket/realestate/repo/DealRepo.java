package com.estatemarket.realestate.repo;

import com.estatemarket.realestate.repo.model.Deal;
import com.estatemarket.realestate.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepo extends JpaRepository<Deal, Long> {
    List<Deal> findAllByRealtor(User realtor);
}
