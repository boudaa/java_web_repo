package com.ensah.core.services;

import java.util.List;

import com.ensah.core.bo.Person;

public interface IPersonService {
	public void addPerson(Person pPerson);
	public List<Person> getAllPersons();
	public Person getPersonById(Long id);

}
