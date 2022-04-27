package com.ensah.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.core.bo.UserAccount;

public interface IUserAccountDao extends JpaRepository<UserAccount, Long> {
    UserAccount getUserByUsername(String username);

}
