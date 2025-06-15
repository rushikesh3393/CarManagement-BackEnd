package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryModel 
{
	private String cname;
	private String vnumber;
	private String servicetype;
	private int servicecost;
	private String sparename;
	private int spareqty;
	private int spareamt;
	private int TotalAmount;
	

}
