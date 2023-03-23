package com.guptha.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guptha.entities.Student;

public interface StudentsRepo extends JpaRepository<Student, Integer>{

}
