package com.banking.banking_api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.banking.banking_api.entity.Account;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account , Long> {


}
