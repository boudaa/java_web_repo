package com.ensah.core.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Person;
import com.ensah.core.dao.IPersonDao;

@Service
@Transactional
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private IPersonDao personDao;

	public void addPerson(Person pPerson) {
		personDao.save(pPerson);
	}

	public List<Person> getAllPersons() {
		return personDao.findAll();
	}



	public Person getPersonById(Long id) {
		return personDao.getById(id);

	}



}
