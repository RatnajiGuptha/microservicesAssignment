package com.guptha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/addDept")
	public Department addDept(Department dept) {
		return deptRepo.save(dept);
	}
	
	@GetMapping("/deptById/{id}")
	public Department getDept(@PathVariable int id) {
		return deptRepo.findByDeptId(id);
	}
}
