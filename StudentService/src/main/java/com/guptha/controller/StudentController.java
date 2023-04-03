package com.guptha.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.guptha.client.DepartmentClient;
import com.guptha.entities.Department;
import com.guptha.entities.Student;
import com.guptha.repo.StudentsRepo;

@RestController
public class StudentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	StudentsRepo repo;

	@Autowired
	DepartmentClient dept;

	@GetMapping("/getAllStudents")
	public List<Student> getAll() {
		LOGGER.info("Retrieving all students from the database.");
		List<Student> students = repo.findAll();
		for (Student student : students) {
			Department department = dept.getDepartment(student.getDeptId());
			student.setDepartment(department);
		}
		LOGGER.info("Returning all students.");
		return students;
	}

	@GetMapping("/getAllStudentsByDept/{id}")
	public List<Student> getByDept(@PathVariable int id) {
		LOGGER.info("Retrieving all students by department id {} from the database.", id);
		List<Student> students = repo.findByDeptId(id);
		for (Student student : students) {
			Department department = dept.getDepartment(student.getDeptId());
			student.setDepartment(department);
		}
		LOGGER.info("Returning all students by department id {}.", id);
		return students;
	}

	@GetMapping("/getAllDepartments")
	public List<Department> getAllDepartments() {
		LOGGER.info("Retrieving all departments from the department service.");
		return dept.getAllDepartments();
	}

	@GetMapping("/getStudentsById/{id}")
	public Student getStudent(@PathVariable("id") int id) {
		LOGGER.info("Retrieving student by id {} from the database.", id);
		Student student = repo.findById(id).orElse(null);
		Department department = dept.getDepartment(student.getDeptId());
		student.setDepartment(department);
		LOGGER.info("Returning student with id {}.", id);
		return student;
	}

	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student s) {
		LOGGER.info("Adding new student to the database.");
		return repo.save(s);
	}

	@DeleteMapping("/deleteStudentById/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable int id) {
		Student student = repo.findByStudentId(id);
		System.err.println(student);
		if (student == null) {
			LOGGER.warn("Student not found by id {}.", id);
			return ResponseEntity.badRequest().body("Student not found by id :" + id);
		}
		repo.deleteByStudentId(id);
		LOGGER.info("Student with id {} deleted successfully.", id);
		return ResponseEntity.ok("Student deleted Successfully");
	}

}
