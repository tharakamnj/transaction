package com.nmm.transaction.repository;

import com.nmm.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    //Transaction findTopByAccountAndOrderByDateDesc(Account account);

    @Query(value = "select * from transaction where accountId = ?1",nativeQuery = true)
    List<Transaction> findByAccountId(int accountId);
}
