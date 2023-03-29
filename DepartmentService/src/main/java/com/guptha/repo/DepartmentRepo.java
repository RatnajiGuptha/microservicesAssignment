package com.guptha.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guptha.entities.Department;

import jakarta.transaction.Transactional;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

	Department findByDeptId(int id);

	Department findByDeptName(String deptName);

	@Transactional
	void deleteByDeptId(int id);

}
