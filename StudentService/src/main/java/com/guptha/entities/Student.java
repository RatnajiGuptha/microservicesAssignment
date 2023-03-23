package com.guptha.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	private int studentId;
	private String studentName;
	private String studentContactNumber;
	private String studentEmail;
	private int deptId;
}
