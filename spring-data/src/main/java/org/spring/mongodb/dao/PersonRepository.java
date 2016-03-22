package org.spring.mongodb.dao;

import org.spring.mongodb.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, String> {

  // additional custom finder methods go here

    Person findById(String id);
}