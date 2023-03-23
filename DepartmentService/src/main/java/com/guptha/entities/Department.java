package com.guptha.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Department {
	
	@Id
	private int deptId;
	private String deptName;
	private String deptHead;
	private String deptMailId;
	private String deptContactNumber;
}
