package com.example.eazyschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eazyschool.model.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer>{

}
