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

import com.guptha.entities.dept.Department;
import com.guptha.repo.DepartmentRepo;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentRepo deptRepo;

	@GetMapping("/getAll")
	public List<Department> getAllDepartments() {
		return deptRepo.findAll();
	}

	@GetMapping("/getDeptById/{id}")
	public Department getDepartment(@PathVariable int id) {
		return deptRepo.findByDeptId(id);
	}

	@PostMapping("/addDept")
	public ResponseEntity<?> addDept(@RequestBody Department dept) {
		Department findByDeptName = deptRepo.findByDeptName(dept.getDeptName());
		if (findByDeptName != null) {
			return ResponseEntity.badRequest().body("Department alredy exists");
		}
		deptRepo.save(dept);
		return ResponseEntity.ok("Department added");
	}

	@DeleteMapping("/deleteDept/{id}")
	public ResponseEntity<?> deleteDept(@PathVariable int id) {
		Department byDeptId = deptRepo.findByDeptId(id);
		if (byDeptId == null) {
			return ResponseEntity.badRequest().body("Department not found");
		}
		deptRepo.deleteByDeptId(id);
		return ResponseEntity.ok("Department deleted Successfully");
	}

}
