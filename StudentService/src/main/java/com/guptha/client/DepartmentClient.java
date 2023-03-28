package com.guptha.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.guptha.entities.Department;

@FeignClient(name = "department-service", url = "http://localhost:8100")
public interface DepartmentClient {

	@GetMapping("/getDeptById/{id}")
	public Department getDepartment(@PathVariable int id);

	@GetMapping("/getAll")
	public List<Department> getAllDepartments();

}
