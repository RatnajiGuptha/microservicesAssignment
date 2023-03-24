package com.guptha.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guptha.entities.Student;

public interface StudentsRepo extends JpaRepository<Student, Integer>{

	List<Student> findByDeptId(int id);

	Student findByStudentId(int id);

}
