package com.guptha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.guptha.client.DepartmentClient;
import com.guptha.entities.Department;
import com.guptha.entities.Student;
import com.guptha.repo.StudentsRepo;

@RestController
public class StudentController {

	@Autowired
	StudentsRepo repo;

	@Autowired
	DepartmentClient dept;

	@GetMapping("/getAllStudents")
	public List<Student> getAll() {
		List<Student> students = repo.findAll();
		for (Student student : students) {
			Department department = dept.getDepartment(student.getDeptId());
			student.setDepartment(department);
		}
		return students;
	}

	@GetMapping("/getAllStudentsByDept/{id}")
	public List<Student> getByDept(@PathVariable int id) {
		List<Student> students = repo.findByDeptId(id);
		for (Student student : students) {
			Department department = dept.getDepartment(student.getDeptId());
			student.setDepartment(department);
		}
		return students;
	}

	@GetMapping("getAllDepartments")
	public List<Department> getAllDepartments() {
		return dept.getAllDepartments();
	}

	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable("id") int id) {
		Student student = repo.findById(id).orElse(null);
		Department department = dept.getDepartment(student.getDeptId());
		student.setDepartment(department);
		return student;
	}

}
