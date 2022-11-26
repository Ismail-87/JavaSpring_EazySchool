package com.example.eazyschool.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.eazyschool.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer>{
	
	List<Contact> findByStatus(String status);
	Page<Contact> findByStatus(String status, Pageable pageable);
}
