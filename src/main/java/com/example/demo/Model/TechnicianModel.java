package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TechnicianModel 
{
	private int tid;
	private String tname;
	private String temail;
	private String tcontact;
	private int salary;

}
