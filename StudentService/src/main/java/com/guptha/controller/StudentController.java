package com.guptha.controller;

import java.util.List;

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

	@GetMapping("/getStudentsById/{id}")
	public Student getStudent(@PathVariable("id") int id) {
		Student student = repo.findById(id).orElse(null);
		Department department = dept.getDepartment(student.getDeptId());
		student.setDepartment(department);
		return student;
	}

	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student s) {
		return repo.save(s);
	}

	@DeleteMapping("/deleteStudentById/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable int id){
		Student student = repo.findByStudentId(id);
		if (student == null){
			return ResponseEntity.badRequest().body("Student not found by id :" + id );
		}
		repo.deleteByStudentId(id);
		return ResponseEntity.ok("Student deleted Successfully");
	}

}
