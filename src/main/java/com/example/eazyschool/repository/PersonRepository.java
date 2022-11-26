package com.example.eazyschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eazyschool.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	Person readByEmail(String email);

}
