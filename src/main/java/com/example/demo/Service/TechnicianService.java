package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.TechnicianModel;
import com.example.demo.Repository.TechnicianRepository;

@Service("techService")
public class TechnicianService 
{
	@Autowired
	TechnicianRepository techRepo;
	
	public boolean isAddNewTechnician(TechnicianModel tmodel)
	{
		return techRepo.isAddNewTechnician(tmodel);
	}
	
	public List<TechnicianModel> getAllTechnicians()
	{
		return techRepo.getAllTechnicians();
	}
	
	public TechnicianModel getTechnicianById(int tid)
	{
		return techRepo.getTechnicianById(tid);
	}
	
	public boolean isDeleteTechnician(int tid)
	{
		return techRepo.isDeleteTechnician(tid);
	}
	
	public boolean isUpdateTechnician(TechnicianModel tmodel)
	{
		return techRepo.isUpdateTechnician(tmodel);
	}
	
	public List<TechnicianModel> getAllCustomPattern(String pattern)
	{
		return techRepo.getAllCustomPattern(pattern);
	}

}
