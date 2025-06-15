package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SparePartModel 
{
	private int spid;
	private String spname;
	private int spprice;
	private int spqty;

}
