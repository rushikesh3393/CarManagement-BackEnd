package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Model.HistoryModel;
import com.example.demo.Repository.HistoryRepository;

@Service("hservice")
public class HistoryService 
{
	@Autowired
	HistoryRepository hrepo;
	
	public List<HistoryModel> getAllHistory(HistoryModel hmodel)
	{
		return hrepo.getAllHistory();
	}

}
