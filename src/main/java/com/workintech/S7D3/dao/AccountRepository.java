package com.workintech.S7D3.dao;

import com.workintech.S7D3.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
}
