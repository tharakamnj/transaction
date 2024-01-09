package com.nmm.transaction.repository;

import com.nmm.transaction.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    @Query(value = "select u.userId from user u",nativeQuery = true)
    Set<Integer> findAllUserIds();

    @Query(value = "select u from user u where u.userId = ?1",nativeQuery = true)
    Account findAccountByUser(int userId);
}
