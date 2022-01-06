package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Verify;

import javax.transaction.Transactional;

@Transactional
public interface  VerifyTokenRepo extends JpaRepository<Verify, Integer> {
    Verify findByUserId (long userId);
    int deleteVerifyByUserId (long userId);
}