package com.ensah.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.core.bo.Person;

public interface IPersonDao  extends JpaRepository<Person, Long> {
}
