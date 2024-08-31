package com.personal.project.accounts.repository;

import com.personal.project.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional // Ensures the delete operation is part of a transaction, so it can be rolled back if something goes wrong
    @Modifying // Indicates that this method performs a modifying operation (DELETE)
    void deleteByCustomerId(Long customerId);
}
