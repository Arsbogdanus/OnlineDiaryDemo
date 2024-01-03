package com.deploy.war.demoapp.repository;

import com.deploy.war.demoapp.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
