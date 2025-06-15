package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.GarageServicesModel;
import com.example.demo.Repository.GarageServicesRepository;

@Service("gservice")
public class GarageService 
{
	@Autowired
	GarageServicesRepository gsRepo;
	
	public boolean isAddNewService(GarageServicesModel gsmodel)
	{
		return gsRepo.isAddNewService(gsmodel);
	}
	
	 public List<GarageServicesModel> getAllServices()
	 {
		 return gsRepo.getAllServices();
	 }

	 public GarageServicesModel getServiceById(int svid)
	 {
		 return gsRepo.getServiceById(svid);
	 }
	 
	 public boolean isDeleteServiceById(int svid)
	 {
		 return gsRepo.isDeleteServiceById(svid);
	 }
	 
	 public boolean isUpdateService(GarageServicesModel gmodel)
	 {
		 return gsRepo.isUpdateService(gmodel);
	 }
	 
	 public List<GarageServicesModel> getServiceByPattern(String patterns)
	 {
		 return gsRepo.getServiceByPattern(patterns);
	 }
	 
	 public int getServiceCostBySvname(String svname)
	 {
		 return gsRepo.getServiceCostBySvname(svname);
	 }
}
