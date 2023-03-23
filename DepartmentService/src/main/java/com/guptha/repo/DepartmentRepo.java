package com.guptha.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guptha.entities.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer>{

}
