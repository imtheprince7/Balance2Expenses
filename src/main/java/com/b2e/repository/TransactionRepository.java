package com.b2e.repository;

import com.b2e.entity.Transaction;
import com.b2e.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBywallet(Wallet wallet);
}
