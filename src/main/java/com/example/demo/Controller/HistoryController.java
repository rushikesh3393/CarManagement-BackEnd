package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.HistoryModel;
import com.example.demo.Service.HistoryService;

@RestController
@CrossOrigin("http://localhost:5173/")
public class HistoryController 
{
	@Autowired
	HistoryService hservice;
	
	List<HistoryModel> ulist;
	
	
	@GetMapping("/history")
	public List<HistoryModel> getAllHistory(HistoryModel hmodel)
	{
		return ulist=hservice.getAllHistory(hmodel);
	}

}
