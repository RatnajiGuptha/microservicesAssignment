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

import com.guptha.entities.Department;
import com.guptha.repo.DepartmentRepo;

@RestController
public class DepartmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	DepartmentRepo deptRepo;

	@GetMapping("/getAll")
	public List<Department> getAllDepartments() {
		LOGGER.info("Getting all departments");
		return deptRepo.findAll();
	}

	@GetMapping("/getDeptById/{id}")
	public Department getDepartment(@PathVariable int id) {
		LOGGER.info("Getting department with id {}", id);
		return deptRepo.findByDeptId(id);
	}

	@PostMapping("/addDept")
	public ResponseEntity<?> addDept(@RequestBody Department dept) {
		Department findByDeptName = deptRepo.findByDeptName(dept.getDeptName());
		if (findByDeptName != null) {
			return ResponseEntity.badRequest().body("Department alredy exists");
		}
		deptRepo.save(dept);
		LOGGER.info("Adding department {}", dept);
		return ResponseEntity.ok("Department added");
	}

	@DeleteMapping("/deleteDept/{id}")
	public ResponseEntity<?> deleteDept(@PathVariable int id) {
		Department byDeptId = deptRepo.findByDeptId(id);
		if (byDeptId == null) {
			LOGGER.error("Department with id {} not found", id);
			return ResponseEntity.badRequest().body("Department not found");
		}
		deptRepo.deleteByDeptId(id);
		LOGGER.info("Deleting department with id {}", id);
		return ResponseEntity.ok("Department deleted Successfully");
	}

}
