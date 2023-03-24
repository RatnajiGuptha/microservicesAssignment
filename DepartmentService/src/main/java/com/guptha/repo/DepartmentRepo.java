package com.guptha.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guptha.entities.dept.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer>{

	Department findByDeptId(int id);

}
