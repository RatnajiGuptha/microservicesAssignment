package com.guptha.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	
	@Id
	@JsonIgnore
	private int deptId;
	private String deptName;
	private String deptHead;
	private String deptMailId;
	private String deptContactNumber;

}
