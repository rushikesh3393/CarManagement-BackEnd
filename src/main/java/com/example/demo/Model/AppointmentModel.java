package com.example.demo.Model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentModel 
{
	private int apid;
	private String cname;
	private Date adate;
	private String atime;
	private String vnumber;
	private int uid;

}

