package com.example.demo.Controller;

import java.util.List;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.GarageServicesModel;
import com.example.demo.Service.GarageService;


@RestController
@CrossOrigin("http://localhost:5173/")
public class GarageServicesController 
{

	@Autowired
	GarageService gservice;
	
	@PostMapping("/addGarageService")
	public String addServices(@RequestBody GarageServicesModel gsmodel)
	{
		boolean b=gservice.isAddNewService(gsmodel);
		if(b)
		{
		   return "New Service Added Successfully...!";
		}
		else
		{
			return "New Service NOT Added Successfully...!";
		}
	}
	
	
	@GetMapping("/getAllServicesList")
	public List<GarageServicesModel> getAllServices()
	{
		return gservice.getAllServices();
	}
	
	
	@GetMapping("/searchService/{svid}")
	public GarageServicesModel searchService(@PathVariable("svid") Integer svid)
	{
		return gservice.getServiceById(svid);
	}
	
	
	@DeleteMapping("/deleteService/{svid}")
	public String deleteService(@PathVariable("svid") Integer svid)
	{
		boolean b=gservice.isDeleteServiceById(svid);
		
		if(b)
		{
			return "Service Deleted Successfully..!";
		}
		else
		{
			return "Service NOT Deleted Successfully..!";
		}
	}
	
	
	@PutMapping("/updateService")
	public String updateService(@RequestBody GarageServicesModel dmodel)
	{
		boolean b=gservice.isUpdateService(dmodel);
		if(b)
		{
			return "Service Updated Successfully...!";
		}
		else
		{
			return "Service NOT  Updated Successfully...!";
		}
	}
	
	 @GetMapping("/searchServicePattern/{patterns}")
	 public List<GarageServicesModel> getServiceByPattern( @PathVariable("patterns") String patterns)
	 {
		 List<GarageServicesModel> gsList=gservice.getServiceByPattern(patterns);
		 
		 if(gsList.size()>0)
		 {
			 return gsList;
		 }
		 else
		 {
			 return null;
		 }
	 }
	
	
	 @GetMapping("/serviceCostByName/{svname}")
	 public int getServiceCostBySvname(@PathVariable("svname") String svname)
	 {
		 int scost=gservice.getServiceCostBySvname(svname);
		 System.out.println(scost);
		 return scost;
	 }
	
	

}
